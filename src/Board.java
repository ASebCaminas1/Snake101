import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {

    public static final int NUM_ROWS = 40;
    public static final int NUM_COLS = 80;
    private int deltaTime = 300;
    private Snake snake;
    private Timer timer;
    private MyKeyAdapter myKey;
    private Incrementer incrementer;


    public Board(Incrementer incrementer) {
        this.incrementer = incrementer;

    }


    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    snake.setDirection(Direction.LEFT);
                    System.out.println("Left");
                    break;
                case KeyEvent.VK_RIGHT:
                    snake.setDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    snake.setDirection(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    snake.setDirection(Direction.DOWN);
                    break;
                case KeyEvent.VK_SPACE:

                    break;
                case KeyEvent.VK_P:


                    break;
                default:
                    break;
            }
            repaint();
        }

    }

    public void initGame() {

        myKey = new MyKeyAdapter();
        snake = new Snake();
        addKeyListener(myKey);
        setFocusable(true);
        requestFocus();
        incrementer.reset();
        timer = new Timer(deltaTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snake.move();

                repaint();
            }
        });
        timer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.paint(g, getSize().width / Board.NUM_COLS,
                getSize().height / Board.NUM_ROWS);

        drawBlackBorder(g);
/*
        if (snake != null) {
            snake.paint(g, getSize().width / Board.NUM_COLS,
                    getSize().height / Board.NUM_ROWS);
            food.paint(g, getSize().width / Board.NUM_COLS,
                    getSize().height / Board.NUM_ROWS);
            if (gameOver)
                printGameOver(g);
        }*/
        Toolkit.getDefaultToolkit().sync(); // To avoid jumps when no pressed key
    }

    private void drawBlackBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() / NUM_COLS * NUM_COLS,
                getHeight() / NUM_ROWS * NUM_ROWS);
    }
}