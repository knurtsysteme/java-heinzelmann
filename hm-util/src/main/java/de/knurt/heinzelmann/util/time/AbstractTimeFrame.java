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
 * a time frame from start to end.
 * this does not validate anything. if you set the end after the start,
 * it just switches the points.
 * @author Daniel Oltmanns
 * @since 0.20090424
 * @version 0.20091104
 */
public abstract class AbstractTimeFrame implements TimeFrame {

    @Override
    public AbstractTimeFrame clone() {
	try {
	    return (AbstractTimeFrame) super.clone();
	} catch (CloneNotSupportedException e) {
	    throw new Error("implements Cloneable!");
	}
    }
    /** the current time in utc miliseconds from the epoch */
    private long start, end;

    /**
     * construct a time frame with given start and end
     * @param start of time frame
     * @param end of time frame
     */
    public AbstractTimeFrame(Calendar start, Calendar end) {
	this(start.getTimeInMillis(), end.getTimeInMillis());
    }

    /**
     * move time frame to other time position
     * @see java.util.Calendar#add(int, int)
     * @param field the calendar field
     * @param amount the amount of date or time to be added to the field
     */
    @Override
    public void add(int field, int amount) {
	Calendar resultStart = this.getCalendarStart();
	resultStart.add(field, amount);
	Calendar resultEnd = this.getCalendarEnd();
	resultEnd.add(field, amount);
	this.setStartEnd(resultEnd.getTimeInMillis(), resultStart.getTimeInMillis());
    }

    /**
     * construct a time frame starting and ending now
     */
    public AbstractTimeFrame() {
	this(Calendar.getInstance(), Calendar.getInstance());
    }

    /**
     * construct time frame from now to given end
     * @param end of time frame
     */
    public AbstractTimeFrame(Calendar end) {
	this(Calendar.getInstance(), end);
    }

    /**
     * construct a time frame with start and end.
     * unix time stamp in miliseconds are used here.
     * if end is before start, end and start are switched.
     * @see Calendar#getTimeInMillis()
     * @param start miliseconds of AbstractTimeFrame's start
     * @param end miliseconds of AbstractTimeFrame's end
     */
    public AbstractTimeFrame(long start, long end) {
	this.setStartEnd(start, end);
    }

    /**
     * set time frame start to other time position
     * @see java.util.Calendar#set(int, int)
     * @param field the calendar field
     * @param amount the amount of date or time to be added to the field
     */
    @Override
    public void setCalendarStart(int field, int amount) {
	Calendar ztart = this.getCalendarStart();
	ztart.set(field, amount);
	this.setStart(ztart);
    }
    /**
     * set time frame start to other time position
     * @see java.util.Calendar#set(int, int)
     * @param field the calendar field
     * @param amount the amount of date or time to be added to the field
     */
    @Override
    public void setCalendarEnd(int field, int amount) {
	Calendar ent = this.getCalendarEnd();
	ent.set(field, amount);
	this.setEnd(ent);
    }

    @Override
    public boolean startsInPast() {
	return this.getCalendarStart().before(Calendar.getInstance());
    }

    /**
     * return true, if a point of time is in the time frame.
     * start is inclusive, end is exclusive. that means, if date is
     * the start of timeframe, return true. if date is end of time frame, return false.
     * @param date point of time
     * @return true, if a point of time is in the time frame
     * XXX might be of public interest - and must be overridden in subclasses
     */
    @Override
    public boolean contains(Date date) {
	return this.getStart() <= date.getTime() && this.getEnd() > date.getTime();
    }

    /**
     * return true, if the given timeFrame overlaps part of this time frame.
     * this includes all iterations.
     * touching does not count as an overlap. so if one timeframe starts where
     * the other ends or the other way arround, this returns false.
     * @param timeFrame to check
     * @return true, if the given timeFrame overlaps part of this time frame
     */
    @Override
    public boolean overlaps(TimeFrame timeFrame) {
	return this.overlapsIntern(timeFrame, this) || this.overlapsIntern(this, timeFrame);
    }

    private boolean overlapsIntern(TimeFrame timeFrame1, TimeFrame timeFrame2) {
	return (timeFrame1.contains(timeFrame2.getDateStart()) ||
		timeFrame1.contains(timeFrame2.getDateEnd())) &&
		timeFrame1.getStart() != timeFrame2.getEnd() &&
		timeFrame1.getEnd() != timeFrame2.getStart();
    }

    /**
     * @return the start
     */
    @Override
    public long getStart() {
	return start;
    }

    @Override
    public Date getDateStart() {
	return new Date(start);
    }

    @Override
    public Calendar getCalendarStart() {
	Calendar c = Calendar.getInstance();
	c.setTime(this.getDateStart());
	return c;
    }

    @Override
    public Calendar getCalendarEnd() {
	Calendar c = Calendar.getInstance();
	c.setTime(this.getDateEnd());
	return c;
    }

    @Override
    public Date getDateEnd() {
	return new Date(end);
    }

    /**
     * set the given values as start and end.
     * the smaller value will be the start.
     * @param a start or end
     * @param b start or end
     */
    @Override
    public void setStartEnd(long a, long b) {
	if (a < b) {
	    this.start = a;
	    this.end = b;
	} else {
	    this.start = b;
	    this.end = a;
	}
    }

    /**
     * set the start and end of the given time frame to this.
     * @param timeFrame start and end is taken from
     */
    @Override
    public void setStartEnd(TimeFrame timeFrame) {
	this.setStartEnd(timeFrame.getStart(), timeFrame.getEnd());
    }

    /**
     * @return the end
     */
    @Override
    public long getEnd() {
	return end;
    }

    /**
     * set the end.
     * @param ent the end to set
     */
    @Override
    public void setEnd(long ent) {
	this.setStartEnd(this.start, ent);
    }

    /**
     * set the start.
     * @param stard the start to set
     */
    @Override
    public final void setStart(long stard) {
	this.setStartEnd(stard, this.end);
    }


    /**
     * return the duration in miliseconds.
     * @return the duration in miliseconds.
     */
    @Override
    public long getDuration() {
	return this.end - this.start;
    }

    @Override
    public String toString() {
	return this.getDateStart().toString() + " -- " + this.getDateEnd().toString();
    }

    @Override
    public boolean endsInPast() {
	return this.getCalendarEnd().before(Calendar.getInstance());
    }

    @Override
    public int compareTo(TimeFrame o) {
	return this.getDateStart().before(o.getDateStart()) ? -1 : 1;
    }

    /**
     * expand or shrink time frame moving the end.
     * if shrinking over the start, start and end is switched.
     * @see java.util.Calendar#add(int, int)
     * @param field the calendar field
     * @param amount the amount of date or time to be added to the field
     */
    @Override
    public void addEnd(int field, int amount) {
	Calendar resultEnd = this.getCalendarEnd();
	resultEnd.add(field, amount);
	this.setEnd(resultEnd);
    }

    /**
     * expand or shrink time frame moving the start.
     * if expanding over the end, start and end is switched.
     * @see java.util.Calendar#add(int, int)
     * @param field the calendar field
     * @param amount the amount of date or time to be added to the field
     */
    @Override
    public void addStart(int field, int amount) {
	Calendar resultStart = this.getCalendarStart();
	resultStart.add(field, amount);
	this.setStart(resultStart);
    }

	@Override
    public void setStart(Calendar calendar) {
	this.setStart(calendar.getTimeInMillis());
    }

	@Override
	public void setStart(Date start) {
		this.setStart(start.getTime());
		
	}

	@Override
	public void setEnd(Date end) {
		this.setEnd(end.getTime());
		
	}

	@Override
    public void setEnd(Calendar calendar) {
	this.setEnd(calendar.getTimeInMillis());
    }
}