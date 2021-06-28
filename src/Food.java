import java.awt.*;
import java.util.Random;

public class Food {


    private int row;
    private int col;
    private String foodType = "Eggplant";
    public static final Color brown = new Color(102,51,0);

    public Food(Snake snake) {

        Random rnd = new Random();
        int num =  (int)(rnd.nextDouble() * 2 + 0);
        switch (num) {
            case 1:
                foodType = "Coffee";
                break;
            case 2:
                foodType = "Advocado";
                break;
        }


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


    public String getFoodType() {
        return foodType;
    }

    public void paint(Graphics g, int width, int height) {
        if (getFoodType().equals("Eggplant")) {
        Util.drawSquare(g, row, col, Color.WHITE, width, height);
        }
        if (getFoodType().equals("Cofee")) {
            Util.drawSquare(g, row, col, brown, width, height);
        }
        if (getFoodType().equals("Advocado")) {
            Util.drawSquare(g, row, col, Color.green, width, height);
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }





}
