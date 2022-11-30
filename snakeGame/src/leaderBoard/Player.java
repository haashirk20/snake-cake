package leaderBoard;

public class Player {

    private final String player;//hasmap key player, value integer Array list - txt file use it
    private final Integer playerScore;


    public Player(String name, Integer score) {
        this.player = name;
        this.playerScore = score;
    }

    public Integer getPlayerScores() {
        return playerScore;
    }

    public String getPlayer() {
        return this.player;
    }

    public int compareTo(Player player2){
        return playerScore.compareTo(player2.playerScore);
    }
}
