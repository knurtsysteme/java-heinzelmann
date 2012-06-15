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
/*
 * Created on Apr 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.knurt.heinzelmann.j3d;

import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;

import com.sun.j3d.utils.universe.SimpleUniverse;

import de.knurt.heinzelmann.ui.swing.BasicFrame;

/**
 * @author danieloltmanns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BasicFrame3D extends BasicFrame {
	private static final long serialVersionUID = 1L;
	private Canvas3D canvas;
	public BasicFrame3D(String name) {
		super(name);
		this.getContentPane().setLayout(new GridLayout());
		GraphicsConfiguration config =
            SimpleUniverse.getPreferredConfiguration();

        canvas = new Canvas3D(config);
		this.getContentPane().add("Center", canvas);
	}
	public Canvas3D getCanvas() {
		return canvas;
	}
	public void showSimpleUniverse(BranchGroup bg) {
		bg.compile();
		SimpleUniverse u = new SimpleUniverse(canvas);
		// sets the viewpoint to (0,0,2.41), looking into the negative z-direction
		u.getViewingPlatform().setNominalViewingTransform();
		u.addBranchGraph(bg);
		this.setVisible(true);
	}

}
