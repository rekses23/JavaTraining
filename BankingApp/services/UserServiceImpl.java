package BankingApp.services;

import BankingApp.db.UsersCollection;
import BankingApp.models.User;

import java.io.IOException;
import java.util.List;

public class UserServiceImpl implements UserService {

  private final UsersCollection usersCollection;

  public UserServiceImpl(UsersCollection usersCollection) {
    this.usersCollection = usersCollection;
  }

  @Override
  public void register(String name, int idNumber, short password) throws DuplicateUserException, IOException {
    User user = new User(name, idNumber, password);
    if (usersCollection.isIdNumberUsedAlready(idNumber)) throw new DuplicateUserException("Id number has already been used.");
    usersCollection.addUser(user);
  }

  @Override
  public User login(int idNumber, short password) throws IOException {
    List<User> users = usersCollection.getUsers();
    for (User user : users) {
      if (user.getIdNumber() == idNumber && user.getPassword() == password) return user;
    }
    return null;
  }

  @Override
  public boolean cashIn(User user, double amount) throws IOException {
    user.setBalance(user.getBalance() + amount);
    return usersCollection.updateUser(user.getIdNumber(), user);
  }

  @Override
  public boolean transferMoney(User user, int idNumber, double amount) throws IOException {
    if (user.getIdNumber() == idNumber) return false;
    if (user.getBalance() < amount) return false;
    List<User> storedUsers = usersCollection.getUsers();
    User targetUser = null;
    for (User storedUser : storedUsers) {
      if (storedUser.getIdNumber() == idNumber) {
        targetUser = storedUser;
        break;
      }
    }

    if (targetUser != null) {
      boolean result;
      user.setBalance(user.getBalance() - amount);
      targetUser.setBalance(targetUser.getBalance() + amount);

      result = usersCollection.updateUser(user.getIdNumber(), user);
      if (result) result = usersCollection.updateUser(idNumber, targetUser);


      if (result) {
        return true;
      } else {
        user.setBalance(user.getBalance() + amount);
        targetUser.setBalance(targetUser.getBalance() - amount);
        return false;
      }
    }
    return false;
  }
}
