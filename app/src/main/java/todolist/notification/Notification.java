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

    public Notification(NotificationMethod method) {
        this(new Timestamp(), method);
    }

    public Notification(Timestamp time, NotificationMethod method) {
        this.time = (time != null) ? time : new Timestamp();
        this.method = method;
    }

    public Notification(Timestamp time, NotificationMethod method, Task task) {
        this.time = (time != null) ? time : new Timestamp();
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
        if (task != null) {
            System.out.println("通知：" + task.getName() + "，时间：" + time + "，方式：" + method);
        } else {
            System.out.println("通知：时间：" + time + "，方式：" + method);
        }
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
