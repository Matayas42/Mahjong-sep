package mahjong;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Set {

	// constants keeping number of tiles in winning set and number of tiles per
	// subset
	private final int WINNING_SET_NO_OF_TILES = 14;
	private final int SUBSET_NO_OF_TILES = 3;
    private final int MAX_NO_OF_SAME_TILE = 4;
	private final int LIMIT = 10;
	// private final int NO_OF_SUBSETS = 5;


    private List<Tile> tiles;
    private int numberOfTiles;
    private boolean canTakeNew = false;
    private boolean isHouse = false;
    private int playerIndex;


	// basic constructor for empty Set Object
	public Set() {
		tiles = new ArrayList<Tile>();
		numberOfTiles = 0;
	}


    public List<Tile> getTiles() {
        return tiles;
    }

    public void setPlayerIndex(int index) {
        playerIndex = index;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setHouse() {
        isHouse = true;
    }

    public boolean isHouse() {
        return isHouse;
    }

    public boolean canTakeNew() {
        if (canTakeNew) {
            canTakeNew = false;
            return true;
        }
        return false;
    }

    // static function that checks a presorted subset for being a pair
    public static boolean isPair(List<Tile> input) {
		if (input.size() == 2)
			if (input.get(0).getValueIndex() == input.get(1).getValueIndex())
				return true;

		return false;
	}

	// static function that checks a presorted subset for being a three of a
	// kind
	public static boolean isThreeOfAKind(List<Tile> input) {

		if (input.size() == 3)
			if (input.get(0).getValueIndex() == input.get(1).getValueIndex())
				if (input.get(1).getValueIndex() == input.get(2).getValueIndex())
					return true;

		return false;
	}

	public static boolean isFourOfAKind(List<Tile> input) {

		if (input.size() == 4)
			if (input.get(0).getValueIndex() == input.get(1).getValueIndex())
				if (input.get(1).getValueIndex() == input.get(2).getValueIndex())
					if (input.get(2).getValueIndex() == input.get(3).getValueIndex())
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

        // check the Tile database file if there are still tiles like that left in the
        // set
        if (checkDataBase(tile)) {
            // it's good, now add it at the right position
            for (Tile t : tiles) {
                if (t.getValueIndex() < tile.getValueIndex())
                    // the type-index of our new tile is smaller than the one we are comparing it
                    // to, that means we have to add it before that tile
                    addAtIndex = tiles.indexOf(t) + 1;
            }
            // adding the tile to the list, counting up our current number of tiles
            tiles.add(addAtIndex, tile);
            numberOfTiles++;
        }
    }

    private boolean checkDataBase(Tile tile) throws InvalidTileException {

        String tmp = "";

        try {
            List<String> fileContent = new ArrayList<String>(
                    Files.readAllLines(Game.getDatabasePath(), StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                tmp = fileContent.get(i);
                if (tmp.contains(tile.getShorthand()))
                    if (Character.getNumericValue(tmp.charAt(3)) == MAX_NO_OF_SAME_TILE)
                        throw new InvalidTileException(tile.getType(), tile.getNumber());
                    else {
                        tmp = tmp.substring(0, 3) + (Character.getNumericValue(tmp.charAt(3)) + 1);
                        fileContent.set(i, tmp);
                    }
            }
            Files.write(Game.getDatabasePath(), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Database was not initiated correctly!!");
        }

        return true;
    }

    // this method creates a new tile object from the input data and gives it to the
    // method above to add it to our set
    // returns true if the action was succesful, false if not succesful
    public boolean addTile(char type, int number, boolean printError) {
        try {
            Tile tile = new Tile(type, number);
            addTile(tile);
            return true;
        } catch (InvalidTileException e) {
            if (printError)
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
    // then passes it to next method that creates the Tile Objects
    public boolean readTiles() {

        Scanner sc = new Scanner(System.in);
        String inputString;

        System.out.println("Please enter the tiles of your set seperated by commas and without spaces.");
        System.out.println("B = Bamboo, C = Character, D = Dot, G = Dragon, W = Wind");
        System.out.println("Example: 'C1,C2,C2,B4,B5,B5,C7,D1,W2,D3,G4,G4,G6,D7'");

        inputString = sc.next();
        sc.close();

        return fillSetFromString(inputString);

    }

    public boolean fillSetFromString(String input) {

        char type = ' ';
        int number = 0;
        int n = 0;

        char tmp[] = input.toCharArray();

        // circle through input string and create Tiles if valid
        for (char c : tmp) {
            if (Character.isAlphabetic(c))
                type = c;
            else if (Character.isDigit(c))
                number = Character.getNumericValue(c);
            else {
                if (c != ',') {
                    System.out.println("Invalid input.");
                    return false;
                }
            }

            if (type != ' ' && number != 0) {
                if (addTile(type, number, true)) {
                    n++;
                }
                type = ' ';
                number = 0;
            }
        }
        // returns true if we found some valid tiles
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
                } else {
                    if (tmpList.size() > i + 3) {
                        subSet.remove(2);
                        subSet.add(tmpList.get(i + 3));
                        if (isThreeOfAKind(subSet) || isThreeConsecutive(subSet)) {
                            Tile save = tmpList.get(i + 2);
                            if (tmpList.size() > i + 4)
                                tmpList2 = new ArrayList<Tile>(tmpList.subList(i + 4, tmpList.size()));
                            else
                                tmpList2 = new ArrayList<Tile>();
                            tmpList = new ArrayList<Tile>(tmpList.subList(0, i));
                            tmpList.add(save);
                            tmpList.addAll(tmpList2);
                            i = 0;
                        } else
                            i++;
                    }
                }

                if (i + 2 > tmpList.size())
                    // we're not down to the last two but can't build a sublist with 3 anymore
                    break;

            } else {
                // only two pieces are left, those have to be a pair
                if (isPair(tmpList))
                    return true;
                else
                    tmpList.clear();
            }

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

	public int computeFanPoints() {
		// no points in this case
		if (!isWinningSet())
			return -1;

		int pointsMax = 0;
		if (isAllHonorTiles())
			pointsMax = pointsMax > LIMIT ? pointsMax : LIMIT;
		if (isGreatDragon())
			pointsMax = pointsMax > 8 ? pointsMax : 8;
		if (isAllOneSuit())
			pointsMax = pointsMax > 7 ? pointsMax : 7;
		if (isCommonHand())
			pointsMax = pointsMax > 1 ? pointsMax : 1;
		if (isAllInTripletts())
			pointsMax = pointsMax > 3 ? pointsMax : 3;
		if (isMixOneSuit())
			pointsMax = pointsMax > 3 ? pointsMax : 3;
		if (isSmallDragon())
			pointsMax = pointsMax > 5 ? pointsMax : 5;
		if (isSevenPairs())
			pointsMax = pointsMax > 4 ? pointsMax : 4;

		// .... some more ...
		return pointsMax;
	}

	private List removePair(List l) {
		List<Tile> tmpList = new ArrayList<Tile>(l);
		List<Tile> tmpList2 = new ArrayList<Tile>();
		List<Tile> subSet;
		boolean check;
		// check for the first four subsets with length 3
		for (int i = 0; i < l.size()-2; i++) {
			if(isPair(l.subList(i, i+2))){
				if(!isThreeOfAKind(l.subList(i, i+3)){
					l.remove(i + 1);
					l.remove(i);
					return l;
				}
			}
		}
	}

	private boolean isCommonHand() {
		List<Tile> tmpList = new ArrayList<Tile>(tiles);
		List<Tile> tmpList2 = new ArrayList<Tile>();
		List<Tile> subSet;
		boolean check;
		// check for the first four subsets with length 3
		for (int i = 0; i < 4; i++) {
			check = false;
			// is it chow?
			if (tmpList.size() > 3) {
				subSet = tmpList.subList(0, 3);
				tmpList2 = tmpList.subList(3, tmpList.size());
				check = isThreeConsecutive(subSet);
			}
			if (!check)
				return false;
		}
		return isPair(tmpList2);
	}

	private boolean isAllInTripletts() {
		List<Tile> tmpList = new ArrayList<Tile>(tiles);
		List<Tile> tmpList2 = new ArrayList<Tile>();
		List<Tile> subSet;
		boolean check;
		// check for the first four subsets with length 3 or 4
		for (int i = 0; i < 4; i++) {
			check = false;
			// is it Kong?
			if (tmpList.size() > 4) {
				subSet = tmpList.subList(0, 4);
				tmpList2 = tmpList.subList(4, tmpList.size());
				check = isFourOfAKind(subSet);
				if (check)
					tmpList = tmpList2;
			}
			// if not, is it Pong?
			if (!check) {
				if (tmpList.size() > 3) {
					subSet = tmpList.subList(0, 3);
					tmpList2 = tmpList.subList(3, tmpList.size());
					check = isThreeOfAKind(subSet);
					if (check)
						tmpList = tmpList2;
				} else {
					// not enough molds
					return false;
				}
			}
			if (!check)
				return false;
		}
		return isPair(tmpList);
	}

	private boolean isMixOneSuit() {

		return false;
	}

	private boolean isAllOneSuit() {

		return false;
	}

	private boolean isAllHonorTiles() {

		return false;
	}

	private boolean isSmallDragon() {

		return false;

	}

	private boolean isGreatDragon() {

		return false;
	}

	private boolean isSevenPairs() {

		return false;

	}
	// .... more forms ....

    // adds a given number of random tiles to the current set
    public void addRandomTiles(int n) {

        Random r = new Random();
        char type;
        int number;
        // boolean wasSuccessful = true;

        for (int i = 0; i < n; i++) {
            type = Tile.TILE_SHORTHAND.get(r.nextInt(Tile.TILE_SHORTHAND.size()));
            number = r.nextInt(Tile.getNumberRange(type));
            if (!addTile(type, number, false))
                i--;
        }

    }

    // get rid of tile with index
    public Tile throwTile(int index) {

        if (index >= 0 && index < tiles.size()) {
            Tile tmpTile = tiles.get(index);
            tiles.remove(index);
            return tmpTile;
        }
        return null;
    }

    public void takeTile(Tile t) {
        tiles.add(t);
    }

    public boolean takeTilePossible(Tile input) {

        // we first check for the special Kong case, where you can take and draw
        int count = 0;
        for (Tile t : tiles) {
            if (input.getValueIndex() == t.getValueIndex()) {
                count++;
            }
        }
        if (count == 3) {
            canTakeNew = true;
            return true;
        }

        // okay no kong so let's see if we have Pong or Chow
        Set tmpSet = new Set();

        try {
            tmpSet.addTile(input);

            for (int i = 0; i < numberOfTiles - 1; i++) {
                tmpSet.addTile(tiles.get(i));
                tmpSet.addTile(tiles.get(i + 1));
                if (Set.isThreeConsecutive(tmpSet.getTiles()) || Set.isThreeOfAKind(tmpSet.getTiles())) {
                    return true;
                }
            }

        } catch (InvalidTileException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

}