package controllerFunctions;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;


public class gamePadStart extends gamePad {
    private final Controller gameController;
    private String controllerName;

    //constructor
    public gamePadStart(){
        this.gameController = getGamePad();
        this.controllerName = gamePadName();
    }
    // will allow everyone to recieve button inputs.
    public int gamePadNow() {
        EventQueue eventQueue = gameController.getEventQueue();
        Event event = new Event();
        Boolean stopped = false;

        while (!stopped) {
            gameController.poll();
            eventQueue.getNextEvent(event);
            Component component = event.getComponent();

            if (component != null) {
                Component.Identifier identifier = component.getIdentifier();
                if (identifier == Component.Identifier.Button._0) {
                    //snake moves up
                    return 0;

                }
                if (identifier == Component.Identifier.Button._1) {
                    //snake moves left
                    return 1;
                }
                if (identifier == Component.Identifier.Button._2) {
                    //snake moves right
                    return 2;
                }
                if (identifier == Component.Identifier.Button._3) {
                    //snake moves down
                    return 3;
                }
            } else {
                System.out.println("No controller plugged in");
            }

            return 4;
        }

            return 6;
        }
}


