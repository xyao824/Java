import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import ui.Event;
import ui.SnakeUserInterface;
import ui.UserInterfaceFactory;

public class Animation {
    
    SnakeUserInterface ui;
 double framesPerSecond = 1.0;
    
    Animation(){
        ui = UserInterfaceFactory.getSnakeUI(3, 3);
    }
    
    void processEvent(Event event, int i, int j, boolean isWall) {
                ui.clear();
				if (isWall){
                	ui.place(j, i, ui.WALL);
				}
				else{
					ui.place(j, i, ui.SNAKE);
				}
                ui.showChanges();
                // Thread.sleep(1000);
            }
 
    void start(){
		
  		
		int i = 0;
		int j = 0;
		boolean isWall = true;
        while (true) {
			ui.setFramesPerSecond(framesPerSecond);
            Event event = ui.getEvent();
			if (event.name.equals("arrow")) {
				System.out.println("Here");
                if (event.data.equals("L")) {
					if (framesPerSecond > 0.5){
                    	framesPerSecond -= 0.5;
						// System.out.println(framesPerSecond);
					}		
                } else if (event.data.equals("R")) {
                    framesPerSecond += 0.5;
                }
			}
			else{
				if (i<3 && j<3){
					// System.out.println(j);
					processEvent(event, i, j, isWall);
					j = j + 1;
					if (j==3 && i<3){
						j = 0;
						i = i + 1;
						if (i == 3){
							i = 0;
							j = 0;
						}
					}
				}
    		}
		}
	}

    public static void main(String[] args) throws InterruptedException {
        new Animation().start();
    }
}