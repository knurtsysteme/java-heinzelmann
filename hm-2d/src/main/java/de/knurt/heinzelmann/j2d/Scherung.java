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
/*
 * Created on Apr 14, 2005
 */
package de.knurt.heinzelmann.j2d;

import java.awt.geom.Point2D.Double;
import java.util.Vector;

/**
 * @author danieloltmanns
 *
 * sorry for the german name of the class and methoed. i don't know, how it is 
 * called in english when a geom. form e.g. switches from a square to a trapezium. 
 * however, that's what this class does ...
 */
public class Scherung {
	/** scales the given point
	 * 
	 * @param p - given point
	 * @param factorX - double of the pixels resized horizontaly
	 * @param factorY - double of the pixels resized verticaly
	 */
	public static Double scherePoint(Double p, double xDirection, double yDirection) {
		double x, y;
		x = xDirection * p.getY() + p.getX();
		y = yDirection * p.getX() + p.getY();
		return new Double(x, y);
	}
	
	/** scales the given point
	 * 
	 * @param p - given point
	 * @param factor - factor of a similiar scalation
	 */
	public static Double scherePoint(Double p, Double factor) {
		return scherePoint(p, factor.getX(), factor.getY());
	}
	
	/** scales every single point of the vector
	 * 
	 * @param v - given vector be rotated
	 * @param factorX - double of the pixels resized horizontaly
	 * @param factorY - double of the pixels resized verticaly
	 */
	public static Vector schere(Vector v, double xDirection, double yDirection) {
		for (int i=0; i<v.size(); i++) {
			v.setElementAt(scherePoint((Double)(v.get(i)), xDirection, yDirection), i);
		}
		return v;
	}

	/** scales every single point of the vector
	 * 
	 * @param v - given vector be rotated
	 * @param factorX - double of the pixels resized horizontaly
	 * @param factorY - double of the pixels resized verticaly
	 */
	public static Vector schereX(Vector v, double factor) {
		for (int i=0; i<v.size(); i++) {
			v.setElementAt(scherePoint((Double)(v.get(i)), factor, 0), i);
		}
		return v;
	}

	/** scales every single point of the vector
	 * 
	 * @param v - given vector be rotated
	 * @param factorX - double of the pixels resized horizontaly
	 * @param factorY - double of the pixels resized verticaly
	 */
	public static Vector schereY(Vector v, double factor) {
		for (int i=0; i<v.size(); i++) {
			v.setElementAt(scherePoint((Double)(v.get(i)), 0, factor), i);
		}
		return v;
	}


}
