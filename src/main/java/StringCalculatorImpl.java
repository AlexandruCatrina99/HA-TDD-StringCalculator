import java.util.ArrayList;
import java.util.Arrays;

public class StringCalculatorImpl implements StringCalculator {
    private final Logger logger;

    public StringCalculatorImpl(Logger logger) {
        this.logger = logger;
    }
    @Override
    public int add(String input) throws NegativeNotAllowedException {
        if (input.isEmpty()) {
            return 0;
        }

        if (input.startsWith("//")) {
            int indexAfterDelimiter = input.indexOf("\n");
            String customDelimiter = input.substring(2, indexAfterDelimiter);
            ArrayList<String> numberStringList = new ArrayList<>(Arrays.asList(input.substring(indexAfterDelimiter + 1).split(customDelimiter + "|\n")));
            int sum = 0;
            for (String s : numberStringList
            ) {
                if (s.isEmpty()) {
                    continue;
                }
                if (Integer.parseInt(s) >= 0) {
                    sum += Integer.parseInt(s);
                } else {
                    throw new NegativeNotAllowedException("Negatives not allowed: " + s);
                }
            }
            return sum;
        }

        ArrayList<String> numberStringList = new ArrayList<>(Arrays.asList(input.split(",|\n")));
        int sum = 0;
        for (String s : numberStringList
        ) {
            if (Integer.parseInt(s) >= 0) {
                sum += Integer.parseInt(s);
            } else {
                throw new NegativeNotAllowedException("Negatives not allowed: " + s);
            }
        }
        return sum;
    }
}
