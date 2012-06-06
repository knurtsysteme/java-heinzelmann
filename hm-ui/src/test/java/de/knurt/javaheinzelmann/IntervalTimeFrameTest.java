package de.knurt.javaheinzelmann;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.knurt.heinzelmann.util.time.AbstractIntervalTimeFrame;
import de.knurt.heinzelmann.util.time.AbstractTimeFrame;
import de.knurt.heinzelmann.util.time.IntervalTimeFrame;
import de.knurt.heinzelmann.util.time.SimpleIntervalTimeFrame;
import de.knurt.heinzelmann.util.time.SimpleTimeFrame;
import de.knurt.heinzelmann.util.time.TimeFrame;

/**
 * COMMENT
 * 
 * @author Daniel Oltmanns
 * @since 0.20090827
 * @version 0.20091104
 */
public class IntervalTimeFrameTest {

	public IntervalTimeFrameTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Test
	public void timeFrameMeetsDeviceAvailibility_outOfTheBox() {
		// Thu Jun 25 06:15:00 CEST 2009 -- Thu Jun 25 06:30:00 CEST 2009
		TimeFrame candidate = new SimpleTimeFrame(1245903300000l, 1245904200000l);
		// Thu Jun 25 00:00:00 CEST 2009 -- Fri Jun 26 00:00:00 CEST 2009
		TimeFrame datf = new SimpleTimeFrame(1245880800000l, 1245967200000l);
		IntervalTimeFrame da = new SimpleIntervalTimeFrame(datf);
		assertTrue(da.overlaps(candidate));
	}

	@Test(timeout = 5000)
	public void performace_overlaps() {
		Calendar cstart = new GregorianCalendar(2009, 3, 28, 5, 0);
		Calendar cend = new GregorianCalendar(2009, 3, 28, 5, 15);
		IntervalTimeFrame tuesday = new SimpleIntervalTimeFrame(cstart, cend);
		tuesday.setHourly();

		Calendar tstart = new GregorianCalendar(2059, 5, 25, 0, 0);
		Calendar tend = new GregorianCalendar(2059, 5, 28, 10, 0);
		long s = new Date().getTime();
		tuesday.overlaps(new SimpleTimeFrame(tstart, tend));
		long e = new Date().getTime();
		System.out.println("time performace_overlaps: " + (e - s));
		assertTrue(e - s < 1000);
	}

	@Test(timeout = 5000)
	public void performace_getSingleSimpleTimeFrames() {
		Calendar cstart = new GregorianCalendar(2009, 3, 28, 5, 0);
		Calendar cend = new GregorianCalendar(2009, 3, 28, 5, 15);
		AbstractIntervalTimeFrame tuesday = new SimpleIntervalTimeFrame(cstart, cend);
		tuesday.setHourly();

		long s = new Date().getTime();
		long e = new Date().getTime();
		System.out.println("time performace_getSingleSimpleTimeFrames: " + (e - s));
		assertTrue(e - s < 1000);
	}

	@Test
	public void getClone() {
		Calendar cstart = new GregorianCalendar(2009, 3, 28, 1, 0);
		Calendar cend = new GregorianCalendar(2009, 3, 28, 6, 0);
		IntervalTimeFrame tuesday = new SimpleIntervalTimeFrame(cstart, cend);
		IntervalTimeFrame tuesday2 = tuesday.clone();
		assertEquals(tuesday.getInterval(), tuesday2.getInterval());
	}

	@Test
	public void getSingleSimple_tick() {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		AbstractIntervalTimeFrame totest = new SimpleIntervalTimeFrame(start, end);
		totest.getBasePeriodOfTime().setStart(new GregorianCalendar(2009, 4, 12, 1, 0, 0).getTimeInMillis());
		totest.getBasePeriodOfTime().setEnd(new GregorianCalendar(2009, 4, 12, 5, 0, 0).getTimeInMillis());
		totest.setDaily();

		AbstractTimeFrame fromTo = new SimpleTimeFrame(new GregorianCalendar(2009, 4, 11, 0, 0, 0), new GregorianCalendar(2009, 4, 12, 0, 0, 0));

		assertEquals(1, totest.getSingleSimpleTimeFrames(fromTo).size());

		totest.getBasePeriodOfTime().setStart(new GregorianCalendar(2009, 4, 12, 0, 0, 0).getTimeInMillis());
		totest.getBasePeriodOfTime().setEnd(new GregorianCalendar(2009, 4, 12, 5, 0, 0).getTimeInMillis());
		assertEquals(1, totest.getSingleSimpleTimeFrames(fromTo).size());

		totest.getBasePeriodOfTime().setStart(new GregorianCalendar(2009, 4, 12, 0, 0, 0));
		totest.getBasePeriodOfTime().setEnd(new GregorianCalendar(2009, 4, 13, 0, 0, 0));
		assertEquals(1, totest.getSingleSimpleTimeFrames(fromTo).size());

		totest.getBasePeriodOfTime().setStart(new GregorianCalendar(2009, 4, 11, 0, 0, 0));
		totest.getBasePeriodOfTime().setEnd(new GregorianCalendar(2009, 4, 12, 0, 0, 0));
		assertEquals(1, totest.getSingleSimpleTimeFrames(fromTo).size());

		totest.getBasePeriodOfTime().setStart(new GregorianCalendar(2009, 4, 10, 0, 0, 0));
		totest.getBasePeriodOfTime().setEnd(new GregorianCalendar(2009, 4, 11, 0, 0, 0));
		assertEquals(1, totest.getSingleSimpleTimeFrames(fromTo).size());
	}

	@Test
	public void getSingleSimpleTimeFrames_easy() {
		Calendar cstart = new GregorianCalendar(2009, 3, 28, 1, 0);
		Calendar cend = new GregorianCalendar(2009, 3, 28, 6, 0);
		AbstractIntervalTimeFrame tuesday = new SimpleIntervalTimeFrame(cstart, cend);
		Calendar tstart = new GregorianCalendar(2009, 3, 28, 1, 0);
		Calendar tend = new GregorianCalendar(2009, 3, 28, 6, 0);
		List<TimeFrame> singles = tuesday.getSingleSimpleTimeFrames(new SimpleTimeFrame(tstart, tend));
		assertEquals(1, singles.size());
		assertEquals("Tue Apr 28 01:00:00 CEST 2009 -- Tue Apr 28 06:00:00 CEST 2009", singles.get(0).toString());
	}

	@Test
	@Ignore("TODO unresoved summertime problem")
	public void getSingleSimpleTimeFrames_summertime() {
		Calendar cstart = new GregorianCalendar(2009, 3, 28, 1, 0);
		Calendar cend = new GregorianCalendar(2009, 3, 28, 2, 0);
		AbstractIntervalTimeFrame tuesday = new SimpleIntervalTimeFrame(cstart, cend);
		tuesday.setDaily();

		Calendar tstart = new GregorianCalendar(2010, 5, 25, 0, 0);
		Calendar tend = new GregorianCalendar(2010, 5, 29, 5, 0);
		List<TimeFrame> singles = tuesday.getSingleSimpleTimeFrames(new SimpleTimeFrame(tstart, tend));

		assertEquals(5, singles.size());
		assertEquals("Fri Jun 25 01:00:00 CEST 2010 -- Fri Jun 25 02:00:00 CEST 2010", singles.get(0).toString());
		assertEquals("Sat Jun 26 01:00:00 CEST 2010 -- Sat Jun 26 02:00:00 CEST 2010", singles.get(1).toString());
		assertEquals("Sun Jun 27 01:00:00 CEST 2010 -- Sun Jun 27 02:00:00 CEST 2010", singles.get(2).toString());
		// TODO summertime: Sun Mar 28 02:00:00 CET 2010 does not exist!
		assertEquals("Mon Jun 28 01:00:00 CEST 2010 -- Mon Jun 28 02:00:00 CEST 2010", singles.get(3).toString());
	}

	@Test
	public void getSingleSimpleTimeFrames_timeFrameInTime() {
		Calendar cstart = new GregorianCalendar(2009, 3, 28, 5, 0);
		Calendar cend = new GregorianCalendar(2009, 3, 28, 6, 0);
		AbstractIntervalTimeFrame tuesday = new SimpleIntervalTimeFrame(cstart, cend);
		tuesday.setDaily();

		Calendar tstart = new GregorianCalendar(2010, 5, 25, 0, 0);
		Calendar tend = new GregorianCalendar(2010, 5, 28, 10, 0);
		List<TimeFrame> singles = tuesday.getSingleSimpleTimeFrames(new SimpleTimeFrame(tstart, tend));
		assertEquals(4, singles.size());
		assertEquals("Fri Jun 25 05:00:00 CEST 2010 -- Fri Jun 25 06:00:00 CEST 2010", singles.get(0).toString());
		assertEquals("Sat Jun 26 05:00:00 CEST 2010 -- Sat Jun 26 06:00:00 CEST 2010", singles.get(1).toString());
		assertEquals("Sun Jun 27 05:00:00 CEST 2010 -- Sun Jun 27 06:00:00 CEST 2010", singles.get(2).toString());
		assertEquals("Mon Jun 28 05:00:00 CEST 2010 -- Mon Jun 28 06:00:00 CEST 2010", singles.get(3).toString());
	}

	@Test
	public void getSingleSimpleTimeFrames_cuttingStart() {
		Calendar cstart = new GregorianCalendar(2009, 3, 28, 5, 0);
		Calendar cend = new GregorianCalendar(2009, 3, 28, 6, 0);
		AbstractIntervalTimeFrame tuesday = new SimpleIntervalTimeFrame(cstart, cend);
		tuesday.setDaily();

		Calendar tstart = new GregorianCalendar(2010, 5, 25, 5, 20);
		Calendar tend = new GregorianCalendar(2010, 5, 28, 10, 0);
		List<TimeFrame> singles = tuesday.getSingleSimpleTimeFrames(new SimpleTimeFrame(tstart, tend));
		assertEquals(4, singles.size());
		assertEquals("Fri Jun 25 05:20:00 CEST 2010 -- Fri Jun 25 06:00:00 CEST 2010", singles.get(0).toString());
		assertEquals("Sat Jun 26 05:00:00 CEST 2010 -- Sat Jun 26 06:00:00 CEST 2010", singles.get(1).toString());
		assertEquals("Sun Jun 27 05:00:00 CEST 2010 -- Sun Jun 27 06:00:00 CEST 2010", singles.get(2).toString());
		assertEquals("Mon Jun 28 05:00:00 CEST 2010 -- Mon Jun 28 06:00:00 CEST 2010", singles.get(3).toString());
	}

	@Test
	public void getSingleSimpleTimeFrames_cuttingEnd() {
		Calendar cstart = new GregorianCalendar(2009, 3, 28, 5, 0);
		Calendar cend = new GregorianCalendar(2009, 3, 28, 6, 0);
		AbstractIntervalTimeFrame tuesday = new SimpleIntervalTimeFrame(cstart, cend);
		tuesday.setDaily();

		Calendar tstart = new GregorianCalendar(2010, 5, 25, 4, 0);
		Calendar tend = new GregorianCalendar(2010, 5, 28, 5, 20);
		List<TimeFrame> singles = tuesday.getSingleSimpleTimeFrames(new SimpleTimeFrame(tstart, tend));
		assertEquals(4, singles.size());
		assertEquals("Fri Jun 25 05:00:00 CEST 2010 -- Fri Jun 25 06:00:00 CEST 2010", singles.get(0).toString());
		assertEquals("Sat Jun 26 05:00:00 CEST 2010 -- Sat Jun 26 06:00:00 CEST 2010", singles.get(1).toString());
		assertEquals("Sun Jun 27 05:00:00 CEST 2010 -- Sun Jun 27 06:00:00 CEST 2010", singles.get(2).toString());
		assertEquals("Mon Jun 28 05:00:00 CEST 2010 -- Mon Jun 28 05:20:00 CEST 2010", singles.get(3).toString());
	}

	@Test
	public void getSingleSimpleTimeFrames_cuttingStartAndEnd() {
		Calendar cstart = new GregorianCalendar(2009, 3, 28, 5, 0);
		Calendar cend = new GregorianCalendar(2009, 3, 28, 6, 0);
		AbstractIntervalTimeFrame tuesday = new SimpleIntervalTimeFrame(cstart, cend);
		tuesday.setDaily();

		Calendar tstart = new GregorianCalendar(2010, 5, 25, 5, 20);
		Calendar tend = new GregorianCalendar(2010, 5, 28, 5, 23);
		List<TimeFrame> singles = tuesday.getSingleSimpleTimeFrames(new SimpleTimeFrame(tstart, tend));
		assertEquals(4, singles.size());
		assertEquals("Fri Jun 25 05:20:00 CEST 2010 -- Fri Jun 25 06:00:00 CEST 2010", singles.get(0).toString());
		assertEquals("Sat Jun 26 05:00:00 CEST 2010 -- Sat Jun 26 06:00:00 CEST 2010", singles.get(1).toString());
		assertEquals("Sun Jun 27 05:00:00 CEST 2010 -- Sun Jun 27 06:00:00 CEST 2010", singles.get(2).toString());
		assertEquals("Mon Jun 28 05:00:00 CEST 2010 -- Mon Jun 28 05:23:00 CEST 2010", singles.get(3).toString());
	}

	private AbstractIntervalTimeFrame getStandardTimeFrameFeb2009() {
		Calendar c1 = new GregorianCalendar(2009, 1, 1);
		Calendar c2 = new GregorianCalendar(2009, 2, 1);
		return new SimpleIntervalTimeFrame(c1, c2);
	}

	@Test
	public void construction() {
		Calendar cstart = new GregorianCalendar(2009, 3, 28, 1, 0);
		Calendar cend = new GregorianCalendar(2009, 3, 28, 6, 0);
		TimeFrame base = new SimpleTimeFrame(cstart, cend);
		assertEquals(SimpleTimeFrame.class, base.getClass());
		IntervalTimeFrame citf = new SimpleIntervalTimeFrame(base);
		assertEquals(SimpleIntervalTimeFrame.class, citf.getClass());
		assertEquals(SimpleTimeFrame.class, citf.getBasePeriodOfTime().getClass());
	}

	@Test
	public void timeFrameOverlapsIsIn_EACH_WEEK() {
		Calendar cstart = new GregorianCalendar(2009, 3, 28, 1, 0);
		Calendar cend = new GregorianCalendar(2009, 3, 28, 6, 0);
		AbstractIntervalTimeFrame tuesday = new SimpleIntervalTimeFrame(cstart, cend);
		tuesday.setWeekly();

		TimeFrame thatTf = (TimeFrame) tuesday.getBasePeriodOfTime().clone();
		assertTrue(tuesday.overlaps(thatTf));

		thatTf.add(Calendar.WEEK_OF_YEAR, 30);
		assertTrue(tuesday.overlaps(thatTf));

		thatTf.add(Calendar.DAY_OF_YEAR, 1);
		assertFalse(tuesday.overlaps(thatTf));

		thatTf.add(Calendar.DAY_OF_YEAR, 6);
		assertTrue(tuesday.overlaps(thatTf));

		thatTf.add(Calendar.HOUR_OF_DAY, 10);
		assertFalse(tuesday.overlaps(thatTf));

	}

	@Test
	public void timeFrameOverlapsIsIn_EACH_DAY() {
		Calendar thisC1 = new GregorianCalendar(2008, 0, 1, 0, 0);
		Calendar thisC2 = new GregorianCalendar(2008, 0, 1, 12, 0);
		AbstractIntervalTimeFrame thisTf = new SimpleIntervalTimeFrame(thisC1, thisC2);
		thisTf.setDaily();

		Calendar thatC1 = new GregorianCalendar(2009, 7, 1, 1, 0);
		Calendar thatC2 = new GregorianCalendar(2009, 7, 1, 2, 0);
		AbstractIntervalTimeFrame thatTf = new SimpleIntervalTimeFrame(thatC1, thatC2);

		assertTrue(thisTf.overlaps(thatTf.getBasePeriodOfTime()));
		assertFalse(thatTf.overlaps(thisTf.getBasePeriodOfTime()));
	}

	@Test
	public void timeFrameOverlapsIsIn_EACH_DAY2() {
		Calendar thisC1 = new GregorianCalendar(2009, 4, 23, 20, 0);
		Calendar thisC2 = new GregorianCalendar(2009, 4, 24, 0, 0);
		AbstractIntervalTimeFrame thisTf = new SimpleIntervalTimeFrame(thisC1, thisC2);
		thisTf.setDaily();

		Calendar thatC1 = new GregorianCalendar(2009, 4, 28, 0, 0);
		Calendar thatC2 = new GregorianCalendar(2009, 4, 29, 0, 0);
		AbstractTimeFrame thatTf = new SimpleTimeFrame(thatC1, thatC2);
		assertTrue(thisTf.overlaps(thatTf));

		thatC1 = new GregorianCalendar(2009, 4, 21, 10, 0);
		thatC2 = new GregorianCalendar(2009, 4, 22, 0, 0);
		thatTf = new SimpleTimeFrame(thatC1, thatC2);
		assertTrue(thisTf.overlaps(thatTf));

		thatC1 = new GregorianCalendar(2009, 4, 21, 18, 0);
		thatC2 = new GregorianCalendar(2009, 4, 21, 19, 0);
		thatTf = new SimpleTimeFrame(thatC1, thatC2);
		assertFalse(thisTf.overlaps(thatTf));

		thatC1 = new GregorianCalendar(2009, 4, 21, 18, 0);
		thatC2 = new GregorianCalendar(2009, 4, 21, 21, 0);
		thatTf = new SimpleTimeFrame(thatC1, thatC2);
		assertTrue(thisTf.overlaps(thatTf));

		thisC1 = new GregorianCalendar(2009, 4, 23, 20, 0);
		thisC2 = new GregorianCalendar(2009, 4, 23, 22, 0);
		thisTf = new SimpleIntervalTimeFrame(thisC1, thisC2);
		thisTf.setDaily();
		assertTrue(thisTf.overlaps(thatTf));

		thisC1 = new GregorianCalendar(2009, 4, 23, 21, 0);
		thisC2 = new GregorianCalendar(2009, 4, 23, 22, 0);
		thisTf = new SimpleIntervalTimeFrame(thisC1, thisC2);
		thisTf.setDaily();
		assertFalse(thisTf.overlaps(thatTf));

		assertTrue(thisTf.overlaps((TimeFrame) thisTf.getBasePeriodOfTime().clone()));

		thisC1 = new GregorianCalendar(2009, 4, 23, 20, 0);
		thisC2 = new GregorianCalendar(2009, 4, 24, 0, 0);
		thisTf = new SimpleIntervalTimeFrame(thisC1, thisC2);
		thisTf.setDaily();
		thatC1 = new GregorianCalendar(2009, 4, 22, 0, 0);
		thatC2 = new GregorianCalendar(2009, 4, 23, 0, 0);
		thatTf = new SimpleTimeFrame(thatC1, thatC2);
		assertTrue(thisTf.overlaps(thatTf));
		thatC1 = new GregorianCalendar(2009, 4, 23, 0, 0);
		thatC2 = new GregorianCalendar(2009, 4, 24, 0, 0);
		thatTf = new SimpleTimeFrame(thatC1, thatC2);
		assertTrue(thisTf.overlaps(thatTf));
		thatC1 = new GregorianCalendar(2009, 4, 24, 0, 0);
		thatC2 = new GregorianCalendar(2009, 4, 25, 0, 0);
		thatTf = new SimpleTimeFrame(thatC1, thatC2);
		assertTrue(thisTf.overlaps(thatTf));

		thisC1 = new GregorianCalendar(2009, 4, 27, 0, 0);
		thisC2 = new GregorianCalendar(2009, 4, 27, 5, 0);
		thisTf = new SimpleIntervalTimeFrame(thisC1, thisC2);
		thisTf.setDaily();
		thatC1 = new GregorianCalendar(2009, 4, 26, 0, 0);
		thatC2 = new GregorianCalendar(2009, 4, 26, 24, 0);
		thatTf = new SimpleTimeFrame(thatC1, thatC2);
		assertTrue(thisTf.overlaps(thatTf));
		thatC1 = new GregorianCalendar(2009, 4, 26, 0, 0);
		thatC2 = new GregorianCalendar(2009, 4, 27, 0, 0);
		thatTf = new SimpleTimeFrame(thatC1, thatC2);
		assertTrue(thisTf.overlaps(thatTf));
		thatC1 = new GregorianCalendar(2009, 4, 26, 24, 0);
		thatC2 = new GregorianCalendar(2009, 4, 27, 24, 0);
		thatTf = new SimpleTimeFrame(thatC1, thatC2);
		assertTrue(thisTf.overlaps(thatTf));
	}

	@Test
	public void timeFrameOverlapsIsIn_EACH_MONTH_sameYearAndMonth() {
		Calendar thisC1 = new GregorianCalendar(2008, 0, 2, 0, 0);
		Calendar thisC2 = new GregorianCalendar(2008, 0, 15, 0, 0);
		AbstractIntervalTimeFrame thisTf = new SimpleIntervalTimeFrame(thisC1, thisC2);
		thisTf.setMonthly();

		Calendar thatC1 = new GregorianCalendar(2008, 0, 13, 1, 0);
		Calendar thatC2 = new GregorianCalendar(2008, 0, 18, 2, 0);
		AbstractIntervalTimeFrame thatTf = new SimpleIntervalTimeFrame(thatC1, thatC2);

		assertTrue(thisTf.overlaps(thatTf.getBasePeriodOfTime()));
		assertTrue(thatTf.overlaps(thisTf.getBasePeriodOfTime()));

		// outside
		thatC1 = new GregorianCalendar(2008, 0, 15, 0, 0);
		thatC2 = new GregorianCalendar(2008, 0, 18, 2, 0);
		thatTf = new SimpleIntervalTimeFrame(thatC1, thatC2);

		assertFalse(thisTf.overlaps(thatTf.getBasePeriodOfTime()));
		assertFalse(thatTf.overlaps(thisTf.getBasePeriodOfTime()));

	}

	@Test
	public void timeFrameOverlapsIsIn_EACH_MONTH_sameYearOtherMonth() {
		Calendar thisC1 = new GregorianCalendar(2008, 0, 1, 0, 0);
		Calendar thisC2 = new GregorianCalendar(2008, 0, 15, 0, 0);
		AbstractIntervalTimeFrame thisTf = new SimpleIntervalTimeFrame(thisC1, thisC2);
		thisTf.setMonthly();

		Calendar thatC1 = new GregorianCalendar(2008, 1, 13, 1, 0);
		Calendar thatC2 = new GregorianCalendar(2008, 1, 18, 2, 0);
		AbstractIntervalTimeFrame thatTf = new SimpleIntervalTimeFrame(thatC1, thatC2);

		assertTrue(thisTf.overlaps(thatTf.getBasePeriodOfTime()));
		assertFalse(thatTf.overlaps(thisTf.getBasePeriodOfTime()));
	}

	@Test
	public void timeFrameOverlapsIsIn_EACH_MONTH_otherYearOtherMonth() {
		Calendar thisC1 = new GregorianCalendar(2008, 0, 1, 0, 0);
		Calendar thisC2 = new GregorianCalendar(2008, 0, 15, 0, 0);
		AbstractIntervalTimeFrame thisTf = new SimpleIntervalTimeFrame(thisC1, thisC2);
		thisTf.setMonthly();

		Calendar thatC1 = new GregorianCalendar(2009, 1, 13, 1, 0);
		Calendar thatC2 = new GregorianCalendar(2009, 1, 18, 2, 0);
		AbstractIntervalTimeFrame thatTf = new SimpleIntervalTimeFrame(thatC1, thatC2);

		assertTrue(thisTf.overlaps(thatTf.getBasePeriodOfTime()));
		assertFalse(thatTf.overlaps(thisTf.getBasePeriodOfTime()));

	}

	@Test
	public void timeFrameOverlapsIsIn_EACH_MONTH_yearAndMonthSteps() {
		Calendar thisC1 = new GregorianCalendar(2008, 11, 24, 0, 0);
		Calendar thisC2 = new GregorianCalendar(2009, 0, 5, 0, 0);
		AbstractIntervalTimeFrame thisTf = new SimpleIntervalTimeFrame(thisC1, thisC2);
		thisTf.setMonthly();

		Calendar thatC1 = new GregorianCalendar(2008, 3, 25, 1, 0);
		Calendar thatC2 = new GregorianCalendar(2008, 4, 2, 2, 0);
		IntervalTimeFrame thatTf = new SimpleIntervalTimeFrame(thatC1, thatC2);

		String before = thatTf.getBasePeriodOfTime().getCalendarStart().toString();
		assertTrue(thisTf.overlaps(thatTf.getBasePeriodOfTime()));
		assertEquals(before, thatTf.getBasePeriodOfTime().getCalendarStart().toString());
		assertFalse(thatTf.overlaps(thisTf.getBasePeriodOfTime()));
		assertEquals(before, thatTf.getBasePeriodOfTime().getCalendarStart().toString());
	}

	@Test
	public void timeFrameOverlapsIsIn_EACH_YEAR() {
		AbstractIntervalTimeFrame feb2009 = this.getStandardTimeFrameFeb2009();
		feb2009.setYearly();

		// simple non hit
		Calendar c1 = new GregorianCalendar(2009, 2, 1);
		Calendar c2 = new GregorianCalendar(2009, 3, 1);
		AbstractTimeFrame testtf = new SimpleTimeFrame(c1, c2);
		assertFalse(feb2009.overlaps(testtf));

		// simple hit
		c1 = new GregorianCalendar(2009, 1, 1);
		c2 = new GregorianCalendar(2009, 1, 2);
		testtf = new SimpleTimeFrame(c1, c2);
		assertTrue(feb2009.overlaps(testtf));

		// simple hit next year
		c1 = new GregorianCalendar(2010, 1, 1);
		c2 = new GregorianCalendar(2010, 1, 2);
		testtf = new SimpleTimeFrame(c1, c2);
		assertTrue(feb2009.overlaps(testtf));

		// simple hit next year overlaps
		c1 = new GregorianCalendar(2009, 12, 15);
		c2 = new GregorianCalendar(2010, 1, 15);
		testtf = new SimpleTimeFrame(c1, c2);
		assertTrue(feb2009.overlaps(testtf));

		// not jan anymore
		feb2009.getBasePeriodOfTime().setStart(new GregorianCalendar(2008, 6, 20).getTimeInMillis());
		feb2009.getBasePeriodOfTime().setEnd(new GregorianCalendar(2008, 6, 25).getTimeInMillis());
		assertFalse(feb2009.overlaps(testtf));

		// not feb anymore: 20.07.2008 - 10.02.2009
		feb2009.getBasePeriodOfTime().setStart(new GregorianCalendar(2008, 6, 20).getTimeInMillis());
		feb2009.getBasePeriodOfTime().setEnd(new GregorianCalendar(2009, 1, 10).getTimeInMillis());
		testtf.setStart(new GregorianCalendar(2008, 0, 15).getTimeInMillis());
		testtf.setEnd(new GregorianCalendar(2009, 1, 15).getTimeInMillis());
		// testtf = 15.01.2008 - 15.02.2009
		assertTrue(feb2009.overlaps(testtf));

		c1 = new GregorianCalendar(2009, 5, 1);
		c2 = new GregorianCalendar(2010, 1, 2);
		testtf = new SimpleTimeFrame(c1, c2);
		assertTrue(feb2009.overlaps(testtf));

		c1 = new GregorianCalendar(2009, 7, 1);
		c2 = new GregorianCalendar(2009, 8, 1);
		testtf = new SimpleTimeFrame(c1, c2);
		assertTrue(feb2009.overlaps(testtf));

	}

	@Test
	public void overlaps_01() {
		// init weekly interval time frame
		Calendar start = Calendar.getInstance();
		start.set(Calendar.YEAR, 2009);
		start.set(Calendar.MONTH, 10);
		start.set(Calendar.DAY_OF_MONTH, 6);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		Calendar end = (Calendar) start.clone();
		end.add(Calendar.DAY_OF_YEAR, 1);
		assertEquals("Fri Nov 06 00:00:00 CET 2009", start.getTime().toString());
		assertEquals("Sat Nov 07 00:00:00 CET 2009", end.getTime().toString());
		SimpleIntervalTimeFrame interval = new SimpleIntervalTimeFrame(start, end);
		interval.setWeekly();

		// init a testing day
		Calendar tdstart = Calendar.getInstance();
		tdstart.set(Calendar.YEAR, 2011);
		tdstart.set(Calendar.MONTH, 0);
		tdstart.set(Calendar.DAY_OF_MONTH, 14);
		tdstart.set(Calendar.HOUR_OF_DAY, 0);
		tdstart.set(Calendar.MINUTE, 0);
		tdstart.set(Calendar.SECOND, 0);
		tdstart.set(Calendar.MILLISECOND, 0);
		Calendar tdend = (Calendar) tdstart.clone();
		tdend.add(Calendar.DAY_OF_YEAR, 1);
		assertEquals("Fri Jan 14 00:00:00 CET 2011", tdstart.getTime().toString());
		assertEquals("Sat Jan 15 00:00:00 CET 2011", tdend.getTime().toString());
		TimeFrame toCheck = new SimpleTimeFrame(tdstart, tdend);
		assertTrue(interval.overlaps(toCheck));
	}

	@Test
	public void overlaps_02() {
		// init weekly interval time frame
		Calendar start = Calendar.getInstance();
		start.set(Calendar.YEAR, 2010);
		start.set(Calendar.MONTH, 1);
		start.set(Calendar.DAY_OF_MONTH, 16);
		start.set(Calendar.HOUR_OF_DAY, 4);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		Calendar end = (Calendar) start.clone();
		end.add(Calendar.HOUR_OF_DAY, 5);
		assertEquals("Tue Feb 16 04:00:00 CET 2010", start.getTime().toString());
		assertEquals("Tue Feb 16 09:00:00 CET 2010", end.getTime().toString());
		SimpleIntervalTimeFrame interval = new SimpleIntervalTimeFrame(start, end);
		interval.setDaily();

		// init a testing day
		Calendar tdstart = Calendar.getInstance();
		tdstart.set(Calendar.YEAR, 2011);
		tdstart.set(Calendar.MONTH, 0);
		tdstart.set(Calendar.DAY_OF_MONTH, 7);
		tdstart.set(Calendar.HOUR_OF_DAY, 0);
		tdstart.set(Calendar.MINUTE, 0);
		tdstart.set(Calendar.SECOND, 0);
		tdstart.set(Calendar.MILLISECOND, 0);
		Calendar tdend = (Calendar) tdstart.clone();
		tdend.add(Calendar.DAY_OF_YEAR, 1);
		assertEquals("Fri Jan 07 00:00:00 CET 2011", tdstart.getTime().toString());
		assertEquals("Sat Jan 08 00:00:00 CET 2011", tdend.getTime().toString());
		TimeFrame toCheck = new SimpleTimeFrame(tdstart, tdend);
		assertTrue(interval.overlaps(toCheck));
	}
}