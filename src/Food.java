import java.awt.*;
import java.util.Random;

public class Food {

    private int row;
    private int col;

    public Food(Snake snake) {

        boolean onTheSnake = true;

        while (onTheSnake) {
            row = (int) (Math.random() * Board.NUM_ROWS);
            col = (int) (Math.random() * Board.NUM_COLS);
            onTheSnake = false;
            if (snake.checkBody(new Node(row, col))) {
                onTheSnake = true;
            }
        }
    }

    public void paint(Graphics g, int width, int height) {
        Util.drawSquare(g, row, col, Color.WHITE, width, height);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
