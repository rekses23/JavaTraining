package BankingApp.components;

import javax.swing.*;
import java.awt.*;

public class ComponentUtils {
  public static JComponent createField(Component control, String labelText, boolean isRequired) {
    JLabel label = new JLabel();
    label.setText(labelText);
    label.setFont(new Font("Arial", Font.BOLD, 14));
    label.setForeground(Color.DARK_GRAY);
    label.setAlignmentX(Component.LEFT_ALIGNMENT);

    JLabel indicator = new JLabel("*");
    indicator.setForeground(Color.RED);
    label.add(indicator);

    JPanel cont = new JPanel();
    cont.setLayout(new BorderLayout(3, 0));
    cont.add(label, BorderLayout.WEST);
    cont.add(indicator, BorderLayout.CENTER);

    JPanel container = new JPanel();
    container.setLayout(new BorderLayout(0, 5));
    container.add(cont, BorderLayout.NORTH);
    container.add(control, BorderLayout.CENTER);
    return container;
  }
}
