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
package de.knurt.heinzelmann.util.query;

/**
 * COMMENT me
 *
 * @author Daniel Oltmanns
 * @since 15.10.2009
 * @version 0.20091104
 */
public class QueryKeys {

    public final static String DELETE = get(1);
    public final static String FLAG = get(2);
    public final static String FLAG_1 = get(3);
    public final static String FLAG_2 = get(4);
    public final static String FLAG_3 = get(5);
    public final static String FLAG_4 = get(6);
    public final static String FLAG_5 = get(7);
    public final static String FLAG_6 = get(8);
    public final static String FLAG_7 = get(9);
    public final static String FLAG_8 = get(10);
    public final static String FLAG_9 = get(11);
    public final static String FROM_10 = get(12);
    public final static String TO = get(13);
    public final static String DATE_AND_TIME = get(14);
    public final static String DATE = get(15);
    public final static String TIME = get(16);

    private static String get(int id) {
        return id+"";
    }

    private QueryKeys() {
    }
}
