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
/* Created on Apr 22, 2005 */
package de.knurt.heinzelmann.ui.swing;

import java.awt.Point;
import java.awt.Toolkit;

/**
 * Screen.java
 * basic screen stats to get to make life easier.
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @version 0.20091104
 */
public class Screen {

    /**
     * does nothing
     */
    public Screen() {
        super();
    }
    
    /**
     * returns the screen width of the monitor, the
     * program runs on
     * @return the screenwidth of the monitor
     */
    public static int getScreenWidth() {
        return (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }
    /**
     * returns the screen height of the monitor, the
     * program runs on
     * @return the screenwidth of the monitor
     */
    public static int getScreenHeight() {
        return (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }
    /**
     * returns the center x-coordinate of the monitor, the
     * program runs on
     * @return the center x coordinate of the monitor
     */
    public static int getScreenCenterX() {
        return Screen.getScreenWidth()/2;
    }
    /**
     * returns the center y-coordinate of the monitor, the
     * program runs on
     * @return the center Y coordinate of the monitor
     */
    public static int getScreenCenterY() {
        return Screen.getScreenHeight()/2;
    }
    /**
     * returns the center point of the monitor, the
     * program runs on
     * @return the center point of the monitor
     */
    public static Point getScreenCenter() {
        return new Point(getScreenCenterX(), getScreenCenterY());
    }

}
