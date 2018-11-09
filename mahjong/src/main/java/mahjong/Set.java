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

    // static function that checks a presorted subset for being a pair
    public static boolean isPair(List<Tile> input) {

        if (input.size() == 2)
            if (input.get(0).getValueIndex() == input.get(1).getValueIndex())
                return true;

        return false;
    }

    // static function that checks a presorted subset for being a three of a kind
    public static boolean isThreeOfAKind(List<Tile> input) {

        if (input.size() == 3)
            if (input.get(0).getValueIndex() == input.get(1).getValueIndex())
                if (input.get(1).getValueIndex() == input.get(2).getValueIndex())
                    return true;

        return false;
    }

    // static function that checks a presorted subset for being three consecutives
    public static boolean isThreeConsecutive(List<Tile> input) {

        if (input.size() == 3)
            if (input.get(0).getType() == input.get(1).getType())
                if (input.get(1).getType() == input.get(2).getType())
                    if (input.get(1).getNumber() - input.get(0).getNumber() == 1)
                        if (input.get(2).getNumber() - input.get(1).getNumber() == 1)
                            return true;

        return false;
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
    public boolean addTile(char type, int number) {
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
        char type = ' ';
        int number = 0;
        int n = 0;
        String input;

        System.out.println("Please enter the tiles of your set seperated by commas and without spaces.");
        System.out.println("B = Bamboo, C = Character, D = Dot, G = Dragon, W = Wind");
        System.out.println("Example: 'C1,C2,C3,C4,C5,C6,C7,D1,D2,D3,D4,D5,D6,D7'");

        input = sc.next();
        char tmp[] = input.toCharArray();

        for (char c : tmp) {
            if (Character.isAlphabetic(c))
                type = c;
            else if (Character.isDigit(c))
                number = Character.getNumericValue(c);
            else {
                if (c != ',') {
                    System.out.println("Invalid input.");
                    sc.close();
                    return false;
                }
            }

            if (type != ' ' && number != 0) {
                if (addTile(type, number)) {
                    n++;
                }
                type = ' ';
                number = 0;
            }
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
            // we first try the faster variant of finding a winning set
            if (checkPairLast()) {
                return true;
            } else {
                // now we try the version that is slower but finds even the rarer cases
                return checkPairFirst();
            }
        }
    }

    private boolean checkPairLast() {
        List<Tile> tmpList = new ArrayList<Tile>(tiles);
        List<Tile> tmpList2;
        List<Tile> subSet;
        int i = 0;
        while (!tmpList.isEmpty()) {
            if (tmpList.size() > 2) {
                // we need to find 3 fitting tiles
                subSet = new ArrayList<Tile>(tmpList.subList(i, i + 3));
                if (isThreeOfAKind(subSet) || isThreeConsecutive(subSet)) {
                    tmpList2 = new ArrayList<Tile>(tmpList.subList(i + 3, tmpList.size()));
                    tmpList = new ArrayList<Tile>(tmpList.subList(0, i));
                    tmpList.addAll(tmpList2);
                    i = 0;
                } else
                    i++;
            } else {
                // only two pieces are left, those have to be a pair
                if (isPair(tmpList))
                    return true;
                else
                    tmpList.clear();
            }
            if (tmpList.size() > 2 && i + 2 > tmpList.size())
                // we're not down to the last two but can't build a sublist with 3 anymore
                break;

        }
        return false;
    }

    private boolean checkPairFirst() {
        List<List<Tile>> tmpListList = new ArrayList<List<Tile>>();
        List<Tile> tmpList;
        List<Tile> subSet;
        int pairsFound = 0;
        
        // find all different sublists where a pair can be removed
        for (int i = 0; i < tiles.size() - 1; i++) {
            subSet = new ArrayList<Tile>(tiles.subList(i, i + 2));
            if (isPair(subSet)) {
                tmpListList.add(new ArrayList<Tile>(tiles.subList(0, i)));
                tmpListList.get(pairsFound).addAll(tiles.subList(i + 2, tiles.size()));
                pairsFound++;
            }
        }
        
        // now check these sublists if they make for a winning set
        for (int i = 0; i < pairsFound; i++) {
            tmpList = new ArrayList<Tile>(tmpListList.get(i));
            while (!tmpList.isEmpty()) {
                subSet = new ArrayList<Tile>(tmpList.subList(0, 3));
                if (isThreeOfAKind(subSet) || isThreeConsecutive(subSet)) {
                    tmpList = new ArrayList<Tile>(tmpList.subList(3, tmpList.size()));
                } else {
                    break;
                }
            }
            // all subsets got removed succesfully, that means we got a winning set
            if (tmpList.isEmpty()) {
                return true;
            }
        }

        return false;
    }

}