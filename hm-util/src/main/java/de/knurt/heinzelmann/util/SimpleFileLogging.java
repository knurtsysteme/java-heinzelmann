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
package de.knurt.heinzelmann.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Class to generate very simple log files. Generates files named
 * <code>logfile.log.[serial number]</code>, one file for each log.
 * 
 * @since 0.20060207
 * @version 0.20091104
 * @author <a href="mailto:Daniel Oltmanns %3Cdanieloltmanns@gmx.de%3E">Daniel
 *         Oltmanns</a>
 */
@Deprecated
public class SimpleFileLogging {

	private File logfile;
	private FileHandler fh;

	/** Construction impossible: solely static methods */
	public SimpleFileLogging(File logfile) {
		this.logfile = logfile;
		try {
			this.fh = new FileHandler(this.logfile.getAbsolutePath(), 500000, 1, true);
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		} catch (SecurityException ex) {
			System.err.println(ex.getMessage());
		}
	}

	public File getLogfile() {
		return logfile;
	}

	/**
	 * TODO rework comment Logs the given <code>message</code> with the given
	 * <code>level</code>. The file is UTF-8 encoded, in XML and have a maximal
	 * size of round about 500 kB. The file is written into the folder specified
	 * in <code>config.Config#LOG_FOLDER</code>.
	 * 
	 * <tt>IOException</tt> objects, and <tt>SecurityException</tt> objects are
	 * ignored.
	 * 
	 * @see config.Config#LOG_FOLDER
	 * @see java.util.logging.Level
	 * @param level
	 *            <tt>Level</tt> of this log
	 * @param message
	 *            <tt>String</tt> the message to log
	 */
	public void log(Level level, String message) {
		try {
			LogRecord lr = new LogRecord(level, message);
			this.fh.setEncoding("UTF-8");
			this.fh.publish(lr);
			this.fh.flush();
		} catch (SecurityException ex) {
		} catch (UnsupportedEncodingException ex) {
		}
	}
}
