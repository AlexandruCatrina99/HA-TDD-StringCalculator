import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
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
}
