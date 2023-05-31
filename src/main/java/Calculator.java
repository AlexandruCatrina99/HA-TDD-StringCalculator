public class Calculator {

    public static StringCalculator stringCalculator;
    public Calculator(final StringCalculator stringCalculator){
        Calculator.stringCalculator = stringCalculator;
    }

    public static void main(String... args) {
        printWelcomeAndHelpText();
    }

    private static void printWelcomeAndHelpText() {
        System.out.println("Welcome!\n");
        System.out.println("To use the calculator use: scalc '//[delimiter]\\ninteger[delimiter]integer...'\n");
        System.out.println("You can use multiple delimiters: scalc '//[delimiter1][delimiter2]\\n...'");
    }

}
