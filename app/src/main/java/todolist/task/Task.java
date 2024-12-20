package todolist.task;

import java.util.List;

public class Task {

    private int id;
    private String name;
    private String description;
    private Attribute attribute;
    private Category category;
    private List<Tag> tags; // 一个任务可以有多个标签
    private boolean isCompleted = false; // 任务是否已完成

    public Task() {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.attribute = null;
        this.category = null;
        this.tags = null;
    }

    public Task(String name) {
        this.name = name;
    }

    public Task(int id, String name, String description, Attribute attribute, Category category, List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.attribute = attribute;
        this.category = category;
        this.tags = tags;
    }

    // 构造函数、getter和setter方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("任务名称不能为空");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void printInfo() {
        System.out.println("任务ID：" + id);
        System.out.println("任务名称：" + name);
        System.out.println("任务描述：" + description);
        System.out.println("任务属性：" + attribute);
        System.out.println("任务分类：" + category);
        System.out.println("任务标签：" + tags);
        System.out.println("是否已完成：" + isCompleted);
    }
}
