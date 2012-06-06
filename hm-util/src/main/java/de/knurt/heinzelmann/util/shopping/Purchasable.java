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
package de.knurt.heinzelmann.util.shopping;

/**
 * does nothing but to sign, this can be buyed.
 * it does not have to be an article itself, but knows, what article it is and
 * can purchse it. Anyway - it can be purchase itself as well.
 * @author Daniel Oltmanns
 * @since 0.1 29.06.2009
 * @version 0.20091104
 */
public interface Purchasable {
    /**
     * return the article number as id for this article.
     * @return the article number as id for this article.
     */
    public String getArticleNumber();
    
    /**
     * purchase one of the article.
     * @return true, if it has been purchased.
     */
    public boolean purchase();

}