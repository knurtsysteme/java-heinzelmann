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
/* created on 25.11.2004 */

package de.knurt.heinzelmann.ui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.io.File;

/**
 * DoFileChooser.java Creates a special FileChooser Object
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>,
 *         <a href="http://www.knurt.de">homepage</a>)
 * @version 0.20091104
 */
public class DoFileChooser extends JFileChooser {
	private static final long serialVersionUID = 1L;

	public DoFileChooser() {
		super();
	}

	/**
	 * choose a dir with a file chooser
	 * 
	 * @param jf
	 *            - jframe of the invoking method
	 * @return File - that was choosen or null, if dialog was canceled
	 */
	public File openDir(JFrame jf) {
		this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int ret = this.showOpenDialog(jf);
		if (ret == JFileChooser.CANCEL_OPTION) // cancel was pressed
			return null;
		else
			// open was pressed
			return this.getSelectedFile();
	}

	/**
	 * choose one or more files with a file chooser
	 * 
	 * @param jf
	 *            - jframe of the invoking method
	 * @return File[] - of choosen files or null, if dialog was canceled
	 */
	public File[] openFiles(JFrame jf) {
		this.setMultiSelectionEnabled(true);
		int ret = this.showOpenDialog(jf);
		if (ret == JFileChooser.CANCEL_OPTION) // cancel was pressed
			return null;
		else
			// open was pressed
			return this.getSelectedFiles();
	}
}