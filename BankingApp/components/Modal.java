package BankingApp.components;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Frame;

public class Modal extends JDialog {

  public Modal(Frame owner, String title) {
    super(owner, title, true);
    this.setLocationRelativeTo(owner);
    this.setLayout(new BorderLayout());
  }

}
