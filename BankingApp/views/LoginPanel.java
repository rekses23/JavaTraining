package BankingApp.views;

import BankingApp.Main;
import BankingApp.components.Button;
import BankingApp.components.ComponentUtils;
import BankingApp.components.PasswordField;
import BankingApp.components.TextField;
import BankingApp.models.User;
import BankingApp.services.UserService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class LoginPanel extends JPanel {
  private final Main main;
  private final TextField idNumberField;
  private final PasswordField passwordField;
  private final UserService userService;

  public LoginPanel(Main main, UserService userService) {
    this.userService = userService;
    this.main = main;
    this.idNumberField = new TextField();
    this.passwordField = new PasswordField();

    JComponent idNumberComponent = ComponentUtils.createField(this.idNumberField, "ID Number", true);
    idNumberComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
    idNumberComponent.setMinimumSize(new Dimension(100, 70));
    idNumberComponent.setPreferredSize(new Dimension(Integer.MAX_VALUE, 70));
    idNumberComponent.setSize(new Dimension(new Dimension(Integer.MAX_VALUE, 70)));
    idNumberComponent.setMaximumSize(new Dimension(300, 120));

    JComponent passwordComponent = ComponentUtils.createField(this.passwordField, "Password (4-pin number)", true);
    passwordComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
    passwordComponent.setMinimumSize(new Dimension(100, 70));
    passwordComponent.setPreferredSize(new Dimension(Integer.MAX_VALUE, 70));
    passwordComponent.setSize(new Dimension(new Dimension(Integer.MAX_VALUE, 70)));
    passwordComponent.setMaximumSize(new Dimension(300, 120));

    JLabel label = new JLabel();
    label.setText("LOGIN");
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    label.setFont(new Font("", Font.BOLD, 24));

    Button loginButton = new Button("Login");
    loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    loginButton.setMinimumSize(new Dimension(100, 50));
    loginButton.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
    loginButton.setSize(new Dimension(new Dimension(Integer.MAX_VALUE, 50)));
    loginButton.setMaximumSize(new Dimension(300, 100));
    loginButton.addActionListener(_ -> login());

    JButton goRegister = new JButton();
    goRegister.setText("Register");
    goRegister.setBackground(new Color(255, 255, 255, 0));
    goRegister.setOpaque(false);
    goRegister.setForeground(new Color(0x4158FF));
    goRegister.setFocusPainted(false);
    goRegister.setBorder(null);
    goRegister.addActionListener(_ -> {
      clearForm();
      main.setFrameTitle("Banking App - Register");
      main.switchPanel("Register");
    });

    JLabel goToRegisterLabel = new JLabel();
    goToRegisterLabel.setText("Don't have an account?");

    JPanel goToRegisterContainer = new JPanel();
    goToRegisterContainer.setLayout(new FlowLayout());
    goToRegisterContainer.add(goToRegisterLabel);
    goToRegisterContainer.add(goRegister);

    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    container.add(label);
    container.add(Box.createVerticalStrut(10));
    container.add(idNumberComponent);
    container.add(Box.createVerticalStrut(10));
    container.add(passwordComponent);
    container.add(Box.createVerticalStrut(10));
    container.add(loginButton);
    container.add(Box.createVerticalStrut(10));
    container.add(goToRegisterContainer);

    this.setBorder(new EmptyBorder(20, 20, 20, 20));
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(Box.createVerticalGlue());
    this.add(container);
    this.add(Box.createVerticalGlue());
  }

  private void login() {
    String idNumberInput = idNumberField.getText();
    String passwordInput = String.valueOf(passwordField.getPassword());
    try {
      int idNumber = Integer.parseInt(idNumberInput);
      short password = Short.parseShort(passwordInput);
      User loggedUser = userService.login(idNumber, password);
      if (loggedUser != null) {
        clearForm();
        main.getHomePanel().setLoggedUser(loggedUser);
        main.setFrameTitle("Banking App");
        main.switchPanel("Home");
      } else {
        JOptionPane.showMessageDialog(this, "Incorrect ID number or password!", "Error", JOptionPane.ERROR_MESSAGE, null);
      }
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid ID number or password!", "Error", JOptionPane.ERROR_MESSAGE, null);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "An error has occurred while accessing the file db.", "Error", JOptionPane.ERROR_MESSAGE, null);
    }
  }

  private void clearForm() {
    idNumberField.setText("");
    passwordField.setText("");
  }
}
