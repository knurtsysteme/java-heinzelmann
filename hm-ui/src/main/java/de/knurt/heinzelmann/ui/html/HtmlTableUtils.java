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

/**
 * utilities for generating html tables.
 * mainly some factory methods.
 * @author Daniel Oltmanns
 * @since 0.20090412
 * @version 0.20091104
 */
public class HtmlTableUtils {

    private boolean even = false;

    public HtmlTableUtils() {
    }
    /**
     * return "odd" and "even" alternately.
     * this is useful, if you want to color the background of your
     * table rows.
     * @return
     */
    public String getOddEven() {
	String result = "";
	if (this.even) {
	    result = "even";
	    this.even = false;
	} else {
	    result = "odd";
	    this.even = true;
	}
	return result;
    }
}