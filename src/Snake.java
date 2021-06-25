import javax.swing.*;
import java.awt.*;

public class Snake extends JFrame {

    Board board = new Board();
    JPanel panel;
    //Food food = new Food();



     public Snake() {
         super();
         setTitle("Snake Game");
         setIconImage(new ImageIcon("res/icon.png").getImage());
         setSize(800, 600);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         panel = new JPanel();
         Dimension dimension = new Dimension();
         dimension.width = 600;
         dimension.height = 400;
         panel.setPreferredSize(dimension);
         panel.setLayout(new BorderLayout());
         this.setLayout(null);
         panel.add(board, BorderLayout.CENTER);
         board.initGame();


         setContentPane(panel);
         pack();
         setLocationRelativeTo(null);
     }



    public static void main(String[] args) {


        javax.swing.SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                Snake snake = new Snake();
                snake.setVisible(true);
            }
        });
    }



}
