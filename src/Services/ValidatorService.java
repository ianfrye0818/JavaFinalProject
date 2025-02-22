package Services;
import java.util.Scanner;


/**
 *
 * @author ianfr
 */


public class ValidatorService {

    private static final Scanner scanner = new Scanner(System.in);

    public static <T extends Number & Comparable<T>> T getValidNumber(
            String prompt, T lowerBound, T upperBound, Class<T> type, String errorMessage) {

        T value = null;

        while (true) {
            try {
                System.out.print(prompt + " ");
                String input = scanner.nextLine().trim();

                Object parsedValue = switch (type.getName()) {
                    case "java.lang.Integer" -> Integer.parseInt(input);
                    case "java.lang.Double" -> Double.parseDouble(input);
                    case "java.lang.Float" -> Float.parseFloat(input);
                    case "java.lang.Long" -> Long.parseLong(input);
                    case "java.lang.Short" -> Short.parseShort(input);
                    case "java.lang.Byte" -> Byte.parseByte(input);
                    default -> throw new IllegalArgumentException("Unsupported type: " + type.getName());
                };

                value = type.cast(parsedValue); // Safe casting

                // allows us to pass in null for upper and lower bounds and give them default
                // values.
                T minValue = (lowerBound != null) ? lowerBound : type.cast(0);

                T maxValue = (upperBound != null) ? upperBound : switch (type.getName()) {
                    case "java.lang.Integer" -> type.cast(Integer.MAX_VALUE);
                    case "java.lang.Double" -> type.cast(Double.MAX_VALUE);
                    case "java.lang.Float" -> type.cast(Float.MAX_VALUE);
                    case "java.lang.Long" -> type.cast(Long.MAX_VALUE);
                    case "java.lang.Short" -> type.cast(Short.MAX_VALUE);
                    case "java.lang.Byte" -> type.cast(Byte.MAX_VALUE);
                    default -> throw new IllegalArgumentException("Unsupported type: " + type.getName());
                };

                if (value.compareTo(minValue) >= 0 && value.compareTo(maxValue) <= 0) {
                    break;
                }

                System.out.print(errorMessage + " ");
            } catch (Exception e) {
                System.out.print(errorMessage + " ");
            }
        }

        return value;
    }

    /**
     * Overloaded method to have a generic error message
     *
     * @param prompt     What to ask the user
     * @param lowerBound The lowest valid number
     * @param upperBound The highest valid number
     * @param type       The type of number we are comparing
     * @param <T>        They type of number we are validating
     * @return A number that can be sure is a valid type and within the bounds of
     *         the upper and lower
     */
    public static <T extends Number & Comparable<T>> T getValidNumber(String prompt, T lowerBound, T upperBound,
            Class<T> type) {
        return getValidNumber(prompt, lowerBound, upperBound, type, "Invalid input. Please try again.");
    }

    public static String getValidString(String prompt) {
        String input;

        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        } while (true);
    }

}