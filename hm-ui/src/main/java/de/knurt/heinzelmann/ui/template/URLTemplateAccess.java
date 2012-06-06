package de.knurt.heinzelmann.ui.template;

import java.io.IOException;
import java.net.MalformedURLException;

import de.knurt.heinzelmann.util.query.QueryString;

/**
 * access content via an url
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>,
 *         <a href="http://www.knurt.de">homepage</a>)
 * @version 0.20100721
 */
public interface URLTemplateAccess {
	/**
	 * return the content for given key and query string.
	 * 
	 * @param key
	 *            for the template
	 * @return the content for given key and query string.
	 * @throws MalformedURLException
	 *             if parameters result in invalid url
	 * @throws IOException
	 *             if getting content result in io problems
	 */
	public String getContent(String key) throws MalformedURLException, IOException;

	/**
	 * return the content for given key and query string.
	 * 
	 * @param key
	 *            for the template
	 * @param qs
	 *            for template parameters
	 * @return the content for given key and query string.
	 * @throws MalformedURLException
	 *             if parameters result in invalid url
	 * @throws IOException
	 *             if getting content result in io problems
	 */
	public String getContent(String key, QueryString qs) throws MalformedURLException, IOException;

	/**
	 * return the url for the template
	 * 
	 * @param key
	 *            for the template
	 */
	public String getURL(String key);

	/**
	 * return the url for the template
	 * 
	 * @param key
	 *            for the template
	 * @param qs
	 *            for template parameters
	 */
	public String getURL(String key, QueryString qs);
}
