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
/*
 * Copyright 2005 - 2009 KNURT Systeme (http://www.knurt.de)
 */
package de.knurt.heinzelmann.util.urlcontent;

import java.io.IOException;

import javax.xml.ws.http.HTTPException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;

/**
 * work with content of an unsecured ssl connection. do not use if it can be a
 * safty leg!
 * 
 * @link 
 *       http://svn.apache.org/viewvc/httpcomponents/oac.hc3x/trunk/src/contrib/org
 *       /apache/commons/httpclient/contrib/ssl/EasySSLProtocolSocketFactory.
 *       java?view=markup
 * @author Daniel Oltmanns
 * @since 0.20100728
 * @version 0.20100728
 */
public class HttpsUnsecuredContent implements URLContent {

	@SuppressWarnings("deprecation")
	@Override
	public String getContent(String urlstring) throws IOException, HTTPException {
		Protocol easyhttps = new Protocol("https", new EasySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", easyhttps);

		HttpClient client = new HttpClient();
		GetMethod httpget = new GetMethod(urlstring);
		client.executeMethod(httpget);
		return httpget.getResponseBodyAsString();
	}

	/** one and only instance of me */
	private volatile static HttpsUnsecuredContent me;

	/** construct me */
	private HttpsUnsecuredContent() {
	}

	/**
	 * return the one and only instance of HttpsUnsecuredContent
	 * 
	 * @return the one and only instance of HttpsUnsecuredContent
	 */
	public static HttpsUnsecuredContent getInstance() {
		if (me == null) { // no instance so far
			synchronized (HttpsUnsecuredContent.class) {
				if (me == null) { // still no instance so far
					me = new HttpsUnsecuredContent(); // the one and only
				}
			}
		}
		return me;
	}
}
