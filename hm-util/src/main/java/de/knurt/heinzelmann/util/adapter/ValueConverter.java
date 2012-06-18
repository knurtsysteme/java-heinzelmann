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

/**
 * convert values to something else
 * 
 * @since 06/15/2012
 * @author danieloltmanns@knurt.de
 */
public abstract class ValueConverter<E> {
	private E[] converted;
	private E[] original;

	public ValueConverter(E[] values) {
		this.setOriginals(values);
	}

	/**
	 * set original values
	 * @param originals
	 */
	public void setOriginals(E[] values) {
		this.original = values;
		this.converted = values.clone();
		for (int i = 0; i < values.length; i++) {
			this.converted[i] = this.convertIntern(values[i]);
		}
	}
	/**
	 * convert the given source value to a target value and return
	 * @param value source
	 * @return target value
	 */
	protected abstract E convertIntern(E value);

	public E[] original() {
		return original;
	}

	public E original(int i) {
		return original[i];
	}

	public int length() {
		return original.length;
	}

	public E[] converted() {
		return converted;
	}

	public E converted(int i) {
		return converted[i];
	}
}
