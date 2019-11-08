/** */
package projectDrone;

import java.util.ArrayList;
import java.util.Random;

/** @author callummclennan */
public class DroneArena {

  public static int xSize; // Size parameters of the arena
  public static int ySize;
  public static int dronesWanted;
  public static ArrayList<Drone> droneAL = new ArrayList<Drone>();
  int k = 0;
  private int randX, randY; // Random coords
  private Direction randDir;

  /**
   * @param x
   * @param y
   */
  DroneArena(int x, int y) {
    xSize = x;
    ySize = y;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    DroneArena a = new DroneArena(xSize, ySize); // create drone arena - X AND Y SIZES
    a.addDrone();

    dronesWanted = 33;
    for (int i = 1; i < dronesWanted; i++) // Has value representing amount of drones created.
    {
      if (i == xSize * ySize) {
        System.out.println(
            "Arena at maximum capacity with: "
                + xSize * ySize
                + " drones.\nCannot fulfil request for remaining "
                + (dronesWanted - xSize * ySize)
                + " drones.\n");
        break;
      } else {
        a.addDrone(); // Adding type Drone into arraylist
      }
    }

    System.out.println(a.toString()); // Print all drones and their locations
  }

  private static void p(String str) {
    System.out.println(str);
  }

  public static boolean canMoveHere(int x, int y) {
    // System.out.println("Checking:\t"+ x+ " | " + y);
    if (x == 0 || y == 0 || x == xSize + 1 || y == ySize + 1) // if new coords are correspond to any of these then
    {
      // System.out.println("Wall obstruction found.\nChecking other directions.\n");
      return false; // Can't move
    } else {
      // Making use of previously created method - If there is a drone at that position, then
      // No drone found in x and y pos
      // System.out.println("Drone obstruction found: " + getDroneAt(x, y) + "Checking other
      // directions.\n");
      return getDroneAt(x, y) == null;
    }
  }

  public static Drone getDroneAt(int x, int y) {

    /**
     * Search droneAL for drone at x and y Iterate through all drones and compare to new drone at x
     * and y if there is a drone then return drone at x,y if not, return null;
     */
    // Create new drone with x n y coords
    for (int i = 0; i < droneAL.size(); i++) // iterate through all drones in al
    {
      // p(droneAL.get(i).xPos + "\t" + x);
      if (droneAL.get(i).xPos == x
          && droneAL.get(i).yPos == y) // if drone i in al has same properties as drone then
      {
        // p("return drone");
        return droneAL.get(i); // return drone
      }
    }

    return null; // If no drone at location
  } // This function gets x and y coords of drones

  public static void moveAllDrones() {

    for (int i = 0; i < droneAL.size(); i++) // Iterate through all drones in arena.
    {
      droneAL.get(i).tryToMove();
    }
  }

  public int getX() {
    return xSize;
  }

  public int getY() {
    return ySize;
  }

  public void addDrone() // Receives drone
      {

    Random rnd = new Random(); // rnd creates new Random value
    randX = rnd.nextInt(xSize) + 1; // Sets random value for randX
    randY = rnd.nextInt(ySize) + 1; // Sets random value for randY
    randDir = Direction.getRandomDirection(); // Randomly generating a direction using method in enumeration
                                   // class.
    if (getDroneAt(randX, randY) != null) // If getDroneAt has a result then
    {}
    // System.out.println("Result: " + getDroneAt(randX, randY));
    while (isHere(randX, randY) == true) {
      if (droneAL.size() < xSize * ySize) { // If droneAL.size is less than xSize * ySize (e.g. 2*2) then carry
        // out code below. droneAL.size starts from 0.
        randX = rnd.nextInt(xSize) + 1; // Sets random value for randX
        randY = rnd.nextInt(ySize) + 1; // Sets random value for randY
      } else {
        break;
      }
    }

    if (droneAL.size()
        != xSize * ySize) // Just less than because droneAL starts from index 0. 2*2 grid can only
    // take 4 drones. If I did < then it would give me 5
    {
      Drone Drone = new Drone(randX, randY, randDir);
      droneAL.add(Drone);
    }
  }

  public void showDrones(ConsoleCanvas c) {
    if (droneAL.size() == 0) {
      System.out.println("No drones.");
      return;
    } else {
      for (Drone d : droneAL) // Enhanced for loop
      {
        ConsoleCanvas.showIt(d.xPos, d.yPos);
        // System.out.println(d);  -- for testing/seeing each drone info
      }
    }
  }

  public boolean isHere(int sx, int sy) {
    k++;

    if (droneAL.size() == 0) {
      return false;
    } // Skips the x and y coords check as there's nothing to compare against.

    for (int i = 0; i < droneAL.size(); i++) {
      if (droneAL.get(i).xPos == sx
          && droneAL.get(i).yPos == sy) // If x and y are used by other drones
      {
        // System.out.println("x and y pos are in use: iteration " + k);
        return true;
      }
    }

    return false;
  }

  public String toString() {

    StringBuilder sb = new StringBuilder(); // Prints and builds all drones properties
    sb.append(
        "Arena's size is: "
            + xSize
            + " | "
            + ySize
            + "\n"
            + xSize * ySize
            + " spaces for drones.\n");
    sb.append(droneAL.size() + " drones in arena.\n\n");
    if (droneAL.size() == 0) {
      return sb.toString();
    } else {
      for (int i = 0; i != droneAL.size(); i++) {
        sb.append(droneAL.get(i));
      }
    }
    return sb.toString();
  }
}
