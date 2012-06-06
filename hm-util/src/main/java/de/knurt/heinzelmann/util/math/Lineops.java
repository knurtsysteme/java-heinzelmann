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
package de.knurt.heinzelmann.util.math;

/**
 * @author danieloltmanns
 * @since i was 2 years old
 * @version i was 2 and a half
 */
import java.awt.geom.Line2D;

public class Lineops {

	/**
	 * returns the g of f(x) = gx + m with the given line
	 * 
	 * @param line
	 *            - the given
	 * @return the g of f(x) = gx + m with the given line
	 */
	public static double getGradient(Line2D line) {
		if (line.getX2() > line.getX1())
			return (line.getY2() - line.getY1()) / (line.getX2() - line.getX1());
		else
			return (line.getY1() - line.getY2()) / (line.getX1() - line.getX2());
	}

	/**
	 * returns the m of f(x) = gx + m with the given line
	 * 
	 * @param line
	 *            - the given
	 * @return the m of f(x) = gx + m with the given line
	 */
	public static double getAbsolutePart(Line2D line) {
		return line.getY1() - getGradient(line) * line.getX1();
	}

}
