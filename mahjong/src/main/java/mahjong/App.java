package mahjong;

/**
 * Mahjong main application class
 */
public final class App {

    private App() {
    }

    /**
     * Mahjong main
     * 
     * @param args The arguments of the program. arguments of the program.
     */
    public static void main(String[] args) {

        Set set = new Set();
        if (set.readTiles()) {
            if (set.isWinningSet())
                System.out.println("Congratulations! The entered set is a winning set!");
            else
                System.out.println("Sadly, the entered set is not a winning set.");
        } else
            System.out.println("No valid set read, exiting.");
    }

}
