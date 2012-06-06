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
/* Created on Apr 2, 2005 */
package de.knurt.heinzelmann.j2d;

import java.awt.geom.Point2D.Double;
import java.awt.geom.Point2D.Float;
import java.awt.geom.Point2D;
import java.util.Vector;
/**
 * Rotation.java
 * this class rotates objects
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @version 0.20091104
 */
public class Rotation {

	/** returns a point that is the given point turned around by the given angle
	 * 
	 * @param p2r - Double of the point to rotate
	 * @param rotatePoint - Double of the point, the p2r will turn around
	 * @param angle - the angle of the rotation
	 * @return Double of the rotated point
	 */
	public static Double rotatePoint(Double p2r, Double rotatePoint, double angle) {
		double x, y;
		x = Math.cos(angle) * p2r.getX() - Math.sin(angle) * p2r.getY() + rotatePoint.getX() * (1 - Math.cos(angle)) + rotatePoint.getY() * Math.sin(angle);
		y = Math.sin(angle) * p2r.getX() + Math.cos(angle) * p2r.getY() + rotatePoint.getY() * (1 - Math.cos(angle)) - rotatePoint.getX() * Math.sin(angle);
		return new Double(x,y);
	}
	
	/** returns a point that is the given point turned around by the given angle
	 * 
	 * @param p2r - Double of the point to rotate
	 * @param rotatePoint - Double of the point, the p2r will turn around
	 * @param angle - the angle of the rotation
	 * @return Double of the rotated point
	 */
	public static Float rotatePointFloat(Point2D p2r, Point2D rotatePoint, float angle) {
		double x, y;
		x = Math.cos(angle) * p2r.getX() - Math.sin(angle) * p2r.getY() + rotatePoint.getX() * (1 - Math.cos(angle)) + rotatePoint.getY() * Math.sin(angle);
		y = Math.sin(angle) * p2r.getX() + Math.cos(angle) * p2r.getY() + rotatePoint.getY() * (1 - Math.cos(angle)) - rotatePoint.getX() * Math.sin(angle);
		return new Float((float)x,(float)y);
	}
	
	
	/**
	 * rotates all points of a <tt>java.util.Vector</tt>
	 * including <tt>java.awt.geom.Point2D.Double</tt> elements
	 * with the given angle at the given point
	 * @param v - Vector (must include <tt>java.awt.geom.Point2D.Double</tt> elements)
	 * @param rotatePoint - point, to rotate at
	 * @param angle - to rotate with (positive direction)
	 * @return rotated points of the given Point2D.Double elements in the vector
	 */
	public static Vector rotatePoints(Vector v, Double rotatePoint, double angle) {
		for (int i=0; i<v.size(); i++) {
			v.setElementAt(rotatePoint((Double)(v.get(i)), rotatePoint, angle), i);
		}
		return v;
	}
	
	/** returns a point that is the given point turned around by the given angle
	 * 
	 * @param p2r - Float of the point to rotate
	 * @param rotatePoint - Float of the point, the p2r will turn around
	 * @param angle - the angle of the rotation
	 * @return Float of the rotated point
	 */
	public static Float rotatePoint(Float p2r, Float rotatePoint, float angle) {
		float x, y;
		x = (float)(Math.cos(angle) * p2r.getX() - Math.sin(angle) * p2r.getY() + rotatePoint.getX() * (1 - Math.cos(angle)) + rotatePoint.getY() * Math.sin(angle));
		y = (float)(Math.sin(angle) * p2r.getX() + Math.cos(angle) * p2r.getY() + rotatePoint.getY() * (1 - Math.cos(angle)) - rotatePoint.getX() * Math.sin(angle));
		return new Float(x,y);
	}
	
	/**
	 * rotates all points of a <tt>java.util.Vector</tt>
	 * including <tt>java.awt.geom.Point2D.Float</tt> elements
	 * with the given angle at the given point
	 * @param v - Vector (must include <tt>java.awt.geom.Point2D.Float</tt> elements)
	 * @param rotatePoint - point, to rotate at
	 * @param angle - to rotate with (positive direction)
	 * @return rotated points of the given Point2D.Float elements in the vector
	 */
	public static Vector rotatePoints(Vector v, Float rotatePoint, float angle) {
		for (int i=0; i<v.size(); i++) {
			v.setElementAt(rotatePoint((Float)(v.get(i)), rotatePoint, angle), i);
		}
		return v;
	}
	
}
