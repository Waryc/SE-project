package todolist.fuzz.generators;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class TaskNameGenerator extends Generator<String> {
    public TaskNameGenerator() {
        super(String.class);
    }

    @Override
    public String generate(SourceOfRandomness random, GenerationStatus status) {
        switch(random.nextInt(5)) {
            case 0: return null;  // 测试空值
            case 1: return "";    // 测试空字符串
            case 2: return " ";   // 测试空白字符
            case 3: return random.nextChar('a', 'z') + String.valueOf(random.nextChar('A', 'Z'));  // 正常情况
            default: return "!@#$%^&*()";  // 特殊字符
        }
    }
}