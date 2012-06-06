package de.knurt.javaheinzelmann;

import java.util.GregorianCalendar;
import java.util.Locale;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.knurt.heinzelmann.ui.html.calendar.CalendarOneMonthHtml;
import static org.junit.Assert.*;

/**
 *
 * @author danieloltmanns
 */
public class CalendarTest {

    public CalendarTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void constructionCalendarOneMonthHtml() {
	GregorianCalendar gc = new GregorianCalendar();
	CalendarOneMonthHtml gclayout = new CalendarOneMonthHtml(gc, Locale.getDefault());
	assertEquals(CalendarOneMonthHtml.class, gclayout.getClass());
	assertNotNull(gclayout.toString());
	assertNotSame("no feedback", "", gclayout.toString());
    }
    @Test
    public void tableCellsCalendarOneMonthHtml() {
	GregorianCalendar gc = new GregorianCalendar(2009, 03, 23);
	CalendarOneMonthHtml gclayout = new CalendarOneMonthHtml(gc, Locale.getDefault());
	String res = gclayout.toString();
	assertTrue(res.matches("^.*<table.+<\\/table>$"));
	// XXX this table cannot be anything else but a calendar ...
    }
}