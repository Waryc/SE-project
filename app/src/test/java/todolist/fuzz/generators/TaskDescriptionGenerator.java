package todolist.fuzz.generators;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class TaskDescriptionGenerator extends Generator<String> {

    public TaskDescriptionGenerator() {
        super(String.class);
    }

    @Override
    public String generate(SourceOfRandomness random, GenerationStatus status) {
        if (random.nextBoolean()) {
            // 生成正常的描述
            StringBuilder sb = new StringBuilder();
            int length = random.nextInt(1, 100);
            for (int i = 0; i < length; i++) {
                sb.append(random.nextChar('a', 'z'));
            }
            return sb.toString();
        } else {
            // 生成边界情况
            return random.nextBoolean() ? null : "";
        }
    }
}
