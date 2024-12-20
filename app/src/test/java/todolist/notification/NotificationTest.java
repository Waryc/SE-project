package todolist.notification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import todolist.notification.Notification.NotificationMethod;
import todolist.task.Task;
import util.Timestamp;

class NotificationTest {

    private Notification notification;
    private Timestamp time;
    private Task task;

    @BeforeEach
    void setUp() {
        time = new Timestamp(System.currentTimeMillis());
        task = new Task("Test Task");
    }

    @Test
    void testConstructorWithoutTask() {
        notification = new Notification(time, NotificationMethod.EMAIL);

        assertEquals(time, notification.getTime());
        assertEquals(NotificationMethod.EMAIL, notification.getMethod());
        assertNull(notification.getTask());
    }

    @Test
    void testConstructorWithTask() {
        notification = new Notification(time, NotificationMethod.SMS, task);

        assertEquals(time, notification.getTime());
        assertEquals(NotificationMethod.SMS, notification.getMethod());
        assertEquals(task, notification.getTask());
    }

    @Test
    void testSetAndGetTime() {
        notification = new Notification(time, NotificationMethod.EMAIL);
        Timestamp newTime = new Timestamp(System.currentTimeMillis() + 1000);

        notification.setTime(newTime);
        assertEquals(newTime, notification.getTime());
    }

    @Test
    void testSetAndGetMethod() {
        notification = new Notification(time, NotificationMethod.EMAIL);

        notification.setMethod(NotificationMethod.SMS);
        assertEquals(NotificationMethod.SMS, notification.getMethod());
    }

    @Test
    void testSetAndGetTask() {
        notification = new Notification(time, NotificationMethod.EMAIL);
        Task newTask = new Task("New Task");

        notification.setTask(newTask);
        assertEquals(newTask, notification.getTask());
    }

    @Test
    void testNotificateWithTask() {
        notification = new Notification(time, NotificationMethod.EMAIL, task);
        notification.notificate();
        // 由于 notificate 方法只是打印输出，我们只能验证它不会抛出异常
        // 实际项目中应该使用依赖注入和 mock 对象来测试输出
    }

    @Test
    void testNotificateWithoutTask() {
        notification = new Notification(time, NotificationMethod.EMAIL);
        notification.notificate();
        // 同上，验证无任务时的通知不会抛出异常
    }

    @Test
    void testAllNotificationMethods() {
        // 测试所有通知方法
        for (NotificationMethod method : NotificationMethod.values()) {
            notification = new Notification(time, method);
            assertEquals(method, notification.getMethod());
        }
    }

    @Test
    void testNullTime() {
        notification = new Notification(null, NotificationMethod.EMAIL);
        assertEquals(new Timestamp().getTime().toMillis(),
                notification.getTime().getTime().toMillis());
    }
}
