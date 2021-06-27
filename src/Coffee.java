import java.awt.*;
import java.awt.Color;

public class Coffee {

    private int row;
    private int col;
    public static final Color brown = new Color(102,51,0);

    public Coffee(Snake snake){

        boolean onTheSnake = true;
        while (onTheSnake){
            row = (int) (Math.random() * Board.NUM_ROWS);
            col = (int) (Math.random() * Board.NUM_COLS);
            onTheSnake = false;
            if (snake.checkBody(new Node(row,col))) {
                onTheSnake = true;
            }
        }
    }
    public void paint(Graphics g, int width, int height) {
        Util.drawSquare(g, getRow(), getCol(), brown, width, height);
        System.out.println("café");
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}


