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
package de.knurt.heinzelmann.physics.util;

import java.util.Date;

/**
 * Stopwatch.java
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @since 0.2004x
 * @version 0.20091104
 * 
 * discription
 */
public class Stopwatch {

    private long startMS = 0; // start time in seconds
    private Date date;
    
    public Stopwatch(){}
    
    /**
     * saves the aktual time in seconds into startMS.
     * means, the stopwatch is setted to 0
     */
    public void start() {
        date = new Date();
        startMS = date.getTime();
    }
    
    /**
     * stops the Count and returns
     * the miliseconds went after start
     * @return the miliseconds went after start
     */
    public long stop() {
        int count = getMiliSeconds();
        startMS = 0;
        return count;
    }
    
    /**
     * returns true, if the watch was set,
     * else false
     * @return true, if watch was set, else false
     */
    public boolean isSet() {
        if(startMS == 0) 
            return false;
        else
            return true;
    }
    
    /**
     * returns the start time (in seconds)
     * @return starttime in miliseconds after 1.1.1970 00:00
     */
    public float getStart() {
        return startMS;
    }
    
    /**
     * returns the seconds went after the
     * object was started. returns 0 if stopwatch wasn't set.
     * @return seconds went since invoking start()
     */
    public float getSeconds() {
        if(this.isSet()) {
            date = new Date();
            return (float)( (float)(date.getTime()-startMS) / 1000.f);
        }
        else {
            return 0.f;
        }
    }

    /**
     * returns the miliseconds went after the
     * object was started. returns 0 if stopwatch wasn't set.
     * @return miliseconds went since invoking start()
     */
    public int getMiliSeconds() {
        if(this.isSet()) {
            date = new Date();
            return (int)(date.getTime()-startMS);
        }
        else {
            return 0;
        }
    }
}
