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
package de.knurt.heinzelmann.util.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * log with number with log4j
 * 
 * @since 06/13/2012
 * @author danieloltmanns@knurt.de
 */
public class LogWithNumberLog4J implements LogWithNumber {

	private Logger logger;

	/**
	 * construct the logger with number with a regular logger
	 * 
	 * @param logger
	 *            regular
	 */
	public LogWithNumberLog4J(Logger logger) {
		this.logger = logger;
	}

	/** {@inheritDoc} */
	@Override
	public void error(String message, int id) {
		this.logger.log(Level.ERROR, this.getMessage(message, id));
	}

	private String getMessage(String message, int id) {
		return String.format("[%s] %s", id, message);
	}

	/** {@inheritDoc} */
	@Override
	public void fatal(String message, int id) {
		this.logger.log(Level.FATAL, this.getMessage(message, id));
	}

	/** {@inheritDoc} */
	@Override
	public void fatal(Exception e, int id) {
		String message = getMessage(e.getMessage(), id);
		this.logger.log(Level.FATAL, message);
	}

	/** {@inheritDoc} */
	@Override
	public void info(String message, int id) {
		this.logger.log(Level.INFO, this.getMessage(message, id));
	}

	/** {@inheritDoc} */
	@Override
	public void warn(String message, int id) {
		this.logger.log(Level.WARN, this.getMessage(message, id));
	}

	/** {@inheritDoc} */
	@Override
	public void debug(String message, int id) {
		this.logger.log(Level.DEBUG, this.getMessage(message, id));
	}

}
