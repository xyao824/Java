import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Point;

import ui.Event;
import ui.SnakeUserInterface;
import ui.UserInterfaceFactory;

public class Longest_path {
    
    SnakeUserInterface ui;
    double framesPerSecond = 1.0;
    private ArrayList<Point> walls;
    private ArrayList<Point> tracks;
    private Point startPoint;
    private Point endPoint;
    private Integer max_x;
    private Integer max_y;

    Longest_path( ArrayList<Point> walls,  Point start, Point end, Integer x, Integer y){

        ui = UserInterfaceFactory.getSnakeUI(x+1, y+1); // +1 to index

        this.walls = walls; // Initialize class members
        this.startPoint = start;
        this.endPoint = end;
        this.max_x = x;
        this.max_y = y;
        this.tracks = new ArrayList<Point>();
    }
    
 
    void drawmap(){
        ui.place((int)startPoint.getX(), (int)startPoint.getY(), ui.SNAKE);
        ui.place((int)endPoint.getX(), (int)endPoint.getY(), ui.FOOD);
        tracks.add(startPoint);
        for (int i = 0; i < walls.size(); i++) {
                ui.place((int)walls.get(i).getX(), (int)walls.get(i).getY(), ui.WALL);
            }
        ui.showChanges();
        tracking_path();
	}

    void tracking_path() {
        if (tracks.get(tracks.size()-1).equals(endPoint)){
            return ;
        }
        else{
            Point pervious_point = tracks.get(tracks.size()-1);
            System.out.println(pervious_point);
            int x1, y1;
            if ((!walls.contains(new Point ((int)pervious_point.getX()-1, (int)pervious_point.getY()))) && 
            (!tracks.contains(new Point ((int)pervious_point.getX()-1, (int)pervious_point.getY()))) &&
            ((int)pervious_point.getX()-1 >= 0)){
                
                x1 = (int)pervious_point.getX()-1;
                y1 = (int)pervious_point.getY();
                tracks.add(new Point(x1, y1));
                ui.place(x1, y1, ui.SNAKE);
                ui.showChanges();
                tracking_path();
            }
            else if ((!walls.contains(new Point ((int)pervious_point.getX(), (int)pervious_point.getY()+1))) && 
            (!tracks.contains(new Point ((int)pervious_point.getX(), (int)pervious_point.getY()+1))) &&
            ((int)pervious_point.getY()+1 <= max_y)){
                x1 = (int)pervious_point.getX();
                y1 = (int)pervious_point.getY()+1;
                tracks.add(new Point(x1, y1));
                ui.place(x1, y1, ui.SNAKE);
                ui.showChanges();
                tracking_path();
            }
            else if ((!walls.contains(new Point ((int)pervious_point.getX()+1, (int)pervious_point.getY()))) && 
            (!tracks.contains(new Point ((int)pervious_point.getX()+1, (int)pervious_point.getY()))) &&
            ((int)pervious_point.getX()+1 <= max_x)){
                x1 = (int)pervious_point.getX()+1;
                y1 = (int)pervious_point.getY();
                tracks.add(new Point(x1, y1));
                ui.place(x1, y1, ui.SNAKE);
                ui.showChanges();
                tracking_path();
            }
            else if ((!walls.contains(new Point ((int)pervious_point.getX(), (int)pervious_point.getY()-1))) && 
            (!tracks.contains(new Point ((int)pervious_point.getX(), (int)pervious_point.getY()-1))) &&
            ((int)pervious_point.getY()-1 >= 0)){
                x1 = (int)pervious_point.getX();
                y1 = (int)pervious_point.getY()-1;
                tracks.add(new Point(x1, y1));
                ui.place(x1, y1, ui.SNAKE);
                ui.showChanges();
                tracking_path();
            }
            else{
                x1 = (int)pervious_point.getX();
                y1 = (int)pervious_point.getY();
                ui.place(x1, y1, ui.EMPTY);
                walls.add(pervious_point);
                tracks.remove(pervious_point);
                tracking_path();
            }
        }
    }



    public static void main(String[] args) throws InterruptedException {
        ArrayList<Point> walls = new ArrayList<Point>();
        Point start = new Point();
        Point end = new Point();
        Integer x = 0;
        Integer y = 0;
        try {
            File myObj = new File("C:\\Users\\Xinyu\\snake\\src\\map.txt");
            Scanner myReader = new Scanner(myObj);
            String firstlane = myReader.nextLine();
            String[] parts = firstlane.split("=");
            String start_point_cord = parts[0];
            String end_point_cord = parts[1];

            String start_point_cord_x = start_point_cord.split(" ")[0];
            String start_point_cord_y = start_point_cord.split(" ")[1];
            
            start = new Point(Integer.valueOf(start_point_cord_x), Integer.valueOf(start_point_cord_y));
            String end_point_cord_x = end_point_cord.split(" ")[0];
            String end_point_cord_y = end_point_cord.split(" ")[1];

            end = new Point(Integer.valueOf(end_point_cord_x), Integer.valueOf(end_point_cord_y));
            x = Math.max(Integer.valueOf(start_point_cord_x), Integer.valueOf(end_point_cord_x));
            y = Math.max(Integer.valueOf(start_point_cord_y), Integer.valueOf(end_point_cord_y));

            String[] first_wall = parts[2].split(" ");

            walls.add(new Point(Integer.valueOf(first_wall[0]), Integer.valueOf(first_wall[1])));

            x = Math.max(x, Integer.valueOf(first_wall[0]));
            y = Math.max(y, Integer.valueOf(first_wall[1]));

            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              String[] wall = data.split(" ");
              x = Math.max(x, Integer.valueOf(wall[0]));
              y = Math.max(y, Integer.valueOf(wall[1]));
              walls.add(new Point(Integer.valueOf(wall[0]), Integer.valueOf(wall[1])));
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

        new Longest_path(walls, start, end, x, y).drawmap();
    }
}