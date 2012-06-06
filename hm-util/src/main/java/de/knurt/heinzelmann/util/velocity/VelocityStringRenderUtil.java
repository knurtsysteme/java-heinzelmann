/*
 * Copyright 2005 - 2010 by KNURT Systeme (http://www.knurt.de)
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
package de.knurt.heinzelmann.util.velocity;

import java.io.StringWriter;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

import de.knurt.heinzelmann.util.validation.AssertOrException;

/**
 * simple string implementation. use the root logger for logging.
 * 
 * @see Logger#getRootLogger()
 * 
 * @author Daniel Oltmanns
 * @since 1.00 SNAPSHOT (08/27/2010)
 * @version 1.00 SNAPSHOT (08/27/2010)
 */
public class VelocityStringRenderUtil implements VelocityRenderUtil<String> {

	/** one and only instance of me */
	private volatile static VelocityStringRenderUtil me;
	private VelocityEngine ve;
	private StringResourceRepository resource = null;

	/** construct me */
	private VelocityStringRenderUtil() {
		ve = new VelocityEngine();
		ve.addProperty(Velocity.RESOURCE_LOADER, "string");
		ve.addProperty("string.resource.loader.class", "org.apache.velocity.runtime.resource.loader.StringResourceLoader");
		ve.addProperty("string.resource.loader.repository.class", "org.apache.velocity.runtime.resource.util.StringResourceRepositoryImpl");
		ve.addProperty("string.resource.loader.description", "Velocity StringResource loader");
		if (Logger.getRootLogger() != null) {
			BasicConfigurator.configure();
			ve.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
			ve.setProperty("runtime.log.logsystem.log4j.logger", Logger.getRootLogger().getName());
		}
		try {
			ve.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.resource = StringResourceLoader.getRepository();
		this.resource.setEncoding("UTF-8");
		AssertOrException.notNull(this.resource, "resource is null");
	}

	/**
	 * return the one and only instance of AccessTemplate
	 * 
	 * @return the one and only instance of AccessTemplate
	 */
	public static VelocityStringRenderUtil getInstance() {
		if (me == null) { // no instance so far
			synchronized (VelocityStringRenderUtil.class) {
				if (me == null) { // still no instance so far
					me = new VelocityStringRenderUtil(); // the one and only
				}
			}
		}
		return me;
	}

	public String getRendered(String template, VelocityContext context) {
		AssertOrException.notNull(template, "cannot handle a null template here");
		AssertOrException.notNull(context, "context is null");
		String result = "";
		this.resource.putStringResource("tmp", template);
		try {
			Template t = ve.getTemplate("tmp", this.resource.getEncoding());
			StringWriter writer = new StringWriter();
			t.merge(context, writer);
			result = writer.toString();
		} catch (ResourceNotFoundException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (ParseErrorException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

}
