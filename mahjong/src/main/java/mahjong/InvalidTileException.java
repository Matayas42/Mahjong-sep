package mahjong;

import java.lang.Exception;

public class InvalidTileException extends Exception {

    private static final long serialVersionUID = -6778428563472722894L;

    public InvalidTileException(String type, int number) {
        super("The current tile with type " + type + " and number " + number + " is either not a valid tile in mahjong or has been added too often.");
    }

} 