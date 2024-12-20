package util;

public class Date {

    private int year;
    private int month;
    private int day;

    public Date() {
        this.year = 2024;
        this.month = 12;
        this.day = 20;
    }

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // 构造函数、getter和setter方法
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
