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

/**
 * split a duration in minutes to days, hours, minutes
 * 
 * @author Daniel Oltmanns
 * @since 0.20100723
 * @version 0.20100723
 */
public class DurationSplitter {
	private int[] daysHoursMinutes = new int[3];

	public DurationSplitter(int minutes) {
		this.setDuration(minutes);
	}

	private DurationSplitter setDuration(int minutes) {
		int days = minutes / (60 * 24);
		int minutesLeft = minutes % (60 * 24);
		int hoursLeft = minutesLeft / 60;
		minutesLeft = minutesLeft % 60;
		this.daysHoursMinutes[0] = days;
		this.daysHoursMinutes[1] = hoursLeft;
		this.daysHoursMinutes[2] = minutesLeft;
		return this;
	}

	public int[] getDaysHoursMinutes() {
		return this.daysHoursMinutes;
	}

	public DurationSplitter setMinutes(int minutes) {
		return this.setDuration(minutes);
	}

	public int getDays() {
		return this.daysHoursMinutes[0];
	}

	public int getHours() {
		return this.daysHoursMinutes[1];
	}

	public int getMinutes() {
		return this.daysHoursMinutes[2];
	}

}
