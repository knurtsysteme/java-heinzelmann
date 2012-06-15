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
/* Created on Apr 24, 2005 */
/* Changed on Apr 24, 2005 */
package de.knurt.heinzelmann.physics.mechanics;

import de.knurt.heinzelmann.physics.util.Stopwatch;

/**
 * Move1D.java
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @since 0.2004x
 * @version 0.20091104
 * 
 * an item with a (start) speed, point and (constant) acceleration.
 * to start moving the item with given speed and acceleration, 
 * invoke the start()-method (@see tk.danieloltmanns.phisics.util.Stopwatch).
 */
public class Move1D extends Stopwatch {

    private Acceleration acc;
    private Speed spe;
    private float position; // the actual position of the item
    
    /**
     * a Move1D item, that stands still (speed = 0, acceleration = 0)
     * even after starting the object.
     */
    public Move1D() {
        this(new Acceleration(), new Speed(), 0);
    }
    
    /**
     * a Move1D item, that stands still (speed = 0, acceleration = 0)
     * even after starting the object, at the given position
     * @param position - of the moving object
     */
    public Move1D(float position) {
        this(new Acceleration(), new Speed(), position);
    }
    
    /**
     * acceleration is 0 -> linear moving
     * @param spe - speed of moving at the start
     */
    public Move1D(Speed spe) {
        this(new Acceleration(), spe, 0);
    }
    
    /**
     * (start)speed is 0 -> accelerated moving after standing still
     * @param acc - acceleration of object
     */
    public Move1D(Acceleration acc) {
        this(acc, new Speed(), 0);
    }
    
    /**
     * an accelerated or breaked moving with given (start)speed
     * @param acc - acceleration of object
     * @param spe - speed of moving at the start
     */
    public Move1D(Acceleration acc, Speed spe) {
        this(acc, spe, 0);
    }
    
    /**
     * an accelerated or breaked moving with given (start)speed
     * aceleration and position
     * @param acc - acceleration of object
     * @param spe - speed of moving at the start
     * @param position - of the moving object
     */
    public Move1D(Acceleration acc, Speed spe, float position) {
        this.acc = acc;
        this.spe = spe;
        this.position = position;
    }
    
    /**
     * returns the (x-)koordinate of an Move1D-Item 
     * after start() was invoked.
     * actual position s = (a/2)*t^2 + v0*t + s0.
     * here s0 = this.position;
     * @return the actual position of the item
     */
    public float getActualPosition() {
        float afterS = this.getSeconds();
        float accValue = this.acc.getAcceleration();
        float speedValue = this.spe.getSpeed();
        return (float)(accValue/2 * Math.pow(afterS, 2) + speedValue * afterS + this.position);
    }
    
    /**
     * returns the actual speed after start() invoked.
     * actual speed v = a * t + v0
     * @return actual speed
     */
    public float getActualSpeed() {
        return this.getAcc().getAcceleration() * this.getSeconds() + getStartSpeed().getSpeed();
    }
    
    /**
     * the direction of the moving item will
     * change into the opposite. speed and acceleration
     * can switch similiary.
     * @param changeSpeedDirection - true, if the direction of the speed shall change
     * @param changeAccDirection - true, if the acceleration shall change direction
     */
    public void changeDirection(boolean changeSpeedDirection, boolean changeAccDirection) {
        // get (actual) values
        float actSpeed = this.getActualSpeed();
        float actAcc = this.getAcc().getAcceleration();
        this.setPosition(this.getActualPosition());
        
        // change speed direction
        if(changeSpeedDirection)
            this.setStartSpeed(new Speed(-actSpeed));
        else
            this.setStartSpeed(new Speed(actSpeed));
        
        // change acc direction
        if(changeAccDirection)
            this.setAcc(new Acceleration(-actAcc));
        else
            this.setAcc(new Acceleration(actAcc));
        
        // start the moving with new values
        this.start();
    }
    
    
    /**
     * returns the acceleration of the object
     * @return Returns the acceleration of the object.
     */
    public Acceleration getAcc() {
        return acc;
    }
    /**
     * sets the acceleration of the object
     * @param acc The acceleration to set.
     */
    public void setAcc(Acceleration acc) {
        this.acc = acc;
    }
    /**
     * returns the start-speed of the object
     * @return Returns the speed of the object at start.
     */
    public Speed getStartSpeed() {
        return spe;
    }
    /**
     * sets the start speed of the object
     * @param spe The speed at start to set.
     */
    public void setStartSpeed(Speed spe) {
        this.spe = spe;
    }
    
    /**
     * returns the position of the moving item at start
     * @return Returns the startposition of an moving item.
     */
    public float getStartPosition() {
        return position;
    }
    /**
     * sets the position of the moving item
     * @param position The position of the moving item to set.
     */
    public void setPosition(float position) {
        this.position = position;
    }    
}
