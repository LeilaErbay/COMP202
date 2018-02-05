import java.awt.Color;
import becker.robots.*;
public class RobotsMoveLightsSmall {
  
  public static void main(String[] args) {
   Robot robot = makeSmallCity();
   //NOTE: L annotation in comments = left turn
   // The robot moves 1 block forward and turns 270 degrees L. 
   robot.move();
   robot.turnLeft();
   robot.turnLeft();
   robot.turnLeft();
   
   // The robot takes 3 steps towards the light and picks up the light (1/1).
   robot.move();
   robot.move();
   robot.move();
   robot.pickThing();
   
   // The robot spins around in a circle to do its victory dance! 
   robot.turnLeft();
   robot.turnLeft();
   robot.turnLeft();
   robot.turnLeft();
   
   // The robot moves 1 block forward and turns a 90 degree L. 
   robot.move();
   robot.turnLeft();
   
   // The robot takes 4 steps forward and exits the maze.
   robot.move();
   robot.move();
   robot.move();
   robot.move();
  }

  
//Ignore everything in this method and below.
public static Robot makeSmallCity() {
  final int SIZE = 4;
  final int LIGHT_STREET = 1;
  final int LIGHT_AVENUE = 1;
  
  City ottawa = new City(12,12);
  
  //box
  for (int i = 0; i < SIZE-1; i++) {
   new Wall(ottawa, LIGHT_STREET, LIGHT_AVENUE + i,
    Direction.NORTH);
  } 
  
  for (int i = SIZE-1; i < SIZE+1; i++) {
   new Wall(ottawa, LIGHT_STREET+2, LIGHT_AVENUE + i,
    Direction.NORTH);
  }
  
  for (int i = 0; i < SIZE+1; i++) {
   new Wall(ottawa, LIGHT_STREET+SIZE, LIGHT_AVENUE + i,
    Direction.SOUTH);
  }  
  for (int i = -1; i < SIZE; i++) {
   new Wall(ottawa, LIGHT_STREET+i+1, LIGHT_AVENUE,
    Direction.WEST);
  }
  for (int i = -1; i < SIZE; i++) {
    if(LIGHT_STREET+i == 0 || LIGHT_STREET+i ==1)
      new Wall(ottawa, LIGHT_STREET+i+1, LIGHT_AVENUE+2, Direction.EAST);
    else if(LIGHT_STREET+i != 4)
      new Wall(ottawa, LIGHT_STREET+i+1, LIGHT_AVENUE+4, Direction.EAST);
  } 
  Robot asimo = new Robot(ottawa, LIGHT_STREET,
   LIGHT_AVENUE, Direction.EAST);
  new Flasher(ottawa, 4, 2, true);
  return asimo;
}

}