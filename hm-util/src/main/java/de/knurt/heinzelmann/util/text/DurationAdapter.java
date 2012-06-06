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
package de.knurt.heinzelmann.util.text;

import java.util.HashMap;
import java.util.Map;

import de.knurt.heinzelmann.util.time.DurationSplitter;

/**
 * get a time duration in words. supported languages: english, german
 * 
 * @author Daniel Oltmanns
 * @since 0.20100723
 * @version 0.20100723
 */
public class DurationAdapter {
	public static enum SupportedLanguage {
		ENGLISH, GERMAN
	};

	private final Map<SupportedLanguage, String[]> timeWords;
	private static final String[] GERMAN_TIME_WORDS = { "Tage", "Tag", "Stunden", "Stunde", "Minuten", "Minute", "und" };
	private static final String[] ENGLISH_TIME_WORDS = { "days", "day", "hours", "hour", "minutes", "minute", "and" };
	private SupportedLanguage language;

	public DurationAdapter(SupportedLanguage language) {
		this.language = language;
		this.timeWords = new HashMap<SupportedLanguage, String[]>();
		this.timeWords.put(SupportedLanguage.GERMAN, GERMAN_TIME_WORDS);
		this.timeWords.put(SupportedLanguage.ENGLISH, ENGLISH_TIME_WORDS);
	}

	public String getText(DurationSplitter ds) {
		String result = "";
		// get single values
		String days = this.getUnitWithTimeUnitWord(ds.getDays(), 0, 1);
		String hours = this.getUnitWithTimeUnitWord(ds.getHours(), 2, 3);
		String minutes = this.getUnitWithTimeUnitWord(ds.getMinutes(), 4, 5);
		// connect values
		if (!days.isEmpty() && hours.isEmpty() && minutes.isEmpty()) {
			result = days;
		} else if (days.isEmpty() && !hours.isEmpty() && minutes.isEmpty()) {
			result = hours;
		} else if (days.isEmpty() && hours.isEmpty() && !minutes.isEmpty()) {
			result = minutes;
		} else if (!days.isEmpty() && !hours.isEmpty() && minutes.isEmpty()) {
			result = String.format("%s %s %s", days, this.getAnd(), hours);
		} else if (!days.isEmpty() && hours.isEmpty() && !minutes.isEmpty()) {
			result = String.format("%s %s %s", days, this.getAnd(), minutes);
		} else if (days.isEmpty() && !hours.isEmpty() && !minutes.isEmpty()) {
			result = String.format("%s %s %s", hours, this.getAnd(), minutes);
		} else if (!days.isEmpty() && !hours.isEmpty() && !minutes.isEmpty()) {
			result = String.format("%s, %s %s %s", days, hours, this.getAnd(), minutes);
		} // everything is empty
		return result;
	}

	private Object getAnd() {
		return this.timeWords.get(this.language)[6];
	}

	private String getUnitWithTimeUnitWord(int units, int pluralIndex, int singleIndex) {
		String result = "";
		if (units > 0) {
			result = units + " ";
			if (units == 1) {
				result += this.getTimeUnitWord(singleIndex);
			} else { // units > 1
				result += this.getTimeUnitWord(pluralIndex);
			}
		}
		return result;
	}

	private String getTimeUnitWord(int i) {
		return this.timeWords.get(this.language)[i];
	}

	public void setLanguage(SupportedLanguage language) {
		this.language = language;
	}

	public String getText(int minutes) {
		return this.getText(new DurationSplitter(minutes));
	}
	
}
