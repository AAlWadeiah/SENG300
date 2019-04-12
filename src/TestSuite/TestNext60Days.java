package TestSuite;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Objects.next60days;

public class TestNext60Days {
	private next60days dateUtil;
	
	@BeforeEach
	void setUp() {
		dateUtil = new next60days();
	}
	
	@Test
	void testParseDate() {
		assertArrayEquals(new int[] {4, 13, 2019}, dateUtil.parseDate("4/13/2019"));
	}
	
	@Test
	void testIsTimeWithinWorkday() {
		try {
			assertEquals(true, dateUtil.isTimeWithinWorkday("9:00"));
			assertEquals(true, dateUtil.isTimeWithinWorkday("4:30"));
			assertEquals(false, dateUtil.isTimeWithinWorkday("1:20"));
			assertEquals(false, dateUtil.isTimeWithinWorkday("8:00"));
			assertEquals(false, dateUtil.isTimeWithinWorkday("5:00"));
		} catch (Exception e) {}
	}
	
	@Test
	void testToTimeslot() {
		assertEquals(1, dateUtil.timeToTimeslot("9:00"));
		assertEquals(16, dateUtil.timeToTimeslot("4:30"));
		assertEquals(-1, dateUtil.timeToTimeslot("5:30"));
		assertEquals(-1, dateUtil.timeToTimeslot("7:30"));
	}
	
	@Test 
	void testTimeslotToTime() {
		assertEquals("9:00", dateUtil.timeslotToTime(1));
		assertEquals("4:30", dateUtil.timeslotToTime(16));
		assertEquals("N/A", dateUtil.timeslotToTime(18));
		assertEquals("N/A", dateUtil.timeslotToTime(-3));
	}
}
