package com.dmcdev.testing;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(LoggingExtension.class)
public class CalculatorAssertJTest {

    private Calculator calculatorUnderTest;
    private static Instant startedAt;

    private static Logger logger;

    public void setLogger(Logger logger) {
        CalculatorAssertJTest.logger = logger;
    }

    @BeforeEach
    public void beforeEach_initCalc() {
        calculatorUnderTest = new Calculator();
        logger.info("Creating a new calculator");
    }

    @AfterEach
    public void afterEach_undefCalc() {
        calculatorUnderTest = null;
        logger.info("New calculator has been shut down");
    }

    @Disabled
    @BeforeAll
    public static void beforeAll_initStartingTime() {
//        logger.info("Appel beforeAll : start chrono");
        startedAt = Instant.now();
    }

    @AfterAll
    public static void afterAll_showTestDuration() {
        logger.info("Appel afterAll : durée des test");
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        logger.info(MessageFormat.format("Durée des tests : {0} ms", duration));
    }

    @Test
    @Tag("QuatreOperations")
    public void testAddTwoPositiveNumbers() {
        //ARRANGE
        int a = 2;
        int b = 3;
        //ACT
        int actualResult = calculatorUnderTest.add(a, b);
        //ASSERT
        assertThat(actualResult).isEqualTo(5);
    }

    @Test
    @Tag("QuatreOperations")
    public void testMultiplyTwoPositiveNumbers() {
        //ARRANGE
        int a = 3;
        int b = 4;
        //ACT
        int actualResult = calculatorUnderTest.multiply(a, b);
        //ASSERT
        assertThat(actualResult).isEqualTo(12);
    }

    @ParameterizedTest(name = "{0} x 0 doit etre égal à 0")
    @ValueSource(ints = {1,2,12,1001,5055})
    public void testMultiplyByZero(int arg) {
        int actualResult = calculatorUnderTest.multiply(arg, 0);
        assertThat(actualResult).isEqualTo(0);
    }

    @ParameterizedTest(name = "{0} + {1} doit etre égal à {2}")
    @CsvSource({ "1,1,2", "5,5,10", "65,35,100"})
    public void addMultiplesIntegers(int a, int b, int expectedResult) {
        int actualResult = calculatorUnderTest.add(a, b);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @Timeout(1)
    public void longCalcul_shouldComputeInLessThan1Second() {
        calculatorUnderTest.longCalculation();
    }

    @Test
    public void digitsSet_shouldReturnsTheSetOfDigits_ofPositiveInterger() {
        //GIVEN
        int number = 95897;
        //WHEN
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);
        //THEN
        assertThat(actualDigits).containsExactlyInAnyOrder(5, 7, 8, 9);
    }

    @Test
    public void listDigits_shouldReturnsTheListOfDigits_ofNegativeInteger() {
        final int number = -124432;
        final Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);
        assertThat(actualDigits).containsExactlyInAnyOrder(1,2,3,4);
    }

    @Test
    public void listDigits_shouldReturnsTheListOfZero_ofZero() {
        final int number = 0;
        final Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);
        assertThat(actualDigits).containsExactly(0);
    }
}
