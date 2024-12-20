package util;

public class Timestamp {

    private Date date;
    private Time time;

    public Timestamp() {
        this.date = new Date();
        this.time = new Time();
    }

    public Timestamp(long relativeMillis) {
        this.date = new Date();
        this.time = new Time(relativeMillis);
    }

    public Timestamp(Date date) {
        this.date = date;
        this.time = new Time();
    }

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
