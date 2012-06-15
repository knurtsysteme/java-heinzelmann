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

import java.util.List;

import de.knurt.heinzelmann.util.query.Identificable;

/**
 * an information given or needed before or after a booking.
 * @author Daniel Oltmanns
 * @since 27.09.2009
 * @version 0.20091104
 */
public interface QuestionInput<E extends AnswerPossibility> extends Identificable {

    /**
     * return the question.
     * @return the question.
     */
    public String getQuestion();

    /**
     * return answers given or null, if no answer is given.
     * if there are only one answer, return this as list.
     * @return the answer or null, if no answer given.
     */
    public List<String> getGivenAnswers();

    /**
     * return answer possibilities or null, if no given
     * @return answer possibilities or null, if no given
     */
    public List<E> getAnswerPossibilities();

    /**
     * return true, if answer is needed and false, if answer is optional
     * @return true, if answer is needed and false, if answer is optional
     */
    public boolean isRequired();
}
