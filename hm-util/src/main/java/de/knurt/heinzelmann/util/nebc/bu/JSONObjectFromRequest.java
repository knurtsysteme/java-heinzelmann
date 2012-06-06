/*
 * Copyright 2009-2012 by KNURT Systeme (http://www.knurt.de)
 *
 * Licensed under the Creative Commons License Attribution-NonCommercial-No Derivative Works 3.0 Unported;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://creativecommons.org/licenses/by-nc-nd/3.0/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */package de.knurt.heinzelmann.util.nebc.bu;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import de.knurt.heinzelmann.util.nebc.BoardUnit;

/**
 * return a json object from a given request. if the request has the parameter
 * "data", this value is taken. otherwise the raw data is given.
 * 
 * @author Daniel Oltmanns
 * @since 02/22/2012
 */

public class JSONObjectFromRequest implements BoardUnit<HttpServletRequest, JSONObject> {
	private JSONObject output = null;

	@Override
	public JSONObject process(HttpServletRequest datum) {
		if (output == null) {
			String jsonString = null;
			if (datum.getParameter("data") != null) {
				jsonString = datum.getParameter("data");
			} else {
				jsonString = new RawDataStringFromRequest().process(datum);
			}
			try {
				output = new JSONObject(jsonString);
			} catch (JSONException e) {
				return null;
			}
		}
		return output;

	}
}
