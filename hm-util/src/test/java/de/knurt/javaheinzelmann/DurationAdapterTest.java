package de.knurt.javaheinzelmann;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.knurt.heinzelmann.util.text.DurationAdapter;
import static de.knurt.heinzelmann.util.text.DurationAdapter.SupportedLanguage.*;
import de.knurt.heinzelmann.util.time.DurationSplitter;

public class DurationAdapterTest {

	@Test
	public void testDurationGerman() throws Exception {
		DurationSplitter ds = new DurationSplitter(1);
		DurationAdapter da = new DurationAdapter(GERMAN);
		assertEquals("1 Minute", da.getText(ds));
		ds.setMinutes(2);
		assertEquals("2 Minuten", da.getText(ds));
		ds.setMinutes(60);
		assertEquals("1 Stunde", da.getText(ds));
		ds.setMinutes(120);
		assertEquals("2 Stunden", da.getText(ds));
		ds.setMinutes(121);
		assertEquals("2 Stunden und 1 Minute", da.getText(ds));
		ds.setMinutes(24 * 60);
		assertEquals("1 Tag", da.getText(ds));
		ds.setMinutes(24 * 60 + 60);
		assertEquals("1 Tag und 1 Stunde", da.getText(ds));
		ds.setMinutes(24 * 60 * 2 + 60);
		assertEquals("2 Tage und 1 Stunde", da.getText(ds));
		ds.setMinutes(24 * 60 * 2 + 2 * 60);
		assertEquals("2 Tage und 2 Stunden", da.getText(ds));
		ds.setMinutes(24 * 60 * 2 + 2 * 60 + 3);
		assertEquals("2 Tage, 2 Stunden und 3 Minuten", da.getText(ds));
		ds.setMinutes(24 * 60 * 2 + 3);
		assertEquals("2 Tage und 3 Minuten", da.getText(ds));
		da.setLanguage(ENGLISH);
		assertEquals("2 days and 3 minutes", da.getText(ds));
	}

	@Test
	public void testDurationEnglish() throws Exception {
		DurationSplitter ds = new DurationSplitter(1);
		DurationAdapter da = new DurationAdapter(ENGLISH);
		assertEquals("1 minute", da.getText(ds));
		ds.setMinutes(2);
		assertEquals("2 minutes", da.getText(ds));
		ds.setMinutes(60);
		assertEquals("1 hour", da.getText(ds));
		ds.setMinutes(120);
		assertEquals("2 hours", da.getText(ds));
		ds.setMinutes(121);
		assertEquals("2 hours and 1 minute", da.getText(ds));
		ds.setMinutes(24 * 60);
		assertEquals("1 day", da.getText(ds));
		ds.setMinutes(24 * 60 + 60);
		assertEquals("1 day and 1 hour", da.getText(ds));
		ds.setMinutes(24 * 60 * 2 + 60);
		assertEquals("2 days and 1 hour", da.getText(ds));
		ds.setMinutes(24 * 60 * 2 + 2 * 60);
		assertEquals("2 days and 2 hours", da.getText(ds));
		ds.setMinutes(24 * 60 * 2 + 2 * 60 + 3);
		assertEquals("2 days, 2 hours and 3 minutes", da.getText(ds));
		ds.setMinutes(24 * 60 * 2 + 3);
		assertEquals("2 days and 3 minutes", da.getText(ds));
	}

}
