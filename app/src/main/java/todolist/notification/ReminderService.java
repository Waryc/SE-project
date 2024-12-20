package todolist.notification;

import java.util.ArrayList;
import java.util.List;

import todolist.notification.Notification.NotificationMethod;
import todolist.task.Task;
import util.Timestamp;

public class ReminderService {

    private final List<Notification> notifications = new ArrayList<>();

    public void scheduleReminder(Timestamp time, NotificationMethod method) {
        Notification notification = new Notification(time, method);
        notifications.add(notification);
    }

    public void scheduleReminder(Timestamp time, NotificationMethod method, Task task) {
        Notification notification = new Notification(time, method, task);
        notifications.add(notification);
    }

    public void cancelReminder(Timestamp time, NotificationMethod method) {
        notifications.removeIf(notification
                -> notification.getTime().equals(time)
                && notification.getMethod().equals(method)
        );
    }

    public void cancelReminder(Notification notification) {
        notifications.remove(notification);
    }

    public List<Notification> getNotifications() {
        return new ArrayList<>(notifications); // 返回副本以保护原始数据
    }

    public boolean isValidReminder(Timestamp time, NotificationMethod method) {
        // 检查参数是否为空
        if (time == null || method == null) {
            return false;
        }

        // 检查时间是否在将来
        if (time.getTime().toMillis() <= System.currentTimeMillis()) {
            return false;
        }

        // 检查是否达到最大提醒数量
        if (notifications.size() >= 100) {
            return false;
        }

        return true;
    }
}
