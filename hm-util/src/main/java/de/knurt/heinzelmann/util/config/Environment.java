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
package de.knurt.heinzelmann.util.config;

/**
 * An environment.
 *
 * This describes an environment, that can be a web server or a localhost or
 * a stand alone computer.
 *
 * It provides one abstract method, that return true, if the system runs
 * under this description.
 *
 * @author Daniel Oltmanns
 * @since 0.20090402
 * @version 0.20091104
 */
public abstract class Environment implements ApplicationConfiguration {

    private String envDependentDataPath, applicationRoot, applicationUrl;

    private boolean dev;

    /**
     * return the path, where data is saved.
     * @return path, where data is saved
     */
    @Override
    public String getEnvDependentDataPath() {
	return envDependentDataPath;
    }

    /**
     * set path, where data is saved
     * @param envDependentDataPath the envDependentDataPath to set
     */
    public void setEnvDependentDataPath(String envDependentDataPath) {
	this.envDependentDataPath = envDependentDataPath;
    }

    /**
     * get absolute root directory in the file system
     * @return absolute root directory in the file system
     */
    public String getApplicationRoot() {
	return applicationRoot;
    }

    /**
     * set absolute root directory in the file system
     * @param applicationRoot absolute root directory in the file system
     */
    public void setApplicationRoot(String applicationRoot) {
	this.applicationRoot = applicationRoot;
    }

    /**
     * get uri of the environment.
     * uri can be "http://www.myapp.com" on web applications or
     * "file:/usr/bin" on local applications.
     * @return the applicationUri
     */
    public String getApplicationUrl() {
	return applicationUrl;
    }

    /**
     * set uri of the environment
     * @param applicationUrl the applicationUri to set
     */
    public void setApplicationUrl(String applicationUrl) {
	this.applicationUrl = applicationUrl;
    }

    /**
     * return true, if it is a development environment.
     * This might be useful, if you want to support functions only
     * be there in development environment.
     * @return true, if it is a development environment.
     */
    @Override
    public boolean isDev() {
	return dev;
    }

    /**
     * set true, if it is a development environment.
     * @param dev the dev to set
     */
    public void setDev(boolean dev) {
	this.dev = dev;
    }

    /**
     * return true, if this is running on me.
     * @return true, if this is running on me
     */
    public abstract boolean actualEnvIsMe();

}