package de.knurt.javaheinzelmann;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import de.knurt.heinzelmann.util.time.SimpleTimeFrame;
import de.knurt.heinzelmann.util.time.TimeFrame;
import de.knurt.heinzelmann.util.time.TimeFrameFactory;

public class TimeFrameFactoryTest {

	@Test
	public void getActualMonth() throws Exception {
		Calendar give = Calendar.getInstance();
		TimeFrame got = new TimeFrameFactory(give).getMonth();
		Calendar start = got.getCalendarStart();
		assertEquals(start.get(Calendar.MILLISECOND), 0);
		assertEquals(start.get(Calendar.SECOND), 0);
		assertEquals(start.get(Calendar.MINUTE), 0);
		assertEquals(start.get(Calendar.HOUR_OF_DAY), 0);
		assertEquals(start.get(Calendar.DAY_OF_MONTH), 1);
		assertEquals(start.get(Calendar.MONTH), give.get(Calendar.MONTH));
		assertEquals(start.get(Calendar.YEAR), give.get(Calendar.YEAR));

		start.add(Calendar.MONTH, 1);

		Calendar end = got.getCalendarEnd();

		assertEquals(end.get(Calendar.MILLISECOND), 0);
		assertEquals(end.get(Calendar.SECOND), 0);
		assertEquals(end.get(Calendar.MINUTE), 0);
		assertEquals(end.get(Calendar.HOUR_OF_DAY), 0);
		assertEquals(end.get(Calendar.DAY_OF_MONTH), 1);
		assertEquals(end.get(Calendar.MONTH), start.get(Calendar.MONTH));
		assertEquals(end.get(Calendar.YEAR), start.get(Calendar.YEAR));
	}

	@Test
	public void getDuration() throws Exception {
		Calendar give = Calendar.getInstance();
		int minutes = 5;
		TimeFrame got = new TimeFrameFactory(give).getDuration(Calendar.MINUTE, minutes);
		Calendar end = (Calendar) give.clone();
		end.add(Calendar.MINUTE, minutes);
		assertEquals(new SimpleTimeFrame(give, end).toString(), got.toString());
	}

	@Test
	public void getMonthWithFullWeeks() throws Exception {
		Calendar give = Calendar.getInstance();
		give.add(Calendar.MONTH, 3);
		TimeFrameFactory tff = new TimeFrameFactory(give);
		TimeFrame got = tff.getMonthWithFullWeeks();
		// assert "first day of week" and more or equal units as the pure month
		assertEquals(got.getCalendarStart().get(Calendar.DAY_OF_WEEK), give.getFirstDayOfWeek());
		assertEquals(got.getCalendarEnd().get(Calendar.DAY_OF_WEEK), give.getFirstDayOfWeek());
		assertTrue(got.getDuration() >= tff.getMonth().getDuration());
	}
	
	@Test
	public void getMonthWithFullWeeks_recognizeFirstDayOfWeek() throws Exception {
		Calendar give1 = Calendar.getInstance();
		Calendar give2 = Calendar.getInstance();
		give2.setFirstDayOfWeek(give1.getFirstDayOfWeek() + 3);
		
		TimeFrame got1 = new TimeFrameFactory(give1).getMonthWithFullWeeks();
		TimeFrame got2 = new TimeFrameFactory(give2).getMonthWithFullWeeks();
		
		assertTrue(got1.getCalendarStart().get(Calendar.DAY_OF_YEAR) + 3 == got2.getCalendarStart().get(Calendar.DAY_OF_YEAR) || got1.getCalendarStart().get(Calendar.DAY_OF_YEAR) -4 == got2.getCalendarStart().get(Calendar.DAY_OF_YEAR));
		assertTrue(got1.getCalendarEnd().get(Calendar.DAY_OF_YEAR) + 3 == got2.getCalendarEnd().get(Calendar.DAY_OF_YEAR) || got1.getCalendarEnd().get(Calendar.DAY_OF_YEAR) -4 == got2.getCalendarEnd().get(Calendar.DAY_OF_YEAR));
	}
}
