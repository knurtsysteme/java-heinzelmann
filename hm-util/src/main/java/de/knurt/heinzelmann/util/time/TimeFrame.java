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
import java.util.Date;

/**
 * a time frame starting and ending at a specific time.
 * @author Daniel Oltmanns
 * @since 0.20090424
 * @version 0.20091104
 */
public interface TimeFrame extends Comparable<TimeFrame>, Cloneable {

    /**
     * return true, if the given date is part of the time frame.
     * @param date to check
     * @return true, if the given date is part of the time frame.
     */
    public boolean contains(Date date);

    /**
     * add the given time to start and end of the time frame.
     * or in other words: move the entire time frame backward or forward.
     * @see Calendar#add(int, int)
     * @param field of the calendar field moving the time frame.
     * @param amount of the calendar field moving the imt frame
     */
    public void add(int field, int amount);

    /**
     * set the start of the calendar
     * @param field of the calendar start to set
     * @param amount of the calendar field to set
     */
    public void setCalendarStart(int field, int amount);

    /**
     * set the end of the calendar
     * @param field of the calendar end to set
     * @param amount of the calendar field to set
     */
    public void setCalendarEnd(int field, int amount);

    /**
     * return true, if the time frame starts in past.
     * @return true, if the time frame starts in past.
     */
    public boolean startsInPast();

    /**
     * return true, if the given time frame overlaps this time frame.
     * this is true, if the start time or the end time is in the duration
     * of this timeframe.
     * @param timeFrame to check.
     * @return true, if the given time frame overlaps this time frame.
     */
    public boolean overlaps(TimeFrame timeFrame);

    /**
     * return unix timestamp of start of the time frame.
     * @see Date#getTime()
     * @return unix timestamp of start of the time frame.
     */
    public long getStart();

    /**
     * return the starting date of the time frame.
     * @return the starting date of the time frame.
     */
    public Date getDateStart();

    /**
     * return the starting date of the time frame.
     * @return the starting date of the time frame.
     */
    public Calendar getCalendarStart();

    /**
     * return the ending date of the time frame.
     * @return the ending date of the time frame.
     */
    public Calendar getCalendarEnd();

    /**
     * return the ending date of the time frame.
     * @return the ending date of the time frame.
     */
    public Date getDateEnd();

    /**
     * set the start and the end of the time frame.
     * if end is smaller then start, values are switched.
     * @param start of the time frame
     * @param end of the time frame
     */
    public void setStartEnd(long start, long end);

    /**
     * set the start and the end of the time frame as it is set in given time frame.
     * @param timeFrame time frame start and end is taken from
     */
    public void setStartEnd(TimeFrame timeFrame);

    /**
     * return unix timestamp of end of the time frame.
     * @see Date#getTime()
     * @return unix timestamp of end of the time frame.
     */
    public long getEnd();

    /**
     * set unix timestamp of end of the time frame.
     * @see Date#getTime()
     * @param end unix timestamp of end of the time frame.
     */
    public void setEnd(long end);

    /**
     * set unix timestamp of start of the time frame.
     * @see Date#getTime()
     * @param start unix timestamp of start of the time frame.
     */
    public void setStart(long start);

    /**
     * return the duration of the time frame.
     * @return the duration of the time frame.
     */
    public long getDuration();

    /**
     * return true, if the end of the time frame is in past.
     * @return true, if the end of the time frame is in past.
     */
    public boolean endsInPast();

    @Override
    public int compareTo(TimeFrame o);

    /**
     * add the given time to the end of the time frame
     * @see Calendar#add(int, int) 
     * @param field calendar field to add amount to
     * @param amount added to
     */
    public void addEnd(int field, int amount);

    /**
     * add the given time to the start of the time frame
     * @see Calendar#add(int, int)
     * @param field calendar field to add amount to
     * @param amount added to
     */
    public void addStart(int field, int amount);

    /**
     * set the start of the time frame
     * @param calendar start of the time frame
     */
    public void setStart(Calendar calendar);

    /**
     * set the end of the time frame
     * @param calendar end of the time frame
     */
    public void setEnd(Calendar calendar);

    /**
     * return a clone
     * @return a clone
     */
    public TimeFrame clone();

    /**
     * set the start of the time frame
     * @param start of the time frame
     */
	public void setStart(Date start);

    /**
     * set the end of the time frame
     * @param end of the time frame
     */
	public void setEnd(Date end);

}
