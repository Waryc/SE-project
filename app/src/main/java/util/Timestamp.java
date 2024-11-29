package util;

public class Timestamp {
    private Date date;
    private Time time;

    public Timestamp(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    // 构造函数、getter和setter方法
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}