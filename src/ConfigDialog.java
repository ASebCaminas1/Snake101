import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigDialog extends JDialog {

    public ConfigDialog() {

        Container panel = getContentPane();
        panel.setLayout(new GridLayout(3, 2));
        panel.setPreferredSize(new Dimension(200, 80));

        JLabel labelName = new JLabel("User name");
        panel.add(labelName, null, 0);

        JTextField textField = new JTextField();
        panel.add(textField);

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
                Settings.level = comboBox.getSelectedIndex();
                dispose();
            }
        });
    }
}
