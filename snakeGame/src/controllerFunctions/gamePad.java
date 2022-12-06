package controllerFunctions;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.*;

//waiting on Graphical window of game to be finished to test controller functionality on game, and figure
// out how to map everyhting to controller.
public class gamePad {
    public static int xd(String[] args){
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers(); //gets all available controller.
        Controller controller = null;
        for (int i = 0; i < controllers.length; i++) {
            if (controllers[i].getType() == Controller.Type.GAMEPAD || controllers[i].getType() == Controller.Type.STICK){
                System.out.println(controllers[i].getName());
                controller = controllers[i];

            }

        }
        Component[] components = controller.getComponents();
        for (int i = 0; i < components.length; i++){
            System.out.println(components[i].getName());
        }
        EventQueue eventQueue = controller.getEventQueue();
        Event event = new Event();
        Boolean stopped = false;

        while(!stopped){
            controller.poll();
            eventQueue.getNextEvent(event);
            Component component = event.getComponent();

            if(component != null){
                Component.Identifier identifier = component.getIdentifier();
                if (identifier == Component.Identifier.Button._0){ //x button
                    return 0; // button 1
                }
                else if (identifier == Component.Identifier.Button._1){ //button 2
                    return 1;
                } else if (identifier == Component.Identifier.Button._2) { //button 3 test
                    return 2;

                } else if (identifier == Component.Identifier.Button._3) { //button 4 test.
                    return 3;

                }
            }

        }
        return 4;

    }
    public Controller getGamePad(){
            Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers(); //gets all available controller.
            Controller controller = null;
        for (Controller value : controllers) {
            if (value.getType() == Controller.Type.GAMEPAD || value.getType() == Controller.Type.STICK) {
                System.out.println(value.getName());
                controller = value;

            }

        }
    return controller;
    }
    public String gamePadName(){

        String ControllerName = null;
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers(); //gets all available controller.
        Controller controller = null;
        for (int i = 0; i < controllers.length; i++) {
            if (controllers[i].getType() == Controller.Type.GAMEPAD || controllers[i].getType() == Controller.Type.STICK){
                System.out.println(controllers[i].getName());
                ControllerName = controllers[i].getName().toString();

            }

        }
        return ControllerName;


    }
}


