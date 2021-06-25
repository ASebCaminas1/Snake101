import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameOver extends JFrame {

    public GameOver() {
            JPanel basic = new JPanel();
            JLabel area = new JLabel("<<GAME OVER>>");
            area.setBounds(10,10,10,10);
            Font font = new Font("Helvetica", Font.BOLD, 11);
            area.setFont(font);
            area.setForeground(Color.BLUE);
            basic.add(area);


            basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
            add(basic);
            basic.add(Box.createVerticalGlue());
            JPanel bottom = new JPanel();
            bottom.setAlignmentX(1f);
            bottom.setLayout(new BoxLayout(bottom,
                    BoxLayout.X_AXIS));
            JButton ok = new JButton("Restart");
            JButton close = new JButton("Close");
            bottom.add(ok);
            bottom.add(Box.createRigidArea(new Dimension(5, 0)));
            bottom.add(close);
            bottom.add(Box.createRigidArea(new Dimension(15, 0)));
            basic.add(bottom);
            basic.add(Box.createRigidArea(new Dimension(0, 15)));
            setTitle("Game over");
            setSize(300, 150);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            close.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                    }
            });

            ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            System.exit(0); // Change This!
                    }
            });


    }
}
