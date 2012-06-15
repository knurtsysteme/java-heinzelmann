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
package de.knurt.heinzelmann.ui.html.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * a html table calender representation of a week of a {@link Calendar}. the
 * calendar has a head row and a leading col. viewed is the week of year. the
 * day steps is always 30 minutes. TODO this is tested only with a
 * {@link GregorianCalendar}!!!!
 * 
 * @author Daniel Oltmanns
 * @since 0.20090514
 * @version 0.20091104
 */
public abstract class CalendarOneWeekHtml extends CalendarHtml {

	@Override
	protected int getCalendarField() {
		return Calendar.WEEK_OF_YEAR;
	}

	/**
	 * construct layout with given calendar
	 * 
	 * @param cal
	 *            given calender
	 */
	public CalendarOneWeekHtml(Calendar cal, Locale locale) {
		super(cal, locale);
		this.getCal().get(Calendar.WEEK_OF_YEAR);
		this.setCalBackToFirstDayOfWeek();
	}

	public CalendarOneWeekHtml(Calendar cal) {
		this(cal, Locale.getDefault());
	}

	/**
	 * construct layout with default calender
	 */
	public CalendarOneWeekHtml() {
		this(new GregorianCalendar());
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
		return "<h1>" + c.get(Calendar.WEEK_OF_YEAR) + "/" + c.get(Calendar.YEAR) + "</h1>";
	}

	@Override
	public String toString() {
		String result = this.getPrefixHtml((Calendar) this.getCalgot().clone());
		result += "<table>";
		result += "<thead>";
		result += this.getTableHead();
		result += "</thead>";
		result += "<tbody>";
		result += this.getHtmlOfWeek();
		result += "</tbody>";
		result += "</table>";
		return result;
	}

	private String getHtmlOfWeek() {
		String result = "<tr>";
		result += "<th>" + this.getTdContentLeadingCell(this.getCal()) + "</th>";
		String toFormat = "<td class=\"%s\" id=\"dayOfYear_%s\">%s</td>";
		result += String.format(toFormat, this.getClassName(), this.getCal().get(Calendar.DAY_OF_YEAR), this.getTdContentDay(this.getCal()));
		// FIXME raus result += "<td class=\""+this.getClassName()+"\">" +
		// this.getTdContentDay(this.getCal()) + "</td>";
		this.plusOneDay();
		while (this.getCal().get(Calendar.DAY_OF_WEEK) != this.getCal().getFirstDayOfWeek()) {
			result += String.format(toFormat, this.getClassName(), this.getCal().get(Calendar.DAY_OF_YEAR), this.getTdContentDay(this.getCal()));
			// FIXME raus result += "<td class=\""+this.getClassName()+"\">" +
			// this.getTdContentDay(this.getCal()) + "</td>";
			this.plusOneDay();
		}
		result += "</tr>";
		return result;
	}

	@Override
	protected String getTableHeadCellContent(Calendar c) {
		return c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, this.getLocale()) + ", " + c.get(Calendar.DAY_OF_MONTH) + " " + c.getDisplayName(Calendar.MONTH, Calendar.SHORT, this.getLocale());
	}

	protected String getTdContentLeadingCellWeekRow(Calendar c) {
		return c.get(Calendar.WEEK_OF_YEAR) + "";
	}

	private String getTableHead() {
		String result = "<tr>";
		result += "<th>" + this.getCal().get(Calendar.YEAR) + "</th>";
		String format = "<th class=\"%s\">%s</th>";
		result += String.format(format, this.getClassName(), this.getTableHeadCellContent(this.getCal()));
		this.plusOneDay();
		while (this.getCal().get(Calendar.DAY_OF_WEEK) != this.getCal().getFirstDayOfWeek()) {
			result += String.format(format, this.getClassName(), this.getTableHeadCellContent(this.getCal()));
			this.plusOneDay();
		}
		this.minusOneWeek();
		result += "</tr>";
		return result;
	}

	protected boolean isThisWeek(Calendar c) {
		return c.get(Calendar.WEEK_OF_YEAR) == this.getCalgot().get(Calendar.WEEK_OF_YEAR);
	}
}