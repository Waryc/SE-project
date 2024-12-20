package todolist.fuzz;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import todolist.task.Category;

public class CategoryGenerator extends Generator<Category> {

    public CategoryGenerator() {
        super(Category.class);
    }

    @Override
    public Category generate(SourceOfRandomness random, GenerationStatus status) {
        Category[] categories = {Category.WORK, Category.STUDY, Category.LIFE, Category.PERSONAL};
        return categories[random.nextInt(categories.length)];
    }
}
