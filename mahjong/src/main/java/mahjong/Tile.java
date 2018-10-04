package mahjong;

import java.util.Arrays;
import java.util.List;

public class Tile {

    public static final List<String> TILE_TYPES = Arrays.asList("Dot", "Bamboo", "Character", "Wind", "Dragon");
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;

    private int valueIndex;
    private String type;
    private int number;

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

    private boolean isInputTileValid() {
        return TILE_TYPES.contains(type) && (number >= MIN_NUMBER) && (number <= MAX_NUMBER);
    }

    private int calcTileIndex() {
        return (TILE_TYPES.indexOf(type) * 9) + number - 1;
    }

    public int getValueIndex() {
        return valueIndex;
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

}