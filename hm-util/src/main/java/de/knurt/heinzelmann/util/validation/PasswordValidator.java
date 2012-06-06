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
 * helper class, to define how strong a password must be to be valid.
 * 
 * @author Daniel Oltmanns
 * @since 0.1 19.03.2009
 * @version 0.20091104
 */
public class PasswordValidator {

	private Integer minLength, minDigits, minUpper, minLower, minSpecial, minChars, minNonChars, maxLength;

	public Integer getMinChars() {
		return minChars;
	}

	public void setMinChars(Integer minChars) {
		this.minChars = minChars;
	}

	public PasswordValidator() {
		this.minLength = 8;
		this.maxLength = 20;
		this.minDigits = 1;
		this.minUpper = 1;
		this.minLower = 1;
		this.minSpecial = 1;
		this.minChars = 0;
		this.minNonChars = 0;
	}

	private Boolean hasMinChars(String password) {
		String result = password.replaceAll("[^a-zA-Z]", "");
		return result.length() >= this.getMinChars();
	}

	private Boolean hasMinNonChars(String password) {
		String result = password.replaceAll("[a-zA-Z]", "");
		return result.length() >= this.getMinNonChars();
	}

	private Boolean isValidInGeneral(String password) {
		return password.equals("") == false && password.equals(password.replaceAll("\\s", ""));
	}

	public Boolean isValid(Object password) {
		Boolean result = false;
		if (password != null) {
			String check = password.toString().trim();
			result = this.isValidInGeneral(check);
			if (result) {
				result = this.hasMinLength(check);
			}
			if (result) {
				result = this.hasMaxLength(check);
			}
			if (result) {
				result = this.hasMinDigits(check);
			}
			if (result) {
				result = this.hasMinUpper(check);
			}
			if (result) {
				result = this.hasMinLower(check);
			}
			if (result) {
				result = this.hasMinSpecial(check);
			}
			if (result) {
				result = this.hasMinChars(check);
			}
			if (result) {
				result = this.hasMinNonChars(check);
			}
		}
		return result;
	}

	private Boolean hasMaxLength(String password) {
		return password.length() <= this.getMaxLength();
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	private Boolean hasMinLength(String password) {
		return password.length() >= this.getMinLength();
	}

	private Boolean hasMinDigits(String password) {
		String result = password.replaceAll("[^0-9]", "");
		return result.length() >= this.getMinDigits();
	}

	private Boolean hasMinLower(String password) {
		String result = password.replaceAll("[^a-z]", "");
		return result.length() >= this.getMinLower();
	}

	private Boolean hasMinSpecial(String password) {
		String result = password.replaceAll("[a-zA-Z0-9]", "");
		return result.length() >= this.getMinSpecial();
	}

	private Boolean hasMinUpper(String password) {
		String result = password.replaceAll("[^A-Z]", "");
		return result.length() >= this.getMinUpper();
	}

	/**
	 * @return the minLength
	 */
	public Integer getMinLength() {
		return minLength;
	}

	/**
	 * @param minLength
	 *            the minLength to set
	 */
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	/**
	 * @return the minDigits
	 */
	public Integer getMinDigits() {
		return minDigits;
	}

	/**
	 * @param minDigits
	 *            the minDigits to set
	 */
	public void setMinDigits(Integer minDigits) {
		this.minDigits = minDigits;
	}

	/**
	 * @return the minUpper
	 */
	public Integer getMinUpper() {
		return minUpper;
	}

	/**
	 * @param minUpper
	 *            the minUpper to set
	 */
	public void setMinUpper(Integer minUpper) {
		this.minUpper = minUpper;
	}

	/**
	 * @return the minLower
	 */
	public Integer getMinLower() {
		return minLower;
	}

	/**
	 * @param minLower
	 *            the minLower to set
	 */
	public void setMinLower(Integer minLower) {
		this.minLower = minLower;
	}

	/**
	 * @return the minSpecial
	 */
	public Integer getMinSpecial() {
		return minSpecial;
	}

	/**
	 * @param minSpecial
	 *            the minSpecial to set
	 */
	public void setMinSpecial(Integer minSpecial) {
		this.minSpecial = minSpecial;
	}

	/**
	 * @param minNonChars
	 *            the minNonChars to set
	 */
	public void setMinNonChars(Integer minNonChars) {
		this.minNonChars = minNonChars;
	}

	/**
	 * @return the minNonChars
	 */
	public Integer getMinNonChars() {
		return minNonChars;
	}

}