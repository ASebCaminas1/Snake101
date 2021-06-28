import java.awt.*;

public class SpecialFood extends Food {

    private int visible;
    private FoodRemover foodRemover;
    private Boolean rot = false;
    private static final String type = "BANANA";
    public static final int MAX = 13000;
    public static final int MIN = 3000;
    public static final int timeRot = 9000;
    public static final Color gold = new Color(255,204, 51 );
    public static final Color rotted = new Color(153,102, 0);

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
                    rot = true;
                    System.out.println("The banana has rotten");
                    visible = timeRot;
                    Thread.sleep(visible);
                    foodRemover.foodRemove();

                } catch (InterruptedException e) {
                    System.out.println("Error in SpecialFood thread");
                }
            }
        }).start();
    }

    public void paint(Graphics g, int width, int height) {
        if (!rot) {
            Util.drawSquare(g, getRow(), getCol(), gold, width, height);
        } else {
            Util.drawSquare(g, getRow(), getCol(), rotted, width, height);
        }
    }

    public String getType(){
        return type;
    }

}