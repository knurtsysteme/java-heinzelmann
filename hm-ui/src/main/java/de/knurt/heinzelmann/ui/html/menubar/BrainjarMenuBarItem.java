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
public class BrainjarMenuBarItem implements MenuBarItem<BrainjarMenuBarItem> {

    private List<BrainjarMenuBarItem> children = new ArrayList<BrainjarMenuBarItem>();
    private String label = "";
    private boolean isSeperator = false;

    /**
     * construct an item with label
     */
    public BrainjarMenuBarItem(String label) {
        this.label = label;
    }

    @Override
    public boolean hasChildren() {
        return this.children.size() > 0;
    }

    @Override
    public void addChild(BrainjarMenuBarItem child) {
        this.children.add(child);
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public List<BrainjarMenuBarItem> getChildren() {
        return this.children;
    }

    @Override
    public boolean isSeperator() {
        return this.isSeperator;
    }

    @Override
    public void setIsSeperator() {
        this.isSeperator = true;
    }

    @Override
    public void setIsNotSeperator() {
        this.isSeperator = false;
    }
}
