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
 * create images
 * 
 * @since 06/14/2012
 * @author danieloltmanns@knurt.de
 */
public interface ImageFactory {

	/**
	 * return an image given scaled to given width and height and set to given
	 * position
	 * 
	 * @param image
	 *            to use
	 * @param width
	 *            to scale to
	 * @param height
	 *            to scale to
	 * @param absoluteX
	 * @param absoluteY
	 * @return an image
	 * @throws BadElementException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public Image getImageFromFileResource(File image, float width, float height, float absoluteX, float absoluteY) throws BadElementException, MalformedURLException, IOException;
}
