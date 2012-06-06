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
package de.knurt.heinzelmann.util;

import java.util.Locale;

/**
 * return the number with the ordinal in the given locale.
 * this is impossible for all locales and grammatical cases!
 * but hey - someone have to start.
 * {@see http://forums.sun.com/thread.jspa?threadID=5233189}
 *
 * Supporting following languages:
 * <ul>
 * <li>English</li>
 * </ul>
 *
 * @author Daniel Oltmanns
 * @since 0.20091107
 * @version 0.20091107
 */
public class IntegerNumeralFormat {

    private Locale locale;
    /** 
     * construct the integer
     * @param locale this is for
     */
    public IntegerNumeralFormat(Locale locale){
        this.locale = locale;
    }

    /**
     * return the ordinal of the given int value.
     * on englisch, it is "1st", "2nd", "3rd", "4th".
     * on other languages, this may return "Erste", "Zweite", Dritte", "Zwanzigste".
     * I know, this is a method to work on the entire life. This is why just a "."
     * is added to the number on unknown languages or on languages, where more data is needed
     * (like a grammatical case).
     * @param value
     * @return
     */
    public String format(int value) {
        String result = "";
        if(locale.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
            int last = value % 10;
            switch(last) {
                case 1:
                    result = value + "st";
                    break;
                case 2:
                    result = value + "nd";
                    break;
                case 3:
                    result = value + "rd";
                    break;
                default:
                    result = value + "th";
                    break;
            }
        } else { // language not supported
            result = value + ".";
        }
        return result;
    }


}
