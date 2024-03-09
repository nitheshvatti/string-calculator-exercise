package test.java;

import com.caluclator.StringCalculator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
            stringCalculator.add("1,2\n3,");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "String is not allowed to end with a separator");
        }
    }

    @AfterEach
    public void destroy() {
        stringCalculator = null;
    }

}
