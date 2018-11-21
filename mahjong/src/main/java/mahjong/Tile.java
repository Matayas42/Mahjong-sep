package mahjong;

import java.util.Arrays;
import java.util.List;

public class Tile {

    // constants holding types and number range of existing tiles
    public static final List<String> TILE_TYPES = Arrays.asList("Dot", "Bamboo", "Character", "Wind", "Dragon");
    public static final List<Character> TILE_SHORTHAND = Arrays.asList('D', 'B', 'C', 'W', 'G');
    private static final int MIN_NUMBER_BASICS = 1;
    private static final int MAX_NUMBER_BASICS = 9;
    private static final int MIN_NUMBER_WINDS = 1;
    private static final int MAX_NUMBER_WINDS = 4;
    private static final int MIN_NUMBER_DRAGONS = 1;
    private static final int MAX_NUMBER_DRAGONS = 3;

    private int valueIndex;
    private String type;
    private int number;
    
    public static void main(String[] args) {
		try {
			Tile t = new Tile('G', 1);
			System.out.println(t.getNumber());
		} catch (InvalidTileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    

    // constructor of Tile object
    // throws custom exception if given tile data is invalid
    public Tile(char inputType, int inputNumber) throws InvalidTileException {
        number = inputNumber;
        if (isInputTileValid(inputType)) {
            valueIndex = calcTileIndex();
        } else {
            valueIndex = -1;
            throw new InvalidTileException(type, number);
        }
    }

    // checks if given string and int values for type and number are in the preset
    // values from the constants
    private boolean isInputTileValid(char inputType) {
        switch (inputType) {
        case 'D':
            type = "Dot";
            return (number >= MIN_NUMBER_BASICS) && (number <= MAX_NUMBER_BASICS);
        case 'B':
            type = "Bamboo";
            return (number >= MIN_NUMBER_BASICS) && (number <= MAX_NUMBER_BASICS);
        case 'C':
            type = "Character";
            return (number >= MIN_NUMBER_BASICS) && (number <= MAX_NUMBER_BASICS);
        case 'W':
            type = "Wind";
            return (number >= MIN_NUMBER_WINDS) && (number <= MAX_NUMBER_WINDS);
        case 'G':
            type = "Dragon";
            return (number >= MIN_NUMBER_DRAGONS) && (number <= MAX_NUMBER_DRAGONS);
        default:
            return false;
        }
    }

    public static int getNumberRange(char inputType) {
        switch (inputType) {
        case 'D':
        case 'B':
        case 'C':
            return MAX_NUMBER_BASICS;
        case 'W':
            return MAX_NUMBER_WINDS;
        case 'G':
            return MAX_NUMBER_DRAGONS;
        default:
            return -1;
        }
    }

    /*public int getNumberRange() {
        char type = getShorthand().charAt(0);
        switch (type) {
        case 'D':
        case 'B':
        case 'C':
            return MIN_NUMBER_BASICS;
        case 'W':
            return MIN_NUMBER_WINDS;
        case 'G':
            return MIN_NUMBER_DRAGONS;
        default:
            return -1;
        }
    }*/

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
	
	public boolean isHonorTile(){
    	if(isDragon() || isWind()){
    		return true;
    	}else {
    		return false;
    	}
    }
	
	public boolean isDragon(){
    	if(type.equals("Dragon")){
    		return true;
    	}else {
    		return false;
    	}
    }
	
	public boolean isWind(){
    	if(type.equals("Wind")){
    		return true;
    	}else {
    		return false;
    	}
    }
	
	public boolean equals(Tile t){
		return (this.getNumber() == t.getNumber() && this.getType().equals(t.getType()));
	}

    // returns the shorthand for a tile like G1
    public String getShorthand() {
        if (type != "Dragon")
            return "" + type.charAt(0) + number;
        else
            return "G" + number;
    }


	@Override
	public String toString() {
		return "" + type + number;
	}
    
    



}