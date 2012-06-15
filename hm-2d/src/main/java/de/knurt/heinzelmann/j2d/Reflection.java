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
/*  Created on Apr 13, 2005 */
package de.knurt.heinzelmann.j2d;

import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.util.Vector;

import de.knurt.heinzelmann.math.Lineops;
import de.knurt.heinzelmann.math.Vectorops;


/**
 * Reflection.java
 * an object, that reflects points at a given line
 * TODO doesn't work!
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @since 0.00 (Apr 13, 2005)
 * @version 0.20091104
 */

public class Reflection {
	/** reflects a point at the given line
	 * TODO this method reflect nonsense (and gives it back)
	 * 
	 * @param p - given point
	 * @param line - given line
	 * @return the reflected point
	 */
	public static Point2D.Double reflectPoint(Point2D.Double p, Line2D line) {
	    
	    // f(x) = gx + m;
		double m = Lineops.getAbsolutePart(line);
		
		// p* = p - 2n < n, p - p0 >
		// p0 = (0, f(0)) -> f(0) = m
		Point2D.Double p0 = new Point2D.Double(0, m);
		// factorN = sqrt( 1/(x^2+y^2))
		double factorN = Math.sqrt( 1/ ( Math.pow( p.getX(), 2 ) + Math.pow( p.getY(), 2) ) );
		Point2D.Double n = new Point2D.Double(factorN*p.getX(), factorN*p.getY());
		
		// <n, p - p0> - in german: "Skalarprodukt"
		Point2D.Double dV = Vectorops.getDifferenceVector(p, p0);
		double rangeProduct = Vectorops.getRangeProduct(n,dV);
		
		// p* = (x,y)
		double x = p.getX() - 2 * n.getX() * rangeProduct;
		double y = p.getY() - 2 * n.getY() * rangeProduct;
		
		return new Point2D.Double(x,y);
	}
	
	/** reflects every single point of the vector at the given line
	 * 
	 * @param v - given vector be reflect
	 * @param line - given line
	 */
	public static Vector reflect(Vector v, Line2D line) {
		for (int i=0; i<v.size(); i++) {
			v.setElementAt(reflectPoint((Point2D.Double)(v.get(i)), line), i);
		}
		return v;
	}
}
