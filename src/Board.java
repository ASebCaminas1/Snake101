import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements FoodRemover{

    public static final int NUM_ROWS = 40;
    public static final int NUM_COLS = 50;
    private Snake snake;
    private Food food;
    private int caffeine = 0;
    public Timer timer;
    private MyKeyAdapter myKey;
    private Incrementer incrementer;
    private boolean foodEated = false;
    private boolean antiTurner = false;
    private Vortex vortex = new Vortex();
    private boolean teleport = false;
    private boolean gameOver;
    private GameOver gameOverPainter;

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
                    if(timer.isRunning()) {
                    jump();
                    }
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
        gameOver = false;
        addKeyListener(myKey);
        setFocusable(true);
        requestFocus();
        incrementer.reset();
        if (timer != null && timer.isRunning()){
            timer.stop();
        }
        timer = new Timer(Settings.getDeltaTime(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lap();
            }
        });
        timer.start();

    }

    public void lap() {
        if (!snake.collides()) {
            snake.move();
            if (snake.checkTeleport(vortex)){
                teleporter();
            }
            if (snake.checkFood(food)) {
                if (food instanceof SpecialFood) {
                    snake.incrementNodesToGrow(3);
                    incrementer.incrementScore(100);
                    SpecialFood.eated = true;
                } else {
                    snake.incrementNodesToGrow(1);
                    incrementer.incrementScore(10);
                }
                if (food instanceof Coffee) {
                    manageOverdose();
                    Coffee.drinked = true;
                }
                food = foodCreator();
            }

        } else {
            gameOver();
        }
        antiTurner = false;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.paint(g, getSize().width / Board.NUM_COLS,
                getSize().height / Board.NUM_ROWS);
        food.paint(g, getSize().width / Board.NUM_COLS, getSize().height / Board.NUM_ROWS);

        if (teleport) {
            vortex.paint(g, getSize().width / Board.NUM_COLS, getSize().height / Board.NUM_ROWS);
        }
        if (gameOver) {
            gameOverPainter.paint(g, getSize().width / Board.NUM_COLS, getSize().height / Board.NUM_ROWS);
        }



        drawBlackBorder(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public void manageOverdose() {
        //Caffeine controls the vortex.
        caffeine++;
        if (caffeine > 3 && caffeine < 6) {
            caffeine++;
            setTeleport(true);
        }
        if (caffeine == 6) {
            caffeine = 0;
            setTeleport(false);
        }
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
            return new SpecialFood(snake, this);
        }
        if (num == 3) {
            return new Coffee(snake,this);
        }
        return new Food(snake);
    }

    public void foodRemove() {
        food = foodCreator();
    }

    private void gameOver(){
        gameOverPainter = new GameOver();
        System.out.println("GAME OVER");
        gameOver = true;
        if (timer != null){
            timer.stop();
        }
        repaint();

    }

    //Teleport methods zone

    public void setTeleport(boolean teleport) {
        this.teleport = teleport;
    }

    public void teleporter(){
        if (teleport) {
            for (int i = 0; i < snake.getBody().size(); i++) {
                Node node = new Node(18,25);
                snake.getBody().remove(0);
                snake.getBody().add(0, node);
            }
        }

    }
}