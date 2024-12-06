/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.11.1/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application

    // Apply the SonarQube plugin to add support for SonarQube.
    id("org.sonarqube") version "6.0.1.5171"

}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used by the application.
    implementation(libs.guava)

    implementation("org.slf4j:slf4j-api:2.0.9")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    // Define the main class for the application.
    mainClass = "todolist.Todolist"
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

tasks.register<Exec>("jbmcAnalysis") {
    dependsOn("classes")  // 确保先编译类文件
    
    description = "Run JBMC analysis on compiled classes"
    group = "verification"
    
    // 获取编译后的类文件目录
    val classesDir = "${buildDir}/classes/java/main"
    
    // 要分析的目标类列表
    val targetClasses = listOf(
        "todolist.task.Timeline",
        "todolist.util.Time",
        "todolist.action.Listener",
        "todolist.action.UserAction",
        "todolist.notification.Notification"
    )
    
    workingDir = file(classesDir)
    
    // 构建命令行参数
    val classFile = targetClasses.first().replace('.', '/') + ".class"
    commandLine(
        "docker",
        "run",
        "--rm",
        "-v",
        "${workingDir}:/workdir",
        "-w",
        "/workdir",
        "local/jbmc",  // 使用本地构建的镜像
        "jbmc",
        classFile,
        "--trace",
        "--pointer-check",
        "--bounds-check",
        "--div-by-zero-check"
    )
    
    // 忽略 JBMC 的非零退出码
    isIgnoreExitValue = true
    
    // 添加日志输出
    logging.captureStandardOutput(LogLevel.INFO)
    logging.captureStandardError(LogLevel.ERROR)
    
    doFirst {
        println("Working directory: ${workingDir}")
        println("Analyzing class: ${classFile}")
        println("Command: ${commandLine.joinToString(" ")}")
    }
}
