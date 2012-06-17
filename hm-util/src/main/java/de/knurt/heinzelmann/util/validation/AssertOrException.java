/*
 * Copyright 2005 - 2010 by KNURT Systeme (http://www.knurt.de)
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
 * helper to throw exceptions on assert not true. this is a workaround for
 * systems, where <code>assert</code> of java compiler is and cannot be activated.
 * 
 * @author Daniel Oltmanns
 * @since 1.00 SNAPSHOT (08/20/2010)
 * @version 1.00 SNAPSHOT (08/20/2010)
 */
public class AssertOrException {

	/**
	 * set this to false and no exception will be thrown. this is useful to get
	 * a better performance on production environments.
	 */
	public static boolean ACTIVE = true;

	public static final void notNull(Object object) {
		notNull(object, "assert object is not null");
	}
	
	public static final void notNull(Object object, String message) {
		if (ACTIVE && !Assert.notNull(object)) {
			throw new NullPointerException(message);
		}
	}

	public static final void assertTrue(boolean expr) {
		assertTrue(expr, "assert true but it is false");
	}

	public static final void assertTrue(boolean expr, String message) {
		if (ACTIVE && !expr) {
			throw new AssertionError(message);
		}
	}

	public static final void assertFalse(boolean expr) {
		assertFalse(expr, "assert false but it is true");
	}

	public static void assertFalse(boolean expr, String message) {
		assertTrue(!expr, message);
	}
}