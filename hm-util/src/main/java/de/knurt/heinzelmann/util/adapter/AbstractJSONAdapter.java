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
package de.knurt.heinzelmann.util.adapter;

import java.util.List;
import org.json.JSONArray;

/**
 * COMMENT me
 *
 * @author Daniel Oltmanns
 * @since 16.09.2009
 * @version 0.20091104
 */
public abstract class AbstractJSONAdapter<T extends ViewableObject> implements JSONAdapter<T> {

    /**
     * return an array of given objects simply using {@link JSONAdapter#getAsJSONObject(de.knurt.heinzelmann.util.adapter.ViewableObject)}
     * for each.
     * @param objects
     * @return
     */
   @Override
    public JSONArray getAsJSONArray(List<T> objects) {
        JSONArray result = new JSONArray();
        for(T object : objects) {
            result.put(this.getAsJSONObject(object));
        }
        return result;
    }


}
