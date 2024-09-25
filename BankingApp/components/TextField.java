package BankingApp.components;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class TextField extends JTextField {

  public TextField() {
    this.setBorder(new EmptyBorder(5, 10, 5, 10));
    this.setFont(new Font("Arial", Font.PLAIN, 16));
  }

}
