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

import java.util.Properties;

/**
 * Factory for {@link HtmlElement}s with minimal must nodes.
 * 
 * E.g. a img-element must have at least a src and an alt node.
 * 
 * A title node is nice, but not neccassary. This is why the factory produces
 * just <code>&lt;img src="foo.gif" alt="bar" /&gt;</code>. Of course, you can
 * add the title after producing the element anyway.
 * 
 * USE STRICTLY LOWER CASE FOR ALL NODES!!! (img != IMG by now). this may be
 * fixed in future versions.
 * 
 * @author Daniel Oltmanns
 * @since 0.1 18.05.2009
 * @version 0.20091104
 */
public class HtmlFactory {

	public static HtmlElement get(Object tagName, Object content) {
		return get(tagName.toString(), content.toString());
	}

	public static HtmlElement get(String tagName, String content) {
		HtmlElement result = new HtmlElement(tagName);
		result.add(content);
		return result;
	}

	/** one and only instance of me */
	private volatile static HtmlFactory me;

	/** construct me */
	private HtmlFactory() {
	}

	/**
	 * return the one and only instance of HtmlFactory
	 * 
	 * @return the one and only instance of HtmlFactory
	 */
	public static HtmlFactory getInstance() {
		if (me == null) { // no instance so far
			synchronized (HtmlFactory.class) {
				if (me == null) { // still no instance so far
					me = new HtmlFactory(); // the one and only
				}
			}
		}
		return me;
	}

	private HtmlElement pattern;

	public HtmlElement get_tr(Object... tdContents) {
		HtmlElement result = HtmlFactory.get("tr");
		for (Object tdContent : tdContents) {
			HtmlElement tdPattern = this.isPattern("td") ? this.pattern : HtmlFactory.get("td");
			tdPattern.setContent(tdContent);
			result.add(tdPattern);
		}
		return result;
	}

	private boolean hasPattern() {
		return pattern != null;
	}

	private boolean isPattern(String tagName) {
		return hasPattern() && pattern.getTagname().equals(tagName);
	}

	public static HtmlElement get(String tagName, String content, Properties attributes) {
		HtmlElement result = new HtmlElement(tagName);
		result.setAttributes(attributes);
		result.add(content);
		return result;
	}

	public static HtmlElement get(String tagName, Properties attributes) {
		HtmlElement result = new HtmlElement(tagName);
		result.setAttributes(attributes);
		return result;
	}

	public static HtmlElement get_button_submit(String content) {
		HtmlElement result = new HtmlElement("button");
		result.setAttribute("onclick", "submit()");
		result.add(content);
		return result;
	}

	public static HtmlElement get_form(String method, String action) {
		HtmlElement form = HtmlFactory.get("form");
		form.setAttribute("method", method);
		form.setAttribute("action", action);
		return form;
	}

	public static HtmlElement get_label(String forr, String content) {
		HtmlElement label = HtmlFactory.get("label");
		label.setAttribute("for", forr);
		label.add(content);
		return label;

	}

	public static HtmlElement get(String tagname) {
		return new HtmlElement(tagname);
	}

	/**
	 * return a minimal img-html element like: <img src="$src" alt="$alt" />.
	 * 
	 * @param src
	 *            attribute node value of the image
	 * @param alt
	 *            attribute node value of the image
	 * @return HtmlElement of an img
	 */
	public static HtmlElement get_img(String src, String alt) {
		HtmlElement result = new HtmlElement("img");
		result.setAttribute("src", src);
		result.setAttribute("alt", alt);
		return result;
	}

	public static HtmlElement get_option(String value, String content, boolean selected) {
		HtmlElement option = get("option", content);
		option.setAttribute("value", value);
		if (selected) {
			option.setAttribute("selected", selected);
		}
		return option;
	}

	public static HtmlElement get_option(Object value, Object content, boolean selected) {
		return get_option(value.toString(), content.toString(), selected);
	}

	public static HtmlElement get_select(Properties attributes) {
		return get("select", attributes);
	}

	public static HtmlElement get_select(String name) {
		HtmlElement result = new HtmlElement("select");
		result.setAttribute("name", name);
		return result;
	}

	public static HtmlElement get_option(int value, String content, boolean selected) {
		return get_option(value + "", content, selected);
	}

	public static HtmlElement get_a_mailto(String mailAddress, String recipient) {
		return get_a("mailto:" + mailAddress, recipient);
	}

	public static HtmlElement get_ul(String[] liEntries) {
		HtmlElement result = HtmlFactory.get("ul");
		for (String liEntry : liEntries) {
			result.add(HtmlFactory.get("li", liEntry));
		}
		return result;
	}

	/**
	 * return a select tag with given options and selected value.
	 * 
	 * @param name
	 *            of the select
	 * @param roleoptions
	 *            with key values for the options. key are the values of the
	 *            options and values are the contents.
	 * @param selected
	 *            value being selected
	 * @return a select tag with given options and selected value.
	 */
	public static HtmlElement get_select_with_options(String name, Properties roleoptions, String selected) {
		HtmlElement result = get("select");
		result.setAttribute("name", name);
		for (Object o : roleoptions.keySet()) {
			HtmlElement option = get("option");
			Object value = roleoptions.get(o);
			option.setAttribute("value", o);
			option.add(value);
			if (value.toString().equals(selected)) {
				option.setAttribute("selected", "selected");
			}
			result.add(option);
		}
		return result;
	}

	public static HtmlElement get_input_checkbox(String name, boolean checked) {
		HtmlElement result = get("input");
		result.setAttribute("type", "checkbox");
		result.setAttribute("name", name);
		if (checked) {
			result.setAttribute("checked", "checked");
		}
		return result;
	}

	public static HtmlElement get_input_radio(String name, String value, boolean selected) {
		HtmlElement result = get("input");
		result.setAttribute("type", "radio");
		result.setAttribute("name", name);
		result.setAttribute("value", value);
		if (selected) {
			result.setAttribute("selected", "selected");
		}
		return result;
	}

	public static HtmlElement get_a(String href, String content) {
		HtmlElement result = get("a", content);
		result.setAttribute("href", href);
		return result;
	}

	/**
	 * @return the pattern
	 */
	public HtmlElement getPattern() {
		return pattern;
	}

	/**
	 * @param pattern
	 *            the pattern to set
	 */
	public void setPattern(HtmlElement pattern) {
		this.pattern = pattern;
	}
}
