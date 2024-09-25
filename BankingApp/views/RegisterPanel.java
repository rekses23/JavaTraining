package BankingApp.views;

import BankingApp.Main;
import BankingApp.components.Button;
import BankingApp.components.ComponentUtils;
import BankingApp.components.PasswordField;
import BankingApp.components.TextField;
import BankingApp.services.UserService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class RegisterPanel extends JPanel {
  private final Main main;
  private final TextField nameField;
  private final TextField idNumberField;
  private final PasswordField passwordField;
  private final UserService userService;

  public RegisterPanel(Main main, UserService userService) {
    this.userService = userService;
    this.main = main;
    this.nameField = new TextField();
    this.idNumberField = new TextField();
    this.passwordField = new PasswordField();

    JComponent nameComponent = ComponentUtils.createField(this.nameField, "Name", true);
    nameComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
    nameComponent.setMinimumSize(new Dimension(100, 70));
    nameComponent.setPreferredSize(new Dimension(Integer.MAX_VALUE, 70));
    nameComponent.setSize(new Dimension(new Dimension(Integer.MAX_VALUE, 70)));
    nameComponent.setMaximumSize(new Dimension(300, 120));

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
    label.setText("REGISTER");
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    label.setFont(new Font("", Font.BOLD, 24));

    Button registerButton = new Button("Register");
    registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    registerButton.setMinimumSize(new Dimension(100, 50));
    registerButton.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
    registerButton.setSize(new Dimension(new Dimension(Integer.MAX_VALUE, 50)));
    registerButton.setMaximumSize(new Dimension(300, 100));
    registerButton.addActionListener(_ -> register());

    JButton goLogin = new JButton();
    goLogin.setText("Login");
    goLogin.setBackground(new Color(255, 255, 255, 0));
    goLogin.setOpaque(false);
    goLogin.setForeground(new Color(0x4158FF));
    goLogin.setFocusPainted(false);
    goLogin.setBorder(null);
    goLogin.addActionListener(_ -> {
      clearForm();
      main.setFrameTitle("Banking App - Login");
      main.switchPanel("Login");
    });

    JLabel goToLoginLabel = new JLabel();
    goToLoginLabel.setText("Already have an account?");

    JPanel goToLoginContainer = new JPanel();
    goToLoginContainer.setLayout(new FlowLayout());
    goToLoginContainer.add(goToLoginLabel);
    goToLoginContainer.add(goLogin);

    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    container.add(label);
    container.add(Box.createVerticalStrut(10));
    container.add(nameComponent);
    container.add(Box.createVerticalStrut(10));
    container.add(idNumberComponent);
    container.add(Box.createVerticalStrut(10));
    container.add(passwordComponent);
    container.add(Box.createVerticalStrut(10));
    container.add(registerButton);
    container.add(Box.createVerticalStrut(10));
    container.add(goToLoginContainer);

    this.setBorder(new EmptyBorder(20, 20, 20, 20));
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(Box.createVerticalGlue());
    this.add(container);
    this.add(Box.createVerticalGlue());
  }

  private void register() {
    String name = nameField.getText();
    String idNumberInput = idNumberField.getText();
    String passwordInput = String.valueOf(passwordField.getPassword());
    try {
      int idNumber = Integer.parseInt(idNumberInput);
      short password = Short.parseShort(passwordInput);
      if (password > 9999 || password < 1000) {
        JOptionPane.showMessageDialog(this, "Invalid password!", "Error", JOptionPane.ERROR_MESSAGE, null);
      } else {
        userService.register(name, idNumber, password);
        JOptionPane.showMessageDialog(this, "Registered Successfully", "Success", JOptionPane.INFORMATION_MESSAGE, null);
        clearForm();
        main.setFrameTitle("Banking App - Login");
        main.switchPanel("Login");
      }
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid ID number or password!", "Error", JOptionPane.ERROR_MESSAGE, null);
    } catch (UserService.DuplicateUserException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "An error has occurred while accessing the file db.", "Error", JOptionPane.ERROR_MESSAGE, null);
    }
  }

  private void clearForm() {
    nameField.setText("");
    idNumberField.setText("");
    passwordField.setText("");
  }
}
