import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Board extends JPanel {

    public static final int NUM_ROWS = 40;
    public static final int NUM_COLS = 50;
    private int deltaTime = 300;
    private Snake snake;
    private Coffee coffee;
    private Timer timer;
    private MyKeyAdapter myKey;
    private Incrementer incrementer;
    private Food food;
    private boolean antiTurner = false;
    private BufferedImage background;



    public Board(Incrementer incrementer) {
        this.incrementer = incrementer;

    }


    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != Direction.RIGHT && !antiTurner) {
                        snake.setDirection(Direction.LEFT);
                        antiTurner = true;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.LEFT && !antiTurner) {
                        snake.setDirection(Direction.RIGHT);
                        antiTurner = true;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.DOWN && !antiTurner) {
                        snake.setDirection(Direction.UP);
                        antiTurner = true;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.UP && !antiTurner) {
                        snake.setDirection(Direction.DOWN);
                        antiTurner = true;
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    jump();
                    break;
                case KeyEvent.VK_P:
                    if (timer.isRunning()) {
                        timer.stop();
                    } else {
                        timer.start();
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
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
        coffee = createCoffee();
        addKeyListener(myKey);
        setFocusable(true);
        requestFocus();
        incrementer.reset();
        timer = new Timer(getDeltaTime(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lap();
            }
        });
        timer.start();

    }

    public void lap() {
        snake.move();

        coffee = createCoffee();
        antiTurner = false;
        repaint();
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

    public void setDeltaTime(int x){
        deltaTime = getDeltaTime() - x;
    }

    public int getDeltaTime(){
        return deltaTime;
    }

    private void drawBlackBorder(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, getWidth() / NUM_COLS * NUM_COLS,
                getHeight() / NUM_ROWS * NUM_ROWS);
    }

    public Coffee createCoffee() {
        Random rand = new Random();
        int x = rand.nextInt(3);
        if (x == 0) {
            return null;
        } else {
            return new Coffee(snake);
        }


        /*
        Random random = new Random();
        int num = random.nextInt(3);
        if (num == 0) {
            return new Advocado(snake);
        } else if (num == 1) {
            return new Coffee(snake);

        } else {
            return new Food(snake);
        }*/
    }

    public void jump() {
        Node node = new Node(snake.calculateNextNode().getRow(), snake.calculateNextNode().getCol());
        for (int i = 0; i < 2; i++) {
            snake.getBody().add(0, node);
            snake.removeLastNode();
        }
    }


}