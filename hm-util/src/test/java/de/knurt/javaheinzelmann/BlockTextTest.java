package de.knurt.javaheinzelmann;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

import de.knurt.heinzelmann.util.itext.TextBlock;

public class BlockTextTest {
	@Test
	public void getTextBlockWithNoBoldItalics() {
		try {
			String teststring = "Lorem ipsum";
			Font testfont = new Font(BaseFont.createFont(), 11f, Font.NORMAL);
			TextBlock tb = new TextBlock(testfont, teststring);
			List<Chunk> chunks = tb.getChunks();
			assertEquals(1, chunks.size());
			assertEquals(teststring, chunks.get(0).getContent());
			assertEquals(Font.NORMAL, chunks.get(0).getFont().getStyle());
		} catch (Exception e) {
			fail("should not throw e");
		}
	}

	@Test
	public void getTextBlockWithKursiv1() {
		try {
			String teststring = "This is a *italic* foo";
			Font testfont = new Font(BaseFont.createFont(), 11f, Font.NORMAL);
			TextBlock tb = new TextBlock(testfont, teststring);
			List<Chunk> chunks = tb.getChunks();
			assertEquals(3, chunks.size());
			assertEquals("This is a ", chunks.get(0).getContent());
			assertEquals("italic", chunks.get(1).getContent());
			assertEquals(" foo", chunks.get(2).getContent());
			assertEquals(Font.NORMAL, chunks.get(0).getFont().getStyle());
			assertEquals(Font.ITALIC, chunks.get(1).getFont().getStyle());
			assertEquals(Font.NORMAL, chunks.get(2).getFont().getStyle());
		} catch (Exception e) {
			fail("should not throw e");
		}
	}

	@Test
	public void getTextBlockWithBold() {
		try {
			String teststring = "This is a **bold** foo";
			Font testfont = new Font(BaseFont.createFont(), 11f, Font.NORMAL);
			TextBlock tb = new TextBlock(testfont, teststring);
			List<Chunk> chunks = tb.getChunks();
			assertEquals(3, chunks.size());
			assertEquals("This is a ", chunks.get(0).getContent());
			assertEquals("bold", chunks.get(1).getContent());
			assertEquals(" foo", chunks.get(2).getContent());
			assertEquals(Font.NORMAL, chunks.get(0).getFont().getStyle());
			assertEquals(Font.BOLD, chunks.get(1).getFont().getStyle());
			assertEquals(Font.NORMAL, chunks.get(2).getFont().getStyle());
		} catch (Exception e) {
			fail("should not throw e");
		}
	}

	@Test
	public void invalid() {
		try {
			String teststring = "This is a ***bold** foo**";
			Font testfont = new Font(BaseFont.createFont(), 11f, Font.NORMAL);
			TextBlock tb = new TextBlock(testfont, teststring);
			tb.getChunks();
			assertTrue("no exception on invalid", true);
		} catch (Exception e) {
			fail("should not throw e");
		}
	}

	@Test
	public void getTextBlockWithAll() {
		try {
			String teststring = "This is a **bold** foo *italic text *with ***bolditalic*** and *italic*";
			Font testfont = new Font(BaseFont.createFont(), 11f, Font.NORMAL);
			TextBlock tb = new TextBlock(testfont, teststring);
			List<Chunk> chunks = tb.getChunks();
			assertEquals(8, chunks.size());
			assertEquals("This is a ", chunks.get(0).getContent());
			assertEquals("bold", chunks.get(1).getContent());
			assertEquals(" foo ", chunks.get(2).getContent());
			assertEquals("italic text ", chunks.get(3).getContent());
			assertEquals("with ", chunks.get(4).getContent());
			assertEquals("bolditalic", chunks.get(5).getContent());
			assertEquals(" and ", chunks.get(6).getContent());
			assertEquals("italic", chunks.get(7).getContent());
			assertEquals(Font.NORMAL, chunks.get(0).getFont().getStyle());
			assertEquals(Font.BOLD, chunks.get(1).getFont().getStyle());
			assertEquals(Font.NORMAL, chunks.get(2).getFont().getStyle());
			assertEquals(Font.ITALIC, chunks.get(3).getFont().getStyle());
			assertEquals(Font.NORMAL, chunks.get(4).getFont().getStyle());
			assertEquals(Font.BOLDITALIC, chunks.get(5).getFont().getStyle());
			assertEquals(Font.NORMAL, chunks.get(6).getFont().getStyle());
			assertEquals(Font.ITALIC, chunks.get(7).getFont().getStyle());
		} catch (Exception e) {
			fail("should not throw e");
		}
	}

	@Test
	public void getTextBlockWithKursiv2() {
		try {
			String teststring = "*italic* foo *italic* bar";
			Font testfont = new Font(BaseFont.createFont(), 11f, Font.NORMAL);
			TextBlock tb = new TextBlock(testfont, teststring);
			List<Chunk> chunks = tb.getChunks();
			assertEquals(4, chunks.size());
			assertEquals("italic", chunks.get(0).getContent());
			assertEquals(" foo ", chunks.get(1).getContent());
			assertEquals("italic", chunks.get(2).getContent());
			assertEquals(" bar", chunks.get(3).getContent());
			assertEquals(Font.ITALIC, chunks.get(0).getFont().getStyle());
			assertEquals(Font.NORMAL, chunks.get(1).getFont().getStyle());
			assertEquals(Font.ITALIC, chunks.get(2).getFont().getStyle());
			assertEquals(Font.NORMAL, chunks.get(3).getFont().getStyle());
		} catch (Exception e) {
			fail("should not throw e");
		}
	}
}
