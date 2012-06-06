/*
 * Copyright 2005 - 2011 by KNURT Systeme (http://www.knurt.de)
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
package de.knurt.heinzelmann.util.nebc.bu;

import java.io.ByteArrayOutputStream;
import java.io.File;

import de.knurt.heinzelmann.util.nebc.BoardUnit;

/**
 * generate a byte array from a given file
 * 
 * @author Daniel Oltmanns
 * @since 06/07/2011
 */
public class File2ByteArray implements BoardUnit<File, Byte[]> {

	@Override
	public Byte[] process(File datum) {
		ByteArrayOutputStream os = new File2ByteArrayOutputStream().process(datum);

		byte[] preresult = os.toByteArray();
		Byte[] result = new Byte[preresult.length];
		for (int i = 0; i < preresult.length; i++) {
			result[i] = new Byte(preresult[i]);
		}

		return result;
	}
}
