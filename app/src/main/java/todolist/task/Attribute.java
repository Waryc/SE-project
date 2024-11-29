package todolist.task;

public class Attribute {

    private int importance;
    private int difficulty;

    public Attribute(int importance, int difficulty) {
        this.importance = importance;
        this.difficulty = difficulty;
    }

    // getter和setter方法
    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
