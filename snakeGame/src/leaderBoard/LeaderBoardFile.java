package leaderBoard;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class LeaderBoardFile {

    private final String leaderBoardFile = "leaderBoard.txt";

    public LeaderBoardFile(ArrayList<Player> players){
        try{
            FileOutputStream f = new FileOutputStream(new File(leaderBoardFile));
            ObjectOutputStream oot = new ObjectOutputStream(f);
            for (Player p: sortArrayList(players)){
                oot.writeObject(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Player> sortArrayList(ArrayList<Player> players){
        Collections.sort(players);
        return players;
    }

    public ArrayList<Player> getLeaderBoardFIle() throws IOException{
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

