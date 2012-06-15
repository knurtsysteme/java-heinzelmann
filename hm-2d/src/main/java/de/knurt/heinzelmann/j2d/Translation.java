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
/* Created on Apr 2, 2005 */
package de.knurt.heinzelmann.j2d;

import java.awt.geom.Point2D.Float;
import java.util.Vector;

/**
 * Translation.java
 * translate points
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @version 0.20091104
 */
public class Translation {

	/** scales every single point of the vector
	 * 
	 * @param v - given vector be rotated
	 * @param factorX - int of the pixels moved horizontaly
	 * @param factorY - int of the pixels moved verticaly
	 */
	public static Vector translate(Vector v, int factorX, int factorY) {
		return translate(v, (float)factorX, (float)factorY);
	}
	/** scales every single point of the vector
	 * 
	 * @param v - given vector be rotated
	 * @param factorX - float of the pixels moved horizontaly
	 * @param factorY - float of the pixels moved verticaly
	 */
	public static Vector translate(Vector v, float factorX, float factorY) {
		for (int i=0; i<v.size(); i++) {
			v.setElementAt(transPoint((Float)(v.get(i)), factorX, factorY), i);
		}
		return v;
	}

	/** scales the given point
	 * 
	 * @param p - given point
	 * @param factorX - float of the pixels moved horizontaly
	 * @param factorY - float of the pixels moved verticaly
	 */
	public static Float transPoint(Float p, float factorX, float factorY) {
		float x, y;
		x = (float)(p.getX() + factorX);
		y = (float)(p.getY() + factorY);
		return new Float(x, y);
	}
	
}
