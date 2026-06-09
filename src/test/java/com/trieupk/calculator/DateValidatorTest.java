package com.trieupk.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link DateValidator}.
 * Covers valid dates, invalid dates, boundary values, leap years,
 * and out-of-range inputs.
 */
class DateValidatorTest {

    private DateValidator validator;

    @BeforeEach
    void setUp() {
        validator = new DateValidator();
    }

    // ===== Lab 3: DayInMonth Function =====

    @ParameterizedTest(name = "TC-DIM-{index}: month={0}, year={1} => {2} days")
    @CsvSource({
            "1, 2024, 31",
            "3, 2024, 31",
            "5, 2024, 31",
            "7, 2024, 31",
            "8, 2024, 31",
            "10, 2024, 31",
            "12, 2024, 31",
            "4, 2024, 30",
            "6, 2024, 30",
            "9, 2024, 30",
            "11, 2024, 30",
            "2, 2024, 29",
            "2, 2000, 29",
            "2, 2023, 28",
            "2, 1900, 28",
            "0, 2024, 0",
            "13, 2024, 0",
            "2, 999, 0",
            "2, 3001, 0"
    })
    @DisplayName("DayInMonth returns expected days for normal, leap, and boundary cases")
    void testDayInMonth(int month, int year, int expectedDays) {
        assertEquals(expectedDays, validator.dayInMonth(month, year));
    }

    // ===== Lab 3: CheckDate Function =====

    @ParameterizedTest(name = "TC-CD-{index}: day={0}, month={1}, year={2} => valid={3}")
    @CsvSource({
            "1, 1, 2024, true",
            "31, 1, 2024, true",
            "30, 4, 2024, true",
            "31, 4, 2024, false",
            "29, 2, 2024, true",
            "29, 2, 2000, true",
            "29, 2, 2023, false",
            "29, 2, 1900, false",
            "28, 2, 2023, true",
            "0, 1, 2024, false",
            "32, 1, 2024, false",
            "15, 0, 2024, false",
            "15, 13, 2024, false",
            "15, 1, 999, false",
            "15, 1, 3001, false",
            "31, 12, 3000, true",
            "1, 1, 1000, true"
    })
    @DisplayName("CheckDate validates valid dates, invalid dates, and boundary values")
    void testCheckDate(int day, int month, int year, boolean expectedValid) {
        assertEquals(expectedValid, validator.checkDate(day, month, year));
    }

    // ===== Valid Dates =====

    @Test
    @DisplayName("Valid regular date: 15/06/2024")
    void testValidRegularDate() {
        assertTrue(validator.isValidDate(15, 6, 2024));
    }

    @Test
    @DisplayName("Valid leap year date: 29/02/2024")
    void testValidLeapYearDate() {
        assertTrue(validator.isValidDate(29, 2, 2024));
    }

    @Test
    @DisplayName("Valid boundary minimum: 1/1/1000")
    void testValidBoundaryMin() {
        assertTrue(validator.isValidDate(1, 1, 1000));
    }

    @Test
    @DisplayName("Valid boundary maximum: 31/12/3000")
    void testValidBoundaryMax() {
        assertTrue(validator.isValidDate(31, 12, 3000));
    }

    @Test
    @DisplayName("Valid 30-day month: 30/04/2024")
    void testValid30DayMonth() {
        assertTrue(validator.isValidDate(30, 4, 2024));
    }

    // ===== Invalid Dates =====

    @Test
    @DisplayName("Invalid: 29/02/2023 (not a leap year)")
    void testInvalidNonLeapYear() {
        assertFalse(validator.isValidDate(29, 2, 2023));
    }

    @Test
    @DisplayName("Invalid: 30/02/2024 (Feb never has 30 days)")
    void testInvalidFeb30() {
        assertFalse(validator.isValidDate(30, 2, 2024));
    }

    @Test
    @DisplayName("Invalid: 31/04/2024 (April has only 30 days)")
    void testInvalid31April() {
        assertFalse(validator.isValidDate(31, 4, 2024));
    }

    // ===== Out-of-Range Inputs =====

    @Test
    @DisplayName("Day below range: 0/01/2024")
    void testDayBelowRange() {
        assertFalse(validator.isValidDate(0, 1, 2024));
    }

    @Test
    @DisplayName("Day above range: 32/01/2024")
    void testDayAboveRange() {
        assertFalse(validator.isValidDate(32, 1, 2024));
    }

    @Test
    @DisplayName("Month below range: 15/0/2024")
    void testMonthBelowRange() {
        assertFalse(validator.isValidDate(15, 0, 2024));
    }

    @Test
    @DisplayName("Month above range: 15/13/2024")
    void testMonthAboveRange() {
        assertFalse(validator.isValidDate(15, 13, 2024));
    }

    @Test
    @DisplayName("Year below range: 15/06/999")
    void testYearBelowRange() {
        assertFalse(validator.isValidDate(15, 6, 999));
    }

    @Test
    @DisplayName("Year above range: 15/06/3001")
    void testYearAboveRange() {
        assertFalse(validator.isValidDate(15, 6, 3001));
    }

    // ===== Validation Message Tests =====

    @Test
    @DisplayName("Message: valid date returns success message")
    void testMessageValidDate() {
        String result = validator.getValidationMessage("29", "2", "2024");
        assertEquals("29/02/2024 is correct date time!", result);
    }

    @Test
    @DisplayName("Message: invalid date returns error message")
    void testMessageInvalidDate() {
        String result = validator.getValidationMessage("30", "2", "2024");
        assertEquals("30/02/2024 is not a valid date!", result);
    }

    @Test
    @DisplayName("Message: non-integer input returns parse error")
    void testMessageNonInteger() {
        String result = validator.getValidationMessage("abc", "2", "2024");
        assertEquals("Invalid input! Please enter integers only.", result);
    }

    @Test
    @DisplayName("Message: out-of-range input returns range error")
    void testMessageOutOfRange() {
        String result = validator.getValidationMessage("15", "6", "999");
        assertEquals("Day must be 1-31, Month must be 1-12, Year must be 1000-3000.", result);
    }
}
