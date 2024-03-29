package leaderBoard;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class LeaderBoardFile {

    private final String leaderBoardFile = "leaderBoard.txt";

    private static LeaderBoardFile getInstance = null;

    public String s;
    public LeaderBoardFile(Player player){ // Singleton used and will add player in to the text file(Violates Single responsibility)
        if(getInstance == null) {
            try {
                ArrayList<Player> all = new ArrayList<>();
                if (!getLeaderBoardFIle().isEmpty()) {
                    all = getLeaderBoardFIle();
                }
                FileOutputStream f = new FileOutputStream(leaderBoardFile);
                ObjectOutputStream oot = new ObjectOutputStream(f);
                all.add(player);
                for (Player p : sortArrayList(all)) {
                    oot.writeObject(p);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            getInstance = new LeaderBoardFile();
        }
        //return getInstance;
    }

    private LeaderBoardFile(){
        /*used for singleton instance
        */
        s = "Part of the class";
    }

    public ArrayList<Player> sortArrayList(ArrayList<Player> players){
        /* Sort the arraylist in descending order and return the sorted array
        */
        Collections.sort(players);
        return players;
    }

    public ArrayList<Player> getLeaderBoardFIle() throws IOException{
        /*get all the players from the txt file */
        ArrayList<Player> objects = new ArrayList<Player>();
        try {
            FileInputStream fis = new FileInputStream(leaderBoardFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Player obj = null;

            boolean isExist = true;

            while (isExist) {
                if (fis.available() != 0) {
                    obj = (Player) ois.readObject();
                    objects.add(obj);
                } else {
                    isExist = false;
                }
            }
        }catch (IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return objects;
    }
}
