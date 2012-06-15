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
package de.knurt.heinzelmann.util.time;

import java.util.Date;

/**
 * convert from windows nt time to {@link java.util.Date} and the other way
 * around.
 * 
 * @author Daniel Oltmanns
 * @since 0.20101201
 * @version 0.20101201
 */
public class TimeFormatConverterWindowsNTTime implements TimeFormatConverter {

	/**
	 * @param time
	 *            in format of the windows nt time
	 * @link http://blogs.msdn.com/b/mikekelly/archive/2009/01/17/unix-time-and-windows-time.aspx
	 */
	@Override
	public Date getTime(Long time) {
		return new Date((time / 10000000 - 11644473600l) * 1000);
	}

	@Override
	public Long getTime(Date time) {
		return new Long(((time.getTime() / 1000) + 11644473600l) * 10000000);
	}

	/** one and only instance of TimeFormatConverterWindowsNTTime */
	private volatile static TimeFormatConverterWindowsNTTime me;

	/** construct TimeFormatConverterWindowsNTTime */
	private TimeFormatConverterWindowsNTTime() {
	}

	/**
	 * return the one and only instance of TimeFormatConverterWindowsNTTime
	 * 
	 * @return the one and only instance of TimeFormatConverterWindowsNTTime
	 */
	public static TimeFormatConverterWindowsNTTime getInstance() {
		if (me == null) {
			// ↖ no instance so far
			synchronized (TimeFormatConverterWindowsNTTime.class) {
				if (me == null) {
					// ↖ still no instance so far
					// ↓ the one and only me
					me = new TimeFormatConverterWindowsNTTime();
				}
			}
		}
		return me;
	}

	/**
	 * short for {@link #getInstance()}
	 * 
	 * @return the one and only instance of TimeFormatConverterWindowsNTTime
	 */
	public static TimeFormatConverterWindowsNTTime me() {
		return getInstance();
	}

}
