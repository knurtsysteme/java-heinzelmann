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
package de.knurt.heinzelmann.util.time;

import java.util.Calendar;
import java.util.List;

/**
 * a time frame looping in a specific interval. the interval is one of
 * {@link Calendar} constants.
 * 
 * @see TimeFrame
 * @see Calendar
 * @author Daniel Oltmanns
 * @since 0.20090424
 * @version 0.20091104
 */
public interface IntervalTimeFrame extends Cloneable {

	/**
	 * do not loop it. using this is the same as using a {@link TimeFrame}
	 * directly.
	 */
	public static final int ONE_TIME = Calendar.ERA;
	/**
	 * repeat time frame "every hour".
	 */
	public static final int EACH_HOUR = Calendar.HOUR_OF_DAY;
	/**
	 * repeat time frame "every day".
	 */
	public static final int EACH_DAY = Calendar.DAY_OF_MONTH;
	/**
	 * repeat time frame "every week".
	 */
	public static final int EACH_WEEK = Calendar.WEEK_OF_YEAR;
	/**
	 * repeat time frame "every month".
	 */
	public static final int EACH_MONTH = Calendar.MONTH;
	/**
	 * repeat time frame "every year".
	 */
	public static final int EACH_YEAR = Calendar.YEAR;

	/**
	 * return a list of time frames in a given time frame not repeating.
	 * 
	 * @param fromTo
	 *            time frame to check intervals in
	 * @return a list of time frames in a given time frame not repeating.
	 */
	public List<IntervalTimeFrame> getIntervalTimeFramesWithNoIteration(TimeFrame fromTo);

	/**
	 * return all single time frames of all intervals with no iteration
	 * overlapping the given time frame.
	 * 
	 * @param fromTo
	 *            as a frame to return the single intervals of
	 * @return all single time frames of all intervals with no iteration
	 *         overlapping the given time frame.
	 */
	public List<TimeFrame> getSingleSimpleTimeFrames(TimeFrame fromTo);

	/**
	 * set interval to "repeat time frame every hour"
	 */
	public void setHourly();

	/**
	 * set interval to "repeat time frame every week"
	 */
	public void setWeekly();

	/**
	 * return true, if one of the interval overlaps the given time frame.
	 * 
	 * @see TimeFrame#overlaps(de.knurt.heinzelmann.util.time.TimeFrame)
	 * @param timeframe
	 *            to check
	 * @return true, if one of the interval overlaps the given time frame.
	 */
	public boolean overlaps(TimeFrame timeframe);

	/**
	 * set the interval - or the time frame is repeating every "?"
	 * 
	 * @param interval
	 *            the interval - or the time frame is repeating every "?"
	 */
	public void setInterval(Integer interval);

	/**
	 * set interval to "repeat time frame every month"
	 */
	public void setMonthly();

	/**
	 * set interval to "repeat time frame every year"
	 */
	public void setYearly();

	/**
	 * this is the base TimeFrame to repeat.
	 * 
	 * @return the base TimeFrame to repeat.
	 */
	public TimeFrame getBasePeriodOfTime();

	/**
	 * set the base TimeFrame to repeat.
	 * 
	 * @param basePeriodOfTime
	 *            the base TimeFrame to repeat.
	 */
	public void setBasePeriodOfTime(TimeFrame basePeriodOfTime);

	/**
	 * return true, if interval is set to "do not repeat"
	 * 
	 * @return true, if interval is set to "do not repeat"
	 */
	public boolean isOneTime();

	/**
	 * int for a comparing of this interval time frame to another time frame.
	 * 
	 * @see Comparable#compareTo(java.lang.Object)
	 * @param o
	 *            antother time frame
	 * @return int for a comparing of this interval time frame to another time
	 *         frame.
	 */
	public int compareTo(TimeFrame o);

	/**
	 * set the start of the base time frame to
	 * 
	 * @see TimeFrame#setCalendarStart(int, int)
	 * @see Calendar#set(int, int)
	 * @param field
	 *            the calendar field to set the start of.
	 * @param amount
	 *            the value to be set for the given calendar field to set the
	 *            start of.
	 */
	public void setCalendarStart(int field, int amount);

	/**
	 * return the interval
	 * 
	 * @return the interval
	 */
	public Integer getInterval();

	/**
	 * set interval to "repeat time frame every day"
	 */
	public void setDaily();

	/**
	 * return a clone
	 * 
	 * @return a clone
	 */
	public IntervalTimeFrame clone();
}
