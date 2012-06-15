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

import java.util.ArrayList;
import java.util.List;

/**
 * COMMENT me
 * 
 * @author Daniel Oltmanns
 * @since 15.09.2009
 * @version 0.20091104
 */
public class BrainjarMenuBar implements MenuBar<BrainjarMenuBarItem> {

	/**
	 * construct it
	 * 
	 * @param mainMenuBarItems
	 *            the (ever viewable) top items
	 */
	public BrainjarMenuBar(List<BrainjarMenuBarItem> mainMenuBarItems) {
		this.menuBarItems = mainMenuBarItems;
	}

	@Override
	public String getSeperator() {
		return "<hr />";
	}

	private String output = null;
	private List<BrainjarMenuBarItem> menuBarItems = new ArrayList<BrainjarMenuBarItem>();

	@Override
	public void addMenuBarMainItem(BrainjarMenuBarItem mbi) {
		this.menuBarItems.add(mbi);
	}

	private void setMenuBarMainItem(BrainjarMenuBarItem mbi, boolean toplevel) {
		if (mbi.isSeperator()) {
			this.output += "<div class=\"menuItemSep\"></div>";
		} else {
			if (toplevel) {
				this.output += "<div id=\"" + this.getId(mbi) + "\" class=\"menu\" onmouseover=\"menuMouseover(event)\">";
			} else {
				this.output += "<div id=\"" + this.getId(mbi) + "\" class=\"menu\" onmouseover=\"menuMouseover(event)\">";
			}
			for (BrainjarMenuBarItem child : mbi.getChildren()) {
				this.setSingleNode(child);
			}
			this.output += "</div>";

			// submenu
			for (BrainjarMenuBarItem child : mbi.getChildren()) {
				this.setMenuBarMainItem(child, false);
			}
		}
	}

	@Override
	public String getOutput() {
		if (this.output == null) {
			// set main bar
			this.output = "<div class=\"menuBar\">";
			for (BrainjarMenuBarItem mbi : menuBarItems) {
				String toFormat = "<span class=\"menuButton\" onclick=\"return buttonClick(event, '%s');\" onmouseover=\"buttonMouseover(event, '%s');\">%s</span>";
				this.output += String.format(toFormat, this.getId(mbi), this.getId(mbi), mbi.getLabel());
			}
			this.output += "</div>";

			// set entries
			for (BrainjarMenuBarItem mbi : menuBarItems) {
				if (mbi.isSeperator()) {
					this.output += this.getSeperator();
				} else { // it must bei a MenuBarItem
					this.setMenuBarMainItem(mbi, true);
				}
			}
		}
		return this.output;
	}

	private String getId(MenuBarItem<?> mbi) {
		return mbi.hashCode() + "";
	}

	private void setSingleNode(BrainjarMenuBarItem child) {
		if (child.isSeperator()) {
			this.output += "<div class=\"menuItemSep\"></div>";
		} else {
			if (child.hasChildren()) {
				this.output += "<a class=\"menuItem\" onclick=\"return false;\" href=\"\" onmouseover=\"menuItemMouseover(event, '" + this.getId(child) + "');\">";
				this.output += "<span class=\"menuItemText\">" + child.getLabel() + "</span><span class=\"menuItemArrow\">" + MORE_ENTITY + "</span></div>";
			} else {
				this.output += "<a class=\"menuItem\" onclick=\"return false;\" href=\"\" id=\"" + this.getId(child) + "\">";
				this.output += child.getLabel();
				this.output += "</a>";
			}
		}
	}

	private final static String MORE_ENTITY = "&#9654;";
}
