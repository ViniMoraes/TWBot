package components;

import database.DB;
import util.Cordinates;

public class Village {

	private int villageID;
	private String villageName;
	private Cordinates cordinates;
	private int playerID;
	private int points;
	private int rank;
	
	public void save() {
		String sql = "INSERT INTO VILLAGE VALUES (" 
				+ villageID + ",'" + villageName + "'," 
				+ cordinates.getX() + "," + cordinates.getY() + "," 
				+ playerID + "," + points + "," + rank + ");";
		DB.executeUpdate(sql);
	}
	
	
	public int getVillageID() {
		return villageID;
	}
	
	public void setVillageID(int villageID) {
		this.villageID = villageID;
	}
	
	public String getVillageName() {
		return villageName;
	}
	
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	
	public Cordinates getCordinates() {
		return cordinates;
	}
	
	public void setCordinates(Cordinates cordinates) {
		this.cordinates = cordinates;
	}
	
	public int getPlayer() {
		return playerID;
	}
	
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
