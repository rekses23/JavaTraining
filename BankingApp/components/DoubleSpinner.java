package BankingApp.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DoubleSpinner extends JSpinner {

  public DoubleSpinner() {
    SpinnerNumberModel doubleModel = new SpinnerNumberModel(0.0, 0.0, Double.POSITIVE_INFINITY, 0.1);
    this.setModel(doubleModel);
    this.setBorder(new EmptyBorder(0, 0, 0, 0));
    this.setFont(new Font("Arial", Font.PLAIN, 16));
  }

}
