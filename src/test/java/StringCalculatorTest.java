package test.java;

import com.caluclator.StringCalculator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class StringCalculatorTest {

    StringCalculator stringCalculator;

    @BeforeEach
    public void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void testEmptyString() {
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    public void testOneNumber() {
        assertEquals(1, stringCalculator.add("1"));
    }

    @Test
    public void testAddTwoNumbers() {
        assertEquals(3, stringCalculator.add("1,2"));
    }

    @Test
    public void testNumbersWithNewLine() {
        assertEquals(6, stringCalculator.add("1,2\n3"));
    }

    @Test
    public void testStringEndsWithDelimiter() {
        try {
            stringCalculator.add("1,2,");
            fail("Expected exception was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "String is not allowed to end with a separator");
        }
    }

    @Test
    public void testNewDelimiter() {
        assertEquals(4, stringCalculator.add("//;\n1;3"));
        assertEquals(6, stringCalculator.add("//|\n1|2|3"));
        assertEquals(7, stringCalculator.add("//sep\n2sep5"));
    }


    @Test
    public void testIncorrectDelimiter() {
        try {
            stringCalculator.add("//|\n1|2,3");
            fail("Expected exception was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "| expected but , found\n");
        }
    }


    @Test
    public void testNegativeNumbers() {
        try {
            stringCalculator.add("2,-4,-9");
            fail("Expected exception was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Negative number(s) not allowed: -4, -9");
        }
    }


    @Test
    public void testMultipleErrors() {
        try {
            stringCalculator.add("//|\n1|2,-3");
            fail("Expected exception was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "| expected but , found\nNegative number not allowed: -3\n");
        }
    }


    @AfterEach
    public void destroy() {
        stringCalculator = null;
    }

}
