package todolist.task;

public class Attribute {

    private Importance importance;
    private int difficulty;

    public enum Importance {
        LOW,
        MEDIUM,
        IMPORTANT,
        URGENT
    }

    public Attribute(Importance importance, int difficulty) {
        this.importance = importance;
        this.difficulty = difficulty;
    }

    // getter和setter方法
    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public static Attribute URGENT = new Attribute(Importance.URGENT, 1);
    public static Attribute IMPORTANT = new Attribute(Importance.IMPORTANT, 2);
    public static Attribute MEDIUM = new Attribute(Importance.MEDIUM, 3);
    public static Attribute LOW = new Attribute(Importance.LOW, 4);
}
