import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JMenu game = new JMenu("Game");
        JMenu settings = new JMenu("Settings");
        JMenu help = new JMenu("Help");

        menubar.add(game);

        JMenuItem newGame = new JMenuItem("New game");
        JMenuItem quit = new JMenuItem("Quit");
        game.add(newGame);
        game.add(quit);

        newGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                board.timer.stop();
                board.initGame();
            }
        });

        quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });


        menubar.add(settings);
        JMenuItem config = new JMenuItem("Config");
        settings.add(config);

        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigDialog configDialog = new ConfigDialog();
                configDialog.setIconImage(new ImageIcon("res/config.png").getImage());
                configDialog.setVisible(true);
                scoreBoard.reset();
            }
        });

        menubar.add(Box.createHorizontalGlue());

        JMenuItem about = new JMenuItem("About");
        help.add(about);
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutDialog ad = new AboutDialog();
                ad.setVisible(true);
            }
        });

        JMenuItem tutorial = new JMenuItem("Tutorial");
        help.add(tutorial);
        tutorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tutorial tut = new Tutorial();
                tut.setVisible(true);

            }
        });

        menubar.add(help);
        setJMenuBar(menubar);
    }
}