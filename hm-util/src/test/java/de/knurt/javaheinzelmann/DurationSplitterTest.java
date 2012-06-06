package de.knurt.javaheinzelmann;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.knurt.heinzelmann.util.time.DurationSplitter;

public class DurationSplitterTest {

	@Test
	public void testDuration() throws Exception {
		DurationSplitter ds = new DurationSplitter(1);

		int[] got = ds.getDaysHoursMinutes();
		assertEquals(3, got.length);
		assertEquals("[0,0,1]", String.format("[%s,%s,%s]", got[0], got[1], got[2]));

		ds.setMinutes(60);
		got = ds.getDaysHoursMinutes();
		assertEquals("[0,1,0]", String.format("[%s,%s,%s]", got[0], got[1], got[2]));

		ds.setMinutes(61);
		got = ds.getDaysHoursMinutes();
		assertEquals("[0,1,1]", String.format("[%s,%s,%s]", got[0], got[1], got[2]));

		ds.setMinutes(60 * 24);
		got = ds.getDaysHoursMinutes();
		assertEquals("[1,0,0]", String.format("[%s,%s,%s]", got[0], got[1], got[2]));

		ds.setMinutes(60 * 24 + 1);
		got = ds.getDaysHoursMinutes();
		assertEquals("[1,0,1]", String.format("[%s,%s,%s]", got[0], got[1], got[2]));

		ds.setMinutes(60 * 24 + 65);
		got = ds.getDaysHoursMinutes();
		assertEquals("[1,1,5]", String.format("[%s,%s,%s]", got[0], got[1], got[2]));

		ds.setMinutes((60 * 24 * 400) + (60 * 23) + 5);
		got = ds.getDaysHoursMinutes();
		assertEquals("[400,23,5]", String.format("[%s,%s,%s]", got[0], got[1], got[2]));
	}

}
