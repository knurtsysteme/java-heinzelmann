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
package de.knurt.heinzelmann.ui;

import de.knurt.heinzelmann.util.colors.ColorMath;

import java.awt.Color;

/**
 * produce things for a css style.
 * @author Daniel Oltmanns
 * @since 19.10.2009
 * @version 0.20091104
 */
public class CssStyleFactory {

    /** one and only instance of me */
    private volatile static CssStyleFactory me;

    /** construct me */
    private CssStyleFactory() {
    }

    /**
     * return the one and only instance of me
     * @return the one and only instance of me
     */
    public static CssStyleFactory getInstance() {
        if (me == null) { // no instance so far
            synchronized (CssStyleFactory.class) {
                if (me == null) { // still no instance so far
                    me = new CssStyleFactory(); // the one and only
                }
            }
        }
        return me;
    }

    /**
     * return a color as being used by css.
     * @param color converted to a css string color
     * @return a color as being used by css.
     */
    public String getColor(Color color) {
        return "#" + ColorMath.getInstance().getHex(color);
    }

    /**
     * return a new constructed CssStyle
     * @return a new constructed CssStyle
     */
    public CssStyle get() {
        return new CssStyle();
    }
}
