import java.util.ArrayList;
import java.util.Arrays;

public class StringCalculatorImpl implements StringCalculator {

    @Override
    public int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }

        String[] nums = input.split(",");
        if (nums.length == 1) {
            return Integer.parseInt(nums[0]);
        } else {
            return Integer.parseInt(nums[0]) + Integer.parseInt(nums[1]);
        }
    }
}

