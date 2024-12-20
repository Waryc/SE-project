package todolist.fuzz;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import todolist.task.Attribute;

public class AttributeGenerator extends Generator<Attribute> {

    public AttributeGenerator() {
        super(Attribute.class);
    }

    @Override
    public Attribute generate(SourceOfRandomness random, GenerationStatus status) {
        Attribute.Importance[] importances = Attribute.Importance.values();
        int randomImportance = random.nextInt(importances.length);
        int randomDifficulty = random.nextInt(1, 11);
        return new Attribute(importances[randomImportance], randomDifficulty);
    }
}
