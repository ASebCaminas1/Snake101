import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {

    private static final int LEFT = 1;
    private int right = 2;
    private int up = 3;
    private int down = 4;
    private int direction = 0;
    private Graphics square;
    private int moveX;
    private int moveY;
    private boolean gameOver;
    private boolean pause = false;
    private MyKeyAdapter myKey;
    private int deltaTime = 300;
    private Timer timer;
    private int board[][] = new int[400][300]; // para comprobar colisiones o comidas?



    public Board() {
        Dimension dimension = new Dimension();
        dimension.width = 600;
        dimension.height = 400;
        setPreferredSize(dimension);
        moveX = 20;
        moveY = 40;
        setBackground(Color.gray);
        setDirection(LEFT);
        this.setFocusable(true);
    }


    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (!pause) {
                        moveLeft();
                        setDirection(LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (!pause) {
                        moveRight();
                        setDirection(right);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (!pause) {
                        moveUP();
                        setDirection(up);
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if (!pause) {
                        System.out.println("SPACE");
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (!pause) {
                        moveDown();
                        setDirection(down);
                    }
                    break;
                case KeyEvent.VK_P:
                    if (!pause) {
                        timer.stop();
                        pause = true;
                    } else {
                        timer.start();
                        pause = false;
                    }
                    break;
                default:
                    break;
            }
            repaint();
        }

    }


    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    public void setMoveY(int moveY) {
        this.moveY = moveY;
    }

    public int getMoveX() {
        return moveX;
    }

    public int getMoveY() {
        return moveY;
    }


    public void moveRight() {
        int x = getMoveX();
        x = x + 20;
        setMoveX(x);
    }

    public void moveLeft() {
        int x = getMoveX();
        x = x - 20;
        setMoveX(x);
    }

    public void moveUP() {
        int y = getMoveY();
        y = y - 20;
        setMoveY(y);
    }

    public void moveDown() {
        int y = getMoveY();
        y = y + 20;
        setMoveY(y);
    }

    public void motor() {
        if (!collides())
        switch (getDirection()) {
            case 1:
                moveLeft();
                break;
            case 2:
                moveRight();
                break;
            case 3:
                moveUP();
                break;
            case 4:
                moveDown();
                break;
        } else {
            System.out.println("Toca");
        }



    }

    public void setDirection(int dir) {
        this.direction = dir;
    }

    public int getDirection() {
        return direction;
    }






    // tabla de puntos




    public boolean inGame() {
        return false;
    }

    public void initGame () {
        myKey = new MyKeyAdapter();
        addKeyListener(myKey);



        timer = new Timer(deltaTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                motor();
                repaint();
            }
        });
        timer.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(moveX, moveY, 20, 20);
    }


    //public int randomFood(){

    //}

    public boolean collides(){
        if (getMoveX() <= 0 || getMoveX() > 600) {
            System.out.println("Toca pared");
            return true;

        } else if (getMoveY() < 1 || getMoveY() > 500) {
            System.out.println("Toca suelo");
            return true;
        }
        return false;
    }



    public int randomizer(int num) {
        int random;
        random = (int) (Math.random() * num);
        return random;
    }








}
