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
package de.knurt.heinzelmann.util.validation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * some util methods for json
 * 
 * @author Daniel Oltmanns
 * @since 06/17/2012
 */
public class JSONValidation {
	/**
	 * return true, if the given key in the given json is a {@link JSONArray}
	 * 
	 * @param json
	 *            object to test
	 * @param key
	 *            to check
	 * @return true, if the given key in the given json is a {@link JSONArray}
	 */
	public static boolean isJSONArray(JSONObject json, String key) {
		// try and error ...
		boolean result = true;
		try {
			json.getJSONArray(key);
		} catch (JSONException e) {
			result = false;
		}
		return result;
	}

	/**
	 * return true, if the given pos in the given json is a {@link JSONArray}
	 * 
	 * @param json
	 *            array to test
	 * @param pos
	 *            to check
	 * @return true, if the given pos in the given json is a {@link JSONArray}
	 */
	public static boolean isJSONArray(JSONArray json, int pos) {
		// try and error ...
		boolean result = true;
		try {
			json.getJSONArray(pos);
		} catch (JSONException e) {
			result = false;
		}
		return result;
	}

	/**
	 * return true, if the given key in the given json is a {@link JSONObject}
	 * 
	 * @param json
	 *            object to test
	 * @param key
	 *            to check
	 * @return true, if the given key in the given json is a {@link JSONObject}
	 */
	public static boolean isJSONObject(JSONObject json, String key) {
		// try and error ...
		boolean result = true;
		try {
			json.getJSONObject(key);
		} catch (JSONException e) {
			result = false;
		}
		return result;
	}

	/**
	 * return true, if the given pos in the given json is a {@link JSONObject}
	 * 
	 * @param json
	 *            array to test
	 * @param pos
	 *            to check
	 * @return true, if the given pos in the given json is a {@link JSONObject}
	 */
	public static boolean isJSONObject(JSONArray json, int pos) {
		// try and error ...
		boolean result = true;
		try {
			json.getJSONObject(pos);
		} catch (JSONException e) {
			result = false;
		}
		return result;
	}

	/**
	 * return true, if the given key in the given json is set
	 * 
	 * @param json
	 *            object to test
	 * @param key
	 *            to check
	 * @return true, if the given key in the given json is set
	 */
	public static boolean isValue(JSONObject json, String key) {
		// try and error ...
		boolean result = true;
		try {
			json.get(key);
		} catch (JSONException e) {
			result = false;
		}
		return result;
	}

	/**
	 * return true, if the given position in the given json is set
	 * 
	 * @param json
	 *            array to test
	 * @param pos
	 *            position to check
	 * @return true, if the given position in the given json is set
	 */
	public static boolean isValue(JSONArray json, int pos) {
		// try and error ...
		boolean result = true;
		try {
			json.get(pos);
		} catch (JSONException e) {
			result = false;
		}
		return result;
	}

	/**
	 * return true, if the given key in the given json is a {@link String}
	 * 
	 * @param json
	 *            object to test
	 * @param key
	 *            to check
	 * @return true, if the given key in the given json is a {@link String}
	 */
	public static boolean isString(JSONObject json, String key) {
		// try and error ...
		boolean result = true;
		try {
			json.getString(key);
		} catch (JSONException e) {
			result = false;
		}
		return result;
	}

	/**
	 * return true, if the given position in the given json is a {@link String}
	 * 
	 * @param json
	 *            object to test
	 * @param pos
	 *            position to check
	 * @return true, if the given position in the given json is a {@link String}
	 */
	public static boolean isString(JSONArray json, int pos) {
		// try and error ...
		boolean result = true;
		try {
			json.getString(pos);
		} catch (JSONException e) {
			result = false;
		}
		return result;
	}
}
