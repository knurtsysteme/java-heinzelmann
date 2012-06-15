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
/* Created on Apr 15, 2005 */
package de.knurt.heinzelmann.util.colors;

import java.awt.Color;
import java.util.Random;

/**
 * RandomColor.java
 * creates (more or less) a <tt>java.awt.Color</tt> at random.
 * there are in some methods possibilities, to give a special
 * stat like hue, saturation, brilliance ...
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @version 0.20091104
 */
public class RandomColor {
    
    /** empty constructor */
    public RandomColor() {}
    
	/** the random component */
    private static Random random = new Random();
	/**
	 * returns a random grayscale color
	 * @return a random grayscale color
	 */
	public Color nextRandomGray() {
		int x = random.nextInt(255);
		return new Color(x,x,x);
	}
	
	/**
	 * returns a random rgb color
	 * @return a random rgb color
	 */
	public Color nextRandomRGB() {
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}
	
	/**
	 * returns a random color with given hue
	 * @param hue - given hue in degrees
	 * @return a random color with given hue
	 * FIXME no, it does not return that
	 */
	public Color nextRandomColorWithHue(int hue) {
	    return new Color(((float)hue/360.f)%1.f, random.nextFloat(), random.nextFloat());
	}
	
	/**
	 * return a random color with given saturation
	 * @param saturation - given saturation in percentage
	 * @return a random color with given saturation
	 * FIXME no, it does not return that
	 */
	public Color nextRandomColorWithSaturation(int saturation) {
	    return new Color(random.nextFloat(), ((float)saturation/100.f)%1.f, random.nextFloat());
	}
	
	/**
	 * returns a random color with given value
	 * @param value - given value in percentage
	 * @return a random color with given value
	 * FIXME no, it does not return that
	 */
	public Color nextRandomColorWithValue(int value) {
	    return new Color(random.nextFloat(), random.nextFloat(), ((float)value/100.f)%1.f);
	}
	
}
