package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest
{
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;

	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount()
	{
		assertEquals(Integer.valueOf(10000), SEK100.getAmount());
		assertEquals(Integer.valueOf(1000), EUR10.getAmount());
		assertEquals(Integer.valueOf(20000), SEK200.getAmount());
		assertEquals(Integer.valueOf(2000), EUR20.getAmount());
		assertEquals(Integer.valueOf(0), SEK0.getAmount());
		assertEquals(Integer.valueOf(0), EUR0.getAmount());
		assertEquals(Integer.valueOf(-10000), SEKn100.getAmount());
	}

	@Test
	public void testGetCurrency()
	{
		assertEquals(SEK, SEK100.getCurrency());
		assertEquals(EUR, EUR10.getCurrency());
		assertEquals(SEK, SEK200.getCurrency());
		assertEquals(EUR, EUR20.getCurrency());
		assertEquals(SEK, SEK0.getCurrency());
		assertEquals(EUR, EUR0.getCurrency());
		assertEquals(SEK, SEKn100.getCurrency());
	}

	@Test
	public void testToString()
	{
		assertEquals("100.0 SEK", SEK100.toString());
		assertEquals("10.0 EUR", EUR10.toString());
		assertEquals("200.0 SEK", SEK200.toString());
		assertEquals("20.0 EUR", EUR20.toString());
		assertEquals("0.0 SEK", SEK0.toString());
		assertEquals("0.0 EUR", EUR0.toString());
		assertEquals("-100.0 SEK", SEKn100.toString());
	}

	@Test
	public void testGlobalValue()
	{
		assertEquals(Integer.valueOf(150000), SEK100.universalValue());
		assertEquals(Integer.valueOf(150000), EUR10.universalValue());
		assertEquals(Integer.valueOf(300000), SEK200.universalValue());
		assertEquals(Integer.valueOf(300000), EUR20.universalValue());
		assertEquals(Integer.valueOf(0), SEK0.universalValue());
		assertEquals(Integer.valueOf(0), EUR0.universalValue());
		assertEquals(Integer.valueOf(-150000), SEKn100.universalValue());
	}

	@Test
	public void testEqualsMoney()
	{
		assertTrue(SEK100.equals(SEK100));
		assertTrue(EUR10.equals(EUR10));
		assertTrue(SEK200.equals(SEK200));
		assertTrue(EUR20.equals(EUR20));
		assertTrue(SEK0.equals(SEK0));
		assertTrue(EUR0.equals(EUR0));
		assertTrue(SEKn100.equals(SEKn100));
		assertFalse(SEK100.equals(SEKn100));
		assertFalse(EUR10.equals(EUR0));
		assertFalse(SEK200.equals(SEK0));
		assertFalse(EUR20.equals(SEK100));
		assertFalse(SEK0.equals(SEK200));
		assertFalse(EUR0.equals(EUR10));
		assertFalse(SEKn100.equals(SEK100));
	}

	@Test
	public void testAdd()
	{
		fail("Write test case here");
	}

	@Test
	public void testSub()
	{
		fail("Write test case here");
	}

	@Test
	public void testIsZero()
	{
		fail("Write test case here");
	}

	@Test
	public void testNegate()
	{
		fail("Write test case here");
	}

	@Test
	public void testCompareTo()
	{
		fail("Write test case here");
	}
}
