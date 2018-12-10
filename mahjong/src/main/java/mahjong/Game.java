package mahjong;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Game extends Thread {

	private final static int NO_OF_PLAYERS = 4;
	private final static String DATABASE_NAME = "database.txt";
	private static Path database_path;
	private File database;
	private List<Set> sets = new ArrayList<Set>();
	private boolean isTestCase = false;
	private boolean inputNeeded = false;
	private BlockingQueue<String> outputStrings;
	private BlockingQueue<Integer> outputUsers;
	private boolean newInput = false;
	private BlockingQueue<String> inputString;

	public boolean isInputNeeded() {
		return inputNeeded;
	}

	public void setNewInput() {
		newInput = true;
	}

	public boolean isNewInput() {
		return newInput;
	}

	public boolean isInputStringEmpty() {
		return inputString.isEmpty();
	}

	public String getOutputString() {
		return outputStrings.poll();
	}

	public Integer getInteractUserId() {
		return outputUsers.poll().intValue();
	}

	public boolean isOutputNeeded() {
		return !outputStrings.isEmpty() && !outputUsers.isEmpty();
	}

	public void setInputString(String input) {
		try {
			inputString.put(input);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Game(boolean drawSets) {

		setupDatabase();

		if (drawSets) {
			outputStrings = new LinkedBlockingQueue<String>();
			outputUsers = new LinkedBlockingQueue<Integer>();
			inputString = new SynchronousQueue<String>();
			System.out.println("Setup:");
			Random r = new Random();

			int houseIndex = r.nextInt(NO_OF_PLAYERS) + 1;
			isTestCase = false;
			// askStuffHere("Test scenario? y/n");
			if (!isTestCase) {
				for (int i = 1; i <= NO_OF_PLAYERS; i++) {
					Set tmpSet = new Set(i);
					tmpSet.addRandomTiles(13);
					if (i == houseIndex)
						tmpSet.setHouse();
					sets.add(tmpSet);
				}
			} else {
				if (houseIndex == 1) {
					houseIndex = 1;
				}
				Set tmpSet = new Set(1);
				tmpSet.fillSetFromString("D1,D1,D1,C2,C3,C4,B5,B5,B5,W1,W2,W3,G1");
				if (1 == houseIndex)
					tmpSet.setHouse();
				sets.add(tmpSet);
				for (int i = 2; i <= 4; i++) {
					tmpSet = new Set(i);
					tmpSet.addRandomTiles(13);
					if (i == houseIndex)
						tmpSet.setHouse();
					sets.add(tmpSet);
				}

			}
		}

	}

	public static Path getDatabasePath() {
		return database_path;
	}

	public boolean setupDatabase() {

		String path = System.getProperty("user.dir");
		String fileSeparator = System.getProperty("file.separator");
		path = path + fileSeparator + DATABASE_NAME;
		database_path = Paths.get(path);
		database = new File(database_path.toString());
		List<String> output = new ArrayList<String>();

		try {
			if (database.createNewFile()) {
				System.out.println(path + " File Created");
			} else
				System.out.println("File " + path + " already exists");

			for (int i = 0; i < Tile.TILE_SHORTHAND.size(); i++) {
				char currentType = Tile.TILE_SHORTHAND.get(i);
				for (int j = 1; j <= Tile.getNumberRange(currentType); j++)
					output.add("" + currentType + j + ":0");
			}
			Files.write(database_path, output, StandardCharsets.UTF_8);

		} catch (IOException ex) {
			return false;
		}

		return true;
	}

	private void addOutputString(String output, int userId) {
		outputStrings.add(output);
		outputUsers.add(userId);
	}

	private void printSets() {
		for (Set s : sets) {
			addOutputString("Your current set:", s.getPlayerIndex());
			addOutputString(" 1  2  3  4  5  6  7  8  9 10 11 12 13 14", s.getPlayerIndex());
			addOutputString(s.print(), s.getPlayerIndex());
		}
	}

	// get Input String from Server
	public boolean askStuffHere(String question, int userId) {

		addOutputString(question, userId);
		inputNeeded = true;
		boolean validInput = false;
		String tmpInput = "";

		while (!validInput) {
			// if (newInput) {
			try {
				tmpInput = inputString.take();
				if (tmpInput.equals("y") || tmpInput.equals("n")) {
					validInput = true;
					inputNeeded = false;
				} else {
					addOutputString("Invalid input.", userId);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// newInput = !inputString.isEmpty();;
			// }
		}

		if (tmpInput.equals("y"))
			return true;
		else
			return false;
	}

	// get Input int from Server
	public int getTileNumberFromUser(int maxInput, int userId) {

		addOutputString("Which tile do you want to throw? Please enter number from 1 to " + maxInput + ".", userId);
		inputNeeded = true;
		int tmpInt = -1;

		while (true) {
			// if (newInput) {
			try {
				tmpInt = Integer.parseInt(inputString.take());
				inputNeeded = false;
				if (tmpInt > 0 && tmpInt <= maxInput) {
					newInput = false;
					return tmpInt;
				}
			} catch (Exception ex) {
				// new try
			}
			addOutputString("Invalid input!", userId);
			// }
			inputNeeded = true;
			// newInput = !inputString.isEmpty();
		}
	}

	private void printCurrentSet(Set set) {

		addOutputString("Your current set:", set.getPlayerIndex());
		addOutputString(" 1  2  3  4  5  6  7  8  9 10 11 12 13 14", set.getPlayerIndex());
		addOutputString(set.print(), set.getPlayerIndex());
	}

	private void removeThrownTile(List<Tile> thrown, Tile tile) {
		String tmp = "";
		try {
			List<String> fileContent = new ArrayList<String>(Files.readAllLines(database_path, StandardCharsets.UTF_8));

			for (int i = 0; i < fileContent.size(); i++) {
				tmp = fileContent.get(i);
				if (tmp.contains(tile.getShorthand())) {
					tmp = tmp.substring(0, 3) + (Character.getNumericValue(tmp.charAt(3)) - 1);
					fileContent.set(i, tmp);
				}
			}
			Files.write(database_path, fileContent, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("Database was not initiated correctly!!");
		}
		thrown.remove(tile);
	}

	private void printThrownTiles(List<Tile> tTiles) {

		int i = 0;
		String output = "";
		addOutputString("Thrown tiles: ", -1);
		for (Tile t : tTiles) {
			i++;
			output = output + t.getShorthand();
			if (i < tTiles.size())
				output = output + ",";
		}
		addOutputString(output, -1);
	}

	// GAMELOOP
	public void run() {

		boolean doRun = true;
		int winnerIndex = -1;
		Iterator<Set> it = sets.iterator();
		Set set = it.next();
		List<Tile> thrownTiles = new ArrayList<Tile>();
		// int rCount = 1;
		boolean pkc = false;
		boolean wasKong = false;
		int i = 0;
		int points = 0;

		// FIRST ROUND: HOUSE TAKES ONE THROWS ONE
		while (!set.isHouse())
			set = it.next();

		addOutputString("-------------------------------------------------------", -1);
		addOutputString("Player " + set.getPlayerIndex() + " is the House, they begin.", -1);

		set.addRandomTiles(1);
		printSets();
		points = set.computeFanPoints();
		if (points != 0) {
			addOutputString("The house got dealt a winning set!", -1);
			doRun = false;
			winnerIndex = set.getPlayerIndex();
		} else {
			thrownTiles.add(set.throwTile(getTileNumberFromUser(set.getNumberOfTiles(), set.getPlayerIndex()) - 1));
			printCurrentSet(set);
			addOutputString("-------------------------------------------------------", -1);
			printThrownTiles(thrownTiles);

			// SECOND ROUND TIL INFINIY
			while (doRun) {
				if (!wasKong) {
					if (!it.hasNext())
						it = sets.iterator();
					set = it.next();
				} else
					wasKong = false;
				// rCount++;
				addOutputString("-------------------------------------------------------", -1);
				addOutputString("It is player " + set.getPlayerIndex() + "'s turn!", -1);
				printCurrentSet(set);
				i = 0;

				// want to take the tile?
				if (!thrownTiles.isEmpty()) {
					for (Tile t : new ArrayList<Tile>(thrownTiles)) {
						if (t.getPlayerIndex() == set.getPlayerIndex())
							removeThrownTile(thrownTiles, t);
						else {
							if (set.canPong(t))
								if (askStuffHere("Do you want to Pong tile " + t.getShorthand() + "? (y/n)",
										set.getPlayerIndex())) {
									set.addTile(t, false, false);
									// thrownTiles.remove(t);
									printCurrentSet(set);
									pkc = true;
									break;
								}
							if (set.canKong(t))
								if (askStuffHere("Do you want to Kong tile " + t.getShorthand() + "? (y/n)",
										set.getPlayerIndex())) {
									set.addTile(t, false, false);
									// thrownTiles.remove(t);
									printCurrentSet(set);
									pkc = true;
									wasKong = true;
									break;
								}
							i++;
						}
					}
					if (!pkc) {
						Tile t = thrownTiles.get(thrownTiles.size() - 1);
						if (set.canChow(t)) {
							if (askStuffHere("Do you want to Chow tile " + t.getShorthand() + "? (y/n)",
									set.getPlayerIndex())) {
								set.addTile(t, false, false);
								// thrownTiles.remove(t);
								printCurrentSet(set);
								pkc = true;
							}
						}
					} else {
						thrownTiles.remove(i);
					}

				}
				if (!pkc) {
					if (isTestCase && set.getPlayerIndex() == 0) {
						try {
							Tile test = new Tile('G', 1);
							set.addTile(test, false, false);
							printCurrentSet(set);
						} catch (Exception e) {
							// unimportant, testcase
						}

					} else {
						addOutputString("No regular moves, adding random tile!", set.getPlayerIndex());
						set.addRandomTiles(1);
						printCurrentSet(set);
					}
				} else
					pkc = false;

				points = set.computeFanPoints();
				if (points != 0) {

					winnerIndex = set.getPlayerIndex();
					break;
				}
				thrownTiles.add(set.throwTile(getTileNumberFromUser(set.getNumberOfTiles(), set.getPlayerIndex()) - 1));
				addOutputString("-------------------------------------------------------", -1);
				printThrownTiles(thrownTiles);
			}
		}
		addOutputString("-------------------------------------------------------", -1);
		addOutputString("Player number " + winnerIndex + " is the winner!! Points: " + points, -1);
		addOutputString("-------------------------------------------------------", -1);
		database.delete();

	}

}