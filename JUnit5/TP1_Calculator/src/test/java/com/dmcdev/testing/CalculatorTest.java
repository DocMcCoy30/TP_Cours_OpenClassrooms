package com.dmcdev.testing;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculatorUnderTest;
    private static Instant startedAt;


    @BeforeEach
    public void beforeEach_initCalc() {
        calculatorUnderTest = new Calculator();
        System.out.println("Creating a new calculator");
    }

    @AfterEach
    public void afterEach_undefCalc() {
        calculatorUnderTest = null;
        System.out.println("New calculator has been shut down");
    }

    @BeforeAll
    public static void beforeAll_initStartingTime() {
//        System.out.println("Appel beforeAll : start chrono");
        startedAt = Instant.now();
    }

    @AfterAll
    public static void afterAll_showTestDuration() {
        System.out.println("Appel afterAll : durée des test");
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
    }

    @Test
    public void testAddTwoPositiveNumbers() {
        //ARRANGE
        int a = 2;
        int b = 3;
        //ACT
        int actualResult = calculatorUnderTest.add(a, b);
        //ASSERT
        assertEquals(5, actualResult);
    }

    @Test
    public void testMultiplyTwoPositiveNumbers() {
        //ARRANGE
        int a = 3;
        int b = 4;
        //ACT
        int actualResult = calculatorUnderTest.multiply(a, b);
        //ASSERT
        assertEquals(12, actualResult);
    }

    @ParameterizedTest(name = "{0} x 0 doit etre égal à 0")
    @ValueSource(ints = {1,2,12,1001,5055})
    public void testMultiplyByZero(int arg) {
        int actualResult = calculatorUnderTest.multiply(arg, 0);
        assertEquals(0, actualResult);
    }

    @ParameterizedTest(name = "{0} + {1} doit etre égal à {2}")
    @CsvSource({ "1,1,2", "5,5,10", "65,35,100"})
    public void addMultiplesIntegers(int a, int b, int expectedResult) {
        int actualResult = calculatorUnderTest.add(a, b);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @Timeout(1)
    public void longCalcul_shouldComputeInLessThan1Second() {
        calculatorUnderTest.longCalculation();
    }
}
