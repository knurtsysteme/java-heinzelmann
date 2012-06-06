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
/* Created on Apr 20, 2005 */
/**
 * ColorNeighbor.java
 * computes colors, that are nearly another given color
 * in a special way.
 * 
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @version 0.20091104
 */
package de.knurt.heinzelmann.util.colors;

import java.awt.Color;

public class ColorNeighbor {

    private ColorMath cm = ColorMath.getInstance();

    /**
     * returns a color, that is nextDoors changed
     * (added value nextDoors)
     * in the hue of the given color
     * @param nextDoors int of the angle in deg (negative or positive)
     * @param color that will be change
     * @return a color, that is changed next doors in hue
     */
    public Color getHueNeighbor(Color color, int nextDoors) {
        float change = (float) nextDoors / 360.f;
        change %= 1;
        float is = cm.getHue(color);
        float newHue = is + change;
        // must convert to rgb again
        int res = Color.HSBtoRGB(newHue, cm.getSaturation(color), cm.getValue(color));
        return new Color(res);
    }

    /**
     * returns a color, that is nextDoors changed
     * (added value nextDoors)
     * in the saturation of the given color
     * @param color that will be changed
     * @param nextDoors - int of the percentage
     * @return
     */
    public Color getSaturationNeighbor(Color color, int nextDoors) {
        float change = (float) nextDoors / 100.f;
        change %= 1;
        float is = cm.getSaturation(color);
        float newSaturation = is + change;
        // must convert to rgb again
        int res = Color.HSBtoRGB(cm.getHue(color), newSaturation, cm.getValue(color));
        return new Color(res);
    }

    /**
     * returns a color, that is nextDoors changed
     * (added value nextDoors)
     * in the value of the given color
     * @param color that will be changed
     * @param nextDoors - int of the percentage
     * @return
     */
    public Color getValueNeighbor(Color color, int nextDoors) {
        float change = (float) nextDoors / 100.f;
        change %= 1;
        float is = cm.getValue(color);
        float newValue = is + change;
        // must convert to rgb again
        int res = Color.HSBtoRGB(cm.getHue(color), cm.getSaturation(color), newValue);
        return new Color(res);
    }
}
