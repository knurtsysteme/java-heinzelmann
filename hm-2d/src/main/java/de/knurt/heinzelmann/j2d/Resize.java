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

import java.awt.geom.Point2D.Double;
import java.awt.geom.Point2D.Float;
import java.util.Vector;

/**
 * Resize.java
 * an object, that scales things
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @version 0.20091104
 */
public class Resize {
	/** scales the given point
	 * 
	 * @param p - given point
	 * @param factorX - double of the pixels resized horizontaly
	 * @param factorY - double of the pixels resized verticaly
	 */
	public static Double resizePoint(Double p, double factorX, double factorY) {
		double x, y;
		x = factorX * p.getX();
		y = factorY * p.getY();
		return new Double(x, y);
	}
	
	/** scales the given point
	 * 
	 * @param p - given point
	 * @param factor - factor of a similiar scalation
	 */
	public static Double resizePoint(Double p, Double factor) {
		return resizePoint(p, factor.getX(), factor.getY());
	}
	
	/** scales the given point at the given point
	 * 
	 * @param p - given point
	 * @param factorX - double of the pixels resized horizontaly
	 * @param factorY - double of the pixels resized verticaly
	 * @param pointX - double of the x coordinate of the point of scale
	 * @param pointY - double of the y coordinate of the point of scale
	 */
	public static Double resizePoint(Double p, double factorX, double factorY, double pointX, double pointY) {
		double x, y;
		x = factorX * p.getX() + pointX * (1 - factorX);
		y = factorY * p.getY() + pointY * (1 - factorY);
		return new Double(x, y);
	}
	
	/** scales the given point p at the given point (pointX, pointY)
	 * 
	 * @param p - given point
	 * @param factorX - float of the pixels resized horizontaly
	 * @param factorY - float of the pixels resized verticaly
	 * @param pointX - float of the x coordinate of the point of scale
	 * @param pointY - float of the y coordinate of the point of scale
	 */
	public static Float resizePoint(Float p, float factorX, float factorY, float pointX, float pointY) {
		float x, y;
		x = (float)(factorX * p.getX() + pointX * (1 - factorX));
		y = (float)(factorY * p.getY() + pointY * (1 - factorY));
		return new Float(x, y);
	}
	
	/** scales the given point at the given point similiar
	 * 
	 * @param p - given point
	 * @param factor - of resize
	 * @param point - to scale at
	 */
	public static Double resizePoint(Double p, Double factor, Double point) {
		return resizePoint(p, factor.getX(), factor.getY(), point.getX(), point.getY());
	}
	
	/** scales every single point of the vector
	 * 
	 * @param v - given vector be rotated
	 * @param factorX - double of the pixels resized horizontaly
	 * @param factorY - double of the pixels resized verticaly
	 */
	public static Vector resize(Vector v, double factorX, double factorY) {
		for (int i=0; i<v.size(); i++) {
			v.setElementAt(resizePoint((Double)(v.get(i)), factorX, factorY), i);
		}
		return v;
	}
	
	/**
	 * scales Vectors, including Point2D.Double points with
	 * the given factor at the given point
	 * @param v - Vector (must include Point2D.Double elements)
	 * @param factorX - factor to scale horizontaly
	 * @param factorY - factor to scale verticaly
	 * @param pointX - x-coordinate of the point to scale on
	 * @param pointY - y-coordingat of the point to scale on
	 * @return Vector with scaled Point2D.Double points
	 */
	public static Vector resize(Vector v, double factorX, double factorY, double pointX, double pointY) {
		for (int i=0; i<v.size(); i++) {
			v.setElementAt(resizePoint((Double)(v.get(i)), factorX, factorY, pointX, pointY), i);
		}
		return v;
	}

	/**
	 * scales Vectors, including Point2D.Float points with
	 * the given factor at the given point
	 * @param v - Vector (must include Point2D.Float elements)
	 * @param factorX - factor to scale horizontaly
	 * @param factorY - factor to scale verticaly
	 * @param pointX - x-coordinate of the point to scale on
	 * @param pointY - y-coordingat of the point to scale on
	 * @return Vector with scaled Point2D.Float points
	 */
	public static Vector resize(Vector v, float factorX, float factorY, Float atP) {
		for (int i=0; i<v.size(); i++) {
			v.setElementAt(resizePoint((Float)(v.get(i)), factorX, factorY, (float)atP.getX(), (float)atP.getY()), i);
		}
		return v;
	}

}
