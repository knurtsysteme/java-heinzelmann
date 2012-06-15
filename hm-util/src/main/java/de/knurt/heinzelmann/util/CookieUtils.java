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
package de.knurt.heinzelmann.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * some utility methods for working with cookies.
 *
 * @author Daniel Oltmanns
 * @since 0.20090405
 * @version 0.20100323
 */
public class CookieUtils {

    /**
     * delete the given cookie and send it to browser.
     * @param cookie to delete
     * @param response used to send deleted cookie
     */
    public static void delete(Cookie cookie, HttpServletResponse response) {
        response.addCookie(getDeleted(cookie));
    }

    /**
     * return the value of the cookie with given name or null if not exists
     * @param rq request
     * @param name of the cookie the value shall be returned
     * @return the value of the cookie with given name or null if not exists
     */
    public static String getCookieValue(HttpServletRequest rq, String name) {
        String result = null;
        Cookie cookie = getCookieWithName(rq, name);
        if (cookie != null) {
            result = cookie.getValue();
        }
        return result;
    }

    /**
     * return the cookie with the given name
     * @see javax.servlet.http.Cookie
     * @param rq request the cookie is in
     * @param name of the cookie returned
     * @return the cookie with the given name or null if it does not exist
     */
    public static Cookie getCookieWithName(HttpServletRequest rq, String name) {
        Cookie result = null;
        if (rq.getCookies() != null) {
            for (Cookie cookie : rq.getCookies()) {
                if (cookie.getName().equals(name)) {
                    result = cookie;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * delete all cookies set in the given request
     * @param rq request the cookies are in
     * @param response the deleted cookies are set to
     */
    public static void deleteAll(HttpServletRequest rq, HttpServletResponse response) {
        if (rq.getCookies() != null) {
            for (Cookie cookie : rq.getCookies()) {
                CookieUtils.delete(cookie, response);
            }
        }
    }

    /**
     * return the given cookie with setting value to empty and max-age to negative
     * @param cookie returned as deleted
     * @return the given cookie with setting value to empty and max-age to negative
     */
    public static Cookie getDeleted(Cookie cookie) {
        cookie.setMaxAge(-100);
        cookie.setValue(null);
        return cookie;
    }

    private CookieUtils() {
    }
}
