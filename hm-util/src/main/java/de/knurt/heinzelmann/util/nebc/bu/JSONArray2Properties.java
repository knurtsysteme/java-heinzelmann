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

import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.knurt.heinzelmann.util.nebc.BoardUnit;

/**
 * Generate {@link Properties} out of a {@link JSONObject}.
 * 
 * @author Daniel Oltmanns
 * @since 06/07/2011
 */
public class JSONArray2Properties implements BoardUnit<JSONArray, Properties> {

	private boolean isJSONArray(JSONArray obj, int key) {
		boolean result = false;
		try {
			obj.getJSONArray(key);
			result = true;
		} catch (JSONException e) {
		}
		return result;
	}

	@Override
	public Properties process(JSONArray datum) {
		Properties result = new Properties();
		try {
			for (int i = 0; i < datum.length(); i++) {
				if (this.isJSONObject(datum, i)) {
					result.put(i, new JSONObject2Properties().process(datum.getJSONObject(i)));
				} else if (this.isJSONArray(datum, i)) {
					result.put(i, this.process(datum.getJSONArray(i)));
				} else {
					result.put(i, datum.get(i));
				}
			}
		} catch (JSONException e) {
			Logger.getRootLogger().fatal("201106121719");
		}
		return result;
	}

	private boolean isJSONObject(JSONArray obj, int key) {
		boolean result = false;
		try {
			obj.getJSONObject(key);
			result = true;
		} catch (JSONException e) {
		}
		return result;
	}
}
