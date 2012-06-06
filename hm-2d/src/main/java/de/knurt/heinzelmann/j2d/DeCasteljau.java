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
/* Created on May 17, 2005 */
/* Changed on May 17, 2005 */
package de.knurt.heinzelmann.j2d;

import java.awt.geom.Point2D;

/**
 * DeCasteljau.java
 * create a bezier-path with the given numbers of points between
 * the start and target point. you can give a point array of a
 * length of x, it will have x-2 support points then.
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @version 0.20091104
 */
public class DeCasteljau extends Bezier {

    /**
     * sets the parameter for the bezier-path
     * @param points - keypoints for the path. points[0] is start point, points[points.length-1] is 
     * target point. points between are support points.
     * @param numPoints - number of points, shall be compute between 2 keypoints (excl.)
     */
    public DeCasteljau(Point2D[] points, int numPoints) {
        super(points, numPoints);
    }
    
    /**
     * returns an Point2D.Float-Array of the computed points.
     * @return an Point2D.Float-Array of the computed points
     */
    protected Point2D[] getBezierPointsFloat() {
        // the width of each step
        float deltaT = 1.f/(getNumPoints()+2.f);
        // whole width
        float u = deltaT;
        // support points
        int n = getPoints().length-1;
        // computed points
        Point2D[] result = new Point2D[getNumPoints()+2]; // given back result
        // add first and last point
        result[0] = getPoints()[0];
        result[result.length-1] = getPoints()[getPoints().length-1];
        // to work with
        Point2D[] tmp = new Point2D.Float[n]; // needed for temporary computes

        // for every step
        for (int k=1; k<getNumPoints()+1; k++) { // while u is in range of [deltaT,1-deltaT]
            for(int j=0; j<n; j++) { // for every given point
                for(int i=0; i<n-j; i++) { // compute point at u
                    if(j==0) {
                        tmp[i] = new Point2D.Float( 
                                (float)( (1-u)*getPoints()[i].getX() + u*getPoints()[i+1].getX() ),
                                (float)( (1-u)*getPoints()[i].getY() + u*getPoints()[i+1].getY() )
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
    
    
    
    /* (non-Javadoc)
     * @see tk.danieloltmanns.www.j2d.Bezier#getBezierPointsDouble()
     */
    protected Point2D[] getBezierPointsDouble() {
        // TODO Auto-generated method stub
        return null;
    }
}
