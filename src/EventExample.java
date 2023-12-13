//The program below will show a 16 by 16 SnakeUserInterface. The program will print "Hello world!" three times per second, and when the user clicks on the interface, the program will print at which coordinate the user clicked.

import ui.Event;
import ui.SnakeUserInterface;
import ui.UserInterfaceFactory;

import java.util.Scanner;

public class EventExample {

    static final double FRAMES_PER_SECOND = 3.0;
    
    SnakeUserInterface ui;
    
    EventExample() {
        ui = UserInterfaceFactory.getSnakeUI(16, 16);
    }
    
    void processEvent(Event event) {
        if (event.name.equals("click")) {
            Scanner dataScanner = new Scanner(event.data);
            int x = dataScanner.nextInt();
            int y = dataScanner.nextInt();
            ui.printf("You clicked at the coordinate %d,%d\n", x, y);
        } else if (event.name.equals("alarm")) {
            ui.printf("Hello world!\n");
        }
    }
    
    void start() {
        ui.setFramesPerSecond(FRAMES_PER_SECOND);

        while (true) {
            Event event = ui.getEvent();
            processEvent(event);
        }
    }
    
    public static void main(String[] args) {
        new EventExample().start();
    }
}
