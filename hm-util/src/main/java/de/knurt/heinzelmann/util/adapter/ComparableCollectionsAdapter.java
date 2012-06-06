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

import java.util.Collection;

/**
 * adapt a collection so that can be compared in a specific way.
 * @author Daniel Oltmanns
 * @since 0.20091107
 * @version 0.20091107
 */
public class ComparableCollectionsAdapter {
    /** one and only instance of me */
    private volatile static ComparableCollectionsAdapter me;

    /** construct me */
    private ComparableCollectionsAdapter(){}

    /**
     * return the one and only instance of ComparableCollectionsAdapter
     * @return the one and only instance of ComparableCollectionsAdapter
     */
    public static ComparableCollectionsAdapter getInstance() {
	if (me == null) { // no instance so far
	    synchronized (ComparableCollectionsAdapter.class) {
		if (me == null) { // still no instance so far
		    me = new ComparableCollectionsAdapter(); // the one and only
		}
	    }
	}
	return me;
    }

    /**
     * set the given comparing modus to all items in the given collection.
     * @param collectionItems comparing modus to set on.
     * @param comparingModus to set on the items.
     */
    public void setModusToAll(Collection<? extends ComparableInDifferentWays> collectionItems, int comparingModus) {
        for(ComparableInDifferentWays collectionItem : collectionItems) {
            collectionItem.setComparingModus(comparingModus);
        }
    }

}
