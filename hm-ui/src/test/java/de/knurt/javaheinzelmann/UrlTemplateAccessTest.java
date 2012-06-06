package de.knurt.javaheinzelmann;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.knurt.heinzelmann.ui.template.URLTemplateAccessFormatString;
import de.knurt.heinzelmann.util.query.QueryString;

public class UrlTemplateAccessTest {
	
	@Test
	public void instance() {
		URLTemplateAccessFormatString uta = new URLTemplateAccessFormatString();
		assertEquals(URLTemplateAccessFormatString.class, uta.getClass());
	}
	
	@Test
	public void checkParametersURLTemplateAccessFormatString() {
		URLTemplateAccessFormatString uta = new URLTemplateAccessFormatString();
		assertEquals("http://127.0.0.1/%s.jsp", uta.getFormatString());
		assertEquals("http://127.0.0.1/foo.jsp", uta.getURL("foo"));
		QueryString qs = new QueryString();
		qs.put("a", "b");
		assertEquals("http://127.0.0.1/foo.jsp?a=b", uta.getURL("foo", qs));
	}

}
