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

/**
 * to find a log message easier in the source code - even in code has been
 * changed - it make sense to log everything with an id. for example the date
 * and time is a perfectly almost unique id for logging: 1206131539 (for
 * 06/13/2012 15:39).
 * 
 * make the logging work with this ids easier.
 * 
 * @since 06/13/2012
 * @author danieloltmanns@knurt.de
 */
public interface LogWithNumber {

	/**
	 * log an error
	 * 
	 * @see Level#ERROR
	 * @param message
	 *            to log
	 * @param id
	 *            to log
	 */
	public void error(String message, int id);

	/**
	 * log a warning
	 * 
	 * @see Level#WARN
	 * @param message
	 *            to log
	 * @param id
	 *            to log
	 */
	public void warn(String message, int id);

	/**
	 * log something fatal
	 * 
	 * @see Level#FATAL
	 * @param message
	 *            to log
	 * @param id
	 *            to log
	 */
	public void fatal(String message, int id);

	/**
	 * log a fatal exception
	 * 
	 * @see Level#FATAL
	 * @param e
	 *            to log
	 * @param id
	 *            to log
	 */
	public void fatal(Exception e, int id);

	/**
	 * log an info
	 * 
	 * @see Level#INFO
	 * @param message
	 *            to log
	 * @param id
	 *            to log
	 */
	public void info(String message, int id);

	/**
	 * log for debugging
	 * 
	 * @see Level#DEBUG
	 * @param message
	 *            to log
	 * @param id
	 *            to log
	 */
	public void debug(String message, int id);
}
