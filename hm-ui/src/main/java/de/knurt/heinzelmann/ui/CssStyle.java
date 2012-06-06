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

import java.awt.Color;
import java.util.Properties;

/**
 * a css style
 * @author Daniel Oltmanns
 * @since 0.20091111
 * @version 0.20091111
 */
public class CssStyle {

    private Properties styles;

    /** construct me */
    public CssStyle() {
        this.styles = new Properties();
    }

    /**
     * add a css key value pair.
     * @param key style attribute
     * @param value style attribute value
     * @return me
     */
    public CssStyle add(String key, String value) {
        this.styles.put(key, value);
        return this;
    }

    /**
     * add a css key value pair.
     * @param key style attribute
     * @param value style attribute value
     * @return me
     */
    public CssStyle add(String key, Color value) {
        this.add(key, CssStyleFactory.getInstance().getColor(value));
        return this;
    }

    /**
     * set <code>background-color</code>.
     * @param value style attribute value
     * @return me
     */
    public CssStyle setBackgroundColor(Color value) {
        this.add("background-color", CssStyleFactory.getInstance().getColor(value));
        return this;
    }

    /**
     * set <code>color</code>.
     * @param value style attribute value
     * @return me
     */
    public CssStyle setColor(Color value) {
        this.add("color", CssStyleFactory.getInstance().getColor(value));
        return this;
    }

    /**
     * add a css key value pair.
     * @param key style attribute
     * @param value style attribute value
     * @return me
     */
    public CssStyle add(String key, Object value) {
        this.add(key, value.toString());
        return this;
    }

    @Override
    public String toString() {
        String result = "";
        for (String key : styles.stringPropertyNames()) {
            result += String.format("%s: %s;", key, styles.get(key));
        }
        return result;
    }
}
