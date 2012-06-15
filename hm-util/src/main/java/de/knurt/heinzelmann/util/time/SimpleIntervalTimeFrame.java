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

import java.util.Calendar;

/**
 * COMMENT
 * 
 * @author Daniel Oltmanns
 * @since 0.20090827
 * @version 0.20091104
 */
public class SimpleIntervalTimeFrame extends AbstractIntervalTimeFrame {

	public SimpleIntervalTimeFrame(TimeFrame basePeriodOfTime) {
		super(basePeriodOfTime);
	}

	public SimpleIntervalTimeFrame(Calendar start, Calendar end) {
		super(start, end);
	}

	public SimpleIntervalTimeFrame() {
		this(new SimpleTimeFrame());
	}

	public IntervalTimeFrame getClone() {
		IntervalTimeFrame itf = new SimpleIntervalTimeFrame(this.getBasePeriodOfTime());
		itf.setInterval(this.getInterval());
		return itf;
	}
}
