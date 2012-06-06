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
package de.knurt.heinzelmann.ui.html.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * help showing a calendar in html
 * 
 * @author Daniel Oltmanns
 * @since 0.1 16.05.2009
 * @version 0.20091104
 */
public abstract class CalendarHtml {

	private String classNameToday, classNameLastX, classNameNextX, classNameThisX, classNameWeekOfYear, classNameSelected;

	protected String getClassName() {
		String result = "";
		Calendar today = Calendar.getInstance();
		if (today.get(Calendar.DAY_OF_YEAR) == this.getCal().get(Calendar.DAY_OF_YEAR) && today.get(Calendar.YEAR) == this.getCal().get(Calendar.YEAR)) {
			result = this.getClassNameToday();
		} else if (this.getCalgot().get(this.getCalendarField()) > this.getCal().get(this.getCalendarField())) {
			result = this.getClassNameLastX();
		} else if (this.getCalgot().get(this.getCalendarField()) < this.getCal().get(this.getCalendarField())) {
			result = this.getClassNameNextX();
		} else { // this month
			result = this.getClassNameThisX();
		}
		// set selected
		if (this.getCalgot().get(Calendar.DAY_OF_YEAR) == this.getCal().get(Calendar.DAY_OF_YEAR) && this.getCalgot().get(Calendar.YEAR) == this.getCal().get(Calendar.YEAR)) {
			result += " " + this.getClassNameSelected();
		}
		return result;
	}

	protected void minusOneWeek() {
		this.getCal().add(Calendar.DAY_OF_YEAR, -7);
	}

	protected void plusOneDay() {
		this.getCal().add(Calendar.DAY_OF_YEAR, 1);
	}

	protected void minusOneDay() {
		this.getCal().add(Calendar.DAY_OF_YEAR, -1);
	}

	protected void setCalBackToFirstDayOfWeek() {
		while (this.getCal().get(Calendar.DAY_OF_WEEK) != this.getCal().getFirstDayOfWeek()) {
			this.minusOneDay();
		}
	}

	public CalendarHtml(Calendar cal, Locale locale) {
		this.calgot = (Calendar) cal.clone();
		this.cal = cal;
		this.locale = locale;
		this.classNameLastX = "lastX";
		this.classNameNextX = "nextX";
		this.classNameThisX = "thisX";
		this.classNameToday = "today";
		this.classNameWeekOfYear = "weekOfYear";
		this.classNameSelected = "selected";
	}

	/**
	 * @return the cal
	 */
	protected Calendar getCal() {
		return cal;
	}

	private Calendar cal;
	private final Calendar calgot;
	private Locale locale;

	protected abstract String getTdContentDay(Calendar c);

	protected abstract String getTdContentLeadingCell(Calendar c);

	protected abstract String getPrefixHtml(Calendar c);

	protected abstract String getTableHeadCellContent(Calendar c);

	protected abstract int getCalendarField();

	/**
	 * @param cal
	 *            the cal to set
	 */
	public void setCal(GregorianCalendar cal) {
		this.cal = cal;
	}

	/**
	 * @return the calgot
	 */
	protected final Calendar getCalgot() {
		return calgot;
	}

	/**
	 * @return the locale
	 */
	protected Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * @return the classNameToday
	 */
	protected String getClassNameToday() {
		return classNameToday;
	}

	/**
	 * @return the classNameLastX
	 */
	protected String getClassNameLastX() {
		return classNameLastX;
	}

	/**
	 * @return the classNameNextX
	 */
	protected String getClassNameNextX() {
		return classNameNextX;
	}

	/**
	 * @return the classNameThisX
	 */
	protected String getClassNameThisX() {
		return classNameThisX;
	}

	/**
	 * @return the classNameWeekOfYear
	 */
	protected String getClassNameWeekOfYear() {
		return classNameWeekOfYear;
	}

	/**
	 * @return the classNameSelected
	 */
	public String getClassNameSelected() {
		return classNameSelected;
	}
}
