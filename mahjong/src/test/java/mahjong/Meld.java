package mahjong;

public class Meld {
	private String meld;
	private Tile tile;
	public Meld(String meld, Tile tile) throws Exception{
		if(meld.equals("Pong") || meld.equals("Kong") || meld.equals("Chow") || meld.equals("Eyes") ){
			this.meld = meld;
			this.tile = tile;
		}else{
			throw new Exception("No such Meld");
		}
	}
	public boolean isKong(){
		return this.meld.equals("Kong");
	}
	
	public boolean isPong(){
		return this.meld.equals("Pong");
	}
	public boolean isChow(){
		return this.meld.equals("Chow");
	}
	public boolean isEyes(){
		return this.meld.equals("Eyes");
	}
	public Tile getTile(){
		return this.tile;
	}
	public String toString(){
		return meld + " " + tile;
	}
	
}
