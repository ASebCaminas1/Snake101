import java.awt.*;

public class SpecialFood extends Food {

    private int visible;
    private FoodRemover foodRemover;
    public static final int MAX = 25000;
    public static final int MIN = 6000;
    public static final Color gold = new Color(255,204, 51 );

    public SpecialFood(Snake snake, FoodRemover foodRemover) {
        super(snake);
        this.foodRemover = foodRemover;
        visible = (int) (Math.random()*(MAX - MIN)
                + MIN);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(visible);
                    foodRemover.foodRemove();

                } catch (InterruptedException e) {
                    System.out.println("Error in SpecialFood thread");
                }
            }
        }).start();
    }

    public void paint(Graphics g, int width, int height) {
            Util.drawSquare(g, getRow(), getCol(), gold, width, height);
    }
}