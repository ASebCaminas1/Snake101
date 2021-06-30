import javax.swing.*;

public class ScoreBoard extends JPanel implements Incrementer {
    private int score;
    private int score2;
    private JLabel scoreLabel;

    public ScoreBoard() {
        super();
        scoreLabel = new JLabel();
        add(scoreLabel);
        reset();
    }

    public void reset(){
        score = 0;
        score2 = 0;
        displayScore();
    }

    public void incrementScore(int increment, int player) {
        if (player == 1) {
            score += increment;
        } else {
            score2 += increment;
        }
        displayScore();
    }

    public void displayScore(){
        scoreLabel.setText("Player 1: " + Settings.playerName + " Score: " + score + "\n Player 2: "+
        Settings.playerName2 + " Score: " + score2);
    }
}
