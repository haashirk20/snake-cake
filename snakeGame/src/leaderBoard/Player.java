package leaderBoard;

import javafx.fxml.FXML;

import java.io.Serial;
import java.io.Serializable;

public class Player implements Serializable, Comparable<Player>{

    @FXML
    private final String player;
    @FXML
    private Integer playerScore;

    @Serial
    private static final long serialVersionUID = 7852570481729415609L;

    public Player(String name, Integer score) {
        this.player = name;
        this.playerScore = score;
    }

    public void setPlayerScore(Integer score){
        this.playerScore = score;
    }

    public Integer getPlayerScore() {
        return this.playerScore;
    }

    public String getPlayer() {
        return this.player;
    }

    public int compareTo(Player player2){
        return -playerScore.compareTo(player2.playerScore);
    }
}

