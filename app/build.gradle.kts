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

    jacoco  // 添加 JaCoCo 插件
}

// 在 repositories 之前声明配置
configurations {
    create("jqf")
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    testImplementation("edu.berkeley.cs.jqf:jqf-fuzz:1.7")
    testImplementation("edu.berkeley.cs.jqf:jqf-instrument:1.7")
    testImplementation("com.pholser:junit-quickcheck-core:1.0")
    testImplementation("com.pholser:junit-quickcheck-generators:1.0")
    
    // 添加到新的 jqf 配置
    "jqf"("edu.berkeley.cs.jqf:jqf-fuzz:1.7")
    
    // 添加运行时依赖
    testRuntimeOnly("edu.berkeley.cs.jqf:jqf-fuzz:1.7")

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
    dependsOn("classes")
    
    description = "Run JBMC analysis on compiled classes"
    group = "verification"
    
    // 使用 layout.buildDirectory 替代 buildDir
    val classesDir = layout.buildDirectory.dir("classes/java/main").get().asFile
    val outputDir = layout.buildDirectory.dir("reports/jbmc").get().asFile
    
    workingDir = classesDir
    
    commandLine(
        "jbmc",
        "--classpath", classesDir,
        "--function", "todolist.Todolist.main",
        "todolist.Todolist",
        "--show-goto-functions",
        "--show-properties",
        "--xml-ui",
        "--unwind", "10",
        "--outfile", "$outputDir/jbmc-analysis.xml"
    )
    
    // 忽略 JBMC 的非零退出码
    isIgnoreExitValue = true
    
    doFirst {
        // 创建输出目录
        mkdir(outputDir)
        println("Analysis output will be saved to: $outputDir/jbmc-analysis.xml")
    }
}

// 添加一个简单的帮助任务
tasks.register<Exec>("jbmcHelp") {
    description = "Show JBMC help information"
    group = "verification"
    
    commandLine("jbmc", "--help")
    isIgnoreExitValue = true
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)  // 测试后自动生成报告
}

tasks.jacocoTestReport {
    // 明确指定 test.exec 文件路径
    executionData.setFrom(fileTree(layout.buildDirectory).include("/jacoco/*.exec"))
    
    // 指定源代码目录
    sourceDirectories.setFrom(files("src/main/java"))
    classDirectories.setFrom(files(layout.buildDirectory.dir("classes/java/main")))
    
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco"))
    }
}

// 修改 JQF 任务配置
tasks.register<JavaExec>("jqf-fuzz") {
    description = "运行 JQF 模糊测试"
    group = "verification"
    
    classpath = sourceSets["test"].runtimeClasspath + configurations["jqf"]
    mainClass.set("edu.berkeley.cs.jqf.fuzz.ei.ZestCLI")
    
    val jqfInstrumentJar = configurations.testRuntimeClasspath.get()
        .find { it.name.contains("jqf-instrument") }
        ?.absolutePath ?: error("Cannot find jqf-instrument jar")
    
    jvmArgs = mutableListOf(
        "-Xbootclasspath/a:$jqfInstrumentJar",
        "-javaagent:$jqfInstrumentJar",
        "-Djqf.directives.active=true",
        "-Djqf.ei.MAX_INPUT_SIZE=100000",
        "-Djqf.ei.QUIET=false"
    )
    
    doFirst {
        val className = project.property("class").toString()
            .replace("class ", "")
            .replace("value class ", "")
            .replace("org.gradle.api.internal.project.DefaultProject_Decorated", "todolist.fuzz.TaskFuzzTest")
            .trim()
        val methodName = project.property("method").toString().trim()
        
        args = listOf(
            "todolist.fuzz",
            className,
            methodName,
            "-o", "fuzz-results"
        )
        
        mkdir("fuzz-results")
    }
    
    dependsOn("testClasses")
}

// 添加一个任务来检查 JQF jar 文件
tasks.register("checkJqfJars") {
    doLast {
        configurations.testRuntimeClasspath.get().forEach { file ->
            if (file.name.contains("jqf")) {
                println("Found JQF jar: ${file.absolutePath}")
            }
        }
    }
}

// 添加一个任务来打印类路径
tasks.register("printClasspath") {
    doLast {
        println("Test Runtime Classpath:")
        sourceSets["test"].runtimeClasspath.forEach { println(it) }
        println("\nTest Compile Classpath:")
        sourceSets["test"].compileClasspath.forEach { println(it) }
    }
}

// 添加一个任务来检查 JQF jar 的内容
abstract class ListJqfClassesTask : DefaultTask() {
    @TaskAction
    fun listClasses() {
        project.configurations.getByName("testRuntimeClasspath").forEach { file ->
            if (file.name.contains("jqf-fuzz")) {
                println("\nListing classes in: ${file.name}")
                project.zipTree(file).matching {
                    include("**/*.class")
                }.forEach { classFile ->
                    val className = classFile.path
                        .replace('\\', '.')
                        .replace('/', '.')
                        .removeSuffix(".class")
                    println(className)
                }
            }
        }
    }
}

tasks.register<ListJqfClassesTask>("listJqfClasses")

