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
package de.knurt.heinzelmann.util.graphics.text;

import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.List;

/**
 * split strings at a given width using {@link FontMetrics#getWidth}
 * 
 * @author Daniel Oltmanns
 * @since 13.10.2009
 * @version 0.20091104
 */
public class TextSplitterOnWidth implements TextSplitter {

	private int maxLength = 10;
	private Character delimiter = ' ';
	private StringMetrics metrics;

	public TextSplitterOnWidth(int maxLength, Character delimiter, StringMetrics metrics) {
		if (maxLength > 0) {
			this.maxLength = maxLength;
		}
		if (delimiter != null) {
			this.delimiter = delimiter;
		}
		this.metrics = metrics;
	}

	/**
	 * return a text splittet to max length of chars and at delimiter. if raw is
	 * null, return null. if a word is longer then maxLength, take it without a
	 * break.
	 * 
	 * @param raw
	 *            to split
	 * @return a text splittet to max length of chars and at delimiter.
	 */
	@Override
	public List<String> split(String raw) {
		List<String> result = null;
		if (raw != null && !raw.isEmpty()) {
			result = new ArrayList<String>();
			if (metrics.getWidth(raw.trim()) > this.maxLength) {
				String[] words = raw.trim().split(delimiter.toString());
				int i = 0;
				String row_tmp = "";
				List<String> wordsInRow = new ArrayList<String>();
				while (i < words.length) {
					row_tmp += words[i] + this.delimiter;
					if (metrics.getWidth(row_tmp) < this.maxLength) {
						wordsInRow.add(words[i]);
					} else {
						String row_result = "";
						for (String word : wordsInRow) {
							row_result += word + this.delimiter;
						}
						if (row_result.isEmpty()) { // one word is too large
							result.add(row_tmp); // take it without a cut
							wordsInRow = new ArrayList<String>();
						} else {
							result.add(row_result.substring(0, row_result.length() - 1));
							wordsInRow = new ArrayList<String>();
							wordsInRow.add(words[i]);
							row_tmp = words[i] + this.delimiter;
						}
					}
					i++;
				}
				if (!row_tmp.isEmpty()) {
					result.add(row_tmp.substring(0, row_tmp.length() - 1));
				}
			} else {
				result.add(raw);
			}
		}
		return result;

	}
}
