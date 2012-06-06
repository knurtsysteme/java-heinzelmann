/*
 * Copyright 2005 - 2009 by KNURT Systeme (http://www.knurt.de)
 *
 * Licensed under the Creative Commons License Attribution-NonCommercial 3.0 Unported;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://creativecommons.org/licenses/by-nc/3.0/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Copyright 2005 - 2009 KNURT Systeme (http://www.knurt.de)
 */
package de.knurt.heinzelmann.util.query;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * This is the part of the URL that is a query string.
 * 
 * @author Daniel Oltmanns
 * @since 0.1 12.04.2009
 * @version 0.20091104
 */
public class QueryString implements Map<String, String> {

	/**
	 * add all key and values of the given request.
	 * 
	 * @param rq
	 *            to add
	 * @return this
	 */
	public QueryString add(HttpServletRequest rq) {
		for (Object keyo : rq.getParameterMap().keySet()) {
			String key = keyo.toString();
			this.put(key, rq.getParameter(key));
		}
		return this;
	}

	private Map<String, String> map;

	public QueryString() {
		this.map = new HashMap<String, String>();
	}

	@SuppressWarnings("deprecation")
	private String encode(String urlpart) {
		String result = urlpart;
		try {
			result = URLEncoder.encode(urlpart, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			result = URLEncoder.encode(urlpart);
		}
		return result;
	}

	public void put(String key, int value) {
		this.put(key, value + "");
	}

	@Override
	public String toString() {
		return this.getAsHtmlLinkHref();
	}

	/**
	 * return the query params of the string. with a leading "?".
	 * 
	 * @see #getAsHtmlLinkHref(boolean)
	 * @param useAmp
	 *            set true, if you want html entity &amp;amp; for &amp;
	 * @return the query params of the string.
	 */
	public String getAsHtmlLinkHref(boolean useAmp) {
		super.toString();
		String result = "";
		if (this.isEmpty() == false) {
			result = "?";
			int i = 0;
			for (String key : this.keySet()) {
				result += this.encode(key) + "=" + this.encode(this.get(key));
				if (i++ < this.size() - 1) {
					result += useAmp ? "&amp;" : "&";
				}
			}
		}
		return result;
	}

	/**
	 * return the query params of the string. with a leading "?" and with
	 * &amp;amp; as delimiter.
	 * 
	 * @see #getAsHtmlLinkHref(boolean)
	 * @return the query params of the string.
	 */
	public String getAsHtmlLinkHref() {
		return this.getAsHtmlLinkHref(true);
	}

	/**
	 * return the query string in input type hidden tags. this is formatting
	 * this string on every key value pair:<br />
	 * <code>
     * <input type="hidden" name="%s" value="%s" />
     * </code>
	 * 
	 * @return the query string in input type hidden tags.
	 */
	public String getAsHtmlInputsTypeHidden() {
		String format = "<input type=\"hidden\" name=\"%s\" value=\"%s\" />";
		String result = "";
		for (String key : this.keySet()) {
			result += String.format(format, key, this.get(key));
		}
		return result;
	}

	@Override
	public int size() {
		return this.map.size();
	}

	@Override
	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return this.map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return this.map.containsValue(value);
	}

	@Override
	public String get(Object key) {
		return this.map.get(key);
	}

	@Override
	public String put(String key, String value) {
		return this.map.put(key, value);
	}

	@Override
	public String remove(Object key) {
		return this.map.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends String> m) {
		this.map.putAll(m);
	}

	@Override
	public void clear() {
		this.map.clear();
	}

	@Override
	public Set<String> keySet() {
		return this.map.keySet();
	}

	@Override
	public Collection<String> values() {
		return this.map.values();
	}

	@Override
	public Set<Entry<String, String>> entrySet() {
		return this.map.entrySet();
	}

	/**
	 * return the query params of the string. this is exatly the output of
	 * {@link #getAsHtmlLinkHref(boolean)} but without the leading "?".
	 * 
	 * @see #getAsHtmlLinkHref(boolean)
	 * @param useAmp
	 *            set true, if you want html entity &amp;amp; for &amp;
	 * @return the query params of the string.
	 */
	public String getAsQueryParams(boolean useAmp) {
		return this.getAsHtmlLinkHref(useAmp).substring(1);
	}

	/**
	 * add all values of the given properties. use {@link Properties#toString()}
	 * for keys and values.
	 * 
	 * @param properties
	 *            to add
	 * @return this
	 */
	public QueryString add(Properties properties) {
		for (Object key : properties.keySet()) {
			this.put(key.toString(), properties.getProperty(key.toString()));
		}
		return this;
	}
}
