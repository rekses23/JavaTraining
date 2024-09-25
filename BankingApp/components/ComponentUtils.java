package BankingApp.components;

import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComponentUtils {
  public static JComponent createField(JComponent control, String labelText, boolean isRequired) {
    JLabel label = new JLabel();
    label.setText(labelText);
    label.setFont(new Font("Arial", Font.BOLD, 14));
    label.setForeground(Color.DARK_GRAY);
    label.setAlignmentX(Component.LEFT_ALIGNMENT);


    JPanel labelContainer = new JPanel();
    labelContainer.setLayout(new BorderLayout(3, 0));
    labelContainer.add(label, BorderLayout.WEST);

    if (isRequired) {
      JLabel indicator = new JLabel("*");
      indicator.setForeground(Color.RED);
      label.add(indicator);
      labelContainer.add(indicator, BorderLayout.CENTER);
    }

    JPanel container = new JPanel();
    container.setLayout(new BorderLayout(0, 5));
    container.add(labelContainer, BorderLayout.NORTH);
    container.add(control, BorderLayout.CENTER);
    return container;
  }
}
