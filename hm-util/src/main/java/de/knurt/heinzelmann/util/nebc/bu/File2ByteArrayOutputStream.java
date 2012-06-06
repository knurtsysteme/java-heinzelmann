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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import de.knurt.heinzelmann.util.nebc.BoardUnit;


/**
 * generate a byte array output stream from a given file
 * 
 * @author Daniel Oltmanns
 * @since 06/07/2011
 */
public class File2ByteArrayOutputStream implements BoardUnit<File, ByteArrayOutputStream> {

	@Override
	public ByteArrayOutputStream process(File datum) {
		try{
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			InputStream is = new FileInputStream(datum.getAbsoluteFile());
			for (int bytee; (bytee = is.read()) != -1;) {
				os.write(bytee);
			}
			is.close();
			os.flush();
			os.close();
			return os;
		} catch (FileNotFoundException e) {
			Logger.getRootLogger().fatal("201106071317");
			return null;
		} catch (IOException e) {
			Logger.getRootLogger().fatal("201106071316");
			return null;
		}
	}
}
