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
/* Created on Apr 24, 2005 */
/* Changed on Apr 24, 2005 */
package de.knurt.heinzelmann.physics.mechanics;

import java.awt.geom.Point2D.Float;

/**
 * Move2D.java
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @since 0.2004x
 * @version 0.20091104
 * 
 * discription
 */
public class Move2D extends Move1D {
    
    private Move1D xM, yM;
    
    /**
     * an 2D moving item, stands still in both dimension
     * at the point 0
     */
    public Move2D() {
        this(new Move1D(), new Move1D());
    }

    /**
     * an 2D moving item with moving xM horizontaly
     * and yM verticaly
     */
    public Move2D(Move1D xM, Move1D yM) {
        this.xM = xM;
        this.yM = yM;
    }
    
    
    /* (non-Javadoc)
     * @see tk.danieloltmanns.www.physics.mechanics.Move1D#getActualPosition()
     */
    public Float getActualCoordinates() {
        return new Float(xM.getActualPosition(), yM.getActualPosition());
    }
    
    /**
     * @return Returns the xM.
     */
    public Move1D getXM() {
        return xM;
    }
    /**
     * @param xm The xM to set.
     */
    public void setXM(Move1D xm) {
        xM = xm;
    }
    /**
     * @return Returns the yM.
     */
    public Move1D getYM() {
        return yM;
    }
    /**
     * @param ym The yM to set.
     */
    public void setYM(Move1D ym) {
        yM = ym;
    }
    
    /**
     * starts the Move2D and both Move1D items
     */
    @Override
	public void start() {
        super.start();
        this.xM.start();
        this.yM.start();
    }
    
    /**
     * starts the Move2D and both Move1D items
     */
    @Override
	public long stop() {
        this.xM.stop();
        this.yM.stop();
        return super.stop();
    }
    
    /** (non-Javadoc)
     * @see tk.danieloltmanns.www.physics.mechanics.Move1D#getActualSpeed()
     * here: |v| = sqrt(|v1|+|v2|)
     */
    @Override
	public float getActualSpeed() {
        return (float) Math.sqrt( (double)( Math.pow(xM.getActualSpeed(),2) + Math.pow(yM.getActualSpeed(),2) ) );
    }
}
