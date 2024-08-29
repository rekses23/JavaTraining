package TaskSheet116;

import java.util.Scanner;

public class Task116 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter a word or any sequence of characters: ");
    String input = scanner.nextLine().replace(" ", "").toLowerCase();
    String reversedInputString = new StringBuilder(input).reverse().toString();

    if (input.equals(reversedInputString)) System.out.println("The input string is a palindrome.");
    else System.out.println("The input string is not a palindrome.");

    scanner.close();
  }

}
