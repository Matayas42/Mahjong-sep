package mahjong;

import java.lang.Exception;

public class InvalidTileException extends Exception {

    private static final long serialVersionUID = -6778428563472722894L;

    // constructor for custom exception if the input tile is not a part of the
    // normal mahjong game set
    public InvalidTileException(String type, int number) {
        super("The current tile with type " + type + " and number " + number
                + " is either not a valid tile in mahjong or has been added too often.");
    }

}