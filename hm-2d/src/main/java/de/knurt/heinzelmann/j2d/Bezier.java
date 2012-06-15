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
/* Created on May 17, 2005 */

package de.knurt.heinzelmann.j2d;

import java.awt.geom.Point2D;

/**
 * Bezier.java
 * creates a bezier path with given points between
 * key-points.
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @version 0.20091104
 */
public abstract class Bezier {
    
    /** points of the bezier-path */
    private Point2D[] points;
    
    /** number of points, shall be compute between 2 keypoints (excl.)*/
    private int numPoints; 

    /**
     * initilizes a new bezier path with
     * the numPoints between the points
     * (means exactness)
     * @param points - discribing the bezier-path
     * @param numPoints - points between the bezierpoints
     */
    public Bezier (Point2D[] points, int numPoints) {
        this.points = points;
        this.numPoints = numPoints;
    }
    
    /**
     * a standard bezier path with an exactness of 100
     * @param points - discribing the bezier-path
     */
    public Bezier(Point2D[] points) {
        this(points, 100);
    }
    
    /**
     * returns an array of the computed points.
     * array can be a Point2D.Float or a Point2D.Double-Array
     * @return an array of the computed points
     */
    public Point2D[] getBezierPoints() {
        if( isFloat(points[0]) ) {
            return getBezierPointsFloat();
        }
        else {
            return getBezierPointsDouble();
        }
    }
    
    /**
     * returns an Point2D.Float-Array of the computed points.
     * @return an Point2D.Float-Array of the computed points
     */
	protected abstract Point2D[] getBezierPointsFloat();
	
	/**
	 * returns an Point2D.Double-Array of the computed points.
	 * @return an Point2D.Double-Array of the computed points
	 */
	protected abstract Point2D[] getBezierPointsDouble();
    
    
    /**
     * returns true, if the given point is a Point2D.Float.
     * if not, it is a Point2D.Double.
     * @param p2d - given Point2D
     * @return true, if p2d is a Point2D.Float and false, if the
     * p2d is a Point2D.Double.
     */
    public boolean isFloat(Point2D p2d) {
        Point2D.Float testFloat = new Point2D.Float();
        if( p2d.getClass() == testFloat.getClass() ) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * returns the numPoints
     * @return Returns the numPoints.
     */
    public int getNumPoints() {
        return numPoints;
    }
    /**
     * The numPoints to set
     * @param numPoints The numPoints to set.
     */
    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }
    
    /**
     * @return Returns the points.
     */
    public Point2D[] getPoints() {
        return points;
    }
    /**
     * @param points The points to set.
     */
    public void setPoints(Point2D[] points) {
        this.points = points;
    }    


}
