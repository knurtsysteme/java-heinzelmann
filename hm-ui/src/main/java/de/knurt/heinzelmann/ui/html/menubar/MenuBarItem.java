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
package de.knurt.heinzelmann.ui.html.menubar;

import java.util.List;

/**
 * COMMENT me
 * 
 * @author Daniel Oltmanns
 * @since 15.09.2009
 * @version 0.20091104
 */
public interface MenuBarItem<T extends MenuBarItem<?>> {

	public boolean hasChildren();

	/**
	 * add a child to this item
	 * 
	 * @param mbi
	 *            item being a child of this item
	 */
	public void addChild(T child);

	/**
	 * return the label of the item
	 * 
	 * @return the label of the item
	 */
	public String getLabel();

	/**
	 * return children items
	 * 
	 * @return children items
	 */
	public List<T> getChildren();

	/**
	 * return true, if this item is a seperator
	 * 
	 * @return true, if this item is a seperator
	 */
	public boolean isSeperator();

	/**
	 * set this item as a seperator
	 */
	public void setIsSeperator();

	/**
	 * set this item as a none seperator
	 */
	public void setIsNotSeperator();
}
