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

    @AfterEach
    public void destroy() {
        stringCalculator = null;
    }

}