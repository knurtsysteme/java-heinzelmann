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
/*
 * Copyright 2005 - 2009 KNURT Systeme (http://www.knurt.de)
 */
package de.knurt.heinzelmann.util.urlcontent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * returns responses from requested urls as strings
 * 
 * @author Daniel Oltmanns
 * @since 0.20090403
 * @version 0.20091104
 */
public class SimpleURLContent implements URLContent {

    /**
     * return answer from request to given urlstring
     *
     * @param urlstring
     *            to request
     * @return answer from given urlstring
     * @throws java.net.MalformedURLException
     * @throws java.io.IOException
     */
    @Override
    public String getContent(String urlstring) throws MalformedURLException, IOException {
        URL url = new URL(urlstring);
        URLConnection connection = url.openConnection();
        return SimpleURLContent.getContent(connection);
    }

    /**
     * return answer from request to given connection
     *
     * @param connection
     *            to use
     * @return answer from request to given connection
     * @throws java.io.IOException
     */
    public static String getContent(URLConnection connection) throws IOException {
        String result = "";
        // TODO when this must be there?
        // if(connection.getDoOutput() == false) {
        // connection.setDoOutput(true);
        // }
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        in.close();
        return result;
    }
    /** one and only instance of me */
    private volatile static SimpleURLContent me;

    /** construct me */
    private SimpleURLContent() {
    }

    /**
     * return the one and only instance of SimpleURLContent
     *
     * @return the one and only instance of SimpleURLContent
     */
    public static SimpleURLContent getInstance() {
        if (me == null) { // no instance so far
            synchronized (SimpleURLContent.class) {
                if (me == null) { // still no instance so far
                    me = new SimpleURLContent(); // the one and only
                }
            }
        }
        return me;
    }
}
