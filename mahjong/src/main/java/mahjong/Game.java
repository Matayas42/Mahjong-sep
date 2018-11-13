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

class Game {

    private final static int NO_OF_PLAYERS = 4;
    private final static String DATABASE_NAME = "database.txt";
    private static Path database_path;
    private File database;
    private List<Set> sets = new CircularArrayList<Set>();

    public Game(boolean drawSets) {

        setupDatabase();

        if (drawSets) {
            Random r = new Random();
            int houseIndex = r.nextInt(NO_OF_PLAYERS);
            for (int i = 0; i < NO_OF_PLAYERS; i++) {
                Set tmpSet = new Set();
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
    public boolean askStuffHere() {
        return true;
    }

    // TODO input int
    public int getNumberFromUser() {
        return 1;
    }

    public void run() {

        boolean doRun = true;
        int winnerIndex = -1;
        Iterator<Set> it = sets.iterator();
        Set set;
        Tile thrownTile;
        boolean lastThrew = false;

        while (true) {
            set = it.next();
            if (set.isHouse()) {
                set.addRandomTiles(1);
                if (set.isWinningSet()) {
                    doRun = false;
                    winnerIndex = set.getPlayerIndex();
                }
                thrownTile = set.throwTile(getNumberFromUser());
                break;
            }
        }

        while (doRun) {
            set = it.next();
            // want to take the tile?
            if (askStuffHere()) {
                if (set.takeTilePossible(thrownTile)) {
                    set.takeTile(thrownTile);
                    if (set.canTakeNew()) {
                        
                    }
                }
            }
            break;
        }

        System.out.println("Player number " + (winnerIndex + 1) + " is the winner!");

        database.delete();

    }

}