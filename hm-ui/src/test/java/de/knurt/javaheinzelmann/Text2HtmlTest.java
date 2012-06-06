package de.knurt.javaheinzelmann;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.knurt.heinzelmann.ui.html.Text2Html;

/**
 * 
 * @author danieloltmanns
 * @since 0.20101212
 */
public class Text2HtmlTest {

	public Text2HtmlTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Test
	public void linkUrls_1() {
		String nothing = " a b c d e f g   	";
		assertEquals(nothing, Text2Html.me().linkUrls(nothing));
	}

	@Test
	public void linkUrls_2() {
		String nothing = "a   http://www.knurt.de c http://www.knurt.de e f g";
		assertEquals("a   <a href=\"http://www.knurt.de\" target=\"_parent\">http://www.knurt.de</a> c <a href=\"http://www.knurt.de\" target=\"_parent\">http://www.knurt.de</a> e f g", Text2Html.me().linkUrls(nothing));
	}

	@Test
	public void linkUrls_nullValue() {
		assertNull(Text2Html.me().linkUrls(null));
	}

	@Test
	public void linkUrls_emptyValue() {
		assertEquals("", Text2Html.me().linkUrls(""));
	}

	@Test
	public void linkUrls_emptyValue2() {
		assertEquals(" ", Text2Html.me().linkUrls(" "));
	}

	@Test
	public void linkUrls_urlOnly() {
		assertEquals("<a href=\"http://www.knurt.de\" target=\"_parent\">http://www.knurt.de</a>", Text2Html.me().linkUrls("http://www.knurt.de"));
	}

	@Test
	public void nl2br() {
		assertEquals("a<br />b ", Text2Html.me().nl2br("a\nb "));
	}
}