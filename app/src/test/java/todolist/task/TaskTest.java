package todolist.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    private Task task;
    private List<Tag> tags;

    @BeforeEach
    void setUp() {
        tags = new ArrayList<>();
        tags.add(new Tag("重要"));
        tags.add(new Tag("紧急"));
    }

    @Test
    void testDefaultConstructor() {
        task = new Task();
        assertEquals(0, task.getId());
        assertEquals("", task.getName());
        assertEquals("", task.getDescription());
        assertNull(task.getAttribute());
        assertNull(task.getCategory());
        assertNull(task.getTags());
        assertFalse(task.isCompleted());
    }

    @Test
    void testNameConstructor() {
        task = new Task("测试任务");
        assertEquals("测试任务", task.getName());
    }

    @Test
    void testFullConstructor() {
        task = new Task(1, "测试任务", "这是一个测试任务",
                Attribute.IMPORTANT, Category.WORK, tags);
        assertEquals(1, task.getId());
        assertEquals("测试任务", task.getName());
        assertEquals("这是一个测试任务", task.getDescription());
        assertEquals(Attribute.IMPORTANT, task.getAttribute());
        assertEquals(Category.WORK, task.getCategory());
        assertEquals(tags, task.getTags());
        assertFalse(task.isCompleted());
    }

    @Test
    void testSettersAndGetters() {
        task = new Task();

        task.setId(2);
        assertEquals(2, task.getId());

        task.setName("新任务名称");
        assertEquals("新任务名称", task.getName());

        task.setDescription("新描述");
        assertEquals("新描述", task.getDescription());

        task.setAttribute(Attribute.URGENT);
        assertEquals(Attribute.URGENT, task.getAttribute());

        task.setCategory(Category.PERSONAL);
        assertEquals(Category.PERSONAL, task.getCategory());

        task.setTags(tags);
        assertEquals(tags, task.getTags());

        task.setCompleted(true);
        assertTrue(task.isCompleted());
    }

    @Test
    void testPrintInfo() {
        // 由于printInfo方法输出到控制台，我们可以通过验证不会抛出异常来测试
        task = new Task(1, "测试任务", "描述",
                Attribute.IMPORTANT, Category.WORK, tags);
        assertDoesNotThrow(() -> task.printInfo());
    }
}
