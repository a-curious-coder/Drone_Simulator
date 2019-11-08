package projectDrone;

import java.io.IOException;

public class CLS {
  private static String OS = System.getProperty("os.name").toLowerCase();

  public static void main(String... arg) throws IOException, InterruptedException {
    if (isWindows()) {
      // System.out.println("This is Windows");
      // Clears CMD in Windows
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } else if (isMac()) {
      // System.out.println("This is Mac");
      System.out.print("\033[H\033[2J"); // Clears terminal on mac
    } else {
      System.out.print("OS/Console Window not supported - Cannot clear screen");
    }
  }

  public static boolean isWindows() {

    return (OS.indexOf("win") >= 0);
  }

  public static boolean isMac() {

    return (OS.indexOf("mac") >= 0);
  }
}
