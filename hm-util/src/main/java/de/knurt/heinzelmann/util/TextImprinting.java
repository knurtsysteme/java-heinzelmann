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

import de.knurt.heinzelmann.util.graphics.text.TextSplitter;
import java.awt.Graphics2D;
import java.util.List;

/**
 * helper class to label a graphic with text.
 * @author Daniel Oltmanns
 * @since 12.11.2009
 * @version 12.11.2009
 */
public class TextImprinting {

    /** one and only instance of me */
    private volatile static TextImprinting me;

    /** construct me */
    private TextImprinting() {
    }

    /**
     * return the one and only instance of me
     * @return the one and only instance of me
     */
    public static TextImprinting getInstance() {
        if (me == null) { // no instance so far
            synchronized (TextImprinting.class) {
                if (me == null) { // still no instance so far
                    me = new TextImprinting(); // the one and only
                }
            }
        }
        return me;
    }

    /**
     * split the given text with the given text splitter and forward to {@link #imprint(<any>, Graphics2D, int, int, int, int)}.
     * @param text to print out
     * @param g2d to print on
     * @param x horizontal position of text
     * @param y vertical position of text
     * @param maxHeight the text ends here
     * @param lineHeight of the text
     * @param textSplitter used to split the given text
     */
    public void imprint(String text, Graphics2D g2d, int x, int y, int maxHeight, int lineHeight, TextSplitter textSplitter) {
        this.imprint(textSplitter.split(text), g2d, x, y, maxHeight, lineHeight);
    }

    /**
     * print the given lines to the given graphics and stops at the height.
     * if the text does not match the image height, last line is cut (brutaly in a word)
     * and a <code> [...]</code> is added.
     * @param lines to print out
     * @param g2d to print on
     * @param x horizontal position of text
     * @param y vertical position of text
     * @param maxHeight the text ends here
     * @param lineHeight of the text
     */
    public void imprint(List<String> lines, Graphics2D g2d, int x, int y, int maxHeight, int lineHeight) {
        if (lines != null) {
            int yPos = y;
            for (String line : lines) {
                if (yPos - y + lineHeight >= maxHeight) {
                    if (line.length() > 5) {
                        line = line.substring(0, line.length() - 5) + " [...]";
                    } else {
                        line = "[...]";
                    }
                }
                g2d.drawString(line, x, yPos + lineHeight);
                yPos += lineHeight;
                if (yPos - y >= maxHeight) {
                    break;
                }
            }
        }
    }
}
