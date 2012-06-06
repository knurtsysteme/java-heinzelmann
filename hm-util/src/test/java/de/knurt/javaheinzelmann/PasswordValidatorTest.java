package de.knurt.javaheinzelmann;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.knurt.heinzelmann.util.validation.PasswordValidator;
import static org.junit.Assert.*;

/**
 * 
 * @author danieloltmanns
 */
public class PasswordValidatorTest {

	public PasswordValidatorTest() {
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
	public void testGeneralInvaldPassword() {
		PasswordValidator pv = new PasswordValidator();
		assertFalse(pv.isValid(null));

		String password = "AAaa11_x";
		assertTrue(pv.isValid(password));

		password = password + " " + "x"; // whitespace is invalid
		assertFalse(pv.isValid(password));
	}

	@Test
	public void testPasswordMaxDigitsAndChars() {
		PasswordValidator pv = new PasswordValidator();
		pv.setMinLength(0);
		pv.setMaxLength(5);
		pv.setMinDigits(0);
		pv.setMinUpper(0);
		pv.setMinSpecial(0);
		assertTrue(pv.isValid("foo"));
		assertFalse(pv.isValid("foobar"));
	}

	@Test
	public void testPasswordMinDigitsAndChars() {
		PasswordValidator pv = new PasswordValidator();
		pv.setMinLength(0);
		pv.setMaxLength(200);
		pv.setMinDigits(0);
		pv.setMinUpper(0);
		pv.setMinSpecial(0);

		String password = "aaaa";
		Boolean result = pv.isValid(password);
		assertTrue(result);

		pv.setMinLength(5);
		result = pv.isValid(password);
		assertFalse(result);

		password += password + "a";
		result = pv.isValid(password);
		assertTrue(result);

		pv.setMinDigits(1);
		result = pv.isValid(password);
		assertFalse(result);

		password += password + "1";
		result = pv.isValid(password);
		assertTrue(result);

		pv.setMinUpper(1);
		result = pv.isValid(password);
		assertFalse(result);

		password += password + "A";
		result = pv.isValid(password);
		assertTrue(password, result);

		pv.setMinSpecial(1);
		result = pv.isValid(password);
		assertFalse(result);

		password += password + "_";
		result = pv.isValid(password);
		assertTrue(result);

		pv.setMinLower(1);
		password = "AS23_AAAA";
		result = pv.isValid(password);
		assertFalse(result);

		password += password + "a";
		result = pv.isValid(password);
		assertTrue(result);
	}

}