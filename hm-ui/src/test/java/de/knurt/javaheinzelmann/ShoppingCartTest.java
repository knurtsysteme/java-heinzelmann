package de.knurt.javaheinzelmann;

import de.knurt.heinzelmann.util.shopping.Purchasable;
import de.knurt.heinzelmann.util.shopping.ShoppingCart;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author danieloltmanns
 */
public class ShoppingCartTest {

    public ShoppingCartTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void constructShoppingCart() {
	ShoppingCart sc = new ShoppingCart();
	assertEquals(ShoppingCart.class, sc.getClass());
    }

    @Test
    public void dontShopItTwice() {
	ShoppingCart sc = new ShoppingCart();
	Purchasable o = new ObjectArticle();
	assertEquals(0, sc.getArticles().size());
	sc.addArticle(o);
	assertEquals(1, sc.getArticles().size());
	sc.addArticle(o);
	assertEquals(1, sc.getArticles().size());
    }
    public void shop() {
	ShoppingCart sc = new ShoppingCart();
	Purchasable o = new ObjectArticle();
	assertEquals(0, sc.getArticles().size());

	sc.addArticle(o);
	assertEquals(1, sc.getArticles().size());
    }

}

class ObjectArticle implements Purchasable {

    public String getArticleNumber() {
        return this.hashCode()+"";
    }

    public boolean purchase() {
        return true;
    }

}