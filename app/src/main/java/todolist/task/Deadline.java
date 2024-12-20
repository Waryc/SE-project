package todolist.task;

import util.Date;
import util.Time;
import util.Timestamp;

public class Deadline extends Timestamp {

    public Deadline() {
        super(new Date(), new Time());
    }

    public Deadline(Date date, Time time) {
        super(date, time);
    }

    public Date getDate() {
        return super.getDate();
    }

    public void setDate(Date date) {
        super.setDate(date);
    }

    public Time getTime() {
        return super.getTime();
    }

    public void setTime(Time time) {
        super.setTime(time);
    }

    public String toString() {
        return getDate().toString() + " " + getTime().toString();
    }

    // 判断是否到时间
    public boolean isTime(Timestamp timestamp) {
        return this.getTime().toMillis() < timestamp.getTime().toMillis();
    }
}
