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
 * Speed.java
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @since 0.2004x
 * @version 0.20091104
 * 
 * distance and time is interpreted in percentage of what you define, where "1.00" is the full time or the full distance.
 * the time is a starting point of starting up
 */
public class Speed {
    private float distance; // -> pixels
    private float time;
    
    public Speed() {
        this(0,0);
    }
    public Speed(float distance, float time) {
        if(time != 0) {
            this.distance = (float)distance/(float)time;
            this.time = time;
        }
        else {
            this.distance = 0;
            this.time = 0;
        }
    }
    /**
     * the speed is six numbers after the point exactly
     * @param speed
     */
    public Speed(float speed) {
        this.distance = speed;
        this.time = 1;
    }
    
    /**
     * return meter
     * @param afterS after seconds
     * @return meter
     * @deprecated distance and time has no unit anymore
     * 	use @link #getDistance(int)
     */
    public float getMeter(float afterS) {
        if(this.time != 0)
            return (this.distance*afterS)/this.time;
        else
            return 0.f;
    }
    /**
     * return distance made
     * @param afterTime
     * 	time units as percentage of what you defined as full.
     * @return distance made
     */
    public float getDistance(float afterTime) {
        if(this.time != 0)
            return (this.distance*afterTime)/this.time;
        else
            return 0.f;
    }
    
    /**
     * return time
     * @param afterS after seconds
     * @return meter
     * @deprecated distance and time has no unit anymore
     * 	use @link #getDistance(int)
     */
   public float getS(float afterM) {
        if(this.distance != 0)
            return (afterM*this.time) / this.distance;
        else
            return 0.f;
    }
    /**
     * returns the speed in distance/time
     * @return speed in distance/time
     */
   public float getSpeed() {
       if(this.time!=0)
           return distance/time;
       else
           return 0;
   }    
   /**
    * return the computed time.
    * @param timeOf1
    * 	the time you have defined for 1.f
    * @param distanceOf1
    * 	the distance you have defined for 1.f
    * @return the computed time.
    */
   public float getSpeed(int timeOf1, int distanceOf1) {
       if(this.time!=0)
           return distance*distanceOf1/time*timeOf1;
       else
           return 0;
   }    
}
