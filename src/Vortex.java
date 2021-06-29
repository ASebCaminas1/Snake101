import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Vortex {

    private ArrayList<Node> nodes;
    private static final int COL_1 = 0;
    private static final int COL_2 = 49;

    public Vortex(){
        nodes = new ArrayList<Node>();
        nodes.add(new Node(34,COL_1));
        nodes.add(new Node(35,COL_1));
        nodes.add(new Node(36,COL_1));
        nodes.add(new Node(4,COL_2));
        nodes.add(new Node(5,COL_2));
        nodes.add(new Node(6,COL_2));

    }

    public Node getNode(int x) {
        return nodes.get(x);
    }

    public void paint(Graphics g, int width, int height){
        //North entrance portal
        Util.drawSquare(g, 34,COL_1, Color.CYAN, width, height);
        Util.drawSquare(g, 35,COL_1, Color.CYAN, width, height);
        Util.drawSquare(g, 36,COL_1, Color.CYAN, width, height);

        //Portal
        Util.drawSquare(g, 18,25, Color.RED, width, height);

        //South entrance portal
        Util.drawSquare(g, 4,COL_2, Color.CYAN,  width, height);
        Util.drawSquare(g, 5,COL_2, Color.CYAN,  width, height);
        Util.drawSquare(g, 6,COL_2, Color.CYAN,  width, height);
    }
}
