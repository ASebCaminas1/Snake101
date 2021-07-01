import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements FoodRemover{

    public static final int NUM_ROWS = 40;
    public static final int NUM_COLS = 50;
    private Snake snake1;
    private Snake snake2;
    private Food food;
    private int caffeine = 0;
    public Timer timer;
    private MyKeyAdapter myKey;
    private Incrementer incrementer;
    private boolean foodEated = false;
    private boolean antiTurner = false;
    private boolean antiTurner2 = false;
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
                    if (snake1.getDirection() != Direction.RIGHT && !antiTurner) {
                        snake1.setDirection(Direction.LEFT);
                        antiTurner = true;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake1.getDirection() != Direction.LEFT && !antiTurner) {
                        snake1.setDirection(Direction.RIGHT);
                        antiTurner = true;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake1.getDirection() != Direction.DOWN && !antiTurner) {
                        snake1.setDirection(Direction.UP);
                        antiTurner = true;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake1.getDirection() != Direction.UP && !antiTurner) {
                        snake1.setDirection(Direction.DOWN);
                        antiTurner = true;
                    }
                    break;
                case KeyEvent.VK_A:
                    if (snake2.getDirection() != Direction.RIGHT && !antiTurner) {
                        snake2.setDirection(Direction.LEFT);
                        antiTurner2 = true;
                    }
                    break;
                case KeyEvent.VK_W:
                    if (snake2.getDirection() != Direction.DOWN && !antiTurner) {
                        snake2.setDirection(Direction.UP);
                        antiTurner2 = true;
                    }
                    break;
                case KeyEvent.VK_S:
                    if (snake2.getDirection() != Direction.UP && !antiTurner) {
                        snake2.setDirection(Direction.DOWN);
                        antiTurner2 = true;
                    }
                    break;
                case KeyEvent.VK_D:
                    if (snake2.getDirection() != Direction.LEFT && !antiTurner) {
                        snake2.setDirection(Direction.RIGHT);
                        antiTurner2 = true;
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if(timer.isRunning()) {
                      jump(snake1);
                    }
                    break;
                case KeyEvent.VK_J:
                    if(timer.isRunning()) {
                        jump(snake2);
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
        snake1 = new Snake(1);
        snake2 = new Snake(2);
        food = new Food(snake1);
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
        snakeManagement(snake1);
        snakeManagement(snake2);
        repaint();
    }


    private void snakeManagement(Snake snake){
        Snake theOther;
        if (snake == snake1) {
            theOther = snake2;
        } else {
            theOther = snake1;
        }

        if (!snake.collides(theOther)) {
            snake.move();
            if (snake.checkTeleport(vortex)){
                teleporter();
            }
            if (snake.checkFood(food)) {
                if (food instanceof SpecialFood) {
                    snake.incrementNodesToGrow(3);
                    incrementer.incrementScore(100, snake.getId());
                    SpecialFood.eated = true;
                } else {
                    snake.incrementNodesToGrow(1);
                    incrementer.incrementScore(10, snake.getId());
                }
                if (food instanceof Coffee) {
                    manageOverdose();
                    incrementer.incrementScore(5, snake.getId());
                    Coffee.drinked = true;
                }
                food = foodCreator();
            }

        } else {
            gameOver(snake.getId());
        }
        antiTurner = false;
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        snake1.paint(g, getSize().width / Board.NUM_COLS, getSize().height / Board.NUM_ROWS);
        snake2.paint(g, getSize().width / Board.NUM_COLS,getSize().height / Board.NUM_ROWS);

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

    public void jump(Snake snake) {
        for (int i = 0; i <= 2; i++) {
            Node node = new Node(snake.calculateNextNode().getRow(), snake.calculateNextNode().getCol());
            snake.getBody().add(0, node);
            snake.removeLastNode();
        }
    }

    public Food foodCreator() {
        //Select randomly between normal and special food.
        int num = (int) (Math.random() * 4);
        if (num == 2) {
            return new SpecialFood(snake1, this);
        }
        if (num == 3) {
            return new Coffee(snake1,this);
        }
        return new Food(snake1);
    }

    public void foodRemove() {
        food = foodCreator();
    }

    private void gameOver(int num){
        gameOverPainter = new GameOver();
        System.out.println("GAME OVER");
        if (num == 1) {
            System.out.println("Player 1 loses");
        } else {
            System.out.println("Player 2 loses");
        }
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
            if (snake1.checkTeleport(vortex)) {
            for (int i = 0; i < snake1.getBody().size(); i++) {
                Node node = new Node(18,25);
                snake1.getBody().remove(0);
                snake1.getBody().add(0, node);
                snake1.setDirection(Direction.DOWN);
                }
            }
            if (snake2.checkTeleport(vortex)) {
                for (int i = 0; i < snake2.getBody().size(); i++) {
                    Node node = new Node(18,25);
                    snake2.getBody().remove(0);
                    snake2.getBody().add(0, node);
                    snake2.setDirection(Direction.UP);
                }
            }
        }
    }
}