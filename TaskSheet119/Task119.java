package TaskSheet119;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task119 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BasicArithmeticOperation bao = new BasicArithmeticOperation();

        double num1, num2, sum, difference, product, quotient;

        try {
            System.out.print("Enter first number: ");
            num1 = scanner.nextDouble();
            System.out.print("Enter second number: ");
            num2 = scanner.nextDouble();

            sum = bao.add(num1, num2);
            difference = bao.subtract(num1, num2);
            product = bao.multiply(num1, num2);
            quotient = bao.divide(num1, num2);

            System.out.println("-------------------------------------------------");
            System.out.print("Sum:\t\t\t");
            System.out.println(sum);
            System.out.print("Difference:\t\t");
            System.out.println(difference);
            System.out.print("Product:\t\t");
            System.out.println(product);
            System.out.print("Quotient:\t\t");
            if (Double.isInfinite(quotient)) {
                System.out.println("Cannot divide by 0");
            } else if (Double.isNaN(quotient)) {
                System.out.println("Undefined");
            } else {
                System.out.println(quotient);
            }
            System.out.println("-------------------------------------------------");
        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println("Input must be a valid number or floating point value.");
        }
    }

}

class BasicArithmeticOperation {

    public double add(double x, double y) {
        return x + y;
    }

    public double subtract(double x, double y) {
        return x - y;
    }

    public double multiply(double x, double y) {
        return x * y;
    }

    public double divide(double x, double y) {
        return x / y;
    }

}
