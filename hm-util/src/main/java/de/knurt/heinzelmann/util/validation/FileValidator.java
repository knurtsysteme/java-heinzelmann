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
package de.knurt.heinzelmann.util.validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * secure file operation
 * 
 * @author Daniel Oltmanns
 * @since 1.00 SNAPSHOT (11/08/2010)
 * @version 1.00 SNAPSHOT (11/08/2010)
 */
public class FileValidator {
	/** one and only instance of FileValidator */
	private volatile static FileValidator me;

	/** construct FileValidator */
	private FileValidator() {
		try {
			this.md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
		}
	}

	/**
	 * return the one and only instance of FileValidator
	 * 
	 * @return the one and only instance of FileValidator
	 */
	public static FileValidator getInstance() {
		if (me == null) {
			// ↖ no instance so far
			synchronized (FileValidator.class) {
				if (me == null) {
					// ↖ still no instance so far
					// ↓ the one and only me
					me = new FileValidator();
				}
			}
		}
		return me;
	}

	/**
	 * short for {@link #getInstance()}
	 * 
	 * @return the one and only instance of FileValidator
	 */
	public static FileValidator me() {
		return getInstance();
	}

	public byte[] getChecksum(File file) throws IOException {
		md.reset();
		InputStream is = new FileInputStream(file);
		is = new DigestInputStream(is, md);
		while (is.read(new byte[4096]) != -1);
		byte[] result = md.digest();
		is.close();
		return result;
	}

	private MessageDigest md = null;

	public boolean hasChecksum(File check, byte[] must) throws IOException {
		boolean result = false;
		byte[] digest = this.getChecksum(check);
		result = MessageDigest.isEqual(digest, must);
		return result;
	}
}
