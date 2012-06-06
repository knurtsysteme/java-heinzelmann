/*
 * Copyright 2005 - 2011 by KNURT Systeme (http://www.knurt.de)
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
package de.knurt.heinzelmann.util.nebc;


/**
 * A Pipe connecting to board units to a new board unit.
 * @author danieloltmanns
 * @since 0.20110606
 * @version 0.20110606
 *
 * @param <T> to put in
 * @param <G> to connect with
 * @param <U> to put out
 */
public class Pipe<T, G, U> implements BoardUnit<T, U> {
	private BoardUnit<T, G> first;
	private BoardUnit<G, U> second;

	public Pipe(BoardUnit<T, G> first, BoardUnit<G, U> second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public U process(T datum) {
		return second.process(first.process(datum));
	}

}