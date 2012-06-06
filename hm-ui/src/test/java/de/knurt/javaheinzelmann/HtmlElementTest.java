package de.knurt.javaheinzelmann;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.knurt.heinzelmann.ui.html.HtmlElement;
import de.knurt.heinzelmann.ui.html.HtmlException;
import de.knurt.heinzelmann.ui.html.HtmlFactory;
import de.knurt.heinzelmann.ui.html.StrictHtmlFactory;

/**
 * @author danieloltmanns
 */
public class HtmlElementTest {

	public HtmlElementTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Test
	public void queue() {
		HtmlElement div = new HtmlElement("div").add("a").add("b").setAttribute("id", "c");
		assertEquals("<div id=\"c\">ab</div>", div.toString());
	}

	@Test
	public void strictHtmlFactoryTest() {
		StrictHtmlFactory fac = StrictHtmlFactory.getInstance();
		String[] musts = { "title" };
		fac.putMustAttributeKeys("img", musts);
		Properties p = new Properties();

		try {
			fac.get_img("a", "b", p);
		} catch (HtmlException e) {
			assertTrue(true);
		}

		p.put("title", "c");
		try {
			HtmlElement el = fac.get_img("a", "b", p);
			assertTrue(el.toString().contains("<img "));
			assertTrue(el.toString().contains(" alt=\"b\""));
			assertTrue(el.toString().contains(" src=\"a\""));
			assertTrue(el.toString().contains(" title=\"c\""));
			assertTrue(el.toString().contains("\"></img>"));
		} catch (Exception e) {
			fail("should not be thrown");
		}
	}

	@Test
	public void testEmptyElement() {
		assertEquals("<br></br>", HtmlFactory.get("br").toString());
	}

	@Test
	public void constructionHtmlElement() {
		HtmlElement el = new HtmlElement("foo");
		assertEquals(el.getClass(), HtmlElement.class);
		assertEquals("<foo></foo>", el.toString());
		el.addContent("bar");
		assertEquals("<foo>bar</foo>", el.toString());
		el.add("bar");
		assertEquals("<foo>barbar</foo>", el.toString());
		el.setAttribute("height", "23");
		assertEquals("<foo height=\"23\">barbar</foo>", el.toString());
		el.addContent(new HtmlElement("p"));
		assertEquals("<foo height=\"23\">barbar<p></p></foo>", el.toString());
	}
}