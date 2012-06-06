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
/* Created on Apr 26, 2005 */
/* Changed on Apr 26, 2005 */
package de.knurt.heinzelmann.physics.mechanics;

/**
 * SolidStats.java
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @since 0.2004x
 * @version 0.20091104
 * 
 * stats of a solid could be set here.
 * the class will be enlarged as it will be needed.
 * implemented:
 * 		mass m[kg]
 */
public class SolidStats {
    private float mass;
    
    /**
     * solid without any stats
     */
    public SolidStats(){
    }
    
    /**
     * a solid with a mass
     * @param mass - of the solid in kg
     */
    public SolidStats(float mass) {
        this.mass = mass;
    }
    
    /**
     * @return Returns the mass.
     */
    public float getMass() {
        return mass;
    }
    /**
     * @param mass The mass to set.
     */
    public void setMass(float mass) {
        this.mass = mass;
    }
}
