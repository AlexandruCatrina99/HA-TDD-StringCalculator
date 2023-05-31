import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class MainCalculatorTest {
    private StringCalculator calculator;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorContent = new ByteArrayOutputStream();
    private final PrintStream outputStream = System.out;
    private final PrintStream outputErrorStream = System.err;


    @BeforeEach
    public void beforeEach() {
        this.calculator = new StringCalculatorImpl(new LoggerImpl());
        Calculator.stringCalculator = new StringCalculatorImpl(new LoggerImpl());
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errorContent));

    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(outputStream);
        System.setErr(outputErrorStream);
    }

    @Test
    public void verifyMainMethodWelcomeAndHelpText() throws Exception {
        Calculator.main("");
        final BufferedReader reader = new BufferedReader(new StringReader(outContent.toString()));
        assertEquals("Welcome!", reader.readLine());
        reader.readLine();
        assertEquals("To use the calculator use: scalc '//[delimiter]\\ninteger[delimiter]integer...'", reader.readLine());
        reader.readLine();
        assertEquals("You can use multiple delimiters: scalc '//[delimiter1][delimiter2]\\n...'", reader.readLine());
    }

    @Test
    public void testRegexForInput() {
        assertTrue(Calculator.isValidFormat("scalc '//[;][**]\n1;2**3'"));
        assertFalse(Calculator.isValidFormat("scalc '//[;][**]1;2**3'"));// No new line after delimiters
        assertFalse(Calculator.isValidFormat("scalc '[;][**]\n1;2**3'"));// No "//" before delimiters

    }

    @Test
    public void testSimpleAdditionWithDefaultDelimiter() throws Exception {
        Calculator.main("scalc '//[;][**]\n1;2**3'");
        final BufferedReader reader = new BufferedReader(new StringReader(outContent.toString()));
        String last = null, line;
        while ((line = reader.readLine()) != null) {
            last = line;
        }
        assertEquals(last, "The result is 6");
    }

    @Test
    public void testLoggerIsNotUsedWhenNumberBelow1000() throws Exception {
        final Logger mockLog = Mockito.mock(Logger.class);
        Calculator.stringCalculator = new StringCalculatorImpl(mockLog);
        Calculator.main("scalc '999'");
        Mockito.verifyNoInteractions(mockLog);
    }

    @Test
    public void testLoggerIsUsedWhenNumberOver1000() throws Exception {
        final Logger mockLog = Mockito.mock(Logger.class);
        Calculator.stringCalculator = new StringCalculatorImpl(mockLog);
        Calculator.main("scalc '1234'");
        Mockito.verify(mockLog).log(1234);
    }

    @Test
    public void testInvalidInput() throws Exception {
        Calculator.main("scalc '/WrongFormmatHere\n1;2**3'");
        final BufferedReader reader = new BufferedReader(new StringReader(outContent.toString()));
        String last = null, line;
        while ((line = reader.readLine()) != null) {
            last = line;
        }
        assertEquals(last, "Invalid input, use format: scalc '//[delimiter]\\ninteger[delimiter]integer...'");
    }

    @Test
    public void testOneComplexDelimitation() throws Exception {
        Calculator.main("scalc '//[%%%]\n1%%%2%%%3%%%4'");
        final BufferedReader reader = new BufferedReader(new StringReader(outContent.toString()));
        String last = null, line;
        while ((line = reader.readLine()) != null) {
            last = line;
        }
        assertEquals(last, "The result is 10");
    }

    @Test
    public void testMultipleComplexDelimitations() throws Exception {
        Calculator.main("scalc '//[***][%%%]\n1***2%%%4'");
        final BufferedReader reader = new BufferedReader(new StringReader(outContent.toString()));
        String last = null, line;
        while ((line = reader.readLine()) != null) {
            last = line;
        }
        assertEquals(last, "The result is 7");
    }
}
