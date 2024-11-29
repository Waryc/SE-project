package todolist.action;

import java.util.List;
import java.util.ArrayList;

import todolist.task.Task;

public class UserAction {

    private List<Task> tasks = new ArrayList<Task>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public void updateTask(Task task) {
        for (Task t : tasks) {
            if (t.getId() == task.getId()) {
                t.setName(task.getName());
                t.setDescription(task.getDescription());
                t.setCategory(task.getCategory());
                t.setTags(task.getTags());
                return;
            }
        }
    }

    public Task queryTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
}