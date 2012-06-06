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
 * Created on Apr 13, 2005
 */
package de.knurt.heinzelmann.util.math;

import java.awt.geom.Point2D.Double;
import java.awt.geom.Point2D.Float;

/**
 * @author danieloltmanns
 * @since i was 2 years old
 * @version i was 2 and a half
 */
public class Vectorops {
	public static double getRangeProduct(Double v1, Double v2) {
		// <n, p - p0> - in german: "Skalarprodukt"
		return v1.getX() * v2.getX() + v1.getY() * v2.getY();
	}

	public static Double getDifferenceVector(Double v1, Double v2) {
		return new Double(v1.getX() - v2.getX(), v1.getY() - v2.getY());
	}

	public static float getRangeProduct(Float v1, Float v2) {
		// <n, p - p0> - in german: "Skalarprodukt"
		return (float) (v1.getX() * v2.getX() + v1.getY() * v2.getY());
	}

	public static Float getDifferenceVector(Float v1, Float v2) {
		return new Float((float) (v1.getX() - v2.getX()), (float) (v1.getY() - v2.getY()));
	}

}
