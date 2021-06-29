import javax.swing.*;
import java.awt.*;

public class ConfigDialog extends JDialog {

    public ConfigDialog() {

        Container panel = getContentPane();
        panel.setLayout(new GridLayout(3, 2));
        panel.setPreferredSize(new Dimension(200, 80));



    }
}
