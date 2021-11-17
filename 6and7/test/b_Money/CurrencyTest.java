package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception
	{
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName()
	{
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate()
	{
		assertEquals(Double.valueOf(0.15), SEK.getRate());
		assertEquals(Double.valueOf(0.20), DKK.getRate());
		assertEquals(Double.valueOf(1.5), EUR.getRate());
	}
	
	@Test
	public void testSetRate()
	{
		SEK.setRate(0.5);
		assertEquals(Double.valueOf(0.5), SEK.getRate());
		DKK.setRate(0.02);
		assertEquals(Double.valueOf(0.02), DKK.getRate());
		EUR.setRate(15.0);
		assertEquals(Double.valueOf(15.0), EUR.getRate());
	}
	
	@Test
	public void testGlobalValue()
	{
		assertEquals(Integer.valueOf(15), SEK.universalValue(1));
		assertEquals(Integer.valueOf(40), DKK.universalValue(2));
		assertEquals(Integer.valueOf(450), EUR.universalValue(3));
		assertEquals(Integer.valueOf(150), SEK.universalValue(10));
		assertEquals(Integer.valueOf(2000), DKK.universalValue(100));
		assertEquals(Integer.valueOf(150000), EUR.universalValue(1000));
	}
	
	@Test
	public void testValueInThisCurrency()
	{
		assertEquals(Integer.valueOf(5), SEK.valueInThisCurrency(5, SEK));
		assertEquals(Integer.valueOf(1500), SEK.valueInThisCurrency(150, EUR));
		assertEquals(Integer.valueOf(1400), SEK.valueInThisCurrency(1050, DKK));
		assertEquals(Integer.valueOf(0), EUR.valueInThisCurrency(5, SEK));
		assertEquals(Integer.valueOf(150), EUR.valueInThisCurrency(150, EUR));
		assertEquals(Integer.valueOf(140), EUR.valueInThisCurrency(1050, DKK));
		assertEquals(Integer.valueOf(3), DKK.valueInThisCurrency(5, SEK));
		assertEquals(Integer.valueOf(1125), DKK.valueInThisCurrency(150, EUR));
		assertEquals(Integer.valueOf(1050), DKK.valueInThisCurrency(1050, DKK));
	}
}
