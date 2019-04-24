package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import components.Player;
import components.Village;

public class WorldData {

	private String worldUrl;
	
	public WorldData(String worldUrl) {
		this.worldUrl = worldUrl;
	}
	
	public void fillVillageTable() throws IOException {
		//TODO - download automático
		File villageFile = new File("village.txt");
		FileInputStream inStream = new FileInputStream(villageFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
		
		int villageID;
		String villageName;
		int x;
		int y;
		int playerID;
		int points;
		int rank;
		
		System.out.println("Salvando aldeias no banco de dados...");
		
		String txtLine = reader.readLine();
		while (txtLine != null) {
			
			String[] data = txtLine.split(",");
			
			villageID = Integer.parseInt(data[0]);
			villageName = data[1];
			x = Integer.parseInt(data[2]);
			y = Integer.parseInt(data[3]);
			playerID = Integer.parseInt(data[4]);
			points = Integer.parseInt(data[5]);
			rank = Integer.parseInt(data[6]);
			
			/*
			System.out.println("Village ID: " + villageID);
			System.out.println("Village Name: " + villageName);
			System.out.println("X: " + x);
			System.out.println("Y: " + y);
			System.out.println("Player ID: " + playerID);
			System.out.println("Points: " + points);
			System.out.println("Rank: " + rank);
			*/
			
			Village village = new Village();		
			village.setVillageID(villageID);
			village.setVillageName(villageName);
			village.setCordinates(new Cordinates(x, y));	
			village.setPlayerID(playerID);
			village.setPoints(points);
			village.setRank(rank);
			village.save();
			
			txtLine = reader.readLine();
		}
		
		System.out.println("Aldeias salvas com sucesso!");
		
		reader.close();
	}
	
}
