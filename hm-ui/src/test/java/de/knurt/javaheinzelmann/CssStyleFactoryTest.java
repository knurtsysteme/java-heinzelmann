package de.knurt.javaheinzelmann;

import de.knurt.heinzelmann.ui.CssStyleFactory;
import java.awt.Color;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author danieloltmanns
 */
public class CssStyleFactoryTest {

    public CssStyleFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void getColor() {
        assertEquals("#0000ff", CssStyleFactory.getInstance().getColor(Color.BLUE));
        assertEquals("#9c0f0f", CssStyleFactory.getInstance().getColor(new Color(156, 15, 15)));
    }
}