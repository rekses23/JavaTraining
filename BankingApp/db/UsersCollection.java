package BankingApp.db;

import BankingApp.models.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UsersCollection {
  private final String path;

  public UsersCollection(String path) {
    this.path = path;
  }


  public void addUser(User user) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
      writer.write(user.toString());
      writer.newLine();
    }
  }

  public List<User> getUsers() throws IOException {
    Path p = Paths.get(path);
    List<String> rows = Files.readAllLines(p);
    List<User> storedUsers = new ArrayList<>();
    for (String row : rows) {
      User user;
      if ((user = toUser(row.split(","))) != null) {
        storedUsers.add(user);
      }
    }
    return storedUsers;
  }

  public boolean updateUser(int idNumber, User updatedUser) throws IOException {
    Path p = Paths.get(path);
    List<String> rows = Files.readAllLines(p);
    int index = -1;
    for (int i = 0; i < rows.size(); i++) {
      User user = toUser(rows.get(i).split(","));
      if (user == null) continue;
      if (user.getIdNumber() == idNumber) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      rows.set(index, updatedUser.toString());
      Files.write(p, rows);
      return true;
    } else {
      return false;
    }
  }

  public boolean isIdNumberUsedAlready(int idNumber) throws IOException {
    List<User> storedUsers = getUsers();
    return storedUsers.stream().anyMatch(u -> u.getIdNumber() == idNumber);
  }

  private User toUser(String... args) {
    if (args.length != 4) return null;
    try {
      String name = args[0];
      int idNumber = Integer.parseInt(args[1]);
      short password = Short.parseShort(args[2]);
      double balance = Double.parseDouble(args[3]);

      return new User(name, idNumber, password, balance);
    } catch (NumberFormatException e) {
      return null;
    }

  }
}
