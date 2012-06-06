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

/* Created on Apr 20, 2005 */
/**
 * ColorMath.java
 * computes things with colors (like hue, saturation) or
 * compares to colors.
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @version 0.20091104
 */
package de.knurt.heinzelmann.util.colors;

import java.awt.Color;

public class ColorMath {

	/** one and only instance of me */
	private volatile static ColorMath me;

	/** construct me */
	private ColorMath() {
	}

	/**
	 * return the one and only instance of ColorMath
	 * 
	 * @return the one and only instance of ColorMath
	 */
	public static ColorMath getInstance() {
		if (me == null) { // no instance so far
			synchronized (ColorMath.class) {
				if (me == null) { // still no instance so far
					me = new ColorMath(); // the one and only
				}
			}
		}
		return me;
	}

	/**
	 * returns true, if col1 is brighter than col2
	 * 
	 * @param col1
	 * @param col2
	 * @return true, if col1 is brighter than col2
	 */
	public boolean isBrighter(Color col1, Color col2) {
		throw new UnsupportedOperationException("not implemented yet");
	}

	public float getHue(Color color) {
		float[] res = getHSB(color);
		return res[0];
	}

	public float getSaturation(Color color) {
		float[] res = getHSB(color);
		return res[1];
	}

	public float getValue(Color color) {
		float[] res = getHSB(color);
		return res[2];
	}

	public float[] getHSB(Color color) {
		return Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
	}

	public String getHex(Color color) {
		return Integer.toHexString(color.getRGB()).substring(2);
	}
}
