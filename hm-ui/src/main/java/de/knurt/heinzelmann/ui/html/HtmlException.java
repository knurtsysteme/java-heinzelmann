/*
 * Copyright 2005 - 2012 by KNURT Systeme (http://www.knurt.de)
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

/**
 * exeption when a html is not conform or not as required.
 * 
 * @see StrictHtmlFactory
 * @author Daniel Oltmanns
 * @since 0.1 20.05.2009
 * @version 0.20091104
 */
public class HtmlException extends Exception {
	private static final long serialVersionUID = 1L;

	public HtmlException(String string) {
		super(string);
	}
}