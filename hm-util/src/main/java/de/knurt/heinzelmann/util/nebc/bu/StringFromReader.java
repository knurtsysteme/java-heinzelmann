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
 */package de.knurt.heinzelmann.util.nebc.bu;

import java.io.BufferedReader;
import java.io.IOException;

import de.knurt.heinzelmann.util.nebc.BoardUnit;

/**
 * return the raw data (mostly the post data) that is part of the request.
 * 
 * @author Daniel Oltmanns
 * @since 02/22/2012
 */

public class StringFromReader implements BoardUnit<BufferedReader, String> {
	private int readAheadLimit = 10000;

	/**
	 * construct it with a default readAheadLimit of 10000 characters
	 */
	public StringFromReader() {
		this(10000);
	}

	/**
	 * construct it with a readAheadLimit
	 * 
	 * @param readAheadLimit
	 *            {@link BufferedReader#mark(int)}
	 */
	public StringFromReader(int readAheadLimit) {
		this.readAheadLimit = readAheadLimit;
	}

	@Override
	public String process(BufferedReader reader) {
		StringBuilder result = new StringBuilder();
		try {
			reader.mark(this.readAheadLimit);
			String line;
			do {
				line = reader.readLine();
				result.append(line).append("\n");
			} while (line != null);
			reader.reset();
			return result.toString();
		} catch (IOException e) {
			return null;
		}
	}
}
