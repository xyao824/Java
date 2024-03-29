import java.util.Scanner;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors // Import the Scanner class to read text files
import java.util.ArrayList;
import java.awt.Point;

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
    
 
    void draw_map(){
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
            int x1 = (int)pervious_point.getX();
            int y1 = (int)pervious_point.getY();
    
            // Check each direction
            for (int[] dir : new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}) {
                int newX = x1 + dir[0];
                int newY = y1 + dir[1];
                
                if (isValidMove(newX, newY)) {
                    tracks.add(new Point(newX, newY));
                    ui.place(newX, newY, ui.SNAKE);
                    ui.showChanges();
                    tracking_path();
                    return;
                }
            }
    
            // If no valid moves, remove current point
            ui.place(x1, y1, ui.EMPTY);
            walls.add(pervious_point);
            tracks.remove(pervious_point);
            tracking_path();
        }
    }
    
    boolean isValidMove(int x, int y) {
        return x >= 0 && y >= 0 && x <= max_x && y <= max_y && !walls.contains(new Point(x, y)) && !tracks.contains(new Point(x, y));
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

        new Longest_path(walls, start, end, x, y).draw_map();
    }
}