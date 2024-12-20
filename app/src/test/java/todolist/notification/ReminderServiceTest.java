package todolist.notification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import todolist.notification.Notification.NotificationMethod;
import util.Timestamp;

class ReminderServiceTest {

    private ReminderService service;

    @BeforeEach
    void setUp() {
        service = new ReminderService();
    }

    @Test
    void shouldAddNewNotification() {
        // 正常情况测试
        Timestamp time = new Timestamp();
        service.scheduleReminder(time, NotificationMethod.EMAIL);

        assertEquals(1, service.getNotifications().size());
    }

    @Test
    void shouldRemoveExistingNotification() {
        // 删除功能测试
        Timestamp time = new Timestamp();
        service.scheduleReminder(time, NotificationMethod.EMAIL);

        service.cancelReminder(time, NotificationMethod.EMAIL);
        assertTrue(service.getNotifications().isEmpty());
    }

    @Test
    void shouldReturnCopyOfNotifications() {
        // 返回副本测试
        Timestamp time = new Timestamp();
        service.scheduleReminder(time, NotificationMethod.EMAIL);

        List<Notification> notifications = service.getNotifications();
        notifications.clear(); // 修改返回的列表

        assertEquals(1, service.getNotifications().size()); // 原列表应该不受影响
    }

    @Test
    void shouldHandleMultipleNotifications() {
        // 多个提醒测试
        service.scheduleReminder(new Timestamp(), NotificationMethod.EMAIL);
        service.scheduleReminder(new Timestamp(), NotificationMethod.SMS);

        assertEquals(2, service.getNotifications().size());
    }

    @Test
    void testIsValidReminder_StatementCoverage() {
        // 语句覆盖：确保每行代码都执行到
        Timestamp futureTime = new Timestamp(1000L);
        assertTrue(service.isValidReminder(futureTime, NotificationMethod.EMAIL));
    }

    @Test
    void testIsValidReminder_DecisionCoverage() {
        // 判定覆盖：确保每个判断都取到 true 和 false
        Timestamp futureTime = new Timestamp(1000L);
        Timestamp pastTime = new Timestamp(-1000L);

        // 测试时间判断分支
        assertTrue(service.isValidReminder(futureTime, NotificationMethod.EMAIL));
        assertFalse(service.isValidReminder(pastTime, NotificationMethod.EMAIL));

        // 测试空值判断分支
        assertFalse(service.isValidReminder(null, NotificationMethod.EMAIL));
        assertFalse(service.isValidReminder(futureTime, null));
    }

    @Test
    void testIsValidReminder_ConditionCoverage() {
        // 条件覆盖：确保复合条件中的每个子条件都取到 true 和 false
        Timestamp futureTime = new Timestamp(1000L);

        // 测试第一个复合条件 (time == null || method == null)
        assertTrue(service.isValidReminder(futureTime, NotificationMethod.EMAIL));  // false || false
        assertFalse(service.isValidReminder(null, NotificationMethod.EMAIL));      // true || false
        assertFalse(service.isValidReminder(futureTime, null));                    // false || true
        assertFalse(service.isValidReminder(null, null));                         // true || true
    }

    @Test
    void testIsValidReminder_MaxNotifications() {
        // 测试最大提醒数量限制
        Timestamp futureTime = new Timestamp(1000L);

        // 添加100个提醒
        for (int i = 0; i < 100; i++) {
            service.scheduleReminder(futureTime, NotificationMethod.EMAIL);
        }

        // 验证第101个提醒会被拒绝
        assertFalse(service.isValidReminder(futureTime, NotificationMethod.EMAIL));
    }

    @Test
    void testNotificationMethodHandling() {
        // 测试不同通知方法的处理
        Timestamp futureTime = new Timestamp(System.currentTimeMillis() + 1000);

        // 测试 EMAIL 通知
        service.scheduleReminder(futureTime, NotificationMethod.EMAIL);
        assertEquals(NotificationMethod.EMAIL,
                service.getNotifications().get(0).getMethod());

        // 测试 SMS 通知
        service.scheduleReminder(futureTime, NotificationMethod.SMS);
        assertEquals(NotificationMethod.SMS,
                service.getNotifications().get(1).getMethod());

        // 验证总数
        assertEquals(2, service.getNotifications().size());
    }

    @Test
    void testTimestampBoundaries() {
        // 测试时间戳边界情况
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Timestamp pastTime = new Timestamp(System.currentTimeMillis() - 1);

        // 当前时间应该是无效的
        assertFalse(service.isValidReminder(currentTime, NotificationMethod.EMAIL));

        // 过去时间应该是无效的
        assertFalse(service.isValidReminder(pastTime, NotificationMethod.EMAIL));
    }
}
