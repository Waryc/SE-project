package todolist.task;

import java.util.List;

public class Timeline {

    private List<Task> tasks;

    public Timeline(List<Task> tasks) {
        this.tasks = tasks;
    }

    // getter和setter方法
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
