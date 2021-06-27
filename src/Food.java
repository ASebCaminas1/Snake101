import java.awt.*;

public class Food{


    private int row;
    private int col;


    public Food(Snake snake){
        int row = (int) (Math.random() * Board.NUM_ROWS);
        int col = (int) (Math.random() * Board.NUM_COLS);
    }

    public void paint(Graphics g, int width, int height) {
        Util.drawSquare(g, row, col, Color.WHITE, width, height);
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
