import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    private StringCalculator calculator ;

    @BeforeEach
    public void beforeEach() {
        this.calculator = new StringCalculatorImpl(new LoggerImpl());
    }

    @Test
    public void testEmptyStringReturnsZero() throws NegativeNotAllowedException {
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void testStringWithOneNumber() throws NegativeNotAllowedException {
        assertEquals(1, calculator.add("1"));
    }

    @Test
    public void testStringWithTwoNumber() throws NegativeNotAllowedException {
        assertEquals(3, calculator.add("1,2"));
    }

    @Test
    public void testStringWithMoreThanTwoNumbers() throws NegativeNotAllowedException {
        assertEquals(55, calculator.add("1,2,3,4,5,6,7,8,9,10"));
    }

    @Test
    public void testStringWithNewLine() throws NegativeNotAllowedException {
        assertEquals(3, calculator.add("1\n2"));
    }

    @Test
    public void testStringWithNewLinesAndCommas() throws NegativeNotAllowedException {
        assertEquals(55, calculator.add("1\n2,3\n4,5,6,7,8\n9,10"));
    }

    @Test
    public void testStringWithCustomDelimiter() throws NegativeNotAllowedException {
        assertEquals(6, calculator.add("//;\n1;2;3"));
        assertEquals(3, calculator.add("//abc\n1abc2"));
        assertEquals(3, calculator.add("//delimiter\n1delimiter2"));
    }

    @Test
    public void testStringWithCustomDelimiterAndNewLine() throws NegativeNotAllowedException {
        assertEquals(10, calculator.add("//;\n1;2;3\n4"));
    }

    @Test
    public void testStringWithCustomDelimiterAndNoNumbers() throws NegativeNotAllowedException {
        assertEquals(0, calculator.add("//;\n"));
    }

    @Test
    public void testStringWithCustomDelimiterAndOneNumber() throws NegativeNotAllowedException {
        assertEquals(12, calculator.add("//;\n12"));
    }

    @Test
    public void testStringWithCustomDelimiterAndOneNumberAndOneExtraDelimiter() throws NegativeNotAllowedException {
        assertEquals(12, calculator.add("//;\n12;"));
    }

    @Test
    public void testPassingNativeNumbers() {
        final NegativeNotAllowedException thrown = assertThrows(NegativeNotAllowedException.class, () -> {
            calculator.add("1,-2");
        });
        assertEquals("Negatives not allowed: -2", thrown.getMessage());
    }

}
