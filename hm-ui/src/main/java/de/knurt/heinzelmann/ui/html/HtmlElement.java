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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import de.knurt.heinzelmann.ui.CssStyle;
import de.knurt.heinzelmann.ui.CssStyleFactory;

/**
 * a html element optimized for putting it out.
 * 
 * @author Daniel Oltmanns
 * @since 0.1 18.05.2009
 * @version 0.20091104
 */
public class HtmlElement implements Cloneable {

	/** The content of the element */
	private List<Object> contents;
	/** attributes of the html element */
	private Properties attributes;
	/** element name of the html element */
	private String tagname;

	/**
	 * Construct a new html element
	 * 
	 * @param tagname
	 *            the name of the element ('&lt;pre&gt;li&lt;/pre&gt;' for
	 *            '&lt;pre&gt;&lt;li&gt;&lt;/li&gt;&lt;/pre&gt;')
	 */
	public HtmlElement(String tagname) {
		this.contents = new ArrayList<Object>();
		this.attributes = new Properties();
		this.tagname = tagname;
	}

	/**
	 * add a css style to the style attribute
	 * 
	 * @param key
	 *            of the style
	 * @param value
	 *            of the style
	 * @return this
	 */
	public HtmlElement addCssStyle(String key, String value) {
		this.cssStyle.add(key, value);
		return this;
	}

	/**
	 * add the style "display: none" to the element
	 * 
	 * @return this
	 */
	public HtmlElement hide() {
		this.addCssStyle("display", "none");
		return this;
	}

	/**
	 * short for {@link #addCssStyle(java.lang.String, java.lang.String)}
	 * 
	 * @param key
	 *            of the style
	 * @param value
	 *            of the style
	 * @return this
	 */
	public HtmlElement style(String key, String value) {
		return this.addCssStyle(key, value);
	}

	/**
	 * set a css style
	 * 
	 * @param cssStyle
	 *            to set
	 * @return this
	 */
	public HtmlElement setCssStyle(CssStyle cssStyle) {
		this.cssStyle = cssStyle;
		return this;
	}

	/**
	 * add a class name to the class attribute
	 * 
	 * @param className
	 *            to add
	 * @return this
	 */
	public HtmlElement addClassName(String className) {
		if (this.getAttributes().containsKey("class")) {
			className = this.getAttributes().getProperty("class") + " " + className;
		}
		this.getAttributes().setProperty("class", className);
		return this;
	}

	/**
	 * short for {@link #addClassName(java.lang.String)}
	 * 
	 * @param className
	 *            to add
	 * @return this
	 */
	public HtmlElement cla(String className) {
		return this.addClassName(className);
	}

	/**
	 * add a title to the element
	 * 
	 * @param title
	 *            to add
	 * @return this
	 */
	public HtmlElement addTitleAttribute(String title) {
		this.setAttribute("title", title);
		return this;
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error("implements Cloneable!");
		}
	}

	/**
	 * Set the content of the element
	 * 
	 * The array can contain everything valid html. The parts of the array will
	 * put together in order of the array.
	 * 
	 * @param contents
	 *            the contents of the element, typically strings or other
	 *            HtmlElements
	 * @return this
	 */
	public HtmlElement setContents(List<Object> contents) {
		this.contents = contents;
		return this;
	}

	/**
	 * Add new content to the element. The content is typically a simple string,
	 * html as string or another HtmlElement
	 * 
	 * @param content
	 *            the content of the HtmlElement
	 * @return this
	 */
	public HtmlElement addContent(Object content) {
		this.contents.add(content);
		return this;
	}

	/**
	 * Alias for addContent
	 * 
	 * @param content
	 *            to add
	 * @return this
	 */
	public HtmlElement add(Object content) {
		this.addContent(content);
		return this;
	}

	/**
	 * set an attribute node to the html element. override attribute if exist.
	 * 
	 * @param key
	 *            the attribute node name
	 * @param value
	 *            the value of the attribute node
	 * @return this
	 */
	public HtmlElement setAttribute(String key, Object value) {
		this.setAttribute(key, value == null ? "" : value.toString());
		return this;
	}

	/**
	 * set an attribute node to the html element. override attribute if exist.
	 * 
	 * @param key
	 *            the attribute node name
	 * @param value
	 *            the value of the attribute node
	 * @return this
	 */
	public HtmlElement setAttribute(String key, int value) {
		this.setAttribute(key, value + "");
		return this;
	}

	/**
	 * set an attribute node to the html element. override attribute if exist.
	 * 
	 * @param key
	 *            the attribute node name
	 * @param value
	 *            the value of the attribute node
	 * @return this
	 */
	public HtmlElement setAttribute(String key, String value) {
		this.getAttributes().setProperty(key, value);
		return this;
	}

	/**
	 * return the element as html, even, if it is not valid (eg "<></>").
	 * 
	 * @return the element as html
	 */
	@Override
	public String toString() {
		String result = '<' + this.tagname;
		String format = " %s=\"%s\"";

		if (this.cssStyle != null && !cssStyle.toString().trim().isEmpty()) {
			this.setAttribute("style", cssStyle.toString());
		}

		for (Object key : attributes.keySet()) {
			String value = attributes.getProperty((String) key);
			result += String.format(format, key, value);
		}
		result += '>';
		for (Object content : this.contents) {
			result += content == null ? "" : content.toString();
		}
		result += String.format("</%s>", this.tagname);
		return result;
	}

	/**
	 * @return the content
	 */
	public List<Object> getContents() {
		return contents;
	}

	/**
	 * @return the attributes
	 */
	public Properties getAttributes() {
		return attributes;
	}

	/**
	 * set all attributes of the element. keys are the names of attribute nodes,
	 * values are the values of attribute nodes.
	 * 
	 * @param attributes
	 *            of the element. keys are the names of attribute nodes, values
	 *            are the values of attribute nodes.
	 * @return this
	 */
	public HtmlElement setAttributes(Properties attributes) {
		this.attributes = attributes;
		return this;
	}

	/**
	 * @return the tagname
	 */
	public String getTagname() {
		return tagname;
	}

	/**
	 * @param tagname
	 *            the tagname to set
	 * @return this
	 */
	public HtmlElement setTagname(String tagname) {
		this.tagname = tagname;
		return this;
	}

	public HtmlElement addAttributes(Properties attributes) {
		this.attributes.putAll(attributes);
		return this;
	}

	/**
	 * clear all existing contents and add the one and only
	 * 
	 * @param content
	 *            to add
	 * @return this
	 */
	public HtmlElement setContent(Object content) {
		this.contents.clear();
		this.contents.add(content);
		return this;
	}

	/**
	 * set the id attribute
	 * 
	 * @param id
	 *            of the element
	 * @return this
	 */
	public HtmlElement setId(String id) {
		this.attributes.setProperty("id", id);
		return this;
	}

	/**
	 * set the id attribute
	 * 
	 * @param id
	 *            of the element
	 * @return this
	 */
	public HtmlElement id(String id) {
		return this.setId(id);
	}

	/**
	 * add an javascript alert on click event.
	 * 
	 * @param string
	 *            to alert via javascript
	 * @return this
	 */
	public HtmlElement jsAlertOnClick(String string) {
		this.attributes.setProperty("onclick", "javascript:window.alert('" + string + "');");
		return this;
	}

	public HtmlElement jsAlertOnClickAndSetAsTitle(String string) {
		this.jsAlertOnClick(string);
		this.attributes.setProperty("title", string);
		return this;
	}

	/**
	 * short form of addAttribute
	 * 
	 * @see #addAttribute(java.lang.String, java.lang.String)
	 * @param name
	 *            of the attribute
	 * @param value
	 *            of the attribute
	 * @return this
	 */
	public HtmlElement att(String name, Object value) {
		return this.setAttribute(name, value);
	}

	/**
	 * set given string as name and value. that is when name is value for e.g.
	 * <code><input selected="selected" ...</code>
	 * 
	 * @see #addAttribute(java.lang.String)
	 * @param nameAndValue
	 *            name and value of the attribute
	 * @return this
	 */
	public HtmlElement att(String nameAndValue) {
		return this.setAttribute(nameAndValue, nameAndValue);
	}

	/**
	 * set <code>style="display: none;"</code> as attribute node.
	 * 
	 * @return this
	 */
	public HtmlElement doNotDisplay() {
		this.cssStyle.add("display", "none");
		return this;
	}

	private CssStyle cssStyle = CssStyleFactory.getInstance().get();

	public CssStyle getCssStyle() {
		return this.cssStyle;
	}

	/**
	 * return this and add an &lt;br /&br; before. same as
	 * <code>this.add(HtmlFactory.get("br"))</code>
	 * 
	 * @return this
	 */
	public HtmlElement br() {
		return this.add(HtmlFactory.get("br"));
	}

	/**
	 * add title attribute
	 * 
	 * @param title
	 *            value of title attribute
	 * @return this
	 */
	public HtmlElement title(String title) {
		return this.att("title", title);
	}

	/**
	 * add src attribute
	 * 
	 * @param src
	 *            value of src attribute
	 * @return this
	 */
	public HtmlElement src(String src) {
		return this.att("src", src);
	}

	/**
	 * add href attribute
	 * 
	 * @param href
	 *            value of href attribute
	 * @return this
	 */
	public HtmlElement href(String href) {
		return this.att("href", href);
	}

	/**
	 * add value attribute
	 * 
	 * @param value
	 *            value of value attribute
	 * @return this
	 */
	public HtmlElement value(String value) {
		return this.att("value", value);
	}

	/**
	 * add name attribute
	 * 
	 * @param name
	 *            value of name attribute
	 * @return this
	 */
	public HtmlElement name(String name) {
		return this.att("name", name);
	}
}
