package todolist.integration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import todolist.notification.Notification;
import todolist.notification.Notification.NotificationMethod;
import todolist.notification.ReminderService;
import todolist.task.Category;
import todolist.task.Task;
import util.Timestamp;

public class NotificationIntegrationTest {

    private ReminderService reminderService;
    private Task task;
    private Timestamp timestamp;

    @BeforeEach
    void setUp() {
        reminderService = new ReminderService();
        task = new Task("测试任务");
        timestamp = new Timestamp();
    }

    @Test
    void testCompleteNotificationFlow() {
        // 1. 创建并设置任务
        task.setDescription("这是一个测试任务");
        task.setCategory(Category.WORK);

        // 2. 测试提醒服务
        reminderService.scheduleReminder(timestamp, NotificationMethod.APP, task);
        assertEquals(1, reminderService.getNotifications().size());

        // 3. 验证通知内容
        List<Notification> notifications = reminderService.getNotifications();
        Notification notification = notifications.get(0);
        assertEquals(NotificationMethod.EMAIL, notification.getMethod());
        assertEquals(task, notification.getTask());
        assertEquals(timestamp, notification.getTime());

        // 4. 测试通知发送
        assertDoesNotThrow(() -> notification.notificate());
    }

    @Test
    void testMultipleNotifications() {
        // 测试多个通知方法
        reminderService.scheduleReminder(timestamp, NotificationMethod.APP, task);
        reminderService.scheduleReminder(timestamp, NotificationMethod.EMAIL, task);
        reminderService.scheduleReminder(timestamp, NotificationMethod.SMS, task);

        List<Notification> notifications = reminderService.getNotifications();
        assertEquals(3, notifications.size());

        // 验证每个通知方法
        assertTrue(notifications.stream()
                .map(Notification::getMethod)
                .distinct()
                .count() == 3);
    }

    @Test
    void testNotificationWithTaskStateChanges() {
        reminderService.scheduleReminder(timestamp, NotificationMethod.EMAIL, task);

        // 修改任务状态
        task.setCompleted(true);

        // 验证通知中的任务状态
        Notification notification = reminderService.getNotifications().get(0);
        assertTrue(notification.getTask().isCompleted());

        // 测试任务更新
        task.setName("更新后的任务名称");
        assertEquals("更新后的任务名称", notification.getTask().getName());
    }

    @Test
    void testInvalidNotificationScenarios() {
        // 测试空时间戳
        assertThrows(IllegalArgumentException.class, () -> {
            reminderService.scheduleReminder(null, NotificationMethod.EMAIL, task);
        });

        // 测试空通知方法
        assertThrows(IllegalArgumentException.class, () -> {
            reminderService.scheduleReminder(timestamp, null, task);
        });

        // 测试无任务的通知
        reminderService.scheduleReminder(timestamp, NotificationMethod.EMAIL);
        Notification notification = reminderService.getNotifications().get(0);
        assertNull(notification.getTask());
        assertDoesNotThrow(() -> notification.notificate());
    }
}
