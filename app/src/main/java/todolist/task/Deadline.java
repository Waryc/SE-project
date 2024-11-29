package todolist.task;
import util.Timestamp;
import util.Time;
import util.Date;

public class Deadline extends Timestamp {
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
}
