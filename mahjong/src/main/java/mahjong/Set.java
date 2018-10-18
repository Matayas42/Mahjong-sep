package mahjong;

import java.util.*;
/*import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Iterator;*/

public class Set {

    // constants keeping number of tiles in winning set and number of tiles per
    // subset
    private final int WINNING_SET_NO_OF_TILES = 14;
    private final int SUBSET_NO_OF_TILES = 3;
    // private final int NO_OF_SUBSETS = 5;

    private List<Tile> tiles;
    private int numberOfTiles;

    // basic constructor for empty Set Object
    public Set() {
        tiles = new ArrayList<Tile>();
        numberOfTiles = 0;
    }

    // Methods adds an existing tile object to the current set and throws the custom
    // exception if all possible tiles of that type are already part of our set
    // it also sorts the tiles before adding the to our list
    public void addTile(Tile tile) throws InvalidTileException {
        int addAtIndex = 0;
        int noOfEquals = 1;
        for (Tile t : tiles) {
            // check if there'S a tile with the same tile-index in our set (aka the same
            // tile)
            if (t.getValueIndex() == tile.getValueIndex()) {
                addAtIndex = tiles.indexOf(t);
                noOfEquals++;
                if (noOfEquals > 4) {
                    // we have the same tile more than 4 times, this is not possible, throw
                    // exception
                    throw new InvalidTileException(t.getType(), t.getNumber());
                }
            } else if (t.getValueIndex() < tile.getValueIndex())
                // the type-index of our new tile is smaller than the one we are comparing it
                // to, that means we have to add it before that tile
                addAtIndex = tiles.indexOf(t) + 1;
        }
        // adding the tile to the list, counting up our current number of tiles
        tiles.add(addAtIndex, tile);
        numberOfTiles++;
    }

    // this method creates a new tile object from the input data and gives it to the
    // method above to add it to our set
    // returns true if the action was succesful, false if not succesful
    public boolean addTile(String type, int number) {
        try {
            Tile tile = new Tile(type, number);
            addTile(tile);
            return true;
        } catch (InvalidTileException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // basic getter for private member variable
    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    // returns the tile with the list-index of the parameter
    public Tile getTileAt(int index) {
        return tiles.get(index);
    }

    // reads the tiles from the standard console
    // always first the type than the number
    // stops reading if you enter "n" for the type of the tile or -1 for ints number
    public boolean readTiles() {

        Scanner sc = new Scanner(System.in);
        String type = "";
        int number = 0;
        int n = 0;

        System.out.println("Please enter the tiles of your set.");
        System.out.println("Enter 'n' for type or '-1' for number once you're finished.");

        // infinite loop, because we only want to stop reading tiles if we get an input
        // from the user telling us that he's finished
        while (true) {
            System.out.print("Type of tile " + (n + 1) + ": ");
            type = sc.next();
            if (type.equals("n"))
                // user input says stop reading tiles
                break;
            // make sure that our tile type is always capitalized
            type = type.substring(0, 1).toUpperCase() + type.substring(1);
            System.out.print("Number of tile " + (n + 1) + ": ");
            try {
                number = sc.nextInt();
            } catch (InputMismatchException e) {
                // this exception gets thrown if the entered values was not an int
                number = 0;
                System.out.println("Please enter a valid integer value.");
            }
            if (number == -1)
                // user input says stop reading tiles
                break;
            if (addTile(type, number)) {
                // all inputs were correct, we add a new tile to our set
                n++;
                System.out.println("Valid tile added to set.");
            }
            System.out.println("");
        }

        sc.close();
        // return true if there was at least on tile added succesfully
        return (n > 0);
    }

    public boolean isWinningSet() {
        if (numberOfTiles != WINNING_SET_NO_OF_TILES) {
            // the number of tiles in our set does not match up the neccessary number for a
            // winning set, so nothing to do here but return false
            System.out.println("Incorrect number of tiles for a winning set (" + numberOfTiles + ").");
            return false;
        } else {
            int n = 0;
            Iterator<Tile> i = tiles.iterator();
            Tile tLast = null;
            Tile t = null;
            boolean lastNosEqual = false; // if true last two numbers were equal
            boolean lastNosConsec = false; // if true last two numbers were consecutive
            boolean pairFound = false; // found a pair we can use for our winning conditions

            // iterator loop that runs as long as there is a tile after the current one in
            // our set
            while (i.hasNext()) {
                if (n % SUBSET_NO_OF_TILES == 0) {
                    // this is the first tile in our current subset
                    // we reset our working flags and get the next one
                    lastNosEqual = false;
                    lastNosConsec = false;
                    tLast = i.next(); // new tile next loop
                } else if (n % SUBSET_NO_OF_TILES == 1) {
                    // this is the second tile in our current subset
                    t = i.next(); // new tile next loop
                    if (t.getType().equals(tLast.getType())) {
                        // same type, lets check the number
                        if (t.getNumber() == tLast.getNumber())
                            // same numbers on the last two tiles
                            lastNosEqual = true;
                        else if (t.getNumber() == tLast.getNumber() + 1)
                            // consecutive numbers on the last two tiles
                            lastNosConsec = true;
                        else
                            return false;
                    } else
                        return false;
                    tLast = t;
                } else if (n % SUBSET_NO_OF_TILES == 2) {
                    // this is the first and list tile in our current subset
                    t = i.next();
                    if (lastNosEqual) {
                        if (!t.getType().equals(tLast.getType()) || !(t.getNumber() == tLast.getNumber())) {
                            // didn't find three matching tiles, but we found two we can put on the end
                            pairFound = true;
                            n -= 2;
                        }
                    } else if (lastNosConsec && !(t.getType().equals(tLast.getType())
                            || lastNosConsec && !(t.getNumber() == tLast.getNumber() + 1)))
                        // after our first two consecutive numbers we didn't find a third matching one
                        return false;
                }
                n++;
            }

            if (pairFound || lastNosEqual) {
                // moved through all tiles without violating winning conditions and found a pair
                // on the way, this is a winning set
                return true;
            }
        }
        // we should never get here, this line is just to prevent a warning
        return false;
    }

}