/*
 * Copyright 2005 - 2010 by KNURT Systeme (http://www.knurt.de)
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
/*
 * Copyright 2005 - 2010 KNURT Systeme (http://www.knurt.de)
 */
package de.knurt.heinzelmann.util.query;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * some utility methods to convert servlet requests into something else.
 * 
 * @see HttpServletRequest
 * @author Daniel Oltmanns
 * @since 0.20101107
 * @version 0.20101107
 */
public class HttpServletRequestConverter {

	/**
	 * return the input contents of the given request using the reader.
	 * 
	 * @see HttpServletRequest#getReader()
	 * @param request
	 * @return the input contents of the given request using the reader.
	 * @throws IOException
	 */
	public String getInputContents(HttpServletRequest request) throws IOException {
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			sb.append(line + "\n");
			line = reader.readLine();
		}
		reader.close();
		return sb.toString();
	}

	/**
	 * convert the input contents of the given request into a json object and
	 * return.
	 * 
	 * @param request
	 * @return json of the input contents
	 * @throws JSONException
	 *             if the input contents is not a correct json string
	 * @throws IOException
	 *             on problems reading the input contents
	 */
	public JSONObject getJSONObject(HttpServletRequest request) throws JSONException, IOException {
		return new JSONObject(this.getInputContents(request));
	}

	/** one and only instance of HttpServletRequestConverter */
	private volatile static HttpServletRequestConverter me;

	/** construct HttpServletRequestConverter */
	private HttpServletRequestConverter() {
	}

	/**
	 * return the one and only instance of HttpServletRequestConverter
	 * 
	 * @return the one and only instance of HttpServletRequestConverter
	 */
	public static HttpServletRequestConverter getInstance() {
		if (me == null) {
			// ↖ no instance so far
			synchronized (HttpServletRequestConverter.class) {
				if (me == null) {
					// ↖ still no instance so far
					// ↓ the one and only me
					me = new HttpServletRequestConverter();
				}
			}
		}
		return me;
	}

	/**
	 * short for {@link #getInstance()}
	 * 
	 * @return the one and only instance of HttpServletRequestConverter
	 */
	public static HttpServletRequestConverter me() {
		return getInstance();
	}
}
