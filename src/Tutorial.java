import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tutorial extends JDialog{
    public Tutorial(){
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0,10)));

        ImageIcon icon = new ImageIcon("res/coffee.png");
        JLabel label = new JLabel(icon);
        label.setAlignmentX(0.5f);
        add(label);

        add(Box.createRigidArea(new Dimension(0,10)));

        JLabel name = new JLabel("Snake tutorial");
        name.setFont(new Font("Helvetica", Font.BOLD, 14));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(name);

        add(Box.createRigidArea(new Dimension(30,10)));

        JTextArea text = new JTextArea();
        text.setText(" Move the snake avoiding the walls.\n  There are three types of food:\n" +
                " - Tofu, which makes a node grow.\n - Banana, which grows three nodes.\n " +
                "- Coffee, that allows you to open teleportation portals \n" +
                " when the snake suffers a caffeine overdose (4 coffees).\n" +
                "- Movement player 1: Arrows\n" +
                "- Movement player 2: A,S,W,D");
        text.setFont(new Font("Helvetica", Font.BOLD, 12));
        add(text);



        add(Box.createRigidArea(new Dimension(0,50)));

        JButton close = new JButton("Close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        close.setAlignmentX(0.5f);
        add(close);

        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setTitle("Tutorial");
        setIconImage(new ImageIcon("res/notes.png").getImage());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 300);

    }
}
