package mahjong;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import java.nio.file.Files;

import java.util.ArrayList;

import java.util.List;

import java.util.Random;

import java.util.Scanner;
import java.util.Stack;

public class Set {

	// constants keeping number of tiles in winning set and number of tiles per
	// subset
	private final int WINNING_SET_NO_OF_TILES = 14;
	// private final int SUBSET_NO_OF_TILES = 3;
	private final int MAX_NO_OF_SAME_TILE = 4;
	private final int LIMIT = 10;
	// private final int NO_OF_SUBSETS = 5;
	private List<Tile> tiles;
	private int numberOfTiles;
	private boolean isHouse = false;
	private int playerIndex;

	// basic constructor for empty Set Object, exists for testing purposes
	public Set() {
		tiles = new ArrayList<Tile>();
		numberOfTiles = 0;
	}

	// constructor for actual gameplay
	public Set(int playerIdx) {
		playerIndex = playerIdx;
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

	// static function that checks a presorted subset for being three

	// consecutives

	public static boolean isThreeConsecutive(List<Tile> input) {

		if (input.size() == 3)
			if (input.get(0).getType() == input.get(1).getType())
				if (input.get(1).getType() == input.get(2).getType())
					if (input.get(1).getNumber() - input.get(0).getNumber() == 1)
						if (input.get(2).getNumber() - input.get(1).getNumber() == 1)
							return true;
		return false;

	}

	// Methods adds an existing tile object to the current set and throws the
	// custom exception if all possible tiles of that type are already part of our
	// set it also sorts the tiles before adding the to our list
	public boolean addTile(Tile tile, boolean newTile, boolean printError) {
		int addAtIndex = 0;

		// check the Tile database file if there are still tiles like that left
		// in the set
		try {
			if (newTile) {
				// it's good, now add it at the right position
				checkDataBase(tile);
			}
			// adding the tile to the list, counting up our current number
			// of tiles
			for (Tile t : tiles) {
				if (t.getValueIndex() < tile.getValueIndex())
					// the type-index of our new tile is smaller than the one we are comparing it
					// to, that means we have to add it before that tile
					addAtIndex = tiles.indexOf(t) + 1;
			}
			tiles.add(addAtIndex, tile);
			numberOfTiles++;
			return true;

		} catch (InvalidTileException e) {
			if (printError)
				System.out.println(e.getMessage());
		}
		return false;
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

	// this method creates a new tile object from the input data and gives it to
	// the method above to add it to our set
	// returns true if the action was succesful, false if not succesful

	public boolean addTile(char type, int number, boolean printError) {

		try {
			Tile tile = new Tile(type, number);
			return addTile(tile, true, printError);
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
			// the number of tiles in our set does not match up the neccessary
			// number for a winning set, so nothing to do here but return false
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

	// assistant function for testing
	public boolean pairLast() {
		return this.checkPairLast();
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

				if (i + 2 < tmpList.size())
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

	// assistant function for testing
	public boolean pairFirst() {
		return this.checkPairFirst();
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
				i++;
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

	public List<List<Meld>> allPossibleHands(List<Tile> list) throws Exception {
		Stack<Melds> toIterate = new Stack<Melds>();
		Melds first = new Melds(list);
		toIterate.push(first);
		List<List<Meld>> endresults = new ArrayList<List<Meld>>();
		while (!toIterate.isEmpty()) {
			Melds pair = toIterate.pop();
			List<Meld> meldList = pair.getMelds();
			List<Tile> restTiles = pair.getRestTiles();
			// get all Pairs in restTiles
			for (int i = 0; i < restTiles.size(); i++) {
				for (int j = i + 1; j < restTiles.size(); j++) {
					if (restTiles.get(i).equals(restTiles.get(j))) {
						Melds meldsNew = makeNewMelds("Eyes", i, j, -1, -1, meldList, restTiles);
						if (meldsNew.getRestTiles().isEmpty()) {
							endresults.add(meldsNew.getMelds());
						} else {
							toIterate.push(meldsNew);
						}
					}
					for (int k = j + 1; k < restTiles.size(); k++) {
						if (restTiles.get(i).equals(restTiles.get(j)) && restTiles.get(j).equals(restTiles.get(k))) {
							Melds meldsNew = makeNewMelds("Pong", i, j, k, -1, meldList, restTiles);
							if (meldsNew.getRestTiles().isEmpty()) {
								endresults.add(meldsNew.getMelds());
							} else {
								toIterate.push(meldsNew);
							}
						}
						Tile Ti = restTiles.get(i);
						Tile Tj = restTiles.get(j);
						Tile Tk = restTiles.get(k);
						if (Ti.getNumber() == Tj.getNumber() - 1 && Tj.getNumber() == Tk.getNumber() - 1
								&& Ti.getType().equals(Tj.getType()) && Tj.getType().equals(Tk.getType())) {
							Melds meldsNew = makeNewMelds("Chow", i, j, k, -1, meldList, restTiles);
							if (meldsNew.getRestTiles().isEmpty()) {
								endresults.add(meldsNew.getMelds());
							} else {
								toIterate.push(meldsNew);
							}
						}
						for (int l = k + 1; l < restTiles.size(); l++) {
							if (restTiles.get(i).equals(restTiles.get(j)) && restTiles.get(j).equals(restTiles.get(k))
									&& restTiles.get(k).equals(restTiles.get(l))) {
								Melds meldsNew = makeNewMelds("Kong", i, j, k, l, meldList, restTiles);
								if (meldsNew.getRestTiles().isEmpty()) {
									endresults.add(meldsNew.getMelds());
								} else {
									toIterate.push(meldsNew);
									for (Tile t : meldsNew.getRestTiles()) {
										System.out.println(" " + t);
									}
								}
							}
						}
					}
				}
			}
		}
		return endresults;
	}

	private static Melds makeNewMelds(String meldType, int i, int j, int k, int l, List<Meld> meldList,
			List<Tile> restTiles) throws Exception {
		Meld m = new Meld(meldType, restTiles.get(i));
		List<Meld> meldListNew = new ArrayList<Meld>();
		meldListNew.addAll(meldList);
		meldListNew.add(m);
		List<Tile> restTilesNew = new ArrayList<Tile>();
		restTilesNew.addAll(restTiles);
		if (m.isKong()) {
			restTilesNew.remove(l);
			restTilesNew.remove(k);
		} else if (m.isPong() || m.isChow()) {
			restTilesNew.remove(k);
		}
		restTilesNew.remove(j);
		restTilesNew.remove(i);
		return new Melds(meldListNew, restTilesNew);
	}

	public int computeFanPoints() {
		try {
			List<List<Meld>> allPossibleMelds = allPossibleHands(tiles);
			int pointsMax = 0;
			for (List<Meld> meldList : allPossibleMelds) {
				if (meldList.size() == 5) {
					if (isAllHonorTiles(meldList))
						pointsMax = pointsMax > LIMIT ? pointsMax : LIMIT;
					if (isGreatDragon(meldList))
						pointsMax = pointsMax > 8 ? pointsMax : 8;
					if (isAllOneSuit(meldList))
						pointsMax = pointsMax > 7 ? pointsMax : 7;
					if (isCommonHand(meldList))
						pointsMax = pointsMax > 1 ? pointsMax : 1;
					if (isAllInTripletts(meldList))
						pointsMax = pointsMax > 3 ? pointsMax : 3;
					if (isMixOneSuit(meldList))
						pointsMax = pointsMax > 3 ? pointsMax : 3;
					if (isSmallDragon(meldList))
						pointsMax = pointsMax > 5 ? pointsMax : 5;
					if (isSmallWinds(meldList))
						pointsMax = pointsMax > LIMIT ? pointsMax : LIMIT;
					if (isGreatWinds(meldList))
						pointsMax = pointsMax > LIMIT ? pointsMax : LIMIT;
					if (isAllKongs(meldList))
						pointsMax = pointsMax > LIMIT ? pointsMax : LIMIT;
					if (isAllOrphans(meldList))
						pointsMax = pointsMax > LIMIT ? pointsMax : LIMIT;
				} else {
					if (isSevenPairs(meldList))
						pointsMax = pointsMax > 4 ? pointsMax : 4;
				}
			}

			return pointsMax;
		} catch (Exception e) {
			System.out.println("A very evil exception has occured, please continiu playing, bugs may crawl out.");
			e.printStackTrace();
		}
		return 0;

	}

	public boolean isAllOrphans(List<Meld> meldList) {
		int eyes = 0;
		int melds = 0;
		for (Meld m : meldList) {
			if (m.getTile().getNumber() != 1 && m.getTile().getNumber() != 9)
				return false;
			if (m.isEyes()) {
				eyes++;
			} else if (m.isKong() || m.isPong()) {
				melds++;
			}
		}
		return (eyes == 1 && melds == 4);
	}

	public boolean isAllKongs(List<Meld> meldList) {
		int eyes = 0;
		int kongs = 0;
		for (Meld m : meldList) {
			if (m.isKong())
				kongs++;
			if (m.isEyes())
				eyes++;
		}
		return (eyes == 1 && kongs == 4);
	}

	public boolean isGreatWinds(List<Meld> meldList) {
		int eyes = 0;
		int windmelds = 0;
		for (Meld m : meldList) {
			if (m.isEyes()) {
				eyes++;
			} else if (!m.getTile().isWind()) {
				return false;
			} else {
				windmelds++;
			}
		}
		return (eyes == 1 && windmelds == 4);
	}

	public boolean isSmallWinds(List<Meld> meldList) {
		int eyes = 0;
		int windmelds = 0;
		for (Meld m : meldList) {
			if (m.isEyes()) {
				if (!m.getTile().isWind()) {
					return false;
				}
				eyes++;
			} else if (m.getTile().isWind()) {
				windmelds++;
			}
		}
		return (eyes == 1 && windmelds >= 3);
	}

	public boolean isSevenPairs(List<Meld> meldList) {
		int eyes = 0;
		for (Meld m : meldList) {
			if (m.isEyes()) {
				eyes++;
			} else {
				return false;
			}
		}
		return eyes == 7;
	}

	public boolean isSmallDragon(List<Meld> meldList) {
		int eyes = 0;
		int dragons = 0;
		int melds = 0;
		for (Meld m : meldList) {
			if (m.isEyes()) {
				if (!m.getTile().isDragon()) {
					return false;
				}
				eyes++;
			} else if (m.getTile().isDragon()) {
				dragons++;
			} else {
				melds++;
			}
		}
		return (eyes == 1 && dragons >= 2 && melds + dragons == 4);
	}

	public boolean isMixOneSuit(List<Meld> meldList) {
		int eyes = 0;
		int melds = 0;
		boolean check = false;
		String suit = "";
		for (Meld m : meldList) {
			if (m.getTile().isHonorTile()) {

			} else if (!check) {
				suit = m.getTile().toString();
			} else if (!m.getTile().toString().equals(suit)) {
				return false;
			}
			if (m.isEyes()) {
				eyes++;
			} else {
				melds++;
			}
		}
		return (eyes == 1 && melds == 4);
	}

	public boolean isAllInTripletts(List<Meld> meldList) {
		int eyes = 0;
		int melds = 0;
		for (Meld m : meldList) {
			if (m.isChow())
				return false;
			if (m.isEyes()) {
				eyes++;
			} else {
				melds++;
			}
		}
		return (eyes == 1 && melds == 4);
	}

	public boolean isCommonHand(List<Meld> meldList) {
		int eyes = 0;
		int chows = 0;
		for (Meld m : meldList) {
			if (m.isChow())
				chows++;
			if (m.isEyes())
				eyes++;
		}
		return (eyes == 1 && chows == 4);
	}

	public boolean isAllOneSuit(List<Meld> meldList) {
		int eyes = 0;
		int melds = 0;
		if (meldList.isEmpty())
			return false;
		String suit = meldList.get(0).getTile().getType();
		for (Meld m : meldList) {
			if (!m.getTile().getType().equals(suit)) {
				return false;
			} else {
				suit = m.getTile().getType();
				if (m.isEyes()) {
					eyes++;
				} else {
					melds++;
				}
			}
		}
		return (eyes == 1 && melds == 4);
	}

	public boolean isGreatDragon(List<Meld> meldList) {
		int eyes = 0;
		int dragons = 0;
		int melds = 0;
		for (Meld m : meldList) {
			if (m.isEyes()) {
				eyes++;
			} else if (m.getTile().isDragon()) {
				dragons++;
			} else {
				melds++;
			}
		}
		return (eyes == 1 && dragons == 3 && melds == 1);
	}

	public boolean isAllHonorTiles(List<Meld> meldList) {
		int eyes = 0;
		int melds = 0;
		for (Meld m : meldList) {
			if (!m.getTile().isHonorTile()) {
				return false;
			} else {
				if (m.isEyes()) {
					eyes++;
				} else {
					melds++;
				}
			}
		}
		return (eyes == 1 && melds == 4);
	}

	// adds a given number of random tiles to the current set

	public void addRandomTiles(int n) {
		Random r = new Random();
		char type;
		int number;

		// boolean wasSuccessful = true;
		System.out.println("Adding " + n + " random Tile(s)!");
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
			numberOfTiles--;
			tmpTile.setPlayerIndex(playerIndex);
			return tmpTile;
		}
		return null;
	}

	// public void takeTile(Tile t) {
	// tiles.add(t);
	// }

	public boolean canPong(Tile input) {
		int count = 0;

		for (Tile t : tiles) {
			if (input.getValueIndex() == t.getValueIndex()) {
				count++;
			}
		}
		if (count == 2) {
			return true;
		}
		return false;
	}

	public boolean canKong(Tile input) {
		int count = 0;

		for (Tile t : tiles) {
			if (input.getValueIndex() == t.getValueIndex()) {
				count++;
			}
		}
		if (count == 3) {
			return true;
		}
		return false;
	}

	public boolean canChow(Tile input) {
		Set tmpSet = new Set();
		tmpSet.addTile(input, false, false);

		for (int i = 0; i < numberOfTiles - 1; i++) {
			tmpSet.addTile(tiles.get(i), false, false);
			tmpSet.addTile(tiles.get(i + 1), false, false);
			if (isThreeConsecutive(tmpSet.getTiles())) {
				return true;
			}
		}
		return false;
	}

	public String print() {
		int i = 0;

		String output = "";
		
		for (Tile t : tiles) {
			i++;
			output = output + t.getShorthand();
			if (i < numberOfTiles)
				output = output + ",";
		}
		if (i == 0)
			output = "Currently no thrown tiles.";

		return output;
	}

}