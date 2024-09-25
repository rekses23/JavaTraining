package BankingApp;


import BankingApp.db.UsersCollection;
import BankingApp.services.UserService;
import BankingApp.services.UserServiceImpl;
import BankingApp.views.HomePanel;
import BankingApp.views.LoginPanel;
import BankingApp.views.RegisterPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.CardLayout;
import java.io.File;

import java.io.IOException;

public class Main {
  private final JFrame mainFrame;
  private final CardLayout cardLayout;
  private final JPanel mainPanel;
  private final HomePanel homePanel;

  public Main() {
    String dbPath = "BankingApp/db/users.csv";
    UserService userService = new UserServiceImpl(new UsersCollection(dbPath));

    this.cardLayout = new CardLayout();
    this.mainPanel = new JPanel();
    this.mainFrame = new JFrame();
    this.homePanel = new HomePanel(this, userService);

    this.mainPanel.setLayout(cardLayout);
    this.mainPanel.add(new LoginPanel(this, userService), "Login");
    this.mainPanel.add(new RegisterPanel(this, userService), "Register");
    this.mainPanel.add(this.homePanel, "Home");

    this.mainFrame.setTitle("Banking App - Login");
    this.mainFrame.setSize(800, 600);
    this.mainFrame.setLocationRelativeTo(null);
    this.mainFrame.setResizable(false);
    this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.mainFrame.add(mainPanel);

    File file = new File(dbPath);
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        JOptionPane.showMessageDialog(mainFrame, "An error has occurred while creating the file db!", "Error", JOptionPane.ERROR_MESSAGE, null);
        System.exit(1);
      }
    }

    this.mainFrame.setVisible(true);
  }

  public static void main(String[] args) {
    new Main();
  }

  public void switchPanel(String panelName) {
    cardLayout.show(mainPanel, panelName);
  }

  public HomePanel getHomePanel() {
    return homePanel;
  }

  public void setFrameTitle(String title) {
    mainFrame.setTitle(title);
  }

  public JFrame getMainFrame() {
    return mainFrame;
  }
}
