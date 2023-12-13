import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;
import java.util.Collections;

import ui.Event;
import ui.SnakeUserInterface;
import ui.UserInterfaceFactory;

public class Longest_path {
    
    SnakeUserInterface ui;
    double framesPerSecond = 1.0;
    private ArrayList<Integer> x;
    private ArrayList<Integer> y;
    
    Longest_path( ArrayList<Integer> x,  ArrayList<Integer> y){

        int max_x = Integer.valueOf(Collections.max(x));

        int max_y = Integer.valueOf(Collections.max(y));
        ui = UserInterfaceFactory.getSnakeUI(max_x+1, max_y+1);

        this.x = x; // Initialize class members
        this.y = y;
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
            }
 
    void drawmap(){
		
        for (int i = 0; i < x.size(); i++) {
            System.out.println(i);
            if (i == 0)
                System.out.println("here");
            else if (i == 1)
                System.out.println();
            else
                ui.place(x.get(i), y.get(i), ui.WALL);
            }
        ui.showChanges();
	}

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();
        
        try {
            File myObj = new File("C:\\Users\\Xinyu\\snake\\src\\map.txt");
            Scanner myReader = new Scanner(myObj);
            String firstlane = myReader.nextLine();
            String[] parts = firstlane.split("=");
            String start_point_cord = parts[0];
            String end_point_cord = parts[1];

            String start_point_cord_x = start_point_cord.split(" ")[0];
            String start_point_cord_y = start_point_cord.split(" ")[1];
            x.add(Integer.valueOf(start_point_cord_x));
            y.add(Integer.valueOf(start_point_cord_y));
            String end_point_cord_x = end_point_cord.split(" ")[0];
            String end_point_cord_y = end_point_cord.split(" ")[1];
            x.add(Integer.valueOf(end_point_cord_x));
            y.add(Integer.valueOf(end_point_cord_y));
            String[] first_wall = parts[2].split(" ");
            x.add(Integer.valueOf(first_wall[0]));
            y.add(Integer.valueOf(first_wall[1]));
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              String[] wall = data.split(" ");
              x.add(Integer.valueOf(wall[0]));
              y.add(Integer.valueOf(wall[1]));
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

        new Longest_path(x, y).drawmap();
    }
}