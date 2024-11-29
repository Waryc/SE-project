package todolist.notification;

import todolist.task.Task;
import util.Timestamp;

public class Notification {

    public enum NotificationMethod {
        EMAIL, SMS, APP
    }

    private Timestamp time; 
    private NotificationMethod method;
    private Task task;

    public Notification(Timestamp time, NotificationMethod method) {
        this.time = time;
        this.method = method;
    }

    public Notification(Timestamp time, NotificationMethod method, Task task) {
        this.time = time;
        this.method = method;
        this.task = task;
    }

    // getter和setter方法
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public NotificationMethod getMethod() {
        return method;
    }

    public void setMethod(NotificationMethod method) {
        this.method = method;
    }

    public void notificate() {
        // 发送通知
    }
}
