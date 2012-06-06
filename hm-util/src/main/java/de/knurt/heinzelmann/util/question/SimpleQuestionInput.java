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
package de.knurt.heinzelmann.util.question;

import java.util.List;

/**
 * COMMENT me
 * 
 * @author Daniel Oltmanns
 * @since 27.09.2009
 * @version 0.20091104
 */
public class SimpleQuestionInput implements QuestionInput<SimpleAnswerPossibility>, ViewableHtmlInputObject {

	@Override
	public String getQuestion() {
		return this.question;
	}

	@Override
	public List<SimpleAnswerPossibility> getAnswerPossibilities() {
		return this.answerPossibilities;
	}

	@Override
	public boolean isRequired() {
		return this.required;
	}

	private String question;
	private List<String> givenAnswers;
	private boolean required;
	private List<SimpleAnswerPossibility> answerPossibilities;
	private Integer id;
	private int htmlInputId;

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @param answerNeeded
	 *            the answerNeeded to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @param answerPossibilities
	 *            the answerPossibilities to set
	 */
	public void setAnswerPossibilities(List<SimpleAnswerPossibility> answerPossibilities) {
		this.answerPossibilities = answerPossibilities;
	}

	/**
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int getHtmlInputId() {
		return this.htmlInputId;
	}

	/**
	 * set one of {@link ViewableHtmlInputObject} constants
	 * 
	 * @param htmlInputId
	 */
	public void setHtmlInputId(int htmlInputId) {
		this.htmlInputId = htmlInputId;
	}

	@Override
	public List<String> getGivenAnswers() {
		return this.givenAnswers;
	}

	/**
	 * @param answers
	 *            the answers to set
	 */
	public void setGivenAnswers(List<String> givenAnswers) {
		this.givenAnswers = givenAnswers;
	}

	@Override
	public boolean hasMultipleAnswers() {
		return this.getHtmlInputId() == ViewableHtmlInputObject.INPUT_CHECKBOX;
	}

}
