/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.knurt.javaheinzelmann;

import de.knurt.heinzelmann.util.query.QueryString;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author danieloltmanns
 */
public class QueryStringTest {

    public QueryStringTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void escape() {
        QueryString qs = new QueryString();
        qs.put("ä", "ö");
        assertEquals("?%C3%A4=%C3%B6", qs.toString());
    }
}
