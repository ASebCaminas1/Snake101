import java.awt.*;

public class SpecialFood extends Food {

    private int visible;
    private FoodRemover foodRemover;
    private Boolean rot = true;
    private static final String type = "BANANA";
    public static final int MAX = 8000;
    public static final int MIN = 3000;
    public static final int timeRot = 1000;

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
                    rot = false;
                    System.out.println("The banana has rotten");
                    Thread.sleep(timeRot);
                    foodRemover.foodRemove();

                } catch (InterruptedException e) {
                    //System.out.println("Error in SpecialFood thread");
                }
            }
        }).start();
        rot = true;
    }

    public void paint(Graphics g, int width, int height) {
        if (rot) {
            Util.drawSquare(g, getRow(), getCol(), Color.YELLOW, width, height);
        } else {
            Util.drawSquare(g, getRow(), getCol(), Color.blue, width, height);
        }
    }

    public String getType(){
        return type;
    }

}