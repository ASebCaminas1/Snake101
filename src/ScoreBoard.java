import javax.swing.*;

public class ScoreBoard extends JPanel implements Incrementer {
    private int score;
    private JLabel scoreLabel;

    public ScoreBoard() {
        super();
        scoreLabel = new JLabel();
        add(scoreLabel);
        reset();
    }

    public void reset(){
        score = 0;
        displayScore();
    }

    public void incrementScore(int increment) {
        score += increment;
        displayScore();
    }

    public void displayScore(){
        scoreLabel.setText("Score: " + score);
    }
}
