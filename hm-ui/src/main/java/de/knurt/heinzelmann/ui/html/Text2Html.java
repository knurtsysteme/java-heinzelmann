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

import org.apache.commons.validator.UrlValidator;

/**
 * transform text to html
 * 
 * @author Daniel Oltmanns
 * @since 0.20101212
 */
public class Text2Html {
	/** one and only instance of Text2Html */
	private volatile static Text2Html me;

	/** construct Text2Html */
	private Text2Html() {
	}

	/**
	 * return the one and only instance of Text2Html
	 * 
	 * @return the one and only instance of Text2Html
	 */
	public static Text2Html getInstance() {
		if (me == null) {
			// ↖ no instance so far
			synchronized (Text2Html.class) {
				if (me == null) {
					// ↖ still no instance so far
					// ↓ the one and only me
					me = new Text2Html();
				}
			}
		}
		return me;
	}

	/**
	 * short for {@link #getInstance()}
	 * 
	 * @return the one and only instance of Text2Html
	 */
	public static Text2Html me() {
		return getInstance();
	}

	/**
	 * do this: {@link http://de2.php.net/manual/de/function.nl2br.php}
	 * 
	 * @param string
	 * @return
	 */
	public String nl2br(String string) {
		return string.replaceAll("\n", "<br />");
	}

	public String linkUrls(String string) {
		return this.linkUrls(new UrlValidator(), string, "_parent");
	}

	public String linkUrls(UrlValidator urlValidator, String string, String target) {
		if (string != null && string.trim().isEmpty() == false) {
			String workWith = string.trim();
			String[] nonWss = workWith.split("\\s+");
			String[] wss = workWith.split("\\S+");
			String result = "";
			int i = 0;
			while (i < nonWss.length) {
				if (urlValidator.isValid(nonWss[i])) {
					result += HtmlFactory.get_a(nonWss[i], nonWss[i]).att("target", target).toString();
				} else {
					result += nonWss[i];
				}
				i++;
				if (wss.length > i) {
					result += wss[i];
				}
			}
			if (string.equals(workWith) == false) {
				// whitespace at start or end
				if (string.matches("\\s.*")) {
					result = string.split("\\S+")[0] + result;
				}
				if (string.matches(".*\\s")) {
					result += string.split("\\S+")[i];
				}
			}
			return result;
		} else {
			return string;
		}
	}

}
