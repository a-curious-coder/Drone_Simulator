/**
 *
 */
package projectDrone;
import java.util.Random;
/**
 * @author callummclennan
 *
 */

public enum Direction // Enumerator containing 4 Direction.
    {
        NORTH,
        EAST,
        SOUTH,
        WEST;

        public static void main(String[] args)
        {
            Direction v = Direction.getRandomDirection(); // This returns a rand variable of type Direction, stored in v. To ensure if multiple instances, values won't get mixed up.
            System.out.printf("Direction = %s%n",  v + "\tNext Direction: " + Direction.getNextDirection(v)  ); // Prints a random value from the enumeration list through the use of the method inside
        }
        
        public static Direction getRandomDirection()
        { // Creates method which retrieves a random direction listed above.
            Random random = new Random(); // Creates instance of Random
          //System.out.println("Array[" + i + "]\t" + values()[i]);
           // i++;
            return values()[random.nextInt(values().length)]; // values() returns the value located at the randomly selected index value of the list of Direction
        }

        public static Direction getNextDirection(Direction CurrentDir) {

            switch (CurrentDir) {
                case NORTH:
                    return EAST;
                case EAST:
                    return SOUTH;
                case SOUTH:
                    return WEST;
                case WEST:
                    return NORTH;
            }
            return null;
        }
    }
