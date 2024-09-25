package BankingApp.views;

import BankingApp.Main;
import BankingApp.components.*;
import BankingApp.components.Button;
import BankingApp.components.TextField;
import BankingApp.models.User;
import BankingApp.services.UserService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HomePanel extends JPanel {
  private final Main main;
  private User loggedUser;
  private JLabel welcomeLabel;
  private final UserService userService;

  public HomePanel(Main main, UserService userService) {
    this.userService = userService;
    this.main = main;
    this.welcomeLabel = new JLabel();
    this.welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
    this.welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

    Button checkBalanceButton = new Button("Check Balance");
    checkBalanceButton.addActionListener(_ -> checkBalance());
    checkBalanceButton.setPreferredSize(new Dimension(200, 100));

    Button cashInButton = new Button("Cash-in");
    cashInButton.addActionListener(_ -> cashIn());

    Button transferMoneyButton = new Button("Transfer Money");
    transferMoneyButton.addActionListener(_ -> transferMoney());

    Button logoutButton = new Button("Logout");
    logoutButton.addActionListener(_ -> logout());

    JPanel centerContainer = new JPanel();
    centerContainer.setLayout(new GridLayout(2, 2, 50, 50));
    centerContainer.setBorder(new EmptyBorder(50, 50, 50, 50));
    centerContainer.add(checkBalanceButton);
    centerContainer.add(cashInButton);
    centerContainer.add(transferMoneyButton);
    centerContainer.add(logoutButton);

    this.setLayout(new BorderLayout(10, 10));
    this.setBorder(new EmptyBorder(50, 10, 10, 10));
    this.add(welcomeLabel, BorderLayout.NORTH);
    this.add(centerContainer, BorderLayout.CENTER);
  }

  public void setLoggedUser(User loggedUser) {
    LocalTime now = LocalTime.now();
    String greeting;
    if (now.isBefore(LocalTime.NOON)) {
      greeting = "Good Morning";
    } else if (now.isBefore(LocalTime.of(18, 0))) {
      greeting = "Good Afternoon";
    } else {
      greeting = "Good Evening";
    }
    welcomeLabel.setText(greeting + ", " + loggedUser.getName() + "!");
    this.loggedUser = loggedUser;
  }

  private void checkBalance() {
    Modal modal = new Modal(main.getMainFrame(), "Check Balance");

    JLabel balance = new JLabel();
    balance.setText("Current Balance: Php." + loggedUser.getBalance());
    balance.setOpaque(true);
    balance.setFont(new Font("Arial", Font.BOLD, 24));

    Button button = new Button("Ok");
    button.setPreferredSize(new Dimension(250, 50));
    button.addActionListener(_ -> modal.dispose());

    JPanel container = new JPanel();
    container.setLayout(new BorderLayout(10, 10));
    container.setBorder(new EmptyBorder(20, 20, 20, 20));
    container.add(balance, BorderLayout.CENTER);
    container.add(button, BorderLayout.SOUTH);

    modal.setUndecorated(true);
    modal.add(container);
    modal.getRootPane().setBorder(new LineBorder(Color.BLACK, 2, true));
    modal.pack();
    modal.setLocationRelativeTo(this);
    modal.setVisible(true);
  }

  private void transferMoney() {
    Modal modal = new Modal(main.getMainFrame(), "Transfer Money");

    TextField targetIdNumberField = new TextField();
    JComponent idNumberComponent = ComponentUtils.createField(targetIdNumberField, "ID Number", true);
    idNumberComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
    idNumberComponent.setPreferredSize(new Dimension(250, 70));

    DoubleSpinner amountField = new DoubleSpinner();
    JComponent amountComponent = ComponentUtils.createField(amountField, "Amount in PHP", true);
    amountComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
    amountComponent.setPreferredSize(new Dimension(250, 70));

    Button transferButton = new Button("Transfer");
    transferButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    transferButton.setMinimumSize(new Dimension(120, 50));
    transferButton.setPreferredSize(new Dimension(120, 50));
    transferButton.setMaximumSize(new Dimension(120, 50));
    transferButton.addActionListener(_ -> {
      try {
        int targetIdNumber = Integer.parseInt(targetIdNumberField.getText());
        double amount = (Double) amountField.getValue();
        if (loggedUser.getBalance() < amount) {
          JOptionPane.showMessageDialog(this, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE, null);
        } else if (loggedUser.getIdNumber() == targetIdNumber) {
          JOptionPane.showMessageDialog(this, "Cannot transfer to self!", "Error", JOptionPane.ERROR_MESSAGE, null);
        } else {
          if (userService.transferMoney(loggedUser, targetIdNumber, amount)) {
            JOptionPane.showMessageDialog(this, "Successfully transferred " + amount + " to " + targetIdNumber, "Success", JOptionPane.INFORMATION_MESSAGE, null);
            modal.dispose();
          } else {
            JOptionPane.showMessageDialog(this, "Transfer Failed!", "Error", JOptionPane.ERROR_MESSAGE, null);
          }
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid ID Number!", "Error", JOptionPane.ERROR_MESSAGE, null);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "An error has occurred while accessing the file db.", "Error", JOptionPane.ERROR_MESSAGE, null);
      }
    });

    Button closeButton = new Button("Cancel");
    closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    closeButton.setMinimumSize(new Dimension(120, 50));
    closeButton.setPreferredSize(new Dimension(120, 50));
    closeButton.setMaximumSize(new Dimension(120, 50));
    closeButton.addActionListener(_ -> modal.dispose());

    JPanel actionsContainer = new JPanel();
    actionsContainer.setLayout(new BoxLayout(actionsContainer, BoxLayout.X_AXIS));
    actionsContainer.add(closeButton);
    actionsContainer.add(Box.createHorizontalStrut(10));
    actionsContainer.add(transferButton);

    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    container.setBorder(new EmptyBorder(10, 20, 10, 20));
    container.add(idNumberComponent);
    container.add(Box.createVerticalStrut(10));
    container.add(amountComponent);
    container.add(Box.createVerticalStrut(10));
    container.add(actionsContainer);

    modal.add(container, BorderLayout.CENTER);
    modal.setUndecorated(true);
    modal.getRootPane().setBorder(new LineBorder(Color.BLACK, 2, true));
    modal.pack();
    modal.setLocationRelativeTo(this);
    modal.setVisible(true);
  }

  private void cashIn() {
    Modal modal = new Modal(main.getMainFrame(), "Transfer Money");

    DoubleSpinner amountField = new DoubleSpinner();
    JComponent amountComponent = ComponentUtils.createField(amountField, "Amount in PHP", true);
    amountComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
    amountComponent.setPreferredSize(new Dimension(250, 70));

    Button cashInButton = new Button("Cash-in");
    cashInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    cashInButton.setMinimumSize(new Dimension(120, 50));
    cashInButton.setPreferredSize(new Dimension(120, 50));
    cashInButton.setMaximumSize(new Dimension(120, 50));
    cashInButton.addActionListener(_ -> {
      if ((Double) amountField.getValue() == 0){
        modal.dispose();
        return;
      }
      try {
        double amount = (Double) amountField.getValue();
        userService.cashIn(loggedUser, amount);
        JOptionPane.showMessageDialog(this, "Successfully added Php. " + amount + ". Your new balance is Php. " + loggedUser.getBalance(), "Success", JOptionPane.INFORMATION_MESSAGE, null);
        modal.dispose();
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid value!", "Error", JOptionPane.ERROR_MESSAGE, null);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "An error has occurred while accessing the file db.", "Error", JOptionPane.ERROR_MESSAGE, null);
      }
    });

    Button closeButton = new Button("Cancel");
    closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    closeButton.setMinimumSize(new Dimension(120, 50));
    closeButton.setPreferredSize(new Dimension(120, 50));
    closeButton.setMaximumSize(new Dimension(120, 50));
    closeButton.addActionListener(_ -> modal.dispose());

    JPanel actionsContainer = new JPanel();
    actionsContainer.setLayout(new BoxLayout(actionsContainer, BoxLayout.X_AXIS));
    actionsContainer.add(closeButton);
    actionsContainer.add(Box.createHorizontalStrut(10));
    actionsContainer.add(cashInButton);

    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    container.setBorder(new EmptyBorder(10, 20, 10, 20));
    container.add(Box.createVerticalStrut(10));
    container.add(amountComponent);
    container.add(Box.createVerticalStrut(10));
    container.add(actionsContainer);

    modal.add(container, BorderLayout.CENTER);
    modal.setUndecorated(true);
    modal.getRootPane().setBorder(new LineBorder(Color.BLACK, 2, true));
    modal.pack();
    modal.setLocationRelativeTo(this);
    modal.setVisible(true);
  }

  private void logout() {
    this.loggedUser = null;
    main.setFrameTitle("Banking App - Login");
    main.switchPanel("Login");
  }
}
