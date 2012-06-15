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
package de.knurt.heinzelmann.util.graphics.text;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * return metrics using java.awt.Graphics
 * 
 * @author Daniel Oltmanns
 * @since 13.10.2009
 * @version 0.20091104
 */
public class StringMetricsGraphics implements StringMetrics {
	private Font font;

	public StringMetricsGraphics(Font font) {
		this.font = font;
	}

	@Override
	public int getWidth(String raw) {
		BufferedImage tempImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		return this.getWidth((Graphics2D) tempImage.getGraphics(), raw);
	}

	public int getWidth(Graphics2D g2d, String raw) {
		FontMetrics metrics = g2d.getFontMetrics(font);
		return metrics.stringWidth(raw);
	}
}
