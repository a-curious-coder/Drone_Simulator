/**
 *
 */
package projectDrone;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/** @author callummclennan */
public class DroneInterface {
    JFileChooser chooseFile = new JFileChooser();
    private Scanner s; // Used to scan for whatever I specify
    private DroneArena myArena;
    private ArrayList<String> contentString = new ArrayList<String>(); // Instantiate

    public DroneInterface() throws InterruptedException, IOException // Constructor
    {
        CLS.main();
        s = new Scanner(System.in); // Scans for user input

        char ch = ' ';
        do {
            System.out.print("A | AddDrone\nB | NewArena\nC | Create/Display Default Arena\nD | Display Arena \n" + "E | Remove All Drones\nF | File Options\nG | Add multiple drones\nM | MoveAllDrones\nI | Information\nX | Exit\n\n> ");
            ch = s.next().charAt(0);
            s.nextLine();
            switch (ch) {
                case 'A':
                case 'a':
                    CLS.main();

                    if (DroneArena.xSize == 0 && DroneArena.ySize == 0) {
                        System.out.println(
                                "No arena to add drones to.\nCreate an arena with your specifications by pressing 'B'\nOr create a default arena by pressing 'C'\n");

                        break;
                    } else {
                        myArena.addDrone(); // Adds new drone to arena
                        if (myArena.droneAL.size() < 2) {
                            System.out.println(myArena.droneAL.size() + " drone in arena.");
                            break;
                        } else {
                            System.out.println(myArena.droneAL.size() + " drones in arena.");
                            break;
                        }
                    }

                case 'B':
                case 'b':
                    System.out.print("Enter x coord: "); // Asks user for Arena coords
                    myArena.xSize = s.nextInt(); // Retrieves next input type Integer
                    System.out.print("Enter y coord: ");
                    myArena.ySize = s.nextInt();
                    CLS.main();
                    myArena.droneAL.clear(); // Reset arraylist size - removing all of the drones created for previous
                    // arena.
                    myArena = new DroneArena(DroneArena.xSize, DroneArena.ySize); // generates arena using inputted x and y vals
                    doDisplay(); // Displays the arena on console.
                    System.out.println(
                            myArena.xSize
                                    + " | "
                                    + myArena.ySize
                                    + " size arena has successfully been created.");
                    System.out.println("\nPress Any Key To Continue...");
                    System.in.read();
                    CLS.main();
                    break;
                case 'C':
                case 'c':
                    CLS.main();
                    if (myArena.xSize == 0 || myArena.ySize == 0) {
                        System.out.println("Successfully created Default 10*10 arena");
                        myArena = new DroneArena(10, 10); // Create arena with x and y sizes
                    } else {
                        System.out.println("Arena already created, cannot build default arena unless program is reset");
                    }
                    break;
                case 'D':
                case 'd':
                    CLS.main();

                    if (myArena.xSize == 0 || myArena.ySize == 0) {
                        System.out.println("No arena to display\nCreate one by pressing ' B '\n");
                        break;
                    } else {
                        doDisplay();
                        System.out.print("Press Any Key To Continue...");
                        System.in.read();
                        CLS.main();
                        break;
                    }
                case 'E':
                case 'e':
                    myArena.droneAL.clear();
                    System.out.println("Drone count set to: " + myArena.droneAL.size());
                    break;

                case 'F':
                case 'f':
                    CLS.main();
                    System.out.println("\n\n\nFile Options\n");
                    System.out.println("1 | Open File\t2 | Save File\t3 | Exit\n> ");
                    int c = s.nextInt();
                    switch (c) {

Save New Duplicate & Edit Just Text Twitter
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
72
73
/**
* Open file code
* Suggested you add in a 'file filter' but I didn't do that - This means the dialog box will only display and allow you to open '.txt' files for example.
*
*/
                            chooseFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // Creates window to choose directory file is stored in
                            int returnValue = chooseFile.showOpenDialog(null);


                            if (returnValue == JFileChooser.APPROVE_OPTION) { // Approve option is when user clicks 'Open' after selecting the file
                                File selectedFile = chooseFile.getSelectedFile(); // Gets selected file from directory
                                System.out.println(selectedFile.getAbsolutePath()); // Prints the directory the file is stored in

                                Scanner sc = new Scanner(selectedFile); // Create scanner focused on selectedFile
                                int i = 0;
                                while (sc.hasNextLine()) { // While the scanner has another line to go to
                                    contentString.add(sc.nextLine()); // Add the nextlines contents to 'contentString' ArrayList
                                    System.out.println(contentString.get(i)); // Prints the contents (Mainly so you can see what this while loop is seeing from the file.)
                                    i++; // Iterate 'i' by 1 to go to the next line in the file.
                                }
                                sc.close(); // Close the scanner after going through the entire file.

                                myArena.droneAL.clear(); // Clear the drones arraylist to make room for the new drones retrieved from the file.
                                System.out.println("Drones in arena: " + myArena.droneAL.size()); // For my own reference to prove that the drones have in fact gone.
                                int xSize = Integer.parseInt(contentString.get(0)); // X size of arena. Converts the element 0 in ArrayList contentString to Integer.
                                int ySize = Integer.parseInt(contentString.get(1)); // Y size of arena. Converts the element 0 in ArrayList contentString to Integer.
                                int xPos = 0; // forgotten this ngl
                                int yPos = 0;

                                myArena = new DroneArena(xSize, ySize); //Creates new arena called 'myArena' using new instance of DroneArena class with xSize and ySize from file
                                for (int j = 3; j < (contentString.size() / 4) - 2; j++) { //Iterate through this /4 to make up for properties - 2 numbers for arenasize
/**
* There are 3 variables that need filling in for each drone. xPos, yPos, Direction and the creation of the drone with these retrieved variables.
* E.g. xPos = Convert(the string content from the string ArrayList at index position j) - same with yPos
* New direction 'dir' = Direction.valueOf (new direction value) from contentString ArrayList
* new Drone.
* Add new drone to ArrayList containing all drones.
*/
                                    xPos = Integer.parseInt(contentString.get(j));
                                    j++;
                                    yPos = Integer.parseInt(contentString.get(j)); // Converts string at index i to integer and stores into yPos.
                                    j++;
                                    Direction dir = Direction.valueOf(contentString.get(j)); // Created new direction - ValueOf contentString index
                                    Drone drone = new Drone(xPos, yPos, dir); // Creates new drone with properties of old drone. - clone
                                    myArena.droneAL.add(drone); // Adds drone back to Drone Arraylist which holds it and it's properties
                                }

                            }

                            break;
                        case 2: // Save file

//Appending means adding to the string being created by the 'StringBuilder'.
                            StringBuilder sb = new StringBuilder(); // Create StringBuilder
                            sb.append(myArena.xSize + "\n" + myArena.ySize + "\n"); // Appending arenasize
                            for (int i = 0; i < myArena.droneAL.size(); i++) { // Appends all drones and their properties
                                sb.append("\n" + myArena.droneAL.get(i).xPos + "\n" + myArena.droneAL.get(i).yPos + "\n" + myArena.droneAL.get(i).dir); //Stores drone and attributes
                            }

                            File outFile = new File("Arena.txt");
                            try {
                                FileWriter outFileWriter = new FileWriter(outFile); // Creates FileWriter referring to File
                                PrintWriter writer = new PrintWriter(outFileWriter); // Creates printwriter referring to FileWriter
                                writer.println(sb.toString()); // Everything collected in toString is written to file.
                                writer.close(); //Closes writer.
                                System.out.println("Successfully written arena details to text file.");
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;

                        case 3:

                            CLS.main();
                            break;

                    }
                    break;

                case 'G':
                case 'g':
                    System.out.println("How many drones would you like to add?\n> ");
                    c = s.nextInt();

                    for (int i = 0; i < c; i++) {
                        myArena.addDrone();
                    }
                    CLS.main();
                    System.out.println(
                            c + " drones added to arena.\nTotal drones: " + myArena.droneAL.size());
                    break;
                case 'I':
                case 'i':
                    CLS.main();
                    p(myArena.toString());
                    break;

                case 'M':
                case 'm':
                    System.out.print("How many time should the drones move?\n> ");
                    int i = s.nextInt();
                    int j = 0;
                    // System.out.println(i);
                    while (System.in.available() == 0) {
                        if (j != i) {
                            j++;
                            CLS.main();
                            System.out.println("Press Enter Key To Exit...");
                            doDisplay();
                            myArena.moveAllDrones();
                            TimeUnit.MILLISECONDS.sleep(25);
                        } else {
                            break;
                        }
                    }

                    break;

                case 'X':
                case 'x':
                    CLS.main();
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
        // System.out.println(JFileChooser.APPROVE_OPTION);
    }

    private void doDisplay() throws InterruptedException {
        // determine the arena size
        int x = myArena.getX(); // Retrieves X coord of Arena
        int y = myArena.getY(); // Retrieves Y coord of Arena
        // hence create a suitable sized ConsoleCanvas object
        ConsoleCanvas c = new ConsoleCanvas(x, y); // Sets out the canvas for arena.
        // call showDrones suitably
        // System.out.println(myArena.droneAL.size()); // Shows that drones are in the arraylist.  --
        // Test
        // b
        myArena.showDrones(c);
        System.out.println(c.toString());
    }

}
