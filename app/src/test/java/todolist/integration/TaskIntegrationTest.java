package todolist.integration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import todolist.task.Attribute;
import todolist.task.Category;
import todolist.task.Tag;
import todolist.task.Task;

public class TaskIntegrationTest {

    private Task task;
    private List<Tag> tags;
    private Attribute attribute;
    private Category category;

    @BeforeEach
    void setUp() {
        tags = new ArrayList<>();
        tags.add(new Tag("工作"));
        tags.add(new Tag("重要"));
        attribute = new Attribute(Attribute.Importance.IMPORTANT, 8);
        category = Category.WORK;
    }

    @Test
    void testFullTaskLifecycle() {
        // 1. 创建任务
        task = new Task("集成测试任务");
        assertNotNull(task);

        // 2. 设置基本属性
        task.setDescription("这是一个完整的集成测试");
        task.setAttribute(attribute);
        task.setCategory(category);
        task.setTags(tags);

        // 3. 验证所有属性
        assertEquals("集成测试任务", task.getName());
        assertEquals("这是一个完整的集成测试", task.getDescription());
        assertEquals(attribute, task.getAttribute());
        assertEquals(category, task.getCategory());
        assertEquals(2, task.getTags().size());

        // 4. 测试状态变化
        assertFalse(task.isCompleted());
        task.setCompleted(true);
        assertTrue(task.isCompleted());

        // 5. 测试属性修改
        Attribute newAttribute = new Attribute(Attribute.Importance.URGENT, 10);
        task.setAttribute(newAttribute);
        assertEquals(Attribute.Importance.URGENT, task.getAttribute().getImportance());
        assertEquals(10, task.getAttribute().getDifficulty());

        // 6. 测试标签操作
        List<Tag> newTags = new ArrayList<>(tags);
        newTags.add(new Tag("紧急"));
        task.setTags(newTags);
        assertEquals(3, task.getTags().size());
    }

    @Test
    void testTaskWithInvalidData() {
        task = new Task("测试任务");

        // 测试空标签列表
        task.setTags(new ArrayList<>());
        assertTrue(task.getTags().isEmpty());

        // 测试空描述
        task.setDescription("");
        assertEquals("", task.getDescription());

        // 测试空名称（应该抛出异常）
        assertThrows(IllegalArgumentException.class, () -> {
            task.setName("");
        });

        // 测试null属性
        task.setAttribute(null);
        assertNull(task.getAttribute());
    }

    @Test
    void testTaskStateTransitions() {
        task = new Task(1, "状态测试", "测试任务状态转换",
                attribute, category, tags);

        // 测试完成状态转换
        assertFalse(task.isCompleted());
        task.setCompleted(true);
        assertTrue(task.isCompleted());
        task.setCompleted(false);
        assertFalse(task.isCompleted());

        // 测试属性变更
        task.setCategory(Category.PERSONAL);
        assertEquals(Category.PERSONAL, task.getCategory());

        // 测试信息打印
        assertDoesNotThrow(() -> task.printInfo());
    }
}
