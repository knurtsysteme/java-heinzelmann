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
package de.knurt.heinzelmann.util.validation;

/**
 * some (pseude) reflected validations
 * @author Daniel Oltmanns
 * @since 0.1 30.06.2009
 * @version 0.20091104
 */
public class CodeValidation {

    /**
     * return true, if o is castable to clazz
     * @param o to check
     * @param clazz try to cast in
     * @return true, if o is castable to clazz
     */
    @SuppressWarnings("unchecked")
    public static boolean isSubclassOf(Object o, Class clazz) {
	boolean result = false;
	try {
	    o.getClass().asSubclass(clazz); // castable?
	    result = true; // yes!
	} catch (ClassCastException e) {
	}
	return result;
    }
}