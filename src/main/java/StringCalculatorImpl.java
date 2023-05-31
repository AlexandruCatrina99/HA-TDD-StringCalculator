import java.util.ArrayList;
import java.util.Arrays;

public class StringCalculatorImpl implements StringCalculator {

    @Override
    public int add(String input) {
        if (input.isEmpty()){
            return 0;
        }
        ArrayList<String> noNewLinesStrings = new ArrayList<>(Arrays.asList(input.split(",|\n")));
        int sum = 0;
        for (String s:noNewLinesStrings
             ) {
            sum = sum + Integer.parseInt(s);
        }
        return sum;
    }
}
