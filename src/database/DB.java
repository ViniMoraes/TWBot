package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB {

	static {
		Connection c = null;
		Statement stmt = null;

		File databaseFile = new File("tw.db");
		if (!databaseFile.exists()) {

			System.out.println("Criando um novo banco de dados...");

			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:tw.db");
				System.out.println("Opened database successfully");

				stmt = c.createStatement();
				String sql = "CREATE TABLE VILLAGE " + "(ID INT PRIMARY KEY     NOT NULL,"
						+ " NAME           TEXT    NOT NULL, " + " X              INT     NOT NULL, "
						+ " Y              INT     NOT NULL, " + " PLAYER_ID      INT     NOT NULL, "
						+ " POINTS         INT     NOT NULL, " + " RANK           INT     NOT NULL)";
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
				System.out.println("Table created successfully");
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}

	public static void executeUpdate(String sql) {
		try (Connection c = DriverManager.getConnection("jdbc:sqlite:tw.db");
				Statement stmt = c.createStatement()) {
			
			stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

}
