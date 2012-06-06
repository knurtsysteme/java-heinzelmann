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

/**
 * a time frame from start to end. this does not validate anything. if you set
 * the end after the start, it just switches the points.
 * 
 * @author Daniel Oltmanns
 * @since 0.20090424
 * @version 0.20091104
 */
public class SimpleTimeFrame extends AbstractTimeFrame {

	public SimpleTimeFrame(Calendar start, Calendar end) {
		super(start, end);
	}

	public SimpleTimeFrame(long start, long end) {
		super(start, end);
	}

	public SimpleTimeFrame(Calendar end) {
		super(end);
	}

	public SimpleTimeFrame() {
		super();
	}

	/**
	 * return the entire day represendted by the given calendar as time frame.
	 * 
	 * @param c
	 *            return the day represented by this calendar
	 * @return the entire day represendted by the given calendar as time frame.
	 */
	public static SimpleTimeFrame getDay(Calendar c) {
		Calendar start = (Calendar) c.clone();
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		Calendar end = (Calendar) start.clone();
		end.add(Calendar.DAY_OF_YEAR, 1);
		return new SimpleTimeFrame(start, end);
	}

	/**
	 * return the entire day of now.
	 * 
	 * @see Calendar#getInstance()
	 * @see AbstractTimeFrame#getDay(java.util.Calendar)
	 * @return the entire day of now.
	 */
	public static SimpleTimeFrame getToday() {
		return getDay(Calendar.getInstance());
	}
}
