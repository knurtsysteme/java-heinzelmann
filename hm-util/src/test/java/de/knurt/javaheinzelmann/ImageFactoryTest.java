package de.knurt.javaheinzelmann;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import com.itextpdf.text.Image;

import de.knurt.heinzelmann.util.itext.ImageFactoryDefault;
import de.knurt.heinzelmann.util.itext.ResizeMode;

public class ImageFactoryTest {

	@Test
	public void getImageScaleToFit() {
		File image = new File(ClassLoader.getSystemResource("knurt_logo.gif").getFile());
		Image got;
		try {
			got = new ImageFactoryDefault().getImageFromFileResource(image, 10, 20, 0, 0);
			assertNotNull(got);
			assertEquals(10, got.getScaledWidth(), 0.3);
			assertEquals(3.8, got.getScaledHeight(), 0.3);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void getImageSkew() {
		File image = new File(ClassLoader.getSystemResource("knurt_logo.gif").getFile());
		Image got;
		try {
			got = new ImageFactoryDefault(ResizeMode.SKEW).getImageFromFileResource(image, 10, 20, 0, 0);
			assertNotNull(got);
			assertEquals(10, got.getScaledWidth(), 0.3);
			assertEquals(20, got.getScaledHeight(), 0.3);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
