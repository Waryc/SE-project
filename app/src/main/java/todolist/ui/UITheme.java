package todolist.ui;

// 用户界面主题类
public class UITheme {
    private String name; // 主题名称
    private UIStyle style; // 关联的样式

    public UITheme(String name, UIStyle style) {
        this.name = name;
        this.style = style;
    }

    // 应用主题到用户界面的方法
    public void applyTheme() {
        // 这里可以添加代码来实际应用主题到UI组件
        // 这可能包括应用样式以及可能的其他主题特定的设置
        System.out.println("应用主题 - 名称: " + name + ", 样式: " + style);
        style.applyStyle(); // 调用样式的applyStyle方法来应用样式
    }

    // getter和setter方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UIStyle getStyle() {
        return style;
    }

    public void setStyle(UIStyle style) {
        this.style = style;
    }
}