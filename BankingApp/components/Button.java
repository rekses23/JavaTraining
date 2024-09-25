package BankingApp.components;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Button extends JButton {

  public Button(String text) {
    super(text);
    this.setBackground(new Color(0x4158FF));
    this.setFocusPainted(false);
    this.setForeground(Color.WHITE);
    this.setFont(new Font("Arial", Font.BOLD, 16));
    this.setBorder(new EmptyBorder(5, 10, 5, 10));
    this.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        e.getComponent().setBackground(new Color(0x3447CF));
      }

      @Override
      public void focusLost(FocusEvent e) {
        e.getComponent().setBackground(new Color(0x4158FF));
      }
    });
  }
}
