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
 * BezierSimpleUnion.java
 * creates a simple bezier path with one support point for any line point
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @version 0.20091104
 */
public class BezierSimpleUnion extends Bezier {

    /** the point array of the points on the line */
    private Point2D[] pathP; 
    /** the point array of the support points */
    private Point2D[] supP; 
    
    /**
     * initilizes a bezier path with the given numPoints (exactness)
     * and given points on the line (pathP) and its support points (supP).
     * every pathP has the support point in supP at the same
     * position in the array. if the support position is null, it has no
     * support point - that means, pathP = supP. if a position in the
     * path array is null, the position is set to point 0,0.
     * both arrays must have the same length.
     * important as well: both arrays must have the same class -
     * means, both must be Point2D.Double or both must be Point2D.Float.
     * @param numPoints
     * @param pathP - point array of the points on the line
     * @param supP - point array of the support points
     */
    public BezierSimpleUnion(Point2D[] pathP, Point2D[] supP, int numPoints) {
        super(null, numPoints); // this bezier form doesn't have any points
        // if arrays doesn't have the same length, print an error line
        if(pathP.length != supP.length) {
            System.err.println("error in BezierSimpleUnion: both arrays must have the same size!");
        }
        // if arrays arent the same class
        if(pathP[0].getClass() != supP[0].getClass()) {
            System.err.println("error in BezierSimpleUnion: both arrays must be the same class!");
        }
        // control values in arrays
        for(int i=0; i<pathP.length; i++) {
            // control pathPoints values
            if(pathP[i] == null) {
                if(isFloat(pathP[i])) {
                    pathP[i] = new Point2D.Float(0,0);
                }
                else {
                    pathP[i] = new Point2D.Double(0,0);
                }
            }
            // control support points values
            if(supP[i] == null) {
                supP[i] = pathP[i];
            }
        }
        this.pathP = pathP;
        this.supP = supP;
    }

    /**
     * constructor for a Bezier without any points
     * @param numPoints
     */
    public BezierSimpleUnion(int numPoints) {
        super(null, numPoints);
    }
    

    /* (non-Javadoc)
     * @see tk.danieloltmanns.www.j2d.Bezier#getBezierPointsDouble()
     */
    protected Point2D[] getBezierPointsDouble() {
        // TODO Auto-generated method stub
        return null;
    }
    /* (non-Javadoc)
     * @see tk.danieloltmanns.www.j2d.Bezier#getBezierPointsFloat()
     */
    protected Point2D[] getBezierPointsFloat() {
        /* the total numbers of points in the path.
         * getNumPoints doesn't count the first and the last.
         * the very last is extra (+1) */
        int lengthOfAllPoints = (pathP.length-1) * (getNumPoints()+1) + 1;
        // the result array with every single point
        Point2D[] allPoints = new Point2D[lengthOfAllPoints];
        // array with the arrays of the points between two points
        Point2D[][] section = new Point2D[pathP.length-1][getNumPoints()+1];
        
        // add first section
        section[0] = getBezierPointsFloat(pathP[0], supP[0], pathP[1], supP[1]);
        
        for(int i=1; i<pathP.length-1; i++) {
            Point2D.Float q1 = Rotation.rotatePointFloat(supP[i], pathP[i], (float)Math.PI);
            // TODO didn't work?!:
            //Point2D.Float q1 = getOpsPFloat(pathP[i+1], pathP[i], supP[i]);
            section[i] = getBezierPointsFloat(pathP[i], q1, pathP[i+1], supP[i+1]);
        }
        
        // fill section in all Points-Array
        int x = 0;
        for(int i=0; i<section.length; i++) {
            for(int j=0; j<section[i].length; j++) {
                allPoints[x] = section[i][j];
                x++;
            }
        }
        // last point must be add manualy
        allPoints[x] = pathP[pathP.length-1];
        
        System.out.println(allPoints.length + " x: "+x);

        for(int i=0; i<allPoints.length; i++) {
            System.out.println(allPoints[i]);
        }
        
        return allPoints;
    }

    /**
     * returns the (support-)point mirrored at the
     * path-point (intersection with tangent).
     * TODO seems as it doesn't work
     * @param q3 - support point to mirror
     * @param p3 - next path point
     * @param p2 - path point
     * @return a mirrored point
     */
    private Point2D.Float getOpsPFloat(Point2D q3, Point2D p3, Point2D p2) {
        return new Point2D.Float(
                (float)(q3.getX() + (p3.getX() - p2.getX())),
                (float)(q3.getY() + (p3.getY() - p2.getY()))
        	);
    }
    
    /**
     * returns all points of a bezier path laying
     * between start and end, having sup_start and
     * sup_end as supporting points in a
     * Point2D.Float-Array
     * @param start - first path point 
     * @param sup_start - support point of start
     * @param end - second path point
     * @param sup_end - support point of end
     * @return <tt>tk.danieloltmanns.www.j2d.Bezier#numPoints</tt> points 
     * between the given start point and the give end point
     */
    public Point2D[] getBezierPointsFloat(Point2D start, Point2D sup_start, Point2D end, Point2D sup_end) {
        // array to work with
        Point2D[] points = new Point2D[4];
        points[0] = start;
        points[1] = sup_start;
        points[2] = sup_end;
        points[3] = end;
        
        // the width of each step
        float deltaT = 1.f/(getNumPoints()+2.f);
        // whole width
        float u = deltaT;
        // computed points
        Point2D[] result = new Point2D[getNumPoints()+1]; // given back result
        // add first point
        result[0] = start;
        // to work with
        Point2D[] tmp = new Point2D.Float[3]; // needed for temporary computes

        // for every step
        for (int k=1; k<getNumPoints()+1; k++) { // while u is in range of [deltaT,1-deltaT]
            for(int j=0; j<3; j++) { // for every given point
                for(int i=0; i<3-j; i++) { // compute point at u
                    if(j==0) {
                        tmp[i] = new Point2D.Float( 
                                (float)( (1-u)*points[i].getX() + u*points[i+1].getX() ),
                                (float)( (1-u)*points[i].getY() + u*points[i+1].getY() )
                                );
                    }
                    else {
                        tmp[i] = new Point2D.Float( 
                                (float)( (1-u)*tmp[i].getX() + u*tmp[i+1].getX() ),
                                (float)( (1-u)*tmp[i].getY() + u*tmp[i+1].getY() )
                                );
                    }
                }
            }
            result[k] = tmp[0];
            u += deltaT;
        }
        return result;
    }
    
    /**
     * returns an array of pathP and supP with pathP as
     * the even elements and supP as its odd.
     * TODO at work!
     */
    public Point2D[] getPoints() {
        Point2D[] p = new Point2D[pathP.length + supP.length];
        // TODO make one of two arrays
        System.err.println("method is a construction area and will return a null-array");
        return p;
    }
}
