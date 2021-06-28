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
        createMenu();


        board.initGame();

        pack();
        setLocationRelativeTo(null);
    }


    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new Game();
                frame.setVisible(true);
            }
        });
    }

    private void createMenu() {

        JMenuBar menubar = new JMenuBar();
        JMenu startm = new JMenu("Start");
        JMenu exitm = new JMenu("Exit");
        JMenu pausem = new JMenu("Pause");
        JMenu helpm = new JMenu("Help");

        menubar.add(startm);
        startm.addActionListener(new java.awt.event.ActionListener() {
                                     public void actionPerformed(java.awt.event.ActionEvent evt) {
                                         board.timer.stop();
                                         board.initGame();
                                     }
                                 });
        menubar.add(exitm);
        exitm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        menubar.add(pausem);
        pausem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (board.timer.isRunning()) {
                board.timer.stop();
                } else {
                    board.timer.start();
                }
            }
        });


        menubar.add(Box.createHorizontalGlue());


        menubar.add(helpm);


        setJMenuBar(menubar);
    }
}