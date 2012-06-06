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
package de.knurt.heinzelmann.util.debug;

import de.knurt.heinzelmann.util.shopping.ShoppingCart;
import de.knurt.heinzelmann.util.time.AbstractIntervalTimeFrame;
import java.text.DateFormat;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;

/**
 * for evil outprinting things without using the ide debugger.
 * @author Daniel Oltmanns
 * @since 0.20090429
 * @version 0.20091104
 * @deprecated not for productive usage!
 */
 public class SystemOutDebug {

    private static String totalResult = "";
    private static String SEPARATOR = " - ";

    public static void add(AbstractIntervalTimeFrame result) {
	add(result.getBasePeriodOfTime().getCalendarStart());
	add(result.getBasePeriodOfTime().getCalendarEnd());
	add(result.getInterval() + "");
    }

    public static void out() {
	System.out.println(totalResult);
	totalResult = "";
    }

    public static void out(Object out) {
	add(out);
	out();
    }
    public static void out(ShoppingCart out) {
	add(out);
	out();
    }

    public static void add(ShoppingCart sc) {
	String result = sc.getArticles().size() + " articles:\n";
	for(String key : sc.getArticleNumbers()) {
	    result += " \t" + key + ": " + sc.getArticle(key) + "\n";
	}
	add(result);
    }

    public static void out(HttpServletRequest out) {
	for(Object key : out.getParameterMap().keySet()) {
	    String outt = out.getParameter(key.toString());
	    add(key + "=" + outt);
	}
	out();
    }

    public static void add(Object out) {
	add(out.toString());
    }

    public static void add(String str) {
	totalResult += str;
	addSeparator();
    }

    public static void add(Calendar cal) {
	DateFormat df = DateFormat.getDateTimeInstance();
	add(df.format(cal.getTime()));
    }
    public static void out(Calendar cal) {
	DateFormat df = DateFormat.getDateTimeInstance();
	out(df.format(cal.getTime()));
    }

    public static void addSeparator() {
	totalResult += " " + SEPARATOR + " ";
    }

    private SystemOutDebug() {
    }
}