package de.knurt.javaheinzelmann;

import de.knurt.heinzelmann.util.urlcontent.HttpsUnsecuredContent;

import org.junit.Test;

import static org.junit.Assert.*;

public class HttpsContentTest {

    @Test
    public void testGetContent() throws Exception {
        String content = HttpsUnsecuredContent.getInstance().getContent("https://www.google.de");
        assertFalse(content.isEmpty());
    }
}
