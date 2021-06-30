import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    private ArrayList<Node> body;
    private Direction direction;
    private int nodesToGrow;
    private Color color;
    private int id;

    //Constructor
    public Snake(int num) {
        this.id = num;
        if (num == 1) {
            color = Color.PINK;
            body = new ArrayList<Node>();
            body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2));
            body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2 - 1));
            body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2 - 2));
            body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2 - 3));

        } else {
            color = Color.GREEN;
            body = new ArrayList<Node>();
            body.add(new Node(Board.NUM_ROWS / 2 + 2, Board.NUM_COLS / 2 ));
            body.add(new Node(Board.NUM_ROWS / 2 + 2, Board.NUM_COLS / 2 - 1));
            body.add(new Node(Board.NUM_ROWS / 2 + 2, Board.NUM_COLS / 2 - 2));
            body.add(new Node(Board.NUM_ROWS / 2 + 2, Board.NUM_COLS / 2 - 3));

        }
        direction = Direction.RIGHT;
        nodesToGrow = 0;
    }

    public int getId() {
        return id;
    }

    //Paint the body

    public void paint(Graphics g, int squareWidth, int squareHeight) {
        for (Node node : body) {
            Util.drawSquare(g, node.getRow(), node.getCol(), color, squareWidth, squareHeight);
        }
    }

    public List<Node> getBody() {
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

    public void incrementNodesToGrow(int numNodes) {
        nodesToGrow += numNodes;
    }

    public void removeLastNode() {
        body.remove(body.size() - 1);
    }


    public boolean canMoveTo(Node nextNode) {
        if (nextNode.getRow() < 0
                || nextNode.getRow() >= Board.NUM_ROWS
                || nextNode.getCol() < 0
                || nextNode.getCol() >= Board.NUM_COLS) {
            return false;
        }
        return true;
    }


    public boolean collides(Snake snake) {
        Node next = calculateNextNode();
        if (!canMoveTo(next) || collidesWithitself(next) || snake.checkBody(next)) {
            return true;
        }
        return false;
    }

    public boolean collidesWithitself(Node next) {
        //Check if the snake collides with is own body
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
            if (body.get(i).getRow() == node.getRow() &&
                    body.get(i).getCol() == node.getCol()) {
                return true;
            }
        }
        return false;
    }

    //Food checker

    public boolean checkFood(Food food) {
        //Check if the first node of the snake matches the node of the food.
        Node first = body.get(0);
        if (food.getRow() == first.getRow() && food.getCol() == first.getCol()) {
            return true;
        } else {
            return false;
        }
    }

    //Teleport checker

    public boolean checkTeleport(Vortex vortex) {
        Node first = body.get(0);
        for (int i = 0; i < 6; i++) {
            if (vortex.getNode(i).getRow() == first.getRow() && vortex.getNode(i).getCol() == first.getCol()) {
                return true;
            }
        }
        return false;
    }
}
