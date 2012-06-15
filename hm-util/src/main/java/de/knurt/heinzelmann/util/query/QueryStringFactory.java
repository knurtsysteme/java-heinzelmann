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
package de.knurt.heinzelmann.util.query;

import javax.servlet.http.HttpServletRequest;

/**
 * COMMENT me
 *
 * @author Daniel Oltmanns
 * @since 16.10.2009
 * @version 0.20091104
 */
public class QueryStringFactory {

    /** one and only instance of me */
    private volatile static QueryStringFactory me;

    public static QueryString get(String key, String value) {
        QueryString result = new QueryString();
        result.put(key, value);
        return result;
    }

    /** construct me */
    private QueryStringFactory(){}

    /**
     * return the one and only instance of me
     * @return the one and only instance of me
     */
    public static QueryStringFactory getInstance() {
	if (me == null) { // no instance so far
	    synchronized (QueryStringFactory.class) {
		if (me == null) { // still no instance so far
		    me = new QueryStringFactory(); // the one and only
		}
	    }
	}
	return me;
    }

    /**
     * produce a QueryString out of the params of the given request.
     * @param rq request params are taken from.
     * @return a QueryString out of the params of the given request.
     */
    public QueryString get(HttpServletRequest rq) {
        QueryString result = new QueryString();
        result.add(rq);
        return result;
    }
}
