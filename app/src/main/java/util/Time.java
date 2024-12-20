package util;

public class Time {

    private int hour;
    private int minute;
    private long Millis;

    static final long MILLIS_PER_HOUR = 60 * 60 * 1000;
    static final long MILLIS_PER_MINUTE = 60 * 1000;
    static final long BaseMillis = 10 * 60 * 60 * 1000;

    public Time() {
        this.hour = 10;
        this.minute = 0;
        this.Millis = BaseMillis;
    }

    public Time(long relativeMillis) {
        this.Millis = BaseMillis + relativeMillis;
        this.hour = (int) (Millis / MILLIS_PER_HOUR);
        this.minute = (int) ((Millis / MILLIS_PER_MINUTE) % 60);
    }

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        this.Millis = hour * MILLIS_PER_HOUR + minute * MILLIS_PER_MINUTE;
    }

    // 构造函数、getter和setter方法
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public long toMillis() {
        return Millis;
    }
}
