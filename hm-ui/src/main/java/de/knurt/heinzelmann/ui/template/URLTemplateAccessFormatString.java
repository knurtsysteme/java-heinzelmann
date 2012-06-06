package de.knurt.heinzelmann.ui.template;

import java.io.IOException;
import java.net.MalformedURLException;

import de.knurt.heinzelmann.util.query.QueryString;
import de.knurt.heinzelmann.util.urlcontent.HttpsUnsecuredContent;

/**
 * access content via an url using a format string and a key.
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>,
 *         <a href="http://www.knurt.de">homepage</a>)
 * @version 0.20100721
 */
public class URLTemplateAccessFormatString implements URLTemplateAccess {

	private String formatString = "http://127.0.0.1/%s.jsp";

	public void setFormatString(String formatString) {
		this.formatString = formatString;
	}

	public String getFormatString() {
		return formatString;
	}

	@Override
	public String getContent(String key) throws MalformedURLException, IOException {
		return this.getContent(key, null);
	}

	@Override
	public String getContent(String key, QueryString qs) throws MalformedURLException, IOException {
            return HttpsUnsecuredContent.getInstance().getContent(this.getURL(key, qs));
	}

	@Override
	public String getURL(String key) {
		return this.getURL(key, null);
	}

	@Override
	public String getURL(String key, QueryString qs) {
		return String.format(this.formatString, key) + (qs == null ? "" : qs.getAsHtmlLinkHref(false));
	}
}
