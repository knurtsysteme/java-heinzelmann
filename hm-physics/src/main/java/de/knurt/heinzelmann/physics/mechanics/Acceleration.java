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

/**
 * Acceleration.java
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @since 0.2004x
 * @version 0.20091104
 * 
 * some methods for the acceleration: a=v/t [m/ms^2]
 */
public class Acceleration {
    private Speed speed;
    private float s;

    /** gravitation of the earth */
    public final static float G_EARTH = 9.80665f;
    /** gravitation of the sun */
    public final static float G_SUN = 273.f;
    /** gravitation of the moon */
    public final static float G_MOON = 1.62f;
    
    /**
     * acceleration of 0
     */
    public Acceleration() {
        this(new Speed(),0);
    }
    /**
     * acceleration with given speed and miliseconds
     * @param speed - of acceleration
     * @param ms - of acceleration (speed/ms)
     */
    public Acceleration(Speed speed, float s) {
        this.speed = speed;
        this.s = s;
    }
    /**
     * acceleration with 6 numbers after the point exactly
     */
    public Acceleration(float acceleration) {
        this.speed = new Speed(acceleration);
        this.s = 1;
    }
    public float getAcceleration() {
        if(this.s != 0)
            return (speed.getSpeed()/s);
        else 
            return 0.f;
    }
    public void setAcceleration(Speed speed, float s) {
        this.speed = speed;
        this.s = s;
    }
    public void setAcceleration(float acceleration) {
        this.speed = new Speed(acceleration);
        this.s = 1;
    }
}
