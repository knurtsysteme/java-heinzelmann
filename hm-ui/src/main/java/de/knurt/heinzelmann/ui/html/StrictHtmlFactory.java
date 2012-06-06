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
package de.knurt.heinzelmann.ui.html;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * a html factory where you can define attributes that must be.
 * 
 * @author Daniel Oltmanns
 * @since 0.1 20.05.2009
 * @version 0.20091104
 */
public class StrictHtmlFactory {

	private Map<String, String[]> mustAttributeKeys;

	private StrictHtmlFactory() {
		this(new LinkedHashMap<String, String[]>());
	}

	private StrictHtmlFactory(Map<String, String[]> mustAttributeKeys) {
		this.mustAttributeKeys = mustAttributeKeys;
	}

	/** one and only instance of me */
	private volatile static StrictHtmlFactory me;

	/**
	 * return the one and only instance of StrictHtmlFactory
	 * 
	 * @return the one and only instance of StrictHtmlFactory
	 */
	public static StrictHtmlFactory getInstance() {
		if (me == null) { // no instance so far
			synchronized (StrictHtmlFactory.class) {
				if (me == null) { // still no instance so far
					me = new StrictHtmlFactory(); // the one and only
				}
			}
		}
		return me;
	}

	/**
	 * short for getInstance()
	 * 
	 * @see getInstance()
	 * @return instance
	 */
	public static StrictHtmlFactory get() {
		return getInstance();
	}

	/**
	 * return a img-html element like: <img src="$src" alt="$alt" />.
	 * 
	 * @param src
	 *            attribute node value of the image
	 * @param alt
	 *            attribute node value of the image
	 * @param attributes
	 * @return HtmlElement of an img
	 * @throws HtmlException
	 *             if not all needed attributes there
	 */
	public HtmlElement get_img(String src, String alt, Properties attributes) throws HtmlException {
		HtmlElement result = HtmlFactory.get_img(src, alt);
		this.checkAndThrowException("img", attributes);
		result.addAttributes(attributes);
		return result;
	}

	public HtmlElement get_img(String src, String alt) throws HtmlException {
		return this.get_img(src, alt, new Properties());
	}

	public HtmlElement get_a(String href, String content, Properties attributes) {
		HtmlElement result = HtmlFactory.get_a(href, content);
		try {
			this.checkAndThrowException("a", attributes);
		} catch (HtmlException ex) {
			ex.printStackTrace();
		}
		result.addAttributes(attributes);
		return result;
	}

	public HtmlElement get_a(String href, String content) {
		return this.get_a(href, content, new Properties());
	}

	/**
	 * @return the mustAttributeKeys
	 */
	public Map<String, String[]> getMustAttributeKeys() {
		return mustAttributeKeys;
	}

	/**
	 * @param tagName
	 *            of attributeKeys set
	 * @param mustAttributeKeys
	 *            the mustAttributeKeys to set
	 */
	public void putMustAttributeKeys(String tagName, String[] mustAttributeKeys) {
		this.mustAttributeKeys.put(tagName, mustAttributeKeys);
	}

	private void checkAndThrowException(String tagName, Properties attributes) throws HtmlException {
		if (this.getMustAttributeKeys().containsKey(tagName)) {
			for (String attributeKey : this.getMustAttributeKeys().get(tagName)) {
				if (attributes.containsKey(attributeKey) == false) {
					throw new HtmlException(tagName + " must contain " + attributeKey);
				}
			}
		}
	}

	/**
	 * @param mustAttributeKeys
	 *            the mustAttributeKeys to set
	 */
	public void setMustAttributeKeys(Map<String, String[]> mustAttributeKeys) {
		this.mustAttributeKeys = mustAttributeKeys;
	}
}
