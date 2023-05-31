import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Calculator {

    public static StringCalculator stringCalculator;
    public Calculator(final StringCalculator stringCalculator){
        Calculator.stringCalculator = stringCalculator;
    }

    public static void main(String... args) throws NegativeNotAllowedException {
        printWelcomeAndHelpText();

        if (args.length != 0) {
            for (String arg : args) {
                if (isValidFormat(arg)) {
                    String formattedInput =  arg.replace("scalc '", "").replace("'","");
                    System.out.println("The result is " + stringCalculator.add(formattedInput));
                } else {
                    System.out.println("Invalid input, use format: scalc '//[delimiter]\\ninteger[delimiter]integer...'");
                }
            }
    }else {
            stringCalculator = new StringCalculatorImpl(new LoggerImpl());
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().replaceAll("\\\\n","\n");
            while(!input.isEmpty()){
                if (isValidFormat(input)){
                    String formattedInput =  input.replace("scalc '", "").replace("'","");
                    System.out.println("The result is " + stringCalculator.add(formattedInput));
                }
                else {
                    System.out.println("Invalid input, use format: scalc '//[delimiter]\\ninteger[delimiter]integer...'");
                }
                input = scanner.nextLine().replaceAll("\\\\n","\n");
            }

        }
    }

    public static boolean isValidFormat(String input) {
        return Pattern.compile("^scalc '((\\/\\/[\\[].*\\]\\n.*)|([\\d,]+)|([\\d\\n]+))'").matcher(input).find();

    }

    private static void printWelcomeAndHelpText() {
        System.out.println("Welcome!\n");
        System.out.println("To use the calculator use: scalc '//[delimiter]\\ninteger[delimiter]integer...'\n");
        System.out.println("You can use multiple delimiters: scalc '//[delimiter1][delimiter2]\\n...'");
    }

}
