import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    //List of nodes and the direction in wich is moving, also te collider check
    // Use a canMove() method to anticipate if the snake can move to the next square
    // To move the snake you have to add a new node at the beginning of the list of nodes

    private ArrayList<Node> body;
    private Direction direction;
    private int nodesToGrow;

    //Constructor
    public Snake(){
        body = new ArrayList<Node>();
        body.add(new Node(Board.NUM_ROWS/2,Board.NUM_COLS/2));
        body.add(new Node(Board.NUM_ROWS/2,Board.NUM_COLS/2 - 1));
        body.add(new Node(Board.NUM_ROWS/2,Board.NUM_COLS/2 - 2));
        body.add(new Node(Board.NUM_ROWS/2,Board.NUM_COLS/2 - 3));
        direction = Direction.RIGHT;
        nodesToGrow = 0;
    }

    //Paint the body

    public void paint(Graphics g, int squareWidth, int squareHeight) {
        for (Node node: body) {
            Util.drawSquare(g, node.getRow(), node.getCol(), Color.PINK, squareWidth,squareHeight);
        }
    }

    public List<Node> getBody(){
        return body;
    }


    //Getter and setter of Direction

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Node calculateNextNode() {
        // It moves the first node of the snake according to the
        // direction and returns the next position in row and column.
        Node first = body.get(0);
        int row = first.getRow();
        int col = first.getCol();
        switch (direction) {
            case UP:
                row--;
                break;
            case DOWN:
                row++;
                break;
            case LEFT:
                col--;
                break;
            case RIGHT:
                col++;
                break;
        }
        return new Node(row, col);
    }

    public void move() {
        Node next = calculateNextNode();
        body.add(0, next);
        if (nodesToGrow == 0) {
            removeLastNode();
        } else {
            nodesToGrow--;
        }
    }

    public boolean canMove(){
        Node next = calculateNextNode();
        return canMoveTo(next);
    }

    public void incrementNodesToGrow(int numNodes) {
        nodesToGrow += numNodes;
    }

    public void removeLastNode(){
        body.remove(body.size() - 1);
    }


    public boolean canMoveTo(Node nextNode){
        if (nextNode.getRow() < 0
                || nextNode.getRow() >= Board.NUM_ROWS
                || nextNode.getCol() < 0
                || nextNode.getCol() >= Board.NUM_COLS) {
            return false;
        }
        return true;
    }


    public boolean colides(){
        Node next = calculateNextNode();
        if (!canMoveTo(next) || colidesWithitself(next)) {
            return true;
        }
        return false;
    }

    public boolean colidesWithitself(Node next) {
        //Check if the snake colides with is own body
        int row = next.getRow();
        int col = next.getCol();
        for (int i = 0; i < body.size(); i++) {
            if (body.get(i).getRow() == row && body.get(i).getCol() == col) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBody(Node node) {
        for (int i = 0; i < body.size(); i++) {
            if(body.get(i).getRow() == node.getRow() &&
            body.get(i).getCol() == node.getCol()){
                return true;
            }
        }
        return false;
    }

    //Food checkers

    public boolean checkFood(Food food){
        //Check if the first node of the snake matches the node of the food.
        Node first = body.get(0);
        if (food.getRow() == first.getRow() && food.getCol() == first.getCol()) {
            return true;
        } else {
            return false;
        }
    }


    public boolean checkTeleport(Vortex vortex) {
        Node first = body.get(0);
        for (int i = 0; i < 6; i++) {
            if (vortex.getNode(i).getRow() == first.getRow() && vortex.getNode(i).getCol() == first.getCol()) {
                System.out.println("vortex");
                return true;
            }
        }
        return false;
    }






        //|| nextNode.getRow() >= Board.NUM_ROWS
        //        || nextNode.getCol() < 0
        //        || nextNode.getCol() >= Board.NUM_COLS){










    //Comprobar que come
    //GETbody
    //Contains para food
}
