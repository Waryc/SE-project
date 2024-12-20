package todolist.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import util.Date;
import util.Time;
import util.Timestamp;

class DeadlineTest {

    @Test
    void testDefaultConstructor() {
        Deadline deadline = new Deadline();
        assertNotNull(deadline.getDate());
        assertNotNull(deadline.getTime());
    }

    @Test
    void testParameterizedConstructor() {
        Date date = new Date(2024, 3, 20);
        Time time = new Time(14, 30);
        Deadline deadline = new Deadline(date, time);

        assertEquals(date, deadline.getDate());
        assertEquals(time, deadline.getTime());
    }

    @Test
    void testSetAndGetDate() {
        Deadline deadline = new Deadline();
        Date newDate = new Date(2024, 3, 21);

        deadline.setDate(newDate);
        assertEquals(newDate, deadline.getDate());
    }

    @Test
    void testSetAndGetTime() {
        Deadline deadline = new Deadline();
        Time newTime = new Time(15, 45);

        deadline.setTime(newTime);
        assertEquals(newTime, deadline.getTime());
    }

    @Test
    void testToString() {
        Date date = new Date(2024, 3, 20);
        Time time = new Time(14, 30);
        Deadline deadline = new Deadline(date, time);

        String expected = date.toString() + " " + time.toString();
        assertEquals(expected, deadline.toString());
    }

    @Test
    void testIsTime() {
        Deadline deadline = new Deadline(new Date(2024, 3, 20), new Time(14, 30));

        // 测试早于当前时间的情况
        Timestamp laterTime = new Timestamp(new Date(2024, 3, 20), new Time(15, 30));
        assertTrue(deadline.isTime(laterTime));

        // 测试晚于当前时间的情况
        Timestamp earlierTime = new Timestamp(new Date(2024, 3, 20), new Time(13, 30));
        assertFalse(deadline.isTime(earlierTime));

        // 测试相同时间的情况
        Timestamp sameTime = new Timestamp(new Date(2024, 3, 20), new Time(14, 30));
        assertFalse(deadline.isTime(sameTime));
    }
}
