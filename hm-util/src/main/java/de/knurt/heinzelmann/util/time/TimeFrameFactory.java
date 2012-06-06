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
package de.knurt.heinzelmann.util.time;

import java.util.Calendar;

/**
 * produce time frames of a point of time to "the entire month",
 * "the entire day" etc.
 * 
 * @author Daniel Oltmanns
 * @since 0.20110923
 * @version 0.20110923
 */
public class TimeFrameFactory {

	private Calendar pointOfTime;

	/**
	 * a factory with a given point of time as base for time frame production
	 * 
	 * @param pointOfTime
	 *            a point of time as base for time frame production
	 */
	public TimeFrameFactory(Calendar pointOfTime) {
		this.pointOfTime = pointOfTime;
	}

	/**
	 * a factory with now as base for time frame production
	 */
	public TimeFrameFactory() {
		this(Calendar.getInstance());
	}

	/**
	 * return the entire month starting at 00:00:00 o'clock. e.g.
	 * <code>Thu Sep 01
	 * 00:00:00 CEST 2011 -- Sat Oct 01 00:00:00 CEST 2011</code>
	 * 
	 * @return the entire month starting at 00:00:00 o'clock
	 */
	public TimeFrame getMonth() {
		Calendar start = (Calendar) this.pointOfTime.clone();
		start.set(Calendar.MILLISECOND, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.DAY_OF_MONTH, 1);

		Calendar end = (Calendar) start.clone();
		end.add(Calendar.MONTH, 1);

		return new SimpleTimeFrame(start, end);
	}

	/**
	 * return the entire day starting and ending at 00:00:00 o'clock
	 * 
	 * @return the entire day starting and ending at 00:00:00 o'clock
	 */
	public TimeFrame getDay() {
		Calendar start = (Calendar) this.pointOfTime.clone();
		start.set(Calendar.MILLISECOND, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.HOUR_OF_DAY, 0);

		Calendar end = (Calendar) start.clone();
		end.add(Calendar.DAY_OF_YEAR, 1);

		return new SimpleTimeFrame(start, end);
	}

	/**
	 * return a time frame with the given duration away from point of time
	 * 
	 * @param field
	 *            the calendar field {@link Calendar}
	 * @param amount
	 *            of units of the field
	 * @return a time frame with the given duration away from point of time
	 */
	public TimeFrame getDuration(int field, int amount) {
		Calendar start = (Calendar) this.pointOfTime.clone();
		Calendar end = (Calendar) start.clone();
		end.add(field, amount);
		return new SimpleTimeFrame(start, end);
	}

	/**
	 * return the entire month extended, in a way that the calendar starts and
	 * ends at a first day of week. the first day of week is the
	 * {@link Calendar#getFirstDayOfWeek()} of the given {@link #pointOfTime}
	 * 
	 * @return the entire month extended, in a way that the calendar starts and
	 *         ends at a first day of week.
	 */
	public TimeFrame getMonthWithFullWeeks() {
		TimeFrame result = this.getMonth();
		while (result.getCalendarStart().get(Calendar.DAY_OF_WEEK) != this.pointOfTime.getFirstDayOfWeek()) {
			result.addStart(Calendar.DAY_OF_YEAR, -1);
		}
		while (result.getCalendarEnd().get(Calendar.DAY_OF_WEEK) != this.pointOfTime.getFirstDayOfWeek()) {
			result.addEnd(Calendar.DAY_OF_YEAR, 1);
		}
		return result;
	}

}
