import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigDialog extends JDialog {

    public ConfigDialog() {

        Container panel = getContentPane();
        panel.setLayout(new GridLayout(4, 2));
        panel.setPreferredSize(new Dimension(200, 80));

        JLabel labelName = new JLabel("Player 1 name:");
        panel.add(labelName, null, 0);
        JLabel labelName2 = new JLabel("Player 2 name:");
        panel.add(labelName2, null, 1);

        JTextField textField = new JTextField();
        panel.add(textField);
        JTextField textField2 = new JTextField();
        panel.add(textField2);

        JLabel labelLevel = new JLabel("Level:");
        panel.add(labelLevel);
        setModal(true);

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("1");
        comboBox.addItem("2");
        comboBox.addItem("3");
        comboBox.addItem("4");
        panel.add(comboBox);

        JButton buttonOK = new JButton("Ok");
        panel.add(buttonOK);
        panel.add(new JPanel());
        pack();
        setLocationRelativeTo(null);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.playerName = textField.getText();
                Settings.playerName2 = textField.getText();
                Settings.level = comboBox.getSelectedIndex();
                dispose();
            }
        });
    }
}
