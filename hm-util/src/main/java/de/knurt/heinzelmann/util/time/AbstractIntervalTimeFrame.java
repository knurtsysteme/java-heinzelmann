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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * a time frame that comes every year, month, day, week or hour.
 *
 * if the interval is <code>ONE_TIME</code>, this is exaclty a
 * {@link AbstractTimeFrame}.
 *
 * all set time frame starts and ends parts are ignored.
 *
 * "yearly from 1.2.2008 to 15.3.2008" ignores "2008"
 * ("yearly from 1.2.1973 to 15.3.1973" is same definition).
 *
 * "monthly from 1.2.2008 to 15.2.2008" ignores "2008" and "2"
 * ("monthly from 1.11.1973 to 15.11.1973" is same definition).
 *
 * for none senseful inputs (e.g. if the duration of the time frame
 * is bigger then the interval), it may come to strange results.
 *
 * same strange results may result from "critical time steps" like
 * "monthly from 31.1.2008 to 1.2.2008". Here the calendar rules are used
 * (example contains 2.3.2008, because of less then 30 days in february).
 *
 * @author Daniel Oltmanns
 * @since 0.20090424
 * @version 0.20091104
 */
public abstract class AbstractIntervalTimeFrame implements IntervalTimeFrame {

    /** the current time in utc miliseconds from the epoch */
    private int interval;
    private TimeFrame basePeriodOfTime;

    @Override
    public AbstractIntervalTimeFrame clone() {
        try {
            return (AbstractIntervalTimeFrame) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("implements Cloneable!");
        }
    }

    /**
     * constructur mainly to construct examples for queries.
     */
    public AbstractIntervalTimeFrame() {
    }

    public AbstractIntervalTimeFrame(TimeFrame basePeriodOfTime) {
        this.basePeriodOfTime = basePeriodOfTime;
    }

    public AbstractIntervalTimeFrame(Calendar start, Calendar end) {
        this(new SimpleTimeFrame(start, end));
    }

    public void setHourStart(int start) {
        Calendar tmp = this.basePeriodOfTime.getCalendarStart();
        tmp.set(Calendar.HOUR_OF_DAY, start);
        this.basePeriodOfTime.setStart(tmp.getTimeInMillis());
    }

    public void setHourEnd(int end) {
        Calendar tmp = this.basePeriodOfTime.getCalendarEnd();
        tmp.set(Calendar.HOUR_OF_DAY, end);
        this.basePeriodOfTime.setEnd(tmp.getTimeInMillis());
    }

    @Override
    public List<IntervalTimeFrame> getIntervalTimeFramesWithNoIteration(TimeFrame fromTo) {
        List<TimeFrame> simpleTimeFrames = this.getSingleSimpleTimeFrames(fromTo);
        List<IntervalTimeFrame> result = new ArrayList<IntervalTimeFrame>();
        for (TimeFrame tf : simpleTimeFrames) {
            IntervalTimeFrame clone = this.clone();
            clone.setBasePeriodOfTime(tf);
            clone.setInterval(ONE_TIME);
            result.add(clone);
        }
        return result;
    }

    @Override
    public List<TimeFrame> getSingleSimpleTimeFrames(TimeFrame fromTo) {
        ArrayList<TimeFrame> result = new ArrayList<TimeFrame>();
        Integer calendarField = this.getInterval();
        if (calendarField != null) {
            TimeFrame clone = basePeriodOfTime.clone();
            // push performance: put nearly to year
            int yearsDiff = fromTo.getCalendarStart().get(Calendar.YEAR) - clone.getCalendarStart().get(Calendar.YEAR);
            switch (calendarField) {
                case Calendar.YEAR:
                    clone.add(Calendar.YEAR, yearsDiff);
                    break;
                case Calendar.MONTH:
                    clone.add(Calendar.MONTH, yearsDiff * 12);
                    break;
                case Calendar.WEEK_OF_YEAR:
                    clone.add(Calendar.WEEK_OF_YEAR, yearsDiff * 52);
                    break;
                case Calendar.DAY_OF_MONTH:
                    clone.add(Calendar.DAY_OF_MONTH, yearsDiff * 365);
                    break;
                case Calendar.HOUR_OF_DAY:
                    clone.add(Calendar.HOUR_OF_DAY, yearsDiff * 365 * 24);
                    break;
            }

            if (calendarField == ONE_TIME) {
                result.add(clone);
            } else {
                if (fromTo.getEnd() <= clone.getStart()) { // time frame got is behind base
                    // move back
                    while (fromTo.getEnd() <= clone.getStart()) {
                        clone.add(calendarField, -1);
                    }
                } else if (fromTo.getStart() > clone.getEnd()) { // time frame got before base
                    // move forward
                    while (fromTo.getStart() > clone.getEnd()) {
                        clone.add(calendarField, 1);
                    }
                }
                // clone here is in base
                while (fromTo.overlaps(clone)) { // move 1 step before base
                    clone.add(calendarField, -1);
                }
                // clone here in front of start
                clone.add(calendarField, 1);
                // clone here first position overlapping start
                while (fromTo.overlaps(clone)) {

                    // set to given start and end, if this is cut be interval.
                    long startBefore = clone.getStart();
                    long endBefore = clone.getEnd();
                    if (startBefore < fromTo.getStart()) { // start is cut
                        clone.setStart(fromTo.getStart());
                    }
                    if (endBefore > fromTo.getEnd()) { // end is cut
                        clone.setEnd(fromTo.getEnd());
                    }

                    // add it
                    result.add(clone.clone());

                    // set back
                    clone.setStart(startBefore);
                    clone.setEnd(endBefore);
                    clone.add(calendarField, 1);
                }
            }
        }
        return result;
    }

    /**
     * return true, if given time frame overlaps with this or an iteration of this.
     * @param timeFrame to check
     * @return true, if given time frame overlaps with this or an iteration of this.
     */
    @Override
    public boolean overlaps(TimeFrame timeFrame) {
        boolean result = getBasePeriodOfTime().overlaps(timeFrame);
        if (result == false) { // no direct overlapping
            // create a list with candidates
            ArrayList<TimeFrame> candidates = new ArrayList<TimeFrame>();
            Integer intervalAndCalendarField = this.getInterval();
            if (intervalAndCalendarField != null && intervalAndCalendarField != ONE_TIME) {
                // set start of neighbor1 one step bigger then end of this
                TimeFrame neighbor1 = timeFrame.clone();
                // push performance: put nearly to year
                int yearsDiff = getBasePeriodOfTime().getCalendarStart().get(Calendar.YEAR) - neighbor1.getCalendarStart().get(Calendar.YEAR);
                switch (intervalAndCalendarField) {
                    case Calendar.YEAR:
                        neighbor1.add(Calendar.YEAR, yearsDiff);
                        break;
                    case Calendar.MONTH:
                        neighbor1.add(Calendar.MONTH, yearsDiff * 12);
                        break;
                    case Calendar.WEEK_OF_YEAR:
                        neighbor1.add(Calendar.WEEK_OF_YEAR, yearsDiff * 52);
                        break;
                    case Calendar.DAY_OF_MONTH:
                        neighbor1.add(Calendar.DAY_OF_MONTH, yearsDiff * 365);
                        break;
                    case Calendar.HOUR_OF_DAY:
                        neighbor1.add(Calendar.HOUR_OF_DAY, yearsDiff * 365 * 24);
                        break;
                }
                if (getBasePeriodOfTime().getEnd() < neighbor1.getStart()) { // time frame got is after this
                    while (getBasePeriodOfTime().getEnd() < neighbor1.getStart()) {
                        neighbor1.add(intervalAndCalendarField, -1);
                    }
                } else { // end of this is bigger or same
                    while (getBasePeriodOfTime().getEnd() > neighbor1.getStart()) {
                        neighbor1.add(intervalAndCalendarField, 1);
                    }
                }
                candidates.add(neighbor1);
                TimeFrame neighbor2 = neighbor1.clone();
                neighbor2.add(intervalAndCalendarField, -1); // just the next one
                candidates.add(neighbor2);
                for (TimeFrame toCheck : candidates) {
                    result = getBasePeriodOfTime().overlaps(toCheck);
                    if (result) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * @return the iteration
     */
    @Override
    public Integer getInterval() {
        return interval;
    }

    @Override
    public void setDaily() {
        this.setInterval(EACH_DAY);
    }

    @Override
    public void setHourly() {
        this.setInterval(EACH_HOUR);
    }

    @Override
    public void setWeekly() {
        this.setInterval(EACH_WEEK);
    }

    /**
     * set thie interval
     * @param interval to set
     */
    @Override
    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    @Override
    public void setMonthly() {
        this.setInterval(EACH_MONTH);
    }

    @Override
    public void setYearly() {
        this.setInterval(EACH_YEAR);
    }

    /**
     * @return the basePeriodOfTime
     */
    @Override
    public TimeFrame getBasePeriodOfTime() {
        return basePeriodOfTime;
    }

    /**
     * @param basePeriodOfTime the basePeriodOfTime to set
     */
    @Override
    public void setBasePeriodOfTime(TimeFrame basePeriodOfTime) {
        this.basePeriodOfTime = basePeriodOfTime;
    }

    @Override
    public boolean isOneTime() {
        return this.interval == AbstractIntervalTimeFrame.ONE_TIME;
    }

    @Override
    public int compareTo(TimeFrame o) {
        return this.getBasePeriodOfTime().compareTo(o);
    }

    @Override
    public void setCalendarStart(int field, int amount) {
        this.getBasePeriodOfTime().setCalendarStart(field, amount);
    }
}
