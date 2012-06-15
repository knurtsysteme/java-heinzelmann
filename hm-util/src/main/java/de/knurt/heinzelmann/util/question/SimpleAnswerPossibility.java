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
package de.knurt.heinzelmann.util.question;


/**
 * COMMENT me
 *
 * @author Daniel Oltmanns
 * @since 27.09.2009
 * @version 0.20091104
 */
public class SimpleAnswerPossibility implements AnswerPossibility {

    private String possibility;
    private boolean selected = false;

    /**
     * @return the possibility
     */
    @Override
    public String getPossibility() {
        return possibility;
    }

    /**
     * @param possibility the possibility to set
     */
    public void setPossibility(String possibility) {
        this.possibility = possibility;
    }

    /**
     * @return the selected
     */
    @Override
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }




}
