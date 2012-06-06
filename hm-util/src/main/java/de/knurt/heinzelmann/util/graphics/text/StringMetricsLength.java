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
package de.knurt.heinzelmann.util.graphics.text;


/**
 * return String#length()
 * 
 * @author Daniel Oltmanns
 * @since 13.10.2009
 * @version 0.20091104
 */
public class StringMetricsLength implements StringMetrics {

	/** one and only instance of me */
	private volatile static StringMetricsLength me;

	/** construct me */
	private StringMetricsLength() {
	}

	/**
	 * return the one and only instance of StringMetricsLength
	 * 
	 * @return the one and only instance of StringMetricsLength
	 */
	public static StringMetricsLength getInstance() {
		if (me == null) { // no instance so far
			synchronized (StringMetricsLength.class) {
				if (me == null) { // still no instance so far
					me = new StringMetricsLength(); // the one and only
				}
			}
		}
		return me;
	}

	@Override
	public int getWidth(String raw) {
		return raw.length();
	}
}
