package todolist.task;

public class Tag {

    private String name;
    private String color;
    private String style;

    public Tag(String name) {
        this(name, "", "");
    }

    public Tag(String name, String color, String style) {
        this.name = name;
        this.color = color;
        this.style = style;
    }

    // getter和setter方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
