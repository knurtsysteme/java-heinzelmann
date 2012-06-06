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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.swing.JOptionPane;

/*
 * Created on Feb 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * @author danieloltmanns
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ConsoleInputops {

	/**
	 * asks for an input from the console
	 * 
	 * @return string from the console
	 */
	public String readStringFromConsole() {
		InputStreamReader isr1 = new InputStreamReader(System.in);
		BufferedReader br1 = new BufferedReader(isr1);
		String input = "";
		try {
			input = br1.readLine();
		} catch (IOException io) {
			System.out.println("IOException: " + io);
		}
		return input;
	}

	/**
	 * reads a string from the console and tests, if the input is a valid input
	 * for a filename. if not, it asks again.
	 * 
	 * @return a valid filename from the console
	 */
	public String readFilenameFromConsole() {
		InputStreamReader isr1 = new InputStreamReader(System.in);
		BufferedReader br1 = new BufferedReader(isr1);
		String filename = "";
		try {
			filename = br1.readLine();
		} catch (IOException io) {
			System.out.println("IOException: " + io);
		}

		char[] test = filename.toCharArray();
		boolean validInput = true;
		for (int i = 0; i < test.length; i++) {
			if (test[i] == '/' || test[i] == '\\' || test[i] == '?' || test[i] == '*' || test[i] == ':' || test[i] == '\"' || test[i] == '<' || test[i] == '>' || test[i] == '|') {
				System.out.println("invalid input. don't use / ? \" \\ * : < > |");
				System.out.println("please try again");
				validInput = false;
			}
		}

		if (validInput) {
			return filename;
		} else {
			return readFilenameFromConsole();
		}
	}

	/**
	 * reads an input from an option pane and controls, if there are invalid
	 * chars (for a filename) in it (e.g. / ? ...)
	 * 
	 * @param message
	 *            - for the fileinput
	 * @return a valid String for a filename
	 */
	@SuppressWarnings("deprecation") // TODO use a validation class for depreciated Stringops
	public String readFilenameFromOptionPane(String message) {
		String fName = JOptionPane.showInputDialog(message);
		if (fName == null) {
			return null;
		}
		Stringops so = new Stringops();
		if (so.validFilename(fName)) {
			return fName;
		} else {
			return readFilenameFromOptionPane("invalid input. don't use / ? \" \\ * : < > |\nplease try again: ");
		}

	}

	/**
	 * reads characters from a given file-input
	 * 
	 * @param path
	 *            - file to read from
	 * @return vector with strings from the given file characters as its content
	 */
	public Vector<String> readCharsFromFile(File path) {
		Vector<String> st = new Vector<String>(20);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			while (br.ready()) {
				st.add(br.readLine());
			}
		} catch (IOException io) {
			System.out.println(io);
		}
		return st;
	}
	// public static void main(String[] args) {
	// ConsoleInputops i = new ConsoleInputops();
	// String tmp = i.readFilenameFromConsole();
	// System.out.println("---> "+tmp);
	// }
}
