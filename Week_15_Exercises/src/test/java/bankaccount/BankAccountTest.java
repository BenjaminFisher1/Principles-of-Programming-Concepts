/* These are imports you may or may not need depending
on where and how you are running the tests

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

*/

package bankaccount;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        // Starts each test with a fresh account of 100.0
        account = new BankAccount(100.0);
    }

    @AfterEach
    void tearDown() {
        // Delete the current bank account to make it available for garbage collection
        account = null;
    }


    @Test
    void testDeposit() {
        /** 2. Add $50 and check that the balance is 150 */
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    void testWithdraw() {
        /** 3. Withdraw $40 and check that the balance is $60; remember that each test is done on a fresh instance of bank account */
        account.withdraw(40.0);
        assertEquals(60.0, account.getBalance());
    }

    @Test
    void testInvalidDeposit() {
        /** 4. Deposit a negative amount and check if an exception is thrown */
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10.0));
    }

    @Test
    void testOverdraft() {
        /** 5. Verify that withdrawing more than the current balance throws an exception */
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(200.0));
    }

    @Test
    void testNegativeInitialBalance() {
        /** 6. Add a test to check that an Exception is thrown when trying to create a new bank account with a negative initial balance */
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1.0));
    }

    @Test
    void testTransfer() {
        /** 1c). Test that transfers money from one account to another */
        BankAccount other = new BankAccount(50.0);
        account.transferTo(other, 30.0);
        assertEquals(70.0, account.getBalance());
        assertEquals(80.0, other.getBalance());
    }
}
