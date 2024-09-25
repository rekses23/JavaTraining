package BankingApp.services;

import BankingApp.models.User;

import java.io.IOException;

public interface UserService {

  class DuplicateUserException extends RuntimeException {
    DuplicateUserException(String message) {
      super(message);
    }
  }

  void register(String name, int idNumber, short password) throws DuplicateUserException, IOException;

  User login(int idNumber, short password) throws IOException;

  boolean cashIn(User user, double amount) throws IOException;

  boolean transferMoney(User user, int idNumber, double amount) throws IOException;
}



