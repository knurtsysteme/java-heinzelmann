/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.knurt.javaheinzelmann;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.knurt.heinzelmann.util.time.TimeFormatConverterWindowsNTTime;

/**
 * 
 * @author danieloltmanns
 */
public class TimeFormatConverterTest {

	public TimeFormatConverterTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void windowsNT_date2long() {
		TimeFormatConverterWindowsNTTime tc = TimeFormatConverterWindowsNTTime.me();
		Date date = new Date(0);
		assertNotNull(tc.getTime(date));
		// ↘ @link http://fieldmarshallhradek.com/2009/05/w32-versus-unix-time
		assertEquals(new Long(116444736000000000l), tc.getTime(date));
	}

	@Test
	public void windowsNT_long2date() {
		TimeFormatConverterWindowsNTTime tc = TimeFormatConverterWindowsNTTime.me();
		Long time = new Long(116444736000000000l);
		Date got = tc.getTime(time);
		assertNotNull(got);
		assertEquals(0l, got.getTime());
	}
}
