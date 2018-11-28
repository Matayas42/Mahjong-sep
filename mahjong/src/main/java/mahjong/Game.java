package mahjong;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Game {

    private final static int NO_OF_PLAYERS = 4;
    private final static String DATABASE_NAME = "database.txt";
    private static Path database_path;
    private File database;
    private List<Set> sets = new ArrayList<Set>();
    private Scanner sc = new Scanner(System.in);

    public Game(boolean drawSets) {

        setupDatabase();

        if (drawSets) {
            Random r = new Random();
            int houseIndex = r.nextInt(NO_OF_PLAYERS);
            for (int i = 0; i < NO_OF_PLAYERS; i++) {
                Set tmpSet = new Set(i);
                tmpSet.addRandomTiles(13);
                if (i == houseIndex)
                    tmpSet.setHouse();
                sets.add(tmpSet);
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

    public void printSets() {

        for (Set s : sets) {
            for (Tile t : s.getTiles())
                System.out.print(t.getShorthand() + ",");
            System.out.println("");
        }
    }

    // TODO were going to need user input here
    public boolean askStuffHere(String question) {
        System.out.println(question);
        String tmp = sc.nextLine();
        if (tmp.equals("y"))
            return true;
        else
            return false;
    }

    // TODO input int
    public int getNumberFromUser(String question) {
        System.out.println(question);
        int tmp = sc.nextInt();
        return tmp;
    }

    private void printCurrentSet(Set set) {
        System.out.println("Your current set:");
        System.out.println(" 1  2  3  4  5  6  7  8  9 10 11 12 13 14");
        set.print();
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

    public void run() {

        boolean doRun = true;
        int winnerIndex = -1;
        Iterator<Set> it = sets.iterator();
        Set set = it.next();
        List<Tile> thrownTiles = new ArrayList<Tile>();
        //int rCount = 1;
        boolean pkc = false;

        // FIRST ROUND: HOUSE TAKES ONE THROWS ONE
        while (!set.isHouse())
            set = it.next();

        System.out.println("Player " + (set.getPlayerIndex() + 1) + " is the House.");

        set.addRandomTiles(1);
        printCurrentSet(set);

        if (set.isWinningSet()) {
            System.out.println("The house got dealt a winning set!");
            doRun = false;
            winnerIndex = set.getPlayerIndex();
        } else {

            thrownTiles.add(set.throwTile(getNumberFromUser(
                    "Which tile do you want to throw? Please enter number from 1 to " + set.getNumberOfTiles() + ".")
                    - 1));
            printCurrentSet(set);

            // SECOND ROUND TIL INFINIY
            while (doRun) {
                if (!it.hasNext())
                    it = sets.iterator();
                set = it.next();
                //rCount++;
                System.out.println("It is player " + (set.getPlayerIndex() + 1) + "'s turn!");
                printCurrentSet(set);
                // want to take the tile?
                if (!thrownTiles.isEmpty()) {

                    for (Tile t : thrownTiles) {
                        if (t.getPlayerIndex() == set.getPlayerIndex())
                            removeThrownTile(thrownTiles, t);
                        else {
                            if (set.canPong(t))
                                if (askStuffHere("Do you want to Pong tile " + t.getShorthand() + "? (y/n)")) {
                                    set.addTile(t, false);
                                    thrownTiles.remove(t);
                                    printCurrentSet(set);
                                    thrownTiles.add(set.throwTile(getNumberFromUser(
                                            "Which tile do you want to throw? Please enter number from 1 to "
                                                    + set.getNumberOfTiles() + ".")
                                            - 1));
                                    pkc = true;
                                    break;
                                }
                            if (set.canKong(t))
                                if (askStuffHere("Do you want to Kong tile " + t.getShorthand() + "? (y/n)")) {
                                    set.addTile(t, false);
                                    thrownTiles.remove(t);
                                    set.addRandomTiles(1);
                                    printCurrentSet(set);
                                    thrownTiles.add(set.throwTile(getNumberFromUser(
                                            "Which tile do you want to throw? Please enter number from 1 to "
                                                    + set.getNumberOfTiles() + ".")
                                            - 1));
                                    pkc = true;
                                    break;
                                }
                        }
                    }
                    Tile t = thrownTiles.get(thrownTiles.size() - 1);
                    if (set.canChow(t)) {
                        if (askStuffHere("Do you want to Chow tile " + t.getShorthand() + "? (y/n)")) {
                            set.addTile(t, false);
                            thrownTiles.remove(t);
                            printCurrentSet(set);
                            thrownTiles.add(set.throwTile(
                                    getNumberFromUser("Which tile do you want to throw? Please enter number from 1 to "
                                            + set.getNumberOfTiles() + ".") - 1));
                            pkc = true;
                        }
                    }

                }
                if (!pkc) {
                    set.addRandomTiles(1);
                    printCurrentSet(set);
                    thrownTiles.add(set.throwTile(getNumberFromUser("Which tile do you want to throw? Please enter number from 1 to "
                            + set.getNumberOfTiles() + ".") - 1));
                } else
                    pkc = false;

                if (set.isWinningSet()) {
                    winnerIndex = set.getPlayerIndex();
                    doRun = false;
                }
            }
        }

        System.out.println("Player number " + (winnerIndex + 1) + " is the winner!");

        sc.close();
        database.delete();

    }

}