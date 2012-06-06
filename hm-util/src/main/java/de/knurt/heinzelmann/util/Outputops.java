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
 * Created on Feb 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.knurt.heinzelmann.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import org.apache.commons.io.IOUtils;

/**
 * @author danieloltmanns
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 * 
 *         TODO kill me!
 */
public class Outputops {

	private Vectorops vo = new Vectorops();

	/**
	 * writes the content (strings!) of a vector into the given output and
	 * returns true, if everything worded fine.
	 * 
	 * @param v
	 *            - vector with strings(!) to write into the file
	 * @param f
	 *            - the file to write in
	 * @return true, if everything works fine
	 */
	public boolean writeCharsToFile(Vector<?> v, File f) {
		boolean done = false;
		String[] out = vo.vectorToStringarray(v);
		if (f.exists()) {
			FileWriter fwStream = null;
			PrintWriter pwStream = null;
			BufferedWriter bw = null;
			try {
				fwStream = new FileWriter(f);
				pwStream = new PrintWriter(fwStream);
				bw = new BufferedWriter(pwStream);

				for (int i = 0; i < out.length; i++) {
					IOUtils.write(out[i] + "\n", bw);
				}
				done = true;
			} catch (IOException io) {
				System.out.println(io);
			} finally {
				IOUtils.closeQuietly(fwStream);
				IOUtils.closeQuietly(pwStream);
				IOUtils.closeQuietly(bw);
			}

		} else {
			System.out.println("output doesn't existes");
			done = false;
		}
		return done;
	}
}
