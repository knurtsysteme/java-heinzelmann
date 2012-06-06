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

import de.knurt.heinzelmann.ui.html.HtmlElement;
import de.knurt.heinzelmann.ui.html.HtmlFactory;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * a html table calender representation of a month of a {@link Calendar}. the
 * calendar has a head row (with weekdays in default locale) and a leading col
 * (no of week in year). the calendar has not any information about the year or
 * month it shows. XXX this is tested only with a {@link GregorianCalendar}!!!!
 * 
 * @author Daniel Oltmanns
 * @since 0.1 23.04.2009
 * @version 0.20091104
 */
public class CalendarOneMonthHtml extends CalendarHtml {

	/**
	 * construct layout with given calendar
	 * 
	 * @param cal
	 *            given calender
	 */
	public CalendarOneMonthHtml(Calendar cal, Locale locale) {
		super(cal, locale);
		this.setCalToFirstDayOfMonth();
		this.setCalBackToFirstDayOfWeek();
	}

	/**
	 * construct layout with default calender
	 */
	public CalendarOneMonthHtml() {
		this(new GregorianCalendar(), Locale.getDefault());
	}

	/**
	 * return headline of calendar.
	 * 
	 * @param c
	 *            calendar
	 * @return headline of calendar.
	 */
	@Override
	protected String getPrefixHtml(Calendar c) {
		return "<h1>" + c.getDisplayName(Calendar.MONTH, Calendar.LONG, this.getLocale()) + " " + c.get(Calendar.YEAR) + "</h1>";
	}

	@Override
	public String toString() {
		String result = this.getPrefixHtml(this.getCalgot());
		result += "<table>";
		result += this.getTableHead();
		result += "<tbody>";
		result += this.getHtmlOfWeek();
		int actualMonth = this.getCal().get(Calendar.MONTH);
		while (actualMonth == this.getCal().get(Calendar.MONTH)) {
			result += this.getHtmlOfWeek();
		}
		result += "</tbody>";
		result += "</table>";
		return result;
	}

	/**
	 * return the full date of the day. this is typicly overridden in subclass.
	 * 
	 * @return the full date of the day.
	 */
	@Override
	protected String getTdContentDay(Calendar c) {
		return "<div>" + c.get(Calendar.DAY_OF_MONTH) + "</div>";
	}

	private String getHtmlOfWeek() {
		String result = "<tr>";
		result += String.format("<td class=\"%s\">%s</td>", this.getClassNameWeekOfYear(), this.getTdContentLeadingCell(this.getCal()));
		result += this.getTdDay();
		this.plusOneDay();
		while (super.getCal().get(Calendar.DAY_OF_WEEK) != this.getCal().getFirstDayOfWeek()) {
			result += this.getTdDay();
			this.plusOneDay();
		}
		result += "</tr>";
		return result;
	}

	@Override
	protected String getTableHeadCellContent(Calendar c) {
		return c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, this.getLocale());
	}

	@Override
	protected String getTdContentLeadingCell(Calendar c) {
		return c.get(Calendar.WEEK_OF_YEAR) + "";
	}

	private String getTableHead() {
		String result = "<thead>";
		result += "<tr><th></th>";
		result += "<th>" + this.getTableHeadCellContent(this.getCal()) + "</th>";
		this.plusOneDay();
		while (this.getCal().get(Calendar.DAY_OF_WEEK) != this.getCal().getFirstDayOfWeek()) {
			result += "<th>" + this.getTableHeadCellContent(this.getCal()) + "</th>";
			this.plusOneDay();
		}
		this.minusOneWeek();
		result += "</tr>";
		result += "</thead>";
		return result;
	}

	protected boolean isThisMonth(Calendar c) {
		return c.get(Calendar.MONTH) == this.getCalgot().get(Calendar.MONTH);
	}

	protected HtmlElement getTdDay() {
		HtmlElement result = HtmlFactory.get("td");
		result.addClassName(this.getClassName());
		result.setId("dayOfYear_" + this.getCal().get(Calendar.DAY_OF_YEAR));
		result.add(this.getTdContentDay(this.getCal()));
		return result;
	}

	private void setCalToFirstDayOfMonth() {
		this.getCal().add(Calendar.DAY_OF_MONTH, (this.getCal().get(Calendar.DAY_OF_MONTH) * -1) + 1);
	}

	@Override
	protected int getCalendarField() {
		return Calendar.MONTH;
	}

}