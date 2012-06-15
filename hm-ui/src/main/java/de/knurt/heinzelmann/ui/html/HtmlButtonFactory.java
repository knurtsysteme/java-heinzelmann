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

import de.knurt.heinzelmann.util.query.QueryString;

/**
 * Factory for html buttons.
 * @author Daniel Oltmanns
 * @since 0.20090629
 * @version 0.20091104
 */
public class HtmlButtonFactory {

    /**
     * CODESMELL TODO <stroke>we</stroke> i need a form wrapper here.
     * produce a button embedded in a form element with given {@link QueryString}.
     * this results in:
     * <code>
     *  <form action="foo.html" method="post">
     *  <input type="hidden" name="a" />
     *  <input type="hidden" name="b" />
     *  <input type="hidden" name="c" />
     *  <button>sent</button>
     * </form>
     * </code>
     * @param queryString
     * @param form
     * @param button
     * @return
     */
    public static HtmlElement getButtonAsForm(QueryString queryString, HtmlElement form, HtmlElement button) {
	button.setAttribute("type", "submit");
	form.add("<p>" + queryString.getAsHtmlInputsTypeHidden() + "</p>");
	form.add("<p>" + button + "</p>");
	return form;
    }

    public HtmlButtonFactory() {
    }
}