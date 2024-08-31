package TaskSheet121;

import java.util.Scanner;
import static java.lang.Math.*;

import java.util.InputMismatchException;

public class Task121 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int num1, num2;
    System.out.println();

    try {
      System.out.print("Enter first number: ");
      num1 = scanner.nextInt();
      System.out.print("Enter second number: ");
      num2 = scanner.nextInt();

      System.out.println("-------------------------------------------------");
      System.out.print("Sum:\t\t\t");
      System.out.println(add(num1, num2));
      System.out.print("Difference:\t\t");
      System.out.println(subtract(num1, num2));
      System.out.print("Product:\t\t");
      System.out.println(multiply(num1, num2));
      System.out.print("Quotient:\t\t");
      System.out.println(divide(num1, num2));
      System.out.println("-------------------------------------------------");
    } catch (InputMismatchException e) {
      scanner.next();
      System.out.println("Invalid input.");
    } catch (ArithmeticException e) {
      System.out.println(e.getMessage());
      System.out.println("-------------------------------------------------");
    } finally {
      scanner.close();
    }
  }

  public static int add(int a, int b) {
    return addExact(a, b);
  }

  public static int subtract(int a, int b) {
    return subtractExact(a, b);
  }

  public static int multiply(int a, int b) {
    return multiplyExact(a, b);
  }

  public static float divide(int a, int b) throws ArithmeticException {
    return floorDiv(a, b);
  }

}