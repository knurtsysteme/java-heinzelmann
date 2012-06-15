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
package de.knurt.heinzelmann.util.itext;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

/**
 * yet another textblock handler. this is for fonts and are working with simple text
 * marking like: this is *kursiv text* and this is **bold text** and this is ***bold
 * italic text*** and this is *kursiv* again. the text supports {@link Font} and
 * {@link BaseFont} (so no need to switch).
 * 
 * @since 06/15/2012
 * @author danieloltmanns@knurt.de
 */
public class TextBlock {
	private float size, leading, spacingAfter;
	private BaseFont baseFont;
	private Font font;
	private List<Chunk> chunks;

	public TextBlock(Font font, String content) {
		this(font.getSize(), font.getBaseFont(), font, content);
	}

	private TextBlock(float size, BaseFont baseFont, Font font, String content) {
		super();
		this.size = size;
		this.baseFont = baseFont;
		this.font = font;
		this.chunks = this.getChunks(content);
		this.leading = this.size * 1.2f;
		this.spacingAfter = this.size / 2;
	}

	public TextBlock(float size, BaseFont baseFont, String content) {
		this(size, baseFont, new Font(baseFont, size), content);
	}

	private List<Chunk> getChunks(String content) {
		Font italic = new Font(this.font);
		italic.setStyle(Font.ITALIC);
		Font normal = new Font(this.font);
		normal.setStyle(Font.NORMAL);
		Font bold = new Font(this.font);
		bold.setStyle(Font.BOLD);
		Font bolditalic = new Font(this.font);
		bolditalic.setStyle(Font.BOLDITALIC);
		Font markfont = null;
		boolean markModus = false;

		List<Chunk> result = new ArrayList<Chunk>();
		List<Chunk> tmp = new ArrayList<Chunk>();

		StringTokenizer tokenizer = new StringTokenizer(content, "\\*", true);
		while (tokenizer.hasMoreTokens()) {
			String tok = tokenizer.nextToken();
			if (tok.equals("*")) {
				if (markModus) {
					if (markfont.getStyle() == Font.ITALIC) {
						markfont = bold;
					} else {
						markfont = bolditalic;
					}
				} else {
					if (markfont == null) {
						markfont = italic;
						markModus = true;
					} else if (markfont.getStyle() == Font.BOLDITALIC) {
						markfont = bold;
					} else if (markfont.getStyle() == Font.BOLD) {
						markfont = italic;
					} else if (markfont.getStyle() == Font.ITALIC) {
						markfont = null;
					}
				}
				continue;
			}
			if (markfont != null) {
				tmp.add(new Chunk(tok, markfont));
				markModus = false;
			} else {
				tmp.add(new Chunk(tok, normal));
			}
		}
		result.addAll(tmp);
		return result;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public BaseFont getBaseFont() {
		return baseFont;
	}

	public void setBaseFont(BaseFont baseFont) {
		this.baseFont = baseFont;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public List<Chunk> getChunks() {
		return this.chunks;
	}

	public float getLeading() {
		return leading;
	}

	public void setLeading(float leading) {
		this.leading = leading;
	}

	public float getSpacingAfter() {
		return spacingAfter;
	}

	public void setSpacingAfter(float spacingAfter) {
		this.spacingAfter = spacingAfter;
	}

}
