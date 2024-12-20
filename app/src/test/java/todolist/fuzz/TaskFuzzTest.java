package todolist.fuzz;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Assume;
import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.From;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import todolist.fuzz.generators.TaskDescriptionGenerator;
import todolist.fuzz.generators.TaskNameGenerator;
import todolist.task.Attribute;
import todolist.task.Category;
import todolist.task.Tag;
import todolist.task.Task;

@RunWith(JQF.class)
public class TaskFuzzTest {

    @Fuzz
    public void testTaskCreation(
            @From(TaskNameGenerator.class) String name,
            @From(TaskDescriptionGenerator.class) String description) {
        try {
            // 添加假设条件
            Assume.assumeTrue(name != null);

            Task task = new Task(name);
            task.setDescription(description);

            // 增加更多断言检查
            assertNotNull("Task should not be null", task);
            assertEquals("Task name should match", name, task.getName());
            assertEquals("Task description should match", description, task.getDescription());
            assertFalse("New task should not be completed", task.isCompleted());

            // 测试状态转换
            task.setCompleted(true);
            assertTrue("Task should be completed after setting", task.isCompleted());

            // 测试重置
            task.setCompleted(false);
            assertFalse("Task should not be completed after reset", task.isCompleted());

            // 测试属性修改
            String newName = "New " + name;
            task.setName(newName);
            assertEquals("Task name should be updated", newName, task.getName());

        } catch (IllegalArgumentException e) {
            // 验证异常信息
            assertNotNull("Exception message should not be null", e.getMessage());
            assertTrue("Exception message should not be empty", !e.getMessage().isEmpty());
        }
    }

    @Fuzz
    public void testTaskWithAttributes(
            String name,
            @From(AttributeGenerator.class) Attribute attribute,
            @From(CategoryGenerator.class) Category category) {
        try {
            Task task = new Task();
            task.setName(name);
            task.setAttribute(attribute);
            task.setCategory(category);

            // 验证属性设置
            assertEquals(name, task.getName());
            assertEquals(attribute, task.getAttribute());
            assertEquals(category, task.getCategory());
        } catch (IllegalArgumentException e) {
            // 预期的异常
        }
    }

    @Fuzz
    public void testTaskWithTags(String name, List<String> tagNames) {
        try {
            Task task = new Task(name);
            List<Tag> tags = new ArrayList<>();

            // 将字符串列表转换为Tag对象列表
            for (String tagName : tagNames) {
                if (tagName != null) {
                    tags.add(new Tag(tagName));
                }
            }

            task.setTags(tags);

            // 验证标签
            List<Tag> resultTags = task.getTags();
            assertNotNull(resultTags);
            assertEquals(tags.size(), resultTags.size());

            // 验证任务完成状态的切换
            assertFalse(task.isCompleted());
            task.setCompleted(true);
            assertTrue(task.isCompleted());
        } catch (IllegalArgumentException e) {
            // 预期的异常
        }
    }

    @Fuzz
    public void testTaskStateTransitions(
            String name,
            String description,
            boolean completed) {
        try {
            Task task = new Task(1, name, description,
                    Attribute.IMPORTANT, Category.WORK, new ArrayList<>());

            task.setCompleted(completed);
            assertEquals(completed, task.isCompleted());

            // 测试 printInfo 方法不会崩溃
            task.printInfo();
        } catch (IllegalArgumentException e) {
            // 预期的异常
        }
    }

    @Fuzz
    public void testTaskAttributes(
            @From(TaskNameGenerator.class) String name,
            @From(TaskDescriptionGenerator.class) String description) {
        try {
            Task task = new Task(name);

            // 测试标签
            task.setTags(new ArrayList<>());
            assertTrue(task.getTags().isEmpty());

            // 测试类别
            task.setCategory(Category.WORK);
            assertTrue(task.getCategory() == Category.WORK);

            // 测试属性
            task.setAttribute(new Attribute(Attribute.Importance.IMPORTANT, 10));
            assertTrue(task.getAttribute().getImportance() == Attribute.Importance.IMPORTANT);
            assertTrue(task.getAttribute().getDifficulty() == 10);

        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
        }
    }
}
