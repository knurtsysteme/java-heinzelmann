package de.knurt.javaheinzelmann;

import de.knurt.heinzelmann.util.time.AbstractTimeFrame;
import de.knurt.heinzelmann.util.time.SimpleTimeFrame;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author danieloltmanns
 */
public class TimeFramesTest {

    public TimeFramesTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    private AbstractTimeFrame getStandardTimeFrame2009() {
	Calendar c1 = new GregorianCalendar(2009, 1, 1);
	Calendar c2 = new GregorianCalendar(2010, 1, 1);
	return new SimpleTimeFrame(c1, c2);
    }

    @Test
    public void timeFrameOverlapsIsIn_NO_ITERATION() {
	AbstractTimeFrame year2009 = this.getStandardTimeFrame2009();
	
	Calendar c1 = new GregorianCalendar(2008, 1, 1);
	Calendar c2 = new GregorianCalendar(2008, 6, 1);
	AbstractTimeFrame tf2 = new SimpleTimeFrame(c1, c2);
	assertFalse(year2009.overlaps(tf2));
	assertFalse(tf2.overlaps(year2009));

	// just touching
	c1 = new GregorianCalendar(2008, 1, 1);
	c2 = new GregorianCalendar(2009, 1, 1);
	tf2 = new SimpleTimeFrame(c1, c2);
	assertFalse(year2009.overlaps(tf2));
	assertFalse(tf2.overlaps(year2009));
	c1 = new GregorianCalendar(2010, 1, 1);
	c2 = new GregorianCalendar(2011, 1, 1);
	tf2 = new SimpleTimeFrame(c1, c2);
	assertFalse(year2009.overlaps(tf2));
	assertFalse(tf2.overlaps(year2009));

	// test hits overlaps
	c1 = new GregorianCalendar(2008, 1, 1);
	c2 = new GregorianCalendar(2009, 6, 1);
	tf2 = new SimpleTimeFrame(c1, c2);
	assertTrue(year2009.overlaps(tf2));

	// test year boost
	Calendar thisC1 = new GregorianCalendar(2008, 6, 20);
	Calendar thisC2 = new GregorianCalendar(2009, 1, 10);
	AbstractTimeFrame thisTf = new SimpleTimeFrame(thisC1, thisC2);
	Calendar thatC1 = new GregorianCalendar(2008, 7, 1);
	Calendar thatC2 = new GregorianCalendar(2008, 8, 1);
	AbstractTimeFrame thatTf = new SimpleTimeFrame(thatC1, thatC2);
	assertTrue(thisTf.overlaps(thatTf));
	assertTrue(thatTf.overlaps(thisTf));
    }
    
    @Test
    public void timeFrameGetCalendarDate() {
	// simple non hit
	Calendar cstart = new GregorianCalendar(2009, 2, 0);
	Calendar cend = new GregorianCalendar(2009, 3, 0);
	AbstractTimeFrame testtf = new SimpleTimeFrame(cstart, cend);
	assertEquals(testtf.getCalendarStart(), cstart);
	assertEquals(testtf.getCalendarEnd(), cend);
	assertEquals(testtf.getDateStart(), cstart.getTime());
	assertEquals(testtf.getDateEnd(), cend.getTime());
    }

    @Test
    @Ignore("TODO unresolved summertime problem!")
    public void addingWithSummertime_in() {
	Calendar start = new GregorianCalendar(2010, 2, 26, 1, 0, 0);
	Calendar end = new GregorianCalendar(2010, 2, 26, 2, 0, 0);
	AbstractTimeFrame result = new SimpleTimeFrame(start, end);

	assertEquals("Fri Mar 26 01:00:00 CET 2010 -- Fri Mar 26 02:00:00 CET 2010", result.toString());

	result.add(Calendar.DAY_OF_YEAR, 1);
	assertEquals("Sat Mar 27 01:00:00 CET 2010 -- Sat Mar 27 02:00:00 CET 2010", result.toString());

	result.add(Calendar.DAY_OF_YEAR, 1);
	// TODO summertime: Sun Mar 28 02:00:00 CET 2010 does not exist!
	System.out.println(result.toString());
	assertEquals("Sun Mar 28 01:00:00 CET 2010 -- Sun Mar 28 03:00:00 CEST 2010", result.toString());

	result.add(Calendar.DAY_OF_YEAR, 1);
	System.out.println(result.toString());
	assertEquals("Mon Mar 29 01:00:00 CEST 2010 -- Mon Mar 29 02:00:00 CEST 2010", result.toString());
    }

    @Test
    public void addingWithSummertime_out() {
	Calendar start = new GregorianCalendar(2010, 2, 26, 10, 0, 0);
	Calendar end = new GregorianCalendar(2010, 2, 26, 12, 0, 0);
	AbstractTimeFrame result = new SimpleTimeFrame(start, end);

	assertEquals("Fri Mar 26 10:00:00 CET 2010 -- Fri Mar 26 12:00:00 CET 2010", result.toString());

	result.add(Calendar.DAY_OF_YEAR, 1);
	assertEquals("Sat Mar 27 10:00:00 CET 2010 -- Sat Mar 27 12:00:00 CET 2010", result.toString());

	result.add(Calendar.DAY_OF_YEAR, 1);
	assertEquals("Sun Mar 28 10:00:00 CEST 2010 -- Sun Mar 28 12:00:00 CEST 2010", result.toString());

	result.add(Calendar.DAY_OF_YEAR, 1);
	assertEquals("Mon Mar 29 10:00:00 CEST 2010 -- Mon Mar 29 12:00:00 CEST 2010", result.toString());
    }

    @Test
    public void testContact() {
	AbstractTimeFrame tf0 = new SimpleTimeFrame(10, 12);
	AbstractTimeFrame tf1 = new SimpleTimeFrame(12, 14);
	assertFalse(tf0.overlaps(tf1));
	assertFalse(tf1.overlaps(tf0));
    }

    @Test
    public void testDuration() {
	Calendar cstart = new GregorianCalendar(2009, 0, 1, 0, 0);
	Calendar cend = new GregorianCalendar(2009, 0, 1, 0, 1);
	AbstractTimeFrame testtf = new SimpleTimeFrame(cstart, cend);
	assertEquals(60*1000, testtf.getDuration());

	cstart = new GregorianCalendar(2009, 0, 1, 0, 0);
	cend = new GregorianCalendar(2009, 0, 1, 1, 0);
	testtf = new SimpleTimeFrame(cstart, cend);
	assertEquals(60*60*1000, testtf.getDuration());

	cstart = new GregorianCalendar(2009, 0, 1, 0, 0);
	cend = new GregorianCalendar(2009, 0, 2, 0, 0);
	testtf = new SimpleTimeFrame(cstart, cend);
	assertEquals(24*60*60*1000, testtf.getDuration());

	cstart = new GregorianCalendar(2009, 0, 1, 0, 0);
	cend = new GregorianCalendar(2009, 1, 1, 0, 0);
	testtf = new SimpleTimeFrame(cstart, cend);
	assertEquals(31*24*60*60*1000l, testtf.getDuration());

	cstart = new GregorianCalendar(2009, 0, 1, 0, 0);
	cend = new GregorianCalendar(2010, 0, 1, 0, 0);
	testtf = new SimpleTimeFrame(cstart, cend);
	assertEquals(365*24*60*60*1000l, testtf.getDuration());
    }
}