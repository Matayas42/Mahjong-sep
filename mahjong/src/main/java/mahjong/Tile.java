package mahjong;

import java.util.Arrays;
import java.util.List;

public class Tile {

    // constants holding types and number range of existing tiles
    public static final List<String> TILE_TYPES = Arrays.asList("Dot", "Bamboo", "Character", "Wind", "Dragon");
    private static final int MIN_NUMBER_BASICS = 1;
    private static final int MAX_NUMBER_BASICS = 9;
    private static final int MIN_NUMBER_WINDS = 1;
    private static final int MAX_NUMBER_WINDS = 4;
    private static final int MIN_NUMBER_DRAGONS = 1;
    private static final int MAX_NUMBER_DRAGONS = 3;

    private int valueIndex;
    private String type;
    private int number;

    // constructor of Tile object
    // throws custom exception if given tile data is invalid
    public Tile(String inputType, int inputNumber) throws InvalidTileException {
        type = inputType;
        number = inputNumber;
        if (isInputTileValid()) {
            valueIndex = calcTileIndex();
        } else {
            valueIndex = -1;
            throw new InvalidTileException(type, number);
        }
    }

    // checks if given string and int values for type and number are in the preset
    // values from the constants
    private boolean isInputTileValid() {
        switch (type) {
        case "Dot":
        case "Bamboo":
        case "Character":
            return (number >= MIN_NUMBER_BASICS) && (number <= MAX_NUMBER_BASICS);
        case "Wind":
            return (number >= MIN_NUMBER_WINDS) && (number <= MAX_NUMBER_WINDS);
        case "Dragon":
            return (number >= MIN_NUMBER_DRAGONS) && (number <= MAX_NUMBER_DRAGONS);
        default:
            return false;
        }
    }

    // calculates an index for our current tile type of all possible tiles
    // from 0 to 32 (33 distinct tiles)
    private int calcTileIndex() {
        return (TILE_TYPES.indexOf(type) * 9) + number - 1;
    }

    // basic getter for private member variable
    public int getValueIndex() {
        return valueIndex;
    }

    // basic getter for private member variable
    public String getType() {
        return type;
    }

    // basic getter for private member variable
    public int getNumber() {
        return number;
    }

}