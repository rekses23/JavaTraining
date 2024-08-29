package TaskSheet120;

import java.util.Scanner;
import java.util.ArrayList;

import java.util.InputMismatchException;

public class Task120 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    ArrayList<Integer> inputs = new ArrayList<>();

    System.out.println("\n-------------------------------------------------");
    System.out.println("Important! Enter 0 to end the loop.");
    System.out.println("-------------------------------------------------");
    while (true) {
      try {
        System.out.print("Enter a number: ");
        int input = scanner.nextInt();
        if (input == 0) break;
        inputs.add(input);
      } catch (InputMismatchException e) {
        scanner.next();
        System.out.println("Input must be a valid number or floating point value.");
      }
    }

    scanner.close();

    System.out.println();

    int[] arrayInputs = new int[inputs.size()];
    for (int i = 0; i < inputs.size(); i++) {
      arrayInputs[i] = inputs.get(i);
    }

    Result result = calculateSums(arrayInputs);

    for (int i = 0; i < result.getCumulativeSums().length; i++) {
      System.out.println("Cumulative sum of " + inputs.get(i) + ":\t\t" + result.getCumulativeSums()[i]);
    }
    System.out.println("Sum of all params:\t\t\t" + result.getSumOfAllParams());
    System.out.println("Cumulative sums total:\t\t" + result.getCumulativeSumsTotal());
  }

  public static Result calculateSums(int... numbers) {
    int sumOfAllParams = 0;
    int cumulativeSumsTotal = 0;
    int[] cumulativeSums = new int[numbers.length];
    for (int i = 0; i < numbers.length; i++) {
      int cumulativeSum = 0;
      for (int j = 1; j <= numbers[i]; j++) cumulativeSum += j;
      cumulativeSums[i] = cumulativeSum;
      sumOfAllParams += numbers[i];
      cumulativeSumsTotal += cumulativeSum;
    }
    return new Result(sumOfAllParams, cumulativeSums, cumulativeSumsTotal);
  }

}

class Result {

  private final int sumOfAllParams;
  private final int[] cumulativeSums;
  private final int cumulativeSumsTotal;

  Result(int sumOfAllParams, int[] cumulativeSums, int cumulativeSumsTotal) {
    this.sumOfAllParams = sumOfAllParams;
    this.cumulativeSums = cumulativeSums;
    this.cumulativeSumsTotal = cumulativeSumsTotal;
  }

  public int getSumOfAllParams() {
    return this.sumOfAllParams;
  }

  public int[] getCumulativeSums() {
    return this.cumulativeSums;
  }

  public int getCumulativeSumsTotal() {
    return cumulativeSumsTotal;
  }
}