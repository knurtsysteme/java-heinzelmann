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
/*
 * Created on Feb 25, 2005
 */
package de.knurt.heinzelmann.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author danieloltmanns
 */
public class Jarops {

	/**
	 * returns a string of the path where the jar-file takes place. JUST WORKS
	 * WITH A JAR FILE!
	 * 
	 * @param c
	 *            - a object, the jar contains (e.g. -> this)
	 * @return string of the path of the jar file ends with a separator
	 */
	public String getPathOfJar(Object c) {

		// find directory of the jar file
		String s = c.getClass().getName(); // tk.danieloltmanns.www.helpers.Jarops

		int dot = s.lastIndexOf("."); // 3
		String doc;
		if (dot != -1) { // if there is a dot
			doc = s.substring(dot + 1) + ".class";
		} else { // without substring
			doc = s + ".class";
		}

		// jar:file:/Users/.../ (unix) OR jar:file:/C:/.../ (windows)
		String docPlace = c.getClass().getResource(doc).toString();

		Pattern p = Pattern.compile(":");
		Matcher m = p.matcher(docPlace);

		int x = 0;
		while (m.find()) {
			x++;
		}

		dot = docPlace.indexOf("/"); // 9 or -1

		// cutting beginning depence on system
		if (x == 2) // unix system
			docPlace = docPlace.substring(dot, docPlace.length() - doc.length()); // /Users/.../
		else if (x == 3) // windows system
			docPlace = docPlace.substring(dot + 1, docPlace.length() - doc.length());
		else { // i don't know!
			JOptionPane.showMessageDialog(new javax.swing.JFrame(), "fuck! could you please write a message just with\n" + "\"fuck\" as its content to: danieloltmanns@knurt.de." + "you'll get special support then!\n\n" + "this program must die now. thank you anyway. - fuck!");
			System.exit(0);
		}

		// kill "%20" for spaces
		docPlace = docPlace.replaceAll("%20", " ");

		int jar = docPlace.lastIndexOf(".jar!/");

		if (jar != -1) {
			docPlace = docPlace.substring(0, jar);
		}

		jar = docPlace.lastIndexOf("/");

		if (jar != -1) {
			docPlace = docPlace.substring(0, jar + 1);
		}

		return docPlace;
	}

	/**
	 * returns a wanted ImageIcon from an image in a jar file. the image file
	 * must be in the same package as the class.
	 * 
	 * @param c
	 *            - class in the same jar file as the image
	 * @param name
	 *            - of the image
	 * @return imageicon from image with <tt>name</tt>
	 */
	public ImageIcon getImageIconInJar(Class<?> c, String name) {
		java.net.URL imageURL = c.getResource(name);
		if (imageURL != null) {
			return new ImageIcon(imageURL);
		} else {
			System.out.println("pic not loaded! imageURL: " + imageURL);
			return null;
		}
	}
	/*
	 * public void test() { Jarops test = new Jarops();
	 * System.out.println(test.getClass()); System.out.println(test.toString());
	 * System.out.println(test.getClass().getConstructors());
	 * System.out.println(test.getClass().getComponentType());
	 * System.out.println(test.getClass().getName());
	 * System.out.println(getPathOfJar(this)); }
	 * 
	 * public static void main(String[] args) { Jarops test = new Jarops();
	 * test.test(); }
	 */

	/*
	 * public void tollerTest() { String s = getPathOfJar(this);
	 * System.out.println(s); } public static void main(String[] args) { Jarops
	 * jo = new Jarops(); jo.tollerTest(); }
	 */
}
