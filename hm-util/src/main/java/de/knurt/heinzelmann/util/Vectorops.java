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
/*
 * Created on Feb 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author danieloltmanns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package de.knurt.heinzelmann.util;

import java.io.File;
import java.util.Vector;

public class Vectorops {

	/**
	 * returns a String-Array with the content of the given Vector
	 * 
	 * @param v
	 *            - vector shall be a string array
	 * @return string array of the vector
	 */
	public String[] vectorToStringarray(Vector<?> v) {
		String[] s = new String[v.size()];
		for (int i = 0; i < v.size(); i++) {
			s[i] = (String) v.get(i);
		}
		return s;
	}

	/**
	 * returns a file-Array with the content of the given Vector
	 * 
	 * @param v
	 *            - vector shall be a file array
	 * @return string array of the vector
	 */
	public File[] vectorToFilearray(Vector<File> v) {
		// puts vector into an file-array
		File[] tmpX = new File[v.size()];
		for (int i = 0; i < v.size(); i++) {
			tmpX[i] = v.get(i);
		}
		return tmpX;
	}

	// public static void main(String[] args) {
	// }
}
