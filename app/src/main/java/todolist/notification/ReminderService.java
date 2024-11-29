package todolist.notification;

import java.util.List;
import todolist.notification.Notification.NotificationMethod;
import util.Timestamp;
import java.util.ArrayList;

public class ReminderService {
    private List<Notification> notifications = new ArrayList<Notification>();

    public void scheduleReminder(Timestamp time, NotificationMethod method) {
        Notification notification = new Notification(time, method);
        notifications.add(notification);
    }

    public void cancelReminder(Notification notification) {
        notifications.remove(notification);
    }

    public List<Notification> getNotifications() {
        return new ArrayList<>(notifications); // 返回副本以保护原始数据
    }
}
