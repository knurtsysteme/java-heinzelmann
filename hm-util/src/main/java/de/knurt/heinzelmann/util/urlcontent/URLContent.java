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

/**
 * work with content of an unsecured ssl connection
 *
 * @author Daniel Oltmanns
 * @since 0.20100728
 * @version 0.20100728
 */
public interface URLContent {
    public String getContent(String urlstring) throws IOException, HTTPException;
}
