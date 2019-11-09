/**
 *
 */
package projectDrone;

import javax.swing.*;
import javax.swing.JComponent;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author callummclennan
 *
 */
public class DroneInterface {
    JFileChooser chooseFile = new JFileChooser();
    private Scanner s; // Used to scan for whatever I specify
    private DroneArena myArena;

    public DroneInterface() throws InterruptedException, IOException // Constructor
    {
        CLS.main();
        s = new Scanner(System.in); // Scans for user input

        char ch = ' ';
        do {
            System.out.print("A | AddDrone\nB | NewArena\nC | Create/Display Default Arena\nD | Display Arena \nF | File Options\nM | MoveAllDrones\nI | Information\nX | Exit\n\n> ");
            ch = s.next().charAt(0);
            s.nextLine();
            switch (ch) {
                case 'A':
                case 'a':
                    CLS.main();

                    if(myArena.xSize == 0 && myArena.ySize == 0)
                    {
                        System.out.println("No arena to add drones to.\nCreate an arena with your specifications by pressing 'B'\nOr create a default arena by pressing 'C'");
                        TimeUnit.SECONDS.sleep(5);
                        CLS.main();
                        break;
                    }
                    else
                    {
                        myArena.addDrone(); // Adds new drone to arena
                        if(myArena.droneAL.size() < 2)
                        {
                            System.out.println(myArena.droneAL.size() + " drone in arena.");
                            break;
                        }
                        else
                        {
                            System.out.println(myArena.droneAL.size() + " drones in arena.");
                            break;
                        }
                    }


                case 'B':
                case 'b':
                    System.out.print("Enter x coord: "); // Asks user for Arena coords
                    DroneArena.xSize = s.nextInt(); // Retrieves next input type Integer
                    System.out.print("Enter y coord: ");
                    DroneArena.ySize = s.nextInt();
                    CLS.main();
                    myArena = new DroneArena(DroneArena.xSize, DroneArena.ySize); // generates arena using inputted x and y vals
                    doDisplay(); // Displays the arena on console.
                    break;
                case 'C':
                case 'c':
                    CLS.main();
                    if (DroneArena.xSize == 0 || DroneArena.ySize == 0) {
                        System.out.println("Creating Default 10*10 arena");
                        myArena = new DroneArena(10, 10); // Create arena with x and y sizes
                        doDisplay();
                    }
                    break;
                case 'D':
                case 'd':
                    CLS.main();

                    if (DroneArena.xSize == 0 || DroneArena.ySize == 0) {
                        System.out.println("No arena to display\nCreate one by pressing ' B '\n");
                        break;
                    } else {
                        doDisplay();
                        break;
                    }

                case 'F':
                case 'f':
                    CLS.main();
                    System.out.println("\n\n\nFile Options\n");
                    System.out.println("1 | Open File\t2 | Save File\t3 | Exit\n> ");
                    int c = s.nextInt();
                    switch (c) {

                        case 1:
                            chooseFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                            int returnValue = chooseFile.showOpenDialog(null);

                            if (returnValue == JFileChooser.APPROVE_OPTION) { // Approve option is when user clicks 'Open'
                                File selectedFile = chooseFile.getSelectedFile(); // gets selected file
                                System.out.println(selectedFile.getAbsolutePath()); // prints the path the file is in
                            }

                            break;
                        case 2:


                                StringBuilder sb = new StringBuilder(); // Create StringBuilder
                                File file = new File("Arena.txt"); // Create new file to store info
                                FileWriter fileWriter = new FileWriter(file); // Create a filewriter so I can set what info is written to file
                                PrintWriter printWriter = new PrintWriter(fileWriter); // print the information to the file.
                                sb.append(myArena.xSize + " | " + myArena.ySize + ", "); // Appending arenasize
                                sb.append("Drones in arena: s" + myArena.droneAL.size()); // Appending Drone count
                                printWriter.println(sb); // Prints all the appended information collected by stringbuilder to the file.
                                printWriter.close(); // Closes the printWriter because we're done writing to file.
                                File fileToSave = chooseFile.getSelectedFile(); // Open directory box for user to select where to save the file.
                                System.out.println("Save as file: " + fileToSave.getAbsolutePath()); // Print if the file is saved and where the file is saved

                            break;

                        case 3:
                            break;
                    }
                    break;
                case 'I':
                case 'i':
                    CLS.main();
                    p(myArena.toString());
                    break;

                case 'X':
                case 'x':
                    CLS.main();
                    break;

                case 'M':
                case 'm':
                    System.out.print("How many time should the drones move?\n> ");
                    int i = s.nextInt();
                    System.out.println(i);

                    for (int j = 0; j < i; j++) {
                        CLS.main();
                        DroneArena.moveAllDrones();
                        doDisplay();
                        TimeUnit.MILLISECONDS.sleep(25);
                    }

                    break;

            }
        } while (ch != 'X');
        s.close();
    }

    private static void p(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        DroneInterface r = new DroneInterface();
        //System.out.println(JFileChooser.APPROVE_OPTION);
    }

    private void doDisplay() throws InterruptedException {
        // determine the arena size
        int x = myArena.getX(); // Retrieves X coord of Arena
        int y = myArena.getY(); // Retrieves Y coord of Arena
        // hence create a suitable sized ConsoleCanvas object
        ConsoleCanvas c = new ConsoleCanvas(x, y); // Sets out the canvas for arena.
        // call showDrones suitably
        //System.out.println(myArena.droneAL.size()); // Shows that drones are in the arraylist.  -- Test
        //b
        myArena.showDrones(c);
        System.out.println(c.toString());
    }
}