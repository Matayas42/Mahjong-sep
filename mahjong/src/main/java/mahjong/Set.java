package mahjong;

import java.util.*;
/*import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Iterator;*/

public class Set {

    private final int WINNING_SET_NO_OF_TILES = 14;
    private final int SUBSET_NO_OF_TILES = 3;
    // private final int NO_OF_SUBSETS = 5;

    private List<Tile> tiles;
    private int numberOfTiles;

    public Set() {
        tiles = new ArrayList<Tile>();
        numberOfTiles = 0;
    }

    public void addTile(Tile tile) throws InvalidTileException {
        int addAtIndex = 0;
        int noOfEquals = 1;
        for (Tile t : tiles) {
            if (t.getValueIndex() == tile.getValueIndex()) {
                addAtIndex = tiles.indexOf(t);
                noOfEquals++;
                if (noOfEquals > 4) {
                    throw new InvalidTileException(t.getType(), t.getNumber());
                }
            } else if (t.getValueIndex() < tile.getValueIndex())
                addAtIndex = tiles.indexOf(t)+1;
        }
        tiles.add(addAtIndex, tile);
        numberOfTiles++;
    }

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

    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    public Tile getTileAt(int index) {
        return tiles.get(index);
    }

    public boolean readTiles() {

        Scanner sc = new Scanner(System.in);
        String type = "";
        int number = 0;
        int n = 0;

        System.out.println("Please enter the tiles of your set.");
        System.out.println("Enter 'n' for type or '-1' for number once you're finished.");

        while (true) {
            System.out.print("Type of tile " + (n + 1) + ": ");
            type = sc.next();
            if (type.equals("n"))
                break;
            type = type.substring(0, 1).toUpperCase() + type.substring(1);
            System.out.print("Number of tile " + (n + 1) + ": ");
            try {
                number = sc.nextInt();
            } catch (InputMismatchException e) {
                number = 0;
                System.out.println("Please enter a valid integer value.");
            }
            if (number == -1)
                break;
            if (addTile(type, number)) {
                n++;
                System.out.println("Valid tile added to set.");
            }
            System.out.println("");
        }

        sc.close();
        return (n > 0);
    }

    public boolean isWinningSet() {
        if (numberOfTiles != WINNING_SET_NO_OF_TILES) {
            System.out.println("Incorrect number of tiles for a winning set (" + numberOfTiles + ").");
            return false;
        } else {
            int n = 0;
            Iterator<Tile> i = tiles.iterator();
            Tile tLast = null;
            Tile t = null;
            boolean lastNosEqual = false;
            boolean lastNosConsec = false;
            boolean pairFound = false;

            while (i.hasNext()) {
                if (n % SUBSET_NO_OF_TILES == 0) {
                    lastNosEqual = false;
                    lastNosConsec = false;
                    tLast = i.next();
                } else if (n % SUBSET_NO_OF_TILES == 1) {
                    t = i.next();
                    if (t.getType().equals(tLast.getType())) {
                        if (t.getNumber() == tLast.getNumber())
                            lastNosEqual = true;
                        else if (t.getNumber() == tLast.getNumber() + 1)
                            lastNosConsec = true;
                        else
                            return false;
                    } else
                        return false;
                    tLast = t;
                } else if (n % SUBSET_NO_OF_TILES == 2) {
                    t = i.next();
                    if (lastNosEqual) {
                        if (!t.getType().equals(tLast.getType()) || !(t.getNumber() == tLast.getNumber())) {
                            pairFound = true;
                            n -= 2;
                        }
                    } else if (lastNosConsec && !(t.getType().equals(tLast.getType())
                            || lastNosConsec && !(t.getNumber() == tLast.getNumber() + 1)))
                        return false;
                }
                n++;
            }

            if (pairFound || lastNosEqual) {
                return true;
            }
        }
        return false;
    }

}