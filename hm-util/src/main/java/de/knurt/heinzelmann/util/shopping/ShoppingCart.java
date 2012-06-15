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
package de.knurt.heinzelmann.util.shopping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * COMMENT comment me
 *
 * @author Daniel Oltmanns
 * @since 0.1 29.06.2009
 * @version 0.20091104
 */
public class ShoppingCart {

    private Map<String, Purchasable> articles;

    public ShoppingCart() {
	this.empty();
    }

    public void addArticle(Purchasable article) {
	this.getArticles().put(article.getArticleNumber(), article);
    }
    public void addArticles(List<Purchasable> freeTimeSlots) {
	for (Purchasable freeTimeSlot : freeTimeSlots) {
	    this.addArticle(freeTimeSlot);
	}
    }

    public void empty() {
	this.articles = new HashMap<String, Purchasable>();
    }

    public Purchasable getArticle(String key) {
	return this.getArticles().get(key);
    }

    public Set<String> getArticleNumbers() {
	return this.getArticles().keySet();
    }

    /**
     * @return the articles
     */
    public Map<String, Purchasable> getArticles() {
	return articles;
    }

    /**
     * @param articles the articles to set
     */
    public void setArticles(Map<String, Purchasable> articles) {
	this.articles = articles;
    }
}