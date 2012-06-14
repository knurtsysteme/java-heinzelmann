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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

/**
 * create images default implementation
 * 
 * @since 06/14/2012
 * @author danieloltmanns@knurt.de
 */
public class ImageFactoryDefault implements ImageFactory {

	private ResizeMode rmUsed;

	public ImageFactoryDefault() {
		this(ResizeMode.SCALE_TO_FIT);
	}
	public ImageFactoryDefault(ResizeMode mode) {
		this.rmUsed = mode;
	}

	/**
	 * scale image using {@link ResizeMode}
	 */
	@Override
	public Image getImageFromFileResource(File image, float width, float height, float absoluteX, float absoluteY) throws BadElementException, MalformedURLException, IOException {
		Image result = Image.getInstance(image.getAbsolutePath());
		switch (rmUsed) {
		case SCALE_TO_FIT:
			result.scaleToFit(width, height);
			break;
		case SKEW:
			if (result.getScaledWidth() != width) {
				result.scaleAbsoluteWidth(width);
			}
			if (result.getScaledHeight() != height) {
				result.scaleAbsoluteHeight(height);
			}
			break;
		}
		result.setAbsolutePosition(absoluteX, absoluteY);
		return result;
	}
}
