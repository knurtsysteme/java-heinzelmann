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
/* created on 06.12.2004 */
/**
 *  <b>Dateiname</b>:      Stringops.java<br>
 *  <b>Beschreibung</b>:   some stupid string operations
 *
 *  @author        Daniel Oltmanns (<a href="mailto:danieloltmanns@knurt.de">E-Mail</a>, <a href="http://www.knurt.de">Homepage</a>)
 * @since 0.20041206
 *  @version 0.20091104
 */
package de.knurt.heinzelmann.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @deprecated it's just a nice sin of my youth - dont use it
 * @author danieloltmanns
 */
public class Stringops {

	/**
	 * returns true, if the given string is a valid filename
	 * 
	 * @param filename
	 *            - given string of a file
	 * @return true, if the given string is a valid filename
	 */
	public boolean validFilename(String filename) {
		boolean validInput = true;
		if (filename != null) {
			char[] test = filename.toCharArray();
			for (int i = 0; i < test.length; i++) {
				if (test[i] == '/' || test[i] == '\\' || test[i] == '?' || test[i] == '*' || test[i] == ':' || test[i] == '\"' || test[i] == '<' || test[i] == '>' || test[i] == '|') {
					System.out.println("invalid input. don't use / ? \" \\ * : < > |");
					System.out.println("please try again");
					validInput = false;
				}
			}
		} else {
			validInput = false;
		}
		return validInput;
	}

	/**
	 * returns true, if the given string is a valid float. the float must not
	 * have a "f" after the number
	 * 
	 * @param value
	 * @return
	 */
	public boolean isValidFloat(String value) {
		boolean test1, test2;

		// at least one number before the sign
		Pattern p = Pattern.compile("[0-9]+[.]?[0-9]*");
		Matcher m = p.matcher(value);
		test1 = m.matches();

		// at least one number after the sign
		p = Pattern.compile("[0-9]*[.]?[0-9]+");
		m = p.matcher(value);
		test2 = m.matches();
		return test1 || test2;
	}

	/**
	 * wandelt ein String Array in einen String, wobei jedes Element im Array
	 * eine Zeile ist.
	 * 
	 * @param sta
	 *            - String[], der als String zur�ckgegeben werden soll
	 * @return String - Stringarray als Strinag
	 */
	public static String stringArrayToString(String[] sta) {
		String tmp = "";
		for (int i = 0; i < sta.length; i++) {
			tmp += sta[i] + "\n";
		}
		return tmp;
	}

	/**
	 * generiert aus einem string[] einen string bis zu einem bestimmten punkt
	 * 
	 * @return
	 */
	public static String generateStringBeforeHit(String[] sourcecode, int trefferZeile, int trefferIndex) {
		// array abschneiden
		String[] tmp = new String[trefferZeile + 1];
		for (int i = 0; i <= trefferZeile; i++) {
			tmp[i] = sourcecode[i];
		}
		// letzte zeile beschneiden
		tmp[trefferZeile] = tmp[trefferZeile].substring(0, trefferIndex);
		return stringArrayToString(tmp);
	}

	/**
	 * returns a string that is the result of a cut from "sourcecode" at the hit
	 * number "trefferNr" of the "toSearch"-Text. E.g. sourcecode: xxaxxayyayy
	 * trefferNr: 2 toSearch: a return: xxaxx
	 * 
	 * @return a cutted String
	 */
	public static String generateStringBeforeHit(String sourcecode, int trefferNr, String toSearch) {
		// array abschneiden
		String tmp = "";
		Pattern p = Pattern.compile(toSearch, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		String[] tmpArray = p.split(sourcecode);
		for (int i = 0; i < trefferNr; i++) {
			tmp += tmpArray[i];
		}
		System.out.println(tmp);
		return tmp;
	}

	/**
	 * replaces all given (sub)strings "search" with the given string
	 * "replaceWith" in the given String "toReplace" and returns the given
	 * String "toReplace"
	 * 
	 * @param toReplace
	 *            - the String, that should be changed
	 * @param search
	 *            - the Substring in toReplace, that should be replaced
	 * @param replaceWith
	 *            - the String, that should "search" be replaced with
	 * @return the changed String
	 */
	public static String getReplacedString(String toReplace, String search, String replaceWith) {
		Pattern pLT = Pattern.compile(search);
		Matcher m = pLT.matcher(toReplace);
		toReplace = m.replaceAll(replaceWith);
		return toReplace;
	}

	/**
	 * replaces Chars of the given String so that it can be used in the
	 * xml-database
	 * 
	 * @param toChange
	 *            - the given String
	 * @return a XML compatible String
	 */
	public static String getXmlCompatibleString(String toChange) {
		toChange = Stringops.getReplacedString(toChange, "<", "###LEFT_TAG###");
		toChange = Stringops.getReplacedString(toChange, "/", "###SLASH###");
		toChange = Stringops.getReplacedString(toChange, ">", "###RIGHT_TAG###");
		toChange = Stringops.getReplacedString(toChange, "=", "###RESULT###");
		toChange = Stringops.getReplacedString(toChange, "@", "###AT###");
		return toChange;
	}

	/**
	 * replaces Chars of the given String back from upper method
	 * "getXmlCompatibleString (String toChange)"
	 * 
	 * @param toChange
	 *            - the given String
	 * @return a User compatible String
	 */
	public static String getQQCompatibleString(String toChange) {
		toChange = Stringops.getReplacedString(toChange, "###LEFT_TAG###", "<");
		toChange = Stringops.getReplacedString(toChange, "###SLASH###", "/");
		toChange = Stringops.getReplacedString(toChange, "###RIGHT_TAG###", ">");
		toChange = Stringops.getReplacedString(toChange, "###RESULT###", "=");
		toChange = Stringops.getReplacedString(toChange, "###AT###", "@");
		return toChange;
	}

	/**
	 * generiert aus einem string[] einen string ab einem bestimmten punkt
	 * 
	 * @return
	 */
	public static String generateStringAfterHit(String[] sourcecode, int trefferZeile, int trefferIndex) {
		// array abschneiden
		String[] tmp = new String[sourcecode.length - trefferZeile];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = sourcecode[i + trefferZeile];
		}
		// letzte zeile beschneiden
		tmp[0] = tmp[0].substring(trefferIndex);
		return stringArrayToString(tmp);
	}

	/**
	 * returns a string that is the result of a cut from "sourcecode" at the hit
	 * number "trefferNr" of the "toSearch"-Text. E.g. sourcecode: xxaxxayyayy
	 * trefferNr: 2 toSearch: a return: yyayy
	 * 
	 * @return a cutted String
	 */
	public static String generateStringAfterHit(String sourcecode, int trefferNr, String toSearch) {
		// array abschneiden
		String tmp = "";
		Pattern p = Pattern.compile(toSearch, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		String[] tmpArray = p.split(sourcecode);
		for (int i = trefferNr; i < tmpArray.length; i++) {
			tmp += tmpArray[i];
		}
		return tmp;
	}
	// public static void main(String[] args) {
	// String[] st = { "hallo da bin ich",
	// "wieder mal hier",
	// "und denkCHECKPOINTmir einen text aus",
	// "der auch noch zeilen haben muss" };
	// System.out.println(Stringops.generateStringBeforeHit(st, 2, 7));
	// System.out.println(Stringops.generateStringAfterHit(st, 2, 7));
	// }
}
