package TaskSheet125;

import java.util.Scanner;

import java.util.InputMismatchException;

public class Task125 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String make, model;
    int year, numberOfDoors;

    try {
      System.out.print("Enter Car Make: ");
      make = scanner.nextLine();
      System.out.print("Enter Car Model: ");
      model = scanner.nextLine();
      System.out.print("Enter Manufacturing Year: ");
      year = scanner.nextInt();
      System.out.print("Enter Number of Doors: ");
      numberOfDoors = scanner.nextInt();

      System.out.println();

      Car car = new Car(make, model, year, numberOfDoors);

      car.displayDetails();
    } catch (InputMismatchException e) {
      scanner.next();
      System.out.println("Invalid input.");
    } finally {
      scanner.close();
    }
  }

}


class Vehicle {

  private final String make;
  private final String model;
  private final int year;

  Vehicle(String make, String model, int year) {
    this.make = make;
    this.model = model;
    this.year = year;
  }

  public String getMake() {
    return make;
  }

  public String getModel() {
    return model;
  }

  public int getYear() {
    return year;
  }

}

class Car extends Vehicle {
  private final int numberOfDoors;

  Car(String make, String model, int year, int numberOfDoors) {
    super(make, model, year);
    this.numberOfDoors = numberOfDoors;
  }

  public int getNumberOfDoors() {
    return numberOfDoors;
  }

  public void displayDetails() {
    System.out.println(this);
  }

  public String toString() {
    return "Car Details:\n" +
            "Make: " + getMake() + "\n" +
            "Model: " + getModel() + "\n" +
            "Year: " + getYear() + "\n" +
            "Number of Doors: " + numberOfDoors;
  }
}