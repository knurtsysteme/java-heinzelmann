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
/* Created on Apr 27, 2005 */
/* Changed on Apr 27, 2005 */
package de.knurt.heinzelmann.physics.substance.forms;

import de.knurt.heinzelmann.physics.substance.Solid;

/**
 * Ball.java
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @since 0.2004x
 * @version 0.20091104
 * 
 * discription
 */
public abstract class Ball implements Solid {
    private float radius;
    
    public Ball() {
        this(10.f);
    }
    
    public Ball(float radius) {
        this.radius = radius;
    }
    
    
    
    /* (non-Javadoc)
     * @see tk.danieloltmanns.www.physics.substance.Solid#getSize()
     */
    public float[] getSize() {
        float diameter = 2 * this.getRadius();
        float[] size = {diameter, diameter, diameter};
        return size;
    }

    /** (non-Javadoc)
     * @see tk.danieloltmanns.www.physics.substance.Substance#getVolume()
     * V = 4/3 pi r^3
     */
    public float getVolume() {
        return (float)( (4.f / 3.f) * Math.PI * Math.pow(this.getRadius(), 3) );
    }
    
    /**
     * @return Returns the radius.
     */
    public float getRadius() {
        return radius;
    }
    /**
     * @param radius The radius to set.
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }
}
