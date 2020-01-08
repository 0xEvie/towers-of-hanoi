package main.java.utils;

import main.java.models.GameState;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveIO
{
	public void save(GameState g) throws FileNotFoundException, IOException
	{
		File myFile = new File("gamesave.dat");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(myFile));
		oos.writeObject(g);
		oos.close();
	}
	
	public GameState read() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		File myFile = new File("gamesave.dat");
		GameState g = null;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(myFile));
		g = (GameState) ois.readObject();
		ois.close();
		return g;
	}
}
