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
    private Advocado advocado;
    private Coffee coffee;
    private int cafeine = 0;
    private Timer timer;
    private MyKeyAdapter myKey;
    private Incrementer incrementer;
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
        food = new Food(snake);
        coffee = new Coffee(snake);
        advocado = new Advocado(snake);

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
            snake.incrementNodesToGrow(1);
            foodSelector();
        }
        if (snake.checkAdvocado(advocado))
        {
            snake.incrementNodesToGrow(3);
            foodSelector();
        }
        if (snake.checkCoffee(coffee)){
            cafeine++;
            setDeltaTime(100);
            foodSelector();
        }
        } else {

        }


        antiTurner = false;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.paint(g, getSize().width / Board.NUM_COLS,
                getSize().height / Board.NUM_ROWS);
        if (coffee != null ) {
            coffee.paint(g, getSize().width / Board.NUM_COLS,getSize().height / Board.NUM_ROWS);
        }
        if (food != null) {
        food.paint(g, getSize().width / Board.NUM_COLS,getSize().height / Board.NUM_ROWS);
        }
        if (advocado != null) {
            advocado.paint(g, getSize().width / Board.NUM_COLS,getSize().height / Board.NUM_ROWS);
        }
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
        if (cafeine < 3) {
            deltaTime = getDeltaTime() - x;
        } else {
            deltaTime = 600;
            cafeine = 0;
        }
    }

    public int getDeltaTime(){
        return deltaTime;
    }

    private void drawBlackBorder(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, getWidth() / NUM_COLS * NUM_COLS,
                getHeight() / NUM_ROWS * NUM_ROWS);
    }

    public void jump() {
        Node node = new Node(snake.calculateNextNode().getRow(), snake.calculateNextNode().getCol());
        for (int i = 0; i <= 2; i++) {
            snake.getBody().add(0, node);
            snake.removeLastNode();
        }
    }

    public void foodSelector(){
        Random rnd = new Random();
        int num =  (int)(rnd.nextDouble() * 5 + 0);
        switch (num) {
            case 1:
                coffee = new Coffee(snake);
                System.out.println("Coffee");

                break;
            case 2:
                food = new Food(snake);
                System.out.println("Food");

                break;
            case 3:
                advocado = new Advocado(snake);
                System.out.println("Advocado");

                break;
            case 4:
                food = new Food(snake);
                coffee = new Coffee(snake);

                System.out.println("bonus");

                break;
            default:
                break;

        }
    }

    private void gameOver(){
        System.out.println("GAME OVER");
        timer.stop();
    }



}