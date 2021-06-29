import java.awt.*;

public class Coffee extends Food {

    private int visible = 30000;
    private FoodRemover foodRemover;
    public static final Color brown = new Color(102, 51, 0);

    public Coffee(Snake snake, FoodRemover foodRemover) {
        super(snake);
        this.foodRemover = foodRemover;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(visible);
                    foodRemover.foodRemove();
                } catch (InterruptedException e) {
                    System.out.println("Error in Coffee thread");
                }
            }
        }).start();
    }

    public void paint(Graphics g, int width, int height) {
            Util.drawSquare(g, getRow(), getCol(), brown, width, height);
    }
}