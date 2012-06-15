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
package de.knurt.heinzelmann.util.question;

/**
 * COMMENT me
 * 
 * @author Daniel Oltmanns
 * @since 28.09.2009
 * @version 0.20091104
 */
public interface ViewableHtmlInputObject {
	public final static int INPUT_TEXT = 1;
	public final static int TEXTAREA = 2;
	public final static int INPUT_RADIO = 3;
	public final static int INPUT_CHECKBOX = 4;

	/**
	 * return the id for the input type. this is one of the class constants
	 * here.
	 * 
	 * @return the id for the input type.
	 */
	public int getHtmlInputId();

	/**
	 * return true, if for this question can be given more then one answer. this
	 * is mostly in type checkbox, so it should be a common implementation:
	 * 
	 * <code>return this.getHtmlInputId() == ViewableHtmlInputObject.INPUT_CHECKBOX</code>
	 * . There might be other ways as well (a select or individual
	 * implementations.
	 * 
	 * @return true, if for this question can be given more then one answer.
	 */
	public boolean hasMultipleAnswers();
}
