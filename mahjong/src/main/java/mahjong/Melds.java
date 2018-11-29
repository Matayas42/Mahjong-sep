package mahjong;

import java.util.ArrayList;
import java.util.List;

public class Melds {
	private ArrayList<Meld> melds; 
	private ArrayList<Tile> restTiles;
	
	public Melds(List<Meld> melds, List<Tile> list){
		this.melds = new ArrayList<Meld>();
		this.melds.addAll(melds);
		this.restTiles = new ArrayList<>();
		restTiles.addAll(list);
	}
	public Melds(List<Tile> list){
		this.melds = new ArrayList<Meld>();
		this.melds.addAll(melds);
		this.restTiles = new ArrayList<>();
		restTiles.addAll(list);
	}
	
	public void addMeld(Meld m){
		melds.add(m);
	}
	public Tile removeTile(){
		if(!restTiles.isEmpty())return restTiles.remove(0);
		return null;
	}
	
	public ArrayList<Meld> getMelds() {
		return melds;
	}



	public ArrayList<Tile> getRestTiles() {
		return restTiles;
	}



	
}
