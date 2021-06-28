import java.awt.*;

public class Coffee extends Food {

    private int visible = 15000;
    private FoodRemover foodRemover;
    private static final String type = "COFFEE";
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
                    System.out.println("ICE COFFEE");
                } catch (InterruptedException e) {
                    System.out.println("Error in Coffee thread");
                }
            }
        }).start();
    }

    public void paint(Graphics g, int width, int height) {
            Util.drawSquare(g, getRow(), getCol(), brown, width, height);
    }
    public String getType(){
        return type;
    }
}