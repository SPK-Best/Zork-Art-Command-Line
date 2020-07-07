package zork_game;

import zork_game.items.ItemFactory;
import zork_game.monsters.MonsterFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * In one Room, it contains height and width of the room, number of monsters.
 */
public class Map {

    private Room[][] rooms;
    private int height;
    private int width;
    private int numberOfMonster;

    public Map() {
        setMapHeightWidth(0,0);
        this.numberOfMonster = 0;
    }

    /**
     * Set height and width of the map
     */
    public void setMapHeightWidth(int height, int width) {
        this.height = height;
        this.width = width;
        if(height > 0 && width > 0) {
            this.rooms = new Room[this.height][this.width];
        }
        else {
            this.rooms = null;
        }
    }

    /**
     * Load the file
     * First line contains Height, Width of the map
     * Second line to number of Height line contains detail of each room separated by space
     * Ex. 5 6   (Height, Width)
     * Represent the room in N,E,S,W -> 1(Wall), 0(No Wall)
     * 1101 1011 1010 1100 1001 1110
     * 0001 1010 1010 0100 0001 1110
     * 0101 1101 1001 0100 0011 1100
     * 0001 0110 0001 0000 1100 0101
     * 0011 1010 0010 0110 0011 0110
     * The rest line contains items and monsters in this map
     */
    public boolean loadMap(String filename) {
        try {
            //Load file to array of strings
            Path filePath = new File(filename).toPath();
            Charset charset = Charset.defaultCharset();
            List<String> stringList = Files.readAllLines(filePath, charset);
            String[] stringArray = stringList.toArray(new String[]{});

            // Get height and width
            int height = Integer.parseInt(stringArray[0].split(" ")[0]);
            int width = Integer.parseInt(stringArray[0].split(" ")[1]);
            setMapHeightWidth(height, width);

            //create rooms
            for(int i = 1;i <= this.height; i++) {
                String[] line = stringArray[i].split(" ");
                for(int j = 0; j < this.width; j++) {
                    boolean northWall = line[j].charAt(Room.NORTH) == '1';
                    boolean eastWall = line[j].charAt(Room.EAST) == '1';
                    boolean southWall = line[j].charAt(Room.SOUTH) == '1';
                    boolean westWall = line[j].charAt(Room.WEST) == '1';
                    rooms[i-1][j] = new Room(northWall, eastWall, southWall, westWall);
                }
            }

            /**
             * Add items and monsters
             * Ex. monster 1 1 zork
             * line = monster 1 1 zork
             * line[0] = monster
             * line[1] = 1(height)
             * line[2] = 1(width)
             * line[3] = zork
             */
            for(int i = height + 1;i < stringArray.length; i++) {
                String[] line = stringArray[i].split(" ");
                if (line[0].equals("item")){
                    rooms[Integer.parseInt(line[1])][Integer.parseInt(line[2])].addItem(ItemFactory.makeItem(line[3]));
                }
                else if (line[0].equals("monster")){
                    rooms[Integer.parseInt(line[1])][Integer.parseInt(line[2])].addMonster(MonsterFactory.createMonster(line[3]));
                    this.numberOfMonster++;
                }
            }

        }
        catch (IOException e) {
            System.out.println("Error: Cannot load this map file");
            return false;
        }
        return true;
    }

    /**
     * Print a map. Use this function when use command "map"
     * ---+ Represent the wall in horizontal
     * | Represent the wall in vertical
     */
    public void showMap(int posX, int posY) {
        for(int i = 0; i < this.height; i++){
            if(i == 0) {
                for(int j = 0; j < this.width; j++) {
                    if(j == 0)
                        System.out.print("+");
                    if(this.rooms[i][j].hasNWall())
                        System.out.print("---+");
                    else
                        System.out.print("   +");
                }
                System.out.println();
            }

            // Print vertical wall, current Player position, item, monster
            for(int j = 0;j < this.width; j++) {
                if(j == 0) {
                    if(this.rooms[i][j].hasWWall())
                        System.out.print("|");
                    else
                        System.out.print(" ");
                }

                if(i == posX && j == posY) {
                    System.out.print(" X ");
                }
                else if(rooms[i][j].isItemExists() || rooms[i][j].isMonsterExist()) {
                    System.out.print(" . ");
                }
                else {
                    System.out.print("   ");
                }

                if(this.rooms[i][j].hasEWall())
                    System.out.print("|");
                else
                    System.out.print(" ");
            }
            System.out.println();

            for(int j = 0; j < this.width; j++) {
                if(j == 0)
                    System.out.print("+");
                if(this.rooms[i][j].hasSWall())
                    System.out.print("---+");
                else
                    System.out.print("   +");
            }
            System.out.println();
        }
    }

    public Room getRoom(int i, int j) {
        return rooms[i][j];
    }

    /**
     * Save a map and a player status to file
     */
    public boolean saveMap(String filename, String saveName) {
        String text = "";
        text += Integer.toString(this.height) + " " + Integer.toString(this.width) + "\n";
        for(int i = 0; i < this.height; i++) {
            for(int j = 0; j < this.width; j++) {
                if(j > 0)
                    text += " ";

                if(this.rooms[i][j].hasNWall())
                    text += "1";
                else
                    text += "0";

                if(this.rooms[i][j].hasEWall())
                    text += "1";
                else
                    text += "0";

                if(this.rooms[i][j].hasSWall())
                    text += "1";
                else
                    text += "0";

                if(this.rooms[i][j].hasWWall())
                    text += "1";
                else
                    text += "0";
            }
            text += "\n";
        }

        for(int i = 0; i < this.height; i++) {
            for(int j = 0;j < this.width; j++) {
                if(this.rooms[i][j].isMonsterExist()) {
                    String monsterName = this.rooms[i][j].getMonster().getName();
                    text += "monster " + Integer.toString(i) + " " + Integer.toString(j) + " " + monsterName + "\n";
                }
                if(this.rooms[i][j].isItemExists()) {
                    String itemName = this.rooms[i][j].getItem().getItemName();
                    text += "item " + Integer.toString(i) + " " + Integer.toString(j) + " " + itemName + "\n";
                }
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(filename);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.printf(text);
            printWriter.close();

        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: cannot save due to error filename");
            return false;
        }
        return true;
    }

    public void reduceNbOfMonsterByOne() {
        numberOfMonster--;
    }

    public boolean isAllMonsterBeKilled() {
        return numberOfMonster == 0;
    }

}
