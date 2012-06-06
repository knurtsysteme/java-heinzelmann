/*
 * Copyright 2005 - 2011 by KNURT Systeme (http://www.knurt.de)
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
package de.knurt.heinzelmann.util.nebc.bu;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import de.knurt.heinzelmann.util.nebc.BoardUnit;

/**
 * Generate {@link Properties} out of a {@link JSONObject}.
 * 
 * @author Daniel Oltmanns
 * @since 06/07/2011
 */
@SuppressWarnings("unchecked")
public class JSONObject2Map implements BoardUnit<JSONObject, Map> {

	private boolean isJSONArray(JSONObject obj, String key) {
		boolean result = false;
		try {
			obj.getJSONArray(key);
			result = true;
		} catch (JSONException e) {
		}
		return result;
	}

	private boolean isJSONObject(JSONObject obj, String key) {
		boolean result = false;
		try {
			obj.getJSONObject(key);
			result = true;
		} catch (JSONException e) {
		}
		return result;
	}

	@Override
	public Map process(JSONObject datum) {
		Map result = new HashMap();
		if(JSONObject.getNames(datum) != null) {
			String[] keys = JSONObject.getNames(datum).clone();
			for (String key : keys) {
				try {
					if (this.isJSONObject(datum, key)) {
						result.put(key, this.process(datum.getJSONObject(key)));
					} else if (this.isJSONArray(datum, key)) {
						result.put(key, new JSONArray2Properties().process(datum.getJSONArray(key)));
					} else {
						result.put(key, datum.get(key));
					}
				} catch (JSONException e) {
					Logger.getRootLogger().fatal("201203071136");
				}
			}
		}
		return result;
	}

}
