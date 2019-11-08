package projectDrone;

/** */

/** @author callummclennan */
public class ConsoleCanvas {

  private static char[][] arenaMap; // Stores chars for x and y positions in arenaMap
  private int xSize, ySize;

  ConsoleCanvas(int x, int y) throws InterruptedException {
    xSize = x;
    ySize = y;
    // System.out.println(xSize + " | " + ySize);
    arenaMap =
        new char[ySize + 2][xSize + 2]; // Making 2d array size to avoid nullpointer exception error.

    for (int i = 0; i < ySize + 2; i++) { // 8 + 2 = 10 - 0,1,2,3,4,5,6,7,8,9
      for (int j = 0; j < xSize + 2; j++) { // 5 + 2 = 7 - 0,1,2,3,4,5,6
        if (i == 0 || i == ySize + 1 || j == 0 || j == xSize + 1) {
          // System.out.print("# ");
          arenaMap[i][j] = '#';
        } else {
          // System.out.print("  ");
          arenaMap[i][j] = ' ';
        }
      }
    }
  }

  public static void showIt(int x, int y) {
    if (x > DroneArena.xSize || y > DroneArena.ySize || y == 0 || x == 0) {
      System.out.println("Cannot print drone as it's Out Of Bounds");
    } else {
      arenaMap[y][x] = 'D';
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ConsoleCanvas c = new ConsoleCanvas(15, 10);

    showIt(5, 1);

    System.out.println(c.toString());
  }

  public String toString() {

    StringBuilder sb = new StringBuilder(); // Prints and builds all drones properties
    for (int i = 0; i < ySize + 2; i++) { // 8 + 2 = 10 - 0,1,2,3,4,5,6,7,8,9
      for (int j = 0; j < xSize + 2; j++) { // 5 + 2 = 7 - 0,1,2,3,4,5,6
        if (i == 0 || i == ySize + 1 || j == 0 || j == xSize + 1) {
          sb.append(arenaMap[i][j] + " ");
        } else {
          sb.append(arenaMap[i][j] + " ");
        }
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
