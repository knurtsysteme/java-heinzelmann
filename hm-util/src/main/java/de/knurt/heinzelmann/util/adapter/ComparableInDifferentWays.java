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
package de.knurt.heinzelmann.util.adapter;

/**
 * the comparing of objects implementing this can be done in different ways.
 * each why have an unspcified id called "comparing modus".
 * as a general rule, each object implements {@link Comparable} as well -
 * but must not have to.
 * @see Comparable
 * @author Daniel Oltmanns
 * @since 0.20091107
 * @version 0.20091107
 */
public interface ComparableInDifferentWays {

    /**
     * set an unspecified id to compare things in collection.
     * @param comparingModus unspecified identification for the comparsion.
     */
    public void setComparingModus(int comparingModus);

    /**
     * return comparing modus.
     * @return unspecified comparing modus.
     */
    public int getComparingModus();
}
