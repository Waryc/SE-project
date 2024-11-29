package todolist.ui;

// 用户界面风格类
public class UIStyle {
    private String primaryColor; // 主题主色调
    private String secondaryColor; // 主题副色调
    private String fontFamily; // 字体族

    public UIStyle(String primaryColor, String secondaryColor, String fontFamily) {
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.fontFamily = fontFamily;
    }

    // 应用样式到用户界面的方法
    public void applyStyle() {
        // 这里可以添加代码来实际应用样式到UI组件
        // 例如，更新UI组件的颜色和字体
        System.out.println("应用样式 - 主色调: " + primaryColor + ", 副色调: " + secondaryColor + ", 字体族: " + fontFamily);
    }

    // getter和setter方法
    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }
}