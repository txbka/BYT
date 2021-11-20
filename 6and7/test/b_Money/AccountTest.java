package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;

	@Before
	public void setUp() throws Exception
    {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}

	@Test
	public void testAddRemoveTimedPayment() throws AccountDoesNotExistException
    {
        assertEquals(Integer.valueOf(1000000), SweBank.getBalance("Alice"));
        assertEquals(Integer.valueOf(10000000), testAccount.getBalance().getAmount());
        testAccount.addTimedPayment("0001", 2, 2, new Money(10,SEK), SweBank, "Alice");
        testAccount.removeTimedPayment("0001");
		for(int i = 1; i < 5; i++)
		{
			testAccount.tick();
			testAccount.tick();
			testAccount.tick();
			assertEquals(Integer.valueOf(1000000), SweBank.getBalance("Alice"));
			assertEquals(Integer.valueOf(10000000), testAccount.getBalance().getAmount());
		}
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testAddRemoveTimedPaymentAccountDoesNotExistException() throws AccountDoesNotExistException
	{
		assertEquals(Integer.valueOf(1000000), SweBank.getBalance("xxx"));
	}

	@Test
	public void testTimedPayment() throws AccountDoesNotExistException
    {
        assertEquals(Integer.valueOf(1000000), SweBank.getBalance("Alice"));
        assertEquals(Integer.valueOf(10000000), testAccount.getBalance().getAmount());
        testAccount.addTimedPayment("0001", 2, 2, new Money(10, SEK), SweBank, "Alice");
        for(int i = 1; i < 5; i++)
		{
			testAccount.tick();
			testAccount.tick();
			testAccount.tick();
			assertEquals(Integer.valueOf(1000000 + 10*i), SweBank.getBalance("Alice"));
			assertEquals(Integer.valueOf(10000000 - 10*i), testAccount.getBalance().getAmount());
		}
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testTimedPaymentAccountDoesNotExistException() throws AccountDoesNotExistException
	{
		assertEquals(Integer.valueOf(1000000), SweBank.getBalance("xxx"));
	}

	@Test
	public void testAddWithdraw()
    {
		assertEquals(Integer.valueOf(10000000), testAccount.getBalance().getAmount());
		testAccount.deposit(new Money(10, SEK));
        assertEquals(Integer.valueOf(10000010), testAccount.getBalance().getAmount());
        testAccount.withdraw(new Money(10, SEK));
        assertEquals(Integer.valueOf(10000000), testAccount.getBalance().getAmount());
	}

	@Test
	public void testGetBalance()
    {
		assertEquals(Integer.valueOf(10000000), testAccount.getBalance().getAmount());
	}

	@Test
	public void timedPaymentExists()
	{
		testAccount.addTimedPayment("0001", 2, 2, new Money(10, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("0001"));
	}
}
