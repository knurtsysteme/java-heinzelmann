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
/*
 * Created on Mar 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.knurt.heinzelmann.util.math;

import java.awt.geom.Point2D;

/**
 * @author danieloltmanns
 * @since i was 2 years old
 * @version i was 2 and a half
 */
public class Mathops {

	/**
	 * this method returns +1, if given int is positive, 0 if given int is 0,
	 * else -1
	 * 
	 * @param i
	 *            - given int
	 * @return +1, if i is positive, 0 if i is 0, else -1
	 */
	public static int sign(int i) {
		if (i < 0) {
			return -1;
		} else if (i == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * returns a fraction with the absolute smaller number as "1" and the other
	 * as a rounded int. it is int[0]/int[1]. if one auf the params is 0, it
	 * returns int[0]=int[1]=0
	 * 
	 * @return
	 */
	public static int[] fractionRounded2One(int x, int y) {
		float tmpRes;
		int[] res = new int[2];
		// to avoid a nullpointer exception
		if (x == 0 || y == 0) {
			res[0] = 0;
			res[1] = 0;
		} else {
			// bigger value devide by smaller value
			if (Math.abs(x) > Math.abs(y)) {
				tmpRes = (float) x / (float) y;
				res[0] = Math.round(tmpRes);
				res[1] = 1;
			} else {
				tmpRes = (float) y / (float) x;
				res[0] = 1;
				res[1] = Math.round(tmpRes);
			}
		}
		return res;
	}

	/**
	 * returns the length of the hypotenuse of a right-angled triangle ala
	 * pythagoras. if the two points are a vertical or horizontal line, it
	 * returns NaN.
	 * 
	 * @param a
	 *            point 1 of hypotenuse
	 * @param b
	 *            point 2 of hypotenuse
	 * @return length of the hypotenuse of a right-angled triangle
	 */
	public static double getPythagorasHypotenuse(Point2D p1, Point2D p2) {
		double a = Math.abs(p1.getX() - p2.getX());
		double b = Math.abs(p1.getY() - p2.getY());
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}

	/**
	 * returns the angle between the two points in rad. imagine a horizontal
	 * line (angle = 0) goes through the point p1 - this method gives back the
	 * needed angle to rotate the line in a (positive) way through p1, so that
	 * the line goes through both points.
	 * 
	 * @param p1
	 *            - first point of line and rotate point
	 * @param p2
	 *            - secont point
	 * @return the angle needed to rotate a 0 rad line positive through p1, so
	 *         that the line goes through p2.
	 */
	public static double getAngle(Point2D p1, Point2D p2) {
		// sugests a positive angle a with 0 <= a <= PI/2
		double a = p2.getX() - p1.getX();
		double b = p2.getY() - p1.getY();
		// first get the angle of this section
		double angle;
		if (Math.abs(a) > 0.001 && Math.abs(b) > 0.001) {
			if (a > 0 && b > 0 || a < 0 && b < 0)
				// tan alpha = offside / ankatede <=> alpha =
				// atan(offside/ankatede)
				angle = Math.atan(Math.abs(b) / Math.abs(a));
			else
				angle = Math.atan(Math.abs(a) / Math.abs(b));
		} else if (Math.abs(a) > 0.001) { // arrow is nearly horizontal
			if (p1.getX() < p2.getX())
				angle = 0;
			else
				angle = Math.PI;
		} else { // arrow is nearly vertical
			if (p1.getY() < p2.getY())
				angle = Math.PI / 2;
			else
				angle = Math.PI / 2 * 3;
		}

		// now have a look at the complete angle
		if (a < -0.01f && b > 0) { // => PI/2 < a <= PI means arrow points
									// north-west
			angle += Math.PI / 2;
		} else if (a < 0 && b < -0.01f) { // => PI < a <= 3*PI/2 means arrow
											// points south-west
			angle += Math.PI;
		} else if (a > 0.01f && b < 0) { // => 3*PI/2 < a <= 2*PI means arrow
											// points south-east
			angle += 3 * (Math.PI / 2);
		}
		return angle;

	}

}
