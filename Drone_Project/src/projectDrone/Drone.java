/**
 *
 */
package projectDrone;
/**
 * @author callummclennan
 *
 */
public class Drone {

    private int ID;
    private static int GeneralID = 0;
    private int xPos, yPos;
    private Direction dir;

    public static void main(String[] args) {

        Drone a = new Drone(10, 10, Direction.getRandomDirection()); // Create drone
        Drone b = new Drone(9, 10, Direction.getRandomDirection());
        DroneArena c = new DroneArena(4, 5);

        DroneArena.droneAL.add(b);

        // NEED TO ADD A VARIABLE TO HOLD DIRECTION IN ORDER TO GET JUST THE DIRECTION.
        System.out.println(a.toString() + b.toString());
        a.tryToMove();
        System.out.println(a.toString());

    }
    /**
     *
     * @param x where the drone is along x axis
     * @param y where the drone is along y axis
     */
    public Drone(int x, int y, Direction c) {
        if(DroneArena.droneAL.size() == 0)
            GeneralID = 0;
        ID = GeneralID + 1;
        GeneralID++;
        xPos = x;
        yPos = y;
        dir = c;
    }

    public boolean isHere() {
        return false;

    }

    public void tryToMove() { // Move drones in appropriate directions determined by direction enumeration class.

        /**
         * Calculate the next x,y position depending on its direction
         * If the drone can move there, update its x,y position to this new position
         * Otherwise, change its direction to the next one in the NESW sequence.
         * A Drone can move to the next position if
         * The new position is within the arena
         * There is not a Drone already there
         */

        int i = 0;
        do{
            i++;
            switch(dir)
            {
                case NORTH:

                    if(DroneArena.canMoveHere(xPos, yPos - 1) == true)
                    {
                        yPos = yPos - 1;
                        return;
                    }
                    else
                    {
                        //dir = Direction.getNextDirection(dir);
                        dir = Direction.getRandomDirection();
                    }

                case EAST:
                    if(DroneArena.canMoveHere(xPos + 1 , yPos) == true)
                    {
                        xPos = xPos + 1;
                        return;
                    }
                    else
                    {
                        //dir = Direction.getNextDirection(dir);
                        dir = Direction.getRandomDirection();
                    }

                case SOUTH:
                    if(DroneArena.canMoveHere(xPos, yPos + 1) == true)
                    {
                        yPos = yPos + 1;
                        return;
                    }
                    else
                    {
                        //dir = Direction.getNextDirection(dir);
                        dir = Direction.getRandomDirection();
                    }

                case WEST:
                    if(DroneArena.canMoveHere(xPos -1 , yPos) == true) // Checks if current drone can move to new x and y pos.
                    {
                        xPos = xPos - 1;
                        dir = Direction.getRandomDirection();
                        return;
                    }
                    else // If canMoveHere returns false (Cannot move drone there due to wall or drone then)
                    {
                        //dir = Direction.getNextDirection(dir); // Change direction to next in the sequence
                        dir = Direction.getRandomDirection();
                    }
            }
        } while (i < 4 ); // Iterate 4 times due to 4 possibilities

    }

    private void displayDrone(ConsoleCanvas c) {
        c.showIt(4, 3);
    }

    public String toString() {
        String str = "Drone " + ID + " at (" + this.xPos + ", " + this.yPos + ", " + dir +")\n";

        return str;
    }

}