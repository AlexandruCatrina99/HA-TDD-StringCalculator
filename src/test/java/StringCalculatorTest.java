import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    public void beforeEach() {
        calculator = new StringCalculatorImpl();
    }

    @Test
    public void testEmptyStringReturnsZero() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void testStringWithOneNumber() {
        assertEquals(1, calculator.add("1"));
    }

    @Test
    public void testStringWithTwoNumber() {
        assertEquals(3, calculator.add("1,2"));
    }

    @Test
    public void testStringWithMoreThanTwoNumbers() {
        assertEquals(55, calculator.add("1,2,3,4,5,6,7,8,9,10"));
    }

}
