package todolist.task;

public class Category {

    private String name;

    public Category(String name) {
        this.name = name;
    }

    // getter和setter方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Category WORK = new Category("工作");
    public static Category STUDY = new Category("学习");
    public static Category LIFE = new Category("生活");
    public static Category PERSONAL = new Category("个人");
}
