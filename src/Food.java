import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Food extends JPanel {

    private int x;
    private int y;
    private Graphics g;

    public Food () {
        paintComponent(g);
        this.setFocusable(true);
    }


    public int randomX(){
        return randomizer(600);
    }

    public int randomY(){
        return randomizer(800);
    }


    public int randomizer(int num) {
        int random;
        random = (int) (Math.random() * num);
        return random;
    }

    //comprobar si sale en la serpiente

    //borrar si la serpiente se lo come



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(randomX(),randomY(), 10, 10);
        g.setColor(Color.green);
    }
}
