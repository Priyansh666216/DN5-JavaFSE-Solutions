package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BankAccountTest {

    private BankAccount account;

    // -------------------------------------------------------
    // @Before runs before EACH test method
    // Used to set up a fresh BankAccount so tests don't
    // interfere with each other (Test Fixture)
    // -------------------------------------------------------
    @Before
    public void setUp() {
        account = new BankAccount("Priyanshu", 1000.0);
        System.out.println("setUp() called - account created with balance 1000.0");
    }

    // -------------------------------------------------------
    // @After runs after EACH test method
    // Used to release resources or reset state
    // -------------------------------------------------------
    @After
    public void tearDown() {
        account = null;
        System.out.println("tearDown() called - account set to null");
    }

    // -------------------------------------------------------
    // Test 1: Deposit
    // -------------------------------------------------------
    @Test
    public void testDeposit() {
        // Arrange - account is already set up with 1000.0
        double depositAmount = 500.0;

        // Act
        account.deposit(depositAmount);

        // Assert
        assertEquals(1500.0, account.getBalance(), 0.001);
    }

    // -------------------------------------------------------
    // Test 2: Withdraw
    // -------------------------------------------------------
    @Test
    public void testWithdraw() {
        // Arrange
        double withdrawAmount = 200.0;

        // Act
        account.withdraw(withdrawAmount);

        // Assert
        assertEquals(800.0, account.getBalance(), 0.001);
    }

    // -------------------------------------------------------
    // Test 3: Withdraw more than balance throws exception
    // -------------------------------------------------------
    @Test(expected = IllegalStateException.class)
    public void testWithdrawInsufficientFunds() {
        // Arrange
        double withdrawAmount = 5000.0;

        // Act - should throw IllegalStateException
        account.withdraw(withdrawAmount);

        // Assert - handled by expected = IllegalStateException.class
    }

    // -------------------------------------------------------
    // Test 4: Deposit negative amount throws exception
    // -------------------------------------------------------
    @Test(expected = IllegalArgumentException.class)
    public void testDepositNegativeAmount() {
        // Arrange
        double negativeAmount = -100.0;

        // Act - should throw IllegalArgumentException
        account.deposit(negativeAmount);

        // Assert - handled by expected = IllegalArgumentException.class
    }

    // -------------------------------------------------------
    // Test 5: Check owner name
    // -------------------------------------------------------
    @Test
    public void testOwnerName() {
        // Arrange - already done in setUp()

        // Act
        String owner = account.getOwner();

        // Assert
        assertEquals("Priyanshu", owner);
        assertNotNull(owner);
    }

    // -------------------------------------------------------
    // Test 6: Balance after multiple operations
    // -------------------------------------------------------
    @Test
    public void testMultipleOperations() {
        // Arrange
        double firstDeposit  = 500.0;
        double secondDeposit = 300.0;
        double withdrawal    = 200.0;

        // Act
        account.deposit(firstDeposit);
        account.deposit(secondDeposit);
        account.withdraw(withdrawal);

        // Assert - 1000 + 500 + 300 - 200 = 1600
        assertEquals(1600.0, account.getBalance(), 0.001);
    }
}
