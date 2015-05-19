/*
 * Tyler Robbins
 * SaveGame
 * 5/11/15
 * Static methods for saving and loading game data.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class SaveGame{
	private static final byte[] MAGIC = {(byte)0xde,(byte)0xad,(byte)0xbe,(byte)0xef};

	private static final Logger logan = Logger.getInstance();

	public static Player load(){
		try{
			File file = new File(".\\Saves\\player.txt");
			if(!file.exists()){
				System.out.println("No save file found.");
				return null;
			}
			BufferedReader f = new BufferedReader(new FileReader(file));

			logan.log_std("Loading Player data...");
			Player p;
			String name = f.readLine();
			p = new Player(name);
			p.setMoney(Integer.valueOf(f.readLine()).intValue());
			p.setHealth(Integer.valueOf(f.readLine()).intValue());
			f.close();

			logan.log_std("Loading inventory data...");
			Item i;
			ArrayList<Item> inv = new ArrayList<Item>();
			File[] allItems = new File(".\\SAVES\\Inventory").listFiles();
			if(allItems != null){
				for(File fi : allItems){
					f = new BufferedReader(new FileReader(fi));
					i = new Item(f.readLine());
					String line;
					while((line = f.readLine()) != null)
						i.addAttribute(line.substring(0,line.indexOf(":")),Integer.valueOf(line.substring(line.indexOf(":")+1)));
					inv.add(i);
				}
			}
			p.setInventory(inv);

			return p;

		}catch(Exception e){
			System.out.println("Failed to load save file.");
			logan.log_error("Failed to load save file: ");
			logan.log_error(e.getMessage());
		}
		return null;
	}

	public static void save(Player p){
		try{
			// Cannot save in a dungeon
			ArrayList<Item> inv = p.getInventory();

			logan.log_std("Making save directory...");
			// Create save directory
			new File(".\\SAVES\\Inventory").mkdirs();

			logan.log_std("Saving Player data...");
			File f = new File(".\\Saves\\player.txt");
			f.createNewFile();
			PrintWriter player = new PrintWriter(f);
			player.println(p.getName());
			player.println(p.getMoney());
			player.println(p.getHealth());
			player.close();

			PrintWriter item;

			logan.log_std("Saving inventory data...");
			for(int i = 0; i < inv.size(); i++){
				item = new PrintWriter(".\\SAVES\\Inventory\\item"+i+".txt");
				item.println(inv.get(i).getName());
				Map<String,Integer> at = inv.get(i).getAllAttributes();

				for(Map.Entry<String,Integer> e: at.entrySet()){
					item.println(e.getKey() + ":" + e.getValue());
				}
				item.close();
			}
		}
		catch(Exception e){
			System.out.println("Failed to save game.");
			logan.log_error("Failed to save game: ");
			logan.log_error(e.getMessage());
		}
	}

	private static boolean isValidFile(byte[] data){
		for(int i = 0; i < MAGIC.length; i++){
			if(data[i] != MAGIC[i])
				return false;
		}
		return true;
	}

	public static boolean doesSaveFileExist(){
		File file = new File(".\\Saves\\player.txt");
		return file.exists();
	}
}
