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
/* Created on Apr 19, 2005 */
package de.knurt.heinzelmann.ui.swing;

import javax.swing.JFrame;

/**
 * BasicFrame.java creates <tt>javax.swing.JFrame</tt>'s with given stats.
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>,
 *         <a href="http://www.knurt.de">homepage</a>)
 * @version 0.20091104
 */

public class BasicFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * opens a new frame with the given name of the window in full screenwidth
	 * and -height. program will exit be default closing.
	 * 
	 * @param name
	 *            of the window
	 */
	public BasicFrame(String name) {
		super(name);
		this.setBounds(0, 0, Screen.getScreenWidth(), Screen.getScreenHeight());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * opens a new frame with the given name of the window in screenwidth/size
	 * and -height/size. program will exit be default closing.
	 * 
	 * @param name
	 *            of the frame
	 */
	public BasicFrame(String name, int size) {
		this(name, size, JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * opens a new frame with the given name of the window in screenwidth/size
	 * and -height/size.
	 * 
	 * @param name
	 *            of the frame
	 * @param size
	 *            see upper discription
	 * @param dafaultClose
	 *            - @see javax.swing.JFrame.setDefaultCloseOperation#operation
	 */
	public BasicFrame(String name, int size, int defaultClose) {
		super(name);
		if (size != 0) {
			int w = Screen.getScreenWidth() / size;
			int h = Screen.getScreenHeight() / size;
			this.setBounds(Screen.getScreenCenterX() - w / 2, Screen.getScreenCenterY() - h / 2, w, h);
		} else
			this.setBounds(Screen.getScreenCenterX(), Screen.getScreenCenterY(), 0, 0);
		switch (defaultClose) {
		case (JFrame.DO_NOTHING_ON_CLOSE):
			this.setDefaultCloseOperation(defaultClose);
			break;
		case (JFrame.HIDE_ON_CLOSE):
			this.setDefaultCloseOperation(defaultClose);
			break;
		case (JFrame.DISPOSE_ON_CLOSE):
			this.setDefaultCloseOperation(defaultClose);
			break;
		case (JFrame.EXIT_ON_CLOSE):
			this.setDefaultCloseOperation(defaultClose);
			break;
		default:
			System.err.println("default close operation doesn't exists. setting is default.");
			break;
		}
		this.setVisible(true);
	}

	/**
	 * opens a new frame with the given name of the window in screenwidth*size
	 * and -height*size.
	 * 
	 * @param name
	 *            of the frame
	 * @param size
	 *            in %
	 * @param dafaultClose
	 *            - @see javax.swing.JFrame.setDefaultCloseOperation#operation
	 */
	public BasicFrame(String name, float size, int defaultClose) {
		super(name);
		this.setBounds(Screen.getScreenCenterX(), Screen.getScreenCenterY(), (int) (Screen.getScreenWidth() * size), (int) (Screen.getScreenHeight() * size));
		switch (defaultClose) {
		case (JFrame.DO_NOTHING_ON_CLOSE):
			this.setDefaultCloseOperation(defaultClose);
			break;
		case (JFrame.HIDE_ON_CLOSE):
			this.setDefaultCloseOperation(defaultClose);
			break;
		case (JFrame.DISPOSE_ON_CLOSE):
			this.setDefaultCloseOperation(defaultClose);
			break;
		case (JFrame.EXIT_ON_CLOSE):
			this.setDefaultCloseOperation(defaultClose);
			break;
		default:
			System.err.println("default close operation doesn't exists. setting is default.");
			break;
		}
		this.setVisible(true);
	}

}
