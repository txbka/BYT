package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest
{
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;

	@Before
	public void setUp() throws Exception
	{
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName()
	{
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency()
	{
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(SEK, Nordea.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException
	{
		SweBank.openAccount("test");
		assertEquals(Integer.valueOf(0), SweBank.getBalance("test"));
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testOpenAccountAccountDoesNotExistException() throws AccountDoesNotExistException
	{
		SweBank.getBalance("xxx");
	}

	@Test(expected = AccountExistsException.class)
	public void testOpenAccountAccountExistsException() throws AccountExistsException
	{
		SweBank.openAccount("Bob");
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException
	{
		SweBank.deposit("Ulrika", new Money(10, SEK));
		assertEquals(Integer.valueOf(10), SweBank.getBalance("Ulrika"));
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testDepositDoesNotExistException() throws AccountDoesNotExistException
	{
		SweBank.deposit("xxx", new Money(10, SEK));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException
	{
		SweBank.withdraw("Ulrika", new Money(10, SEK));
		assertEquals(Integer.valueOf(-10), SweBank.getBalance("Ulrika"));
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testWithdrawDoesNotExistException() throws AccountDoesNotExistException
	{
		SweBank.withdraw("xxx", new Money(10, SEK));
	}

	@Test
	public void testGetBalance() throws AccountDoesNotExistException
	{
		assertEquals(Integer.valueOf(0), SweBank.getBalance("Ulrika"));
		SweBank.deposit("Ulrika", new Money(10, SEK));
		assertEquals(Integer.valueOf(10), SweBank.getBalance("Ulrika"));
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testGetBalanceDoesNotExistException() throws AccountDoesNotExistException
	{
		SweBank.getBalance("xxx");
	}

	@Test
	public void testTransfer() throws AccountDoesNotExistException
	{
		assertEquals(Integer.valueOf(0), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(0), SweBank.getBalance("Bob"));
		SweBank.deposit("Bob", new Money(10, SEK));
		assertEquals(Integer.valueOf(10), SweBank.getBalance("Bob"));
		SweBank.transfer("Bob", "Ulrika", new Money(10, SEK));
		assertEquals(Integer.valueOf(10), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(0), SweBank.getBalance("Bob"));
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testTransferDoesNotExistException1() throws AccountDoesNotExistException
	{
		SweBank.transfer("xxx", "yyy", new Money(10, SEK));
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testTransferDoesNotExistException2() throws AccountDoesNotExistException
	{
		SweBank.transfer("Bob", "yyy", new Money(10, SEK));
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testTransferDoesNotExistException3() throws AccountDoesNotExistException
	{
		SweBank.transfer("xxx", "Ulrika", new Money(10, SEK));
	}

	@Test
	public void testTimedPayment() throws AccountDoesNotExistException
	{
		assertEquals(Integer.valueOf(0), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(0), SweBank.getBalance("Bob"));
		SweBank.addTimedPayment("Bob", "0001", 2, 2, new Money(10, SEK), SweBank, "Ulrika");
		for(int i = 1; i < 5; i++)
		{
			SweBank.tick();
			SweBank.tick();
			SweBank.tick();
			assertEquals(Integer.valueOf(10 * i), SweBank.getBalance("Ulrika"));
			assertEquals(Integer.valueOf(-10 * i), SweBank.getBalance("Bob"));
		}
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testTimedPaymentDoesNotExistException1() throws AccountDoesNotExistException
	{
		SweBank.addTimedPayment("xxx", "0001", 2, 2, new Money(10, SEK), SweBank, "yyy");
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testTimedPaymentDoesNotExistException2() throws AccountDoesNotExistException
	{
		SweBank.addTimedPayment("xxx", "0001", 2, 2, new Money(10, SEK), SweBank, "Ulrika");
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testTimedPaymentDoesNotExistException3() throws AccountDoesNotExistException
	{
		SweBank.addTimedPayment("Bob", "0001", 2, 2, new Money(10, SEK), SweBank, "yyy");
	}

	@Test
	public void testAddRemoveTimedPayment() throws AccountDoesNotExistException
	{
		assertEquals(Integer.valueOf(0), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(0), SweBank.getBalance("Bob"));
		SweBank.addTimedPayment("Bob", "0001", 2, 2, new Money(10, SEK), SweBank, "Ulrika");
		SweBank.removeTimedPayment("Bob", "0001");
		for(int i = 1; i < 5; i++)
		{
			SweBank.tick();
			SweBank.tick();
			SweBank.tick();
			assertEquals(Integer.valueOf(0), SweBank.getBalance("Ulrika"));
			assertEquals(Integer.valueOf(0), SweBank.getBalance("Bob"));
		}
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testAddRemoveTimedPaymentDoesNotExistException1() throws AccountDoesNotExistException
	{
		SweBank.addTimedPayment("xxx", "0001", 2, 2, new Money(10, SEK), SweBank, "yyy");
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testAddRemoveTimedPaymentDoesNotExistException2() throws AccountDoesNotExistException
	{
		SweBank.addTimedPayment("xxx", "0001", 2, 2, new Money(10, SEK), SweBank, "Ulrika");
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testAddRemoveTimedPaymentDoesNotExistException3() throws AccountDoesNotExistException
	{
		SweBank.addTimedPayment("Bob", "0001", 2, 2, new Money(10, SEK), SweBank, "yyy");
	}
}
