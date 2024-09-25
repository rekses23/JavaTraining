package BankingApp.models;

public class User {
  private String name;
  private int idNumber;
  private short password;
  private double balance;

  public User(String name, int idNumber, short password) {
    this(name, idNumber, password, 0d);
  }

  public User(String name, int idNumber, short password, double balance) {
    this.name = name;
    this.idNumber = idNumber;
    this.password = password;
    this.balance = balance;
  }

  public int getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(int idNumber) {
    this.idNumber = idNumber;
  }

  public short getPassword() {
    return password;
  }

  public void setPassword(short password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return name + "," + idNumber + "," + password + ","  + balance;
  }
}
