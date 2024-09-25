package BankingApp.components;

import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class PasswordField extends JPasswordField {

  public PasswordField() {
    this.setBorder(new EmptyBorder(5, 10, 5, 10));
    this.setFont(new Font("Arial", Font.PLAIN, 16));
  }

}
