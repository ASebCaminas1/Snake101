import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    private JPanel mainPanel;
    private Board board;
    private ScoreBoard scoreBoard;


     public Game() {
         super();
         setTitle("Snake Game");
         setIconImage(new ImageIcon("res/icon.png").getImage());
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         scoreBoard = new ScoreBoard();
         board = new Board(scoreBoard);
         board.setBackground(Color.black);
         scoreBoard.setBackground(Color.GRAY);

         Dimension dimension = new Dimension();
         dimension.width = 800;
         dimension.height = 640;
         setResizable(false);


         mainPanel = new JPanel();

         mainPanel.setPreferredSize(dimension);

         mainPanel.setLayout(new BorderLayout());

         mainPanel.add(board, BorderLayout.CENTER);
         mainPanel.add(scoreBoard, BorderLayout.PAGE_END);
         setContentPane(mainPanel);


         board.initGame();

         pack();
         setLocationRelativeTo(null);
     }



    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                JFrame frame = new Game();
                frame.setVisible(true);
            }
        });
    }
}
