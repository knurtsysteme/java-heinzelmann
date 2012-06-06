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

import java.awt.geom.Point2D.Float;
import java.util.Vector;

/**
 * Polygon.java
 * a Vector including Points, discribing a polygon
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @version 0.20091104
 */
public class Polygon extends Vector {
	
	/** a default polygon at default position
	 *  and a default size
	 */
	public Polygon() {
		this(0,0,1);
	}
	/** a default polygon at given position
	 *  and with a given size
	 * 
	 * @param transX - x position
	 * @param transY - y position
	 * @param scale - size (1 means 100 %)
	 */
	public Polygon(int transX, int transY, double scale) {
		this.add(new Float((float)((-50+transX)*scale),(float)((50+transY)*scale)));
		this.add(new Float((float)((20+transX)*scale),(float)((100+transY)*scale)));
		this.add(new Float((float)((60+transX)*scale),(float)((20+transY)*scale)));
		this.add(new Float((float)((0+transX)*scale),(float)((300+transY)*scale)));
	}
	/** a polygon with given coordinates
	 * 
	 * @param points - coordinates of the polygon
	 */
	public Polygon(Float[] points) {
		for(int i=0; i<points.length; i++) {
			this.add(new Float((float)points[i].getX(), (float)points[i].getY()));
		}
	}

	/** returns the x coordinate of the
	 *  point at the given position. if position is
	 *  not existing, it returns 0
	 * 
	 * @param i - position
	 * @return the x coordinate of the point at the given position
	 */
	public double getX(int i) {
		if(i<this.size())
			return ((Float)(this.get(i))).getX();
		else
			return 0; // point is invalid
	}
	
	/** returns the y coordinate of the
	 *  point at the given position. if position is
	 *  not existing, it returns 0
	 * 
	 * @param i - position
	 * @return the y coordinate of the point at the given position
	 */
	public double getY(int i) {
		if(i<this.size())
			return ((Float)(this.get(i))).getY();
		else
			return 0; // point is invalid
	}
	
	/** returns the center point of the polygon
	 * 
	 * @return Float of the center point of the polygon
	 */
	public Float getCenterPoint() {
		double xMin = getMinX();
		double yMin = getMinY();
		double xMax = getMaxX();
		double yMax = getMaxY();

		return new Float((float)(xMax+xMin)/2, (float)(yMax+yMin)/2);
	}
	
	/**
	 * returns the lowest x-coordinate of the polygon
	 * @return the lowest x-coordinate of the polygon
	 */
	public double getMinX() {
		double xMin = getX(0);
		for(int i=0; i<this.size(); i++) {
			if(xMin > getX(i))
				xMin = getX(i);
		}
		return xMin;
	}
	
	/**
	 * returns the heighest x-coordinate of the polygon
	 * @return the heighest x-coordinate of the polygon
	 */
	public double getMaxX() {
		double xMax = getX(0);
		for(int i=0; i<this.size(); i++) {
			if(xMax < getX(i))
				xMax = getX(i);
		}
		return xMax;
	}
	
	/**
	 * returns the lowest y-coordinate of the polygon
	 * @return the lowest y-coordinate of the polygon
	 */
	public double getMinY() {
		double yMin = getY(0);
		for(int i=0; i<this.size(); i++) {
			if(yMin > getY(i))
				yMin = getY(i);
		}
		return yMin;
	}
	
	/**
	 * returns the heighest y-coordinate of the polygon
	 * @return the heighest y-coordinate of the polygon
	 */
	public double getMaxY() {
		double yMax = getY(0);
		for(int i=0; i<this.size(); i++) {
			if(yMax < getY(i))
				yMax = getY(i);
		}
		return yMax;
	}
	
	/** returns the height of the polygon
	 * 
	 * @return double of the height of the polygon
	 */
	public double getHeight() {
		double yMin = getMinY();
		double yMax = getMaxY();
		return Math.abs(yMax-yMin);
	}

	/** returns the width of the polygon
	 * 
	 * @return double of the width of the polygon
	 */
	public double getWidth() {
		double xMin = getMinX();
		double xMax = getMaxX();
		return Math.abs(xMax-xMin);
	}
	
	/**
	 * returns every x-coordinate (of all points)
	 * in an int-array
	 * @return an int-array of all x-coordinates
	 */
	public int[] getXPoints() {
		int[] xP = new int[this.size()];
		for (int i=0; i<xP.length; i++) {
			xP[i] = (int)((Float)(this.get(i))).getX();
		}
		return xP;
	}
	/**
	 * returns every y-coordinate (of all points)
	 * in an int-array
	 * @return an int-array of all y-coordinates
	 */
	public int[] getYPoints() {
		int[] yP = new int[this.size()];
		for (int i=0; i<yP.length; i++) {
			yP[i] = (int)((Float)(this.get(i))).getY();
		}
		return yP;
	}
	/**
	 * the size of all points discribing the poygon
	 * @return the size of all points discribing the poygon
	 */
	public int getPointCount() {
		return this.size();
	}

}
