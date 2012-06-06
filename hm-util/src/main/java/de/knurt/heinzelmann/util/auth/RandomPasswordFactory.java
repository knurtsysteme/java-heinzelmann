/*
 * Copyright 2009 by KNURT Systeme (http://www.knurt.de)
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
package de.knurt.heinzelmann.util.auth;

import java.util.Random;

/**
 * produce passwords.
 * 
 * @author Daniel Oltmanns
 * @since 0.20101202
 * @version 0.20101202
 */
public class RandomPasswordFactory {

	public String getPassword(int length) {
		char[] possibleChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
				'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '_', '-', '.', '+', ',', '#' };
		return this.getPassword(length, possibleChars);
	}

	public String getPassword(int length, char[] possibleChars) {
		String result = "";
		Random random = new Random();
		while (result.length() < length) {
			result += possibleChars[random.nextInt(possibleChars.length)];
		}
		return result;
	}

	public String getPassword() {
		return this.getPassword(13);
	}

	/** one and only instance of RandomPasswordFactory */
	private volatile static RandomPasswordFactory me;

	/** construct RandomPasswordFactory */
	private RandomPasswordFactory() {
	}

	/**
	 * return the one and only instance of RandomPasswordFactory
	 * 
	 * @return the one and only instance of RandomPasswordFactory
	 */
	public static RandomPasswordFactory getInstance() {
		if (me == null) {
			// ↖ no instance so far
			synchronized (RandomPasswordFactory.class) {
				if (me == null) {
					// ↖ still no instance so far
					// ↓ the one and only me
					me = new RandomPasswordFactory();
				}
			}
		}
		return me;
	}

	/**
	 * short for {@link #getInstance()}
	 * 
	 * @return the one and only instance of RandomPasswordFactory
	 */
	public static RandomPasswordFactory me() {
		return getInstance();
	}
}
