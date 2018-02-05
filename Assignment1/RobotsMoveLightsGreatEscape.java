import java.awt.Color;
import becker.robots.*;
public class RobotsMoveLightsGreatEscape {
  
  public static void main(String[] args) {
    Robot robot = makeCity();
   //Note: L annotation in comments = left turn 
   //The robot takes 2 steps forward and rotates 270 degrees L. 
    robot.move();
    robot.move();
    robot.turnLeft();
    robot.turnLeft();
    robot.turnLeft();
    
    //The robot takes 7 steps forward and rotates 270 degrees L.
    robot.move();
    robot.move();
    robot.move();
    robot.move();
    robot.move();
    robot.move();
    robot.move();
    robot.turnLeft();
    robot.turnLeft();
    robot.turnLeft();
    
     //The robot takes 2 steps forward toward the light.
    robot.move();
    robot.move();
    robot.pickThing();      //picks up the light (1/3).
    robot.turnLeft();       //and rotates 180 degrees L (turns around).
    robot.turnLeft();        
    
    
    //The robot moves 4 steps forward and turns 90 degrees L. 
    robot.move();
    robot.move();
    robot.move();
    robot.move();
    robot.turnLeft();
    
    // Then it moves 7 steps forward and makes another 90 degree turn L.
    robot.move();
    robot.move();
    robot.move();
    robot.move();
    robot.move();
    robot.move();
    robot.move();
    robot.turnLeft();
    
    //The robot moves forward 1 step and picks up the second light (2/3).
    robot.move();
    robot.pickThing();
    
    //Turns around (180 degrees L) and takes one step forward.
    robot.turnLeft();
    robot.turnLeft();
    robot.move();
    
    // The robot turns 270 degrees L, moves forward 2 steps, and turns again 270 degrees L. 
    robot.turnLeft();
    robot.turnLeft();
    robot.turnLeft();
    robot.move();
    robot.move();
    robot.turnLeft();
    robot.turnLeft();
    robot.turnLeft();
    
    //The robot moves 1 step forward, turns 270 degrees L, moves another step forward. 
    robot.move();
    robot.turnLeft();
    robot.turnLeft();
    robot.turnLeft();
    robot.move();     
    
    robot.pickThing();         //Robot picks up light (3/3).
    robot.turnLeft();          // The robot then turns around (180 degrees L).
    robot.turnLeft();
    
    // The robot moves 1 step forward, turns 90 degrees L, and moves another step forward. 
    robot.move();
    robot.turnLeft();
    robot.move();
   
    //The robot turns 270 degrees L, takes 2 steps forward, turns left 90 degrees L.
    robot.turnLeft();
    robot.turnLeft();
    robot.turnLeft();
    robot.move();
    robot.move();
    robot.turnLeft();
   
    //Now the robot is facing the exit, takes 2 steps forward and exits the maze! */
    robot.move();
    robot.move();
    
  }

//Ignore everything in this method.
 public static Robot makeCity(){
  final int LIGHT_STREET = 1;
  final int LIGHT_AVENUE = 0;
  final int HEIGHT = 6;
  final int WIDTH = 4;
  
  City montreal = new City(12,12);

  Robot asimo = new Robot(montreal, LIGHT_STREET,
   LIGHT_AVENUE, Direction.EAST);

  //box
  for (int i = 0; i < WIDTH+1; i++) {
   new Wall(montreal, LIGHT_STREET, LIGHT_AVENUE + i,
    Direction.NORTH);
  }  
  for (int i = 0; i < WIDTH+1; i++) {
   new Wall(montreal, LIGHT_STREET+HEIGHT+1, LIGHT_AVENUE + i,
    Direction.SOUTH);
  }  
  for (int i = -1; i < HEIGHT+1; i++) {
   new Wall(montreal, LIGHT_STREET+i+1, LIGHT_AVENUE,
    Direction.WEST);
  }
  for (int i = -1; i < HEIGHT+1; i++) {
    if(LIGHT_STREET+i != 4)
      new Wall(montreal, LIGHT_STREET+i+1, LIGHT_AVENUE+4, Direction.EAST);
  } 
  
  //obstacles
  for (int i = -1; i < HEIGHT; i++) {
   new Wall(montreal, LIGHT_STREET+i+1, LIGHT_AVENUE+2,
    Direction.EAST);
  } 

  //obstacles
  new Wall(montreal, 3,3, Direction.SOUTH);
  new Wall(montreal, 2,3, Direction.EAST);
  new Wall(montreal, 1,3, Direction.SOUTH);
  new Wall(montreal, 1, 4, Direction.EAST);
  
  //tall middle wall
  for (int i = 2; i < HEIGHT; i++) {
   new Wall(montreal, LIGHT_STREET+i+1, LIGHT_AVENUE+1,
    Direction.EAST);
  }
  for (int i = 0; i < 2; i++) {
   new Wall(montreal, LIGHT_STREET+3, LIGHT_AVENUE + i,
    Direction.NORTH);
  }  

  int x2 = 1; int y2 = 3;

  new Flasher(montreal, x2, y2, true);
  new Flasher(montreal, 2, 3, true);
  new Flasher(montreal, 8, 0, true);
  return asimo;
 }
}
