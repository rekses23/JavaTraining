package TaskSheet117;

import java.util.Scanner;

import java.util.InputMismatchException;

public class Task117 {

  private static final int INPUT_COUNT = 3;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[] numbers = new int[INPUT_COUNT];
    int i = 0;
    while (i < INPUT_COUNT) {
      try {
        System.out.print("Enter number" + (i + 1) + ": ");
        int input = scanner.nextInt();
        numbers[i] = input;
        i++;
      } catch (InputMismatchException e) {
        scanner.next();
        System.out.println("Input must be a number");
      }
    }
    scanner.close();

    int largestNumber = numbers[0];
    boolean areAllEqual = true;
    for (int j = 1; j < INPUT_COUNT; j++) {
      if (numbers[j] != largestNumber) areAllEqual = false;
      if (numbers[j] > largestNumber) largestNumber = numbers[j];
    }
    if (areAllEqual) {
      System.out.println("All numbers are equal");
    } else System.out.println(largestNumber);
  }
}
