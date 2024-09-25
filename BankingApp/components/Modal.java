package BankingApp.components;

import javax.swing.*;
import java.awt.*;

public class Modal extends JDialog {

  public Modal(Frame owner, String title) {
    super(owner, title, true);
    this.setLocationRelativeTo(owner);
    this.setLayout(new BorderLayout());
  }

}
