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

public class Game {

    private final static int NO_OF_PLAYERS = 4;
    private final static String DATABASE_NAME = "database.txt";
    private static Path database_path;
    private File database;
    private List<Set> sets = new ArrayList<Set>();
    private Scanner sc = new Scanner(System.in);
    boolean isTestCase = false;

    public Game(boolean drawSets) {

        setupDatabase();

        if (drawSets) {
            System.out.println("Setup:");
            Random r = new Random();
            int houseIndex = r.nextInt(NO_OF_PLAYERS);
            isTestCase = askStuffHere("Test scenario? y/n");
            if (!isTestCase) {
                for (int i = 0; i < NO_OF_PLAYERS; i++) {
                    Set tmpSet = new Set(i);
                    tmpSet.addRandomTiles(13);
                    if (i == houseIndex)
                        tmpSet.setHouse();
                    sets.add(tmpSet);
                }
            } else {
                if (houseIndex == 0) {
                    houseIndex = 1;
                }
                Set tmpSet = new Set(0);
                tmpSet.fillSetFromString("D1,D1,D1,C2,C3,C4,B5,B5,B5,W1,W2,W3,G1");
                if (0 == houseIndex)
                    tmpSet.setHouse();
                sets.add(tmpSet);
                for (int i = 1; i < 4; i++) {
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
        boolean validInput = false;
        String tmp = "";

        while (!validInput) {
            tmp = sc.next();
            if (tmp.equals("y") || tmp.equals("n")) {
                validInput = true;
            } else {
                System.out.println("Invalid input.");
            }
        }
        if (tmp.equals("y"))
            return true;
        else
            return false;
    }

    // TODO input int
    public int getTileNumberFromUser(int maxInput) {

        System.out.println("Which tile do you want to throw? Please enter number from 1 to " + maxInput + ".");
        int tmp = sc.nextInt();
        while (true) {
            if (tmp > 0 && tmp <= maxInput)
                return tmp;
            else {
                System.out.println("Invalid input!");
                tmp = sc.nextInt();
            }
        }
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

    private void printThrownTiles(List<Tile> tTiles) {

        int i = 0;
        System.out.print("Thrown tiles: ");
        for (Tile t : tTiles) {
            i++;
            System.out.print(t.getShorthand());
            if (i < tTiles.size())
                System.out.print(",");
        }
        System.out.println();
    }

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

        System.out.println("-------------------------------------------------------");
        System.out.println("Player " + (set.getPlayerIndex() + 1) + " is the House. It is your turn Player "
                + (set.getPlayerIndex() + 1) + ".");

        set.addRandomTiles(1);
        printCurrentSet(set);

        if (set.isWinningSet()) {
            System.out.println("The house got dealt a winning set!");
            doRun = false;
            winnerIndex = set.getPlayerIndex();
        } else {

            thrownTiles.add(set.throwTile(getTileNumberFromUser(set.getNumberOfTiles()) - 1));
            printCurrentSet(set);
            System.out.println("-------------------------------------------------------");
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
                System.out.println("-------------------------------------------------------");
                System.out.println("It is player " + (set.getPlayerIndex() + 1) + "'s turn!");
                printCurrentSet(set);
                i = 0;

                // want to take the tile?
                if (!thrownTiles.isEmpty()) {
                    for (Tile t : new ArrayList<Tile>(thrownTiles)) {
                        if (t.getPlayerIndex() == set.getPlayerIndex())
                            removeThrownTile(thrownTiles, t);
                        else {
                            if (set.canPong(t))
                                if (askStuffHere("Do you want to Pong tile " + t.getShorthand() + "? (y/n)")) {
                                    set.addTile(t, false, false);
                                    // thrownTiles.remove(t);
                                    printCurrentSet(set);
                                    pkc = true;
                                    break;
                                }
                            if (set.canKong(t))
                                if (askStuffHere("Do you want to Kong tile " + t.getShorthand() + "? (y/n)")) {
                                    set.addTile(t, false, false);
                                    // thrownTiles.remove(t);
                                    printCurrentSet(set);
                                    pkc = true;
                                    wasKong = true;
                                    break;
                                }
                        }
                        i++;
                    }
                    if (!pkc) {
                        Tile t = thrownTiles.get(thrownTiles.size() - 1);
                        if (set.canChow(t)) {
                            if (askStuffHere("Do you want to Chow tile " + t.getShorthand() + "? (y/n)")) {
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
                            // TODO: handle exception
                        }

                    } else {
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
                thrownTiles.add(set.throwTile(getTileNumberFromUser(set.getNumberOfTiles()) - 1));
                System.out.println("-------------------------------------------------------");
                printThrownTiles(thrownTiles);
            }
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("Player number " + (winnerIndex + 1) + " is the winner!! Points: " + points);
        System.out.println("-------------------------------------------------------");
        sc.close();
        database.delete();

    }

}