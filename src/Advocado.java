import java.awt.*;

public class Advocado extends Food {

    private int row;
    private int col;


    public Advocado(Snake snake){
        super(snake);
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
        Util.drawSquare(g, row, col, Color.GREEN, width, height);
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
