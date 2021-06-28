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
    private int deltaTime = 600;
    private Snake snake;
    private Food food;
    private SpecialFood specialFood;
    private int caffeine = 0;
    private Timer timer;
    private MyKeyAdapter myKey;
    private Incrementer incrementer;
    private boolean antiTurner = false;
    private Coffee coffee;
    //private BufferedImage background;

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
        food = new Food(snake);

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
        if (!snake.colides()) {
            snake.move();
            if (snake.checkFood(food)) {
                if (food instanceof SpecialFood) {
                        snake.incrementNodesToGrow(3);
                        incrementer.incrementScore(100);
                } else {
                    snake.incrementNodesToGrow(1);
                    incrementer.incrementScore(10);
                    System.out.println(food.getType());
                }
                if (food.getType().equals("COFFEE")) {
                    setDeltaTime(100);
                    System.out.println(food.getType());
                }
                food = foodCreator();
            }
            antiTurner = false;
            repaint();
        } else {
            gameOver();
        }
    }


    public void foodManage() {
        food = foodCreator();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.paint(g, getSize().width / Board.NUM_COLS,
                getSize().height / Board.NUM_ROWS);
        food.paint(g, getSize().width / Board.NUM_COLS, getSize().height / Board.NUM_ROWS);

        drawBlackBorder(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public void setDeltaTime(int x) {
        //Caffeine controls that the coffee type modifies the delta time up to a maximum of three times.
        if (caffeine < 3) {
            deltaTime = getDeltaTime() - x;
        } else {
            deltaTime = 600;
            caffeine = 0;
        }
    }

    public int getDeltaTime() {
        return deltaTime;
    }

    private void drawBlackBorder(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, getWidth() / NUM_COLS * NUM_COLS,
                getHeight() / NUM_ROWS * NUM_ROWS);
    }

    public void jump() {
        //Pressing space moves the snake forward two nodes.
        Node node = new Node(snake.calculateNextNode().getRow(), snake.calculateNextNode().getCol());
        for (int i = 0; i <= 2; i++) {
            snake.getBody().add(0, node);
            snake.removeLastNode();
        }
    }

    public Food foodCreator() {
        //Select randomly between normal and special food.
        int num = (int) (Math.random() * 4);
        if (num == 2) {
            return new SpecialFood(snake, this::foodRemove);
        }
        if (num == 3) {
            return new Coffee(snake,this::foodRemove);
        }
        return new Food(snake);


    }

    public void foodRemove() {
        foodCreator();
    }


    private void gameOver(){
        System.out.println("GAME OVER");
        timer.stop();
    }



}