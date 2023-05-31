import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            StringBuilder customDelimiter = new StringBuilder();
            int indexAfterDelimiter = input.indexOf("\n");
            String customDelimiters = input.substring(2, indexAfterDelimiter);
            Pattern p = Pattern.compile("\\[.*?\\]");
            Matcher m = p.matcher(customDelimiters);
            while(m.find()){
                customDelimiter.append(m.group());
                customDelimiter.append("|");
            }
            ArrayList<String> numberStringList = new ArrayList<>(Arrays.asList(input.substring(indexAfterDelimiter + 1).split(customDelimiter + "\n")));
            int sum = 0;
            for (String s : numberStringList
            ) {
                if (s.isEmpty()) {
                    continue;
                }
                if (Integer.parseInt(s) >= 0) {
                    int number = Integer.parseInt(s);
                    sum += number;
                    if (number >= 1000){
                        logger.log(number);
                    }
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
                int number = Integer.parseInt(s);
                sum += number;
                if (number >= 1000){
                    logger.log(number);
                }
            } else {
                throw new NegativeNotAllowedException("Negatives not allowed: " + s);
            }
        }
        return sum;
    }
}
