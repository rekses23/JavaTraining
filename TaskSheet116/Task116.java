package TaskSheet116;

public class Task116 {

    public static void main(String[] args) {
        String inputString1 = "Rotator";
        String inputString2 = "mom";
        String inputString3 = "mama";

        checkUsingLoop(inputString1);
        checkUsingReverse(inputString1);
        checkUsingLoop(inputString2);
        checkUsingReverse(inputString2);
        checkUsingLoop(inputString3);
        checkUsingReverse(inputString3);
    }

    public static void checkUsingLoop(String inputString) {
        String lowerCaseInputString = inputString.toLowerCase();

        StringBuilder reversed = new StringBuilder();
        for (int i = inputString.length() - 1; i >= 0; i--) {
            reversed.append(lowerCaseInputString.charAt(i));
        }

        if (reversed.toString().equals(lowerCaseInputString)) {
            System.out.println("The input string is a palindrome.");
        } else {
            System.out.println("The input string is not a palindrome.");
        }
    }

    public static void checkUsingReverse(String inputString) {
        String lowerCaseInputString = inputString.toLowerCase();

        String reversedString = new StringBuilder(lowerCaseInputString).reverse().toString();

        if (reversedString.equals(lowerCaseInputString)) {
            System.out.println("The input string is a palindrome.");
        } else {
            System.out.println("The input string is not a palindrome.");
        }
    }

}
