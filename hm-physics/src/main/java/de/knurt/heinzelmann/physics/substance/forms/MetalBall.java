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
  /* Created on Apr 27, 2005 */
/* Changed on Apr 27, 2005 */
package de.knurt.heinzelmann.physics.substance.forms;

import de.knurt.heinzelmann.physics.substance.material.Metal;

/**
 * MetalBall.java
 * @author daniel oltmanns (<a href="mailto:danieloltmanns@knurt.de">mail</a>, <a href="http://www.knurt.de">homepage</a>)
 * @since 0.2004x
 * @version 0.20091104
 * 
 * discription
 */
public class MetalBall extends Ball {

    
    /* (non-Javadoc)
     * @see tk.danieloltmanns.www.physics.substance.Solid#getFriction()
     */
    public float getFriction() {
		throw new UnsupportedOperationException("not implemented yet");
    }
    /* (non-Javadoc)
     * @see tk.danieloltmanns.www.physics.substance.Substance#getDensity()
     */
    public float getDensity() {
        return Metal.DENSITY;
    }
    /* (non-Javadoc)
     * @see tk.danieloltmanns.www.physics.substance.Substance#getMass()
     */
    public float getMass() {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
