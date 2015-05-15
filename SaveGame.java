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

	public static State load(){
		try{
			File file = new File(".\\Saves\\player.txt");
			if(!file.exists()){
				System.out.println("No save file found.");
				return null;
			}
			BufferedReader f = new BufferedReader(new FileReader(file));

			Player p;
			String name = f.readLine();
			p = new Player(name);
			p.setMoney(Integer.valueOf(f.readLine()).intValue());
			p.setHealth(Integer.valueOf(f.readLine()).intValue());
			f.close();

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

			return new State(p);

		}catch(Exception e){
			System.out.println("Failed to load save file.");
		}
		return null;
	}

	public static void save(State s){
		try{
			// Cannot save in a dungeon
			Player p = s.getPlayer();

			ArrayList<Item> inv = p.getInventory();

			// Create save directory
			new File(".\\SAVES\\Inventory").mkdirs();
			File f = new File(".\\Saves\\player.txt");
			f.createNewFile();
			PrintWriter player = new PrintWriter(f);
			player.println(p.getName());
			player.println(p.getMoney());
			player.println(p.getHealth());
			player.close();

			PrintWriter item;

			for(int i = 0; i < inv.size(); i++){
				item = new PrintWriter(".\\SAVES\\Inventory\\item"+i+".txt");
				item.println(inv.get(i).getName());
				Map<String,Object> at = inv.get(i).getAllAttributes();

				for(Map.Entry<String,Object> e: at.entrySet()){
					item.println(e.getKey() + ":" + e.getValue());
				}
				item.close();
			}
		}
		catch(Exception e){
			System.out.println("Failed to save game.");
			System.out.println(e.getMessage());
		}
	}

	private static boolean isValidFile(byte[] data){
		for(int i = 0; i < MAGIC.length; i++){
			if(data[i] != MAGIC[i])
				return false;
		}
		return true;
	}

	//TODO: Scan current directory for save file
	private static boolean doesSaveFileExist(){
		return false;
	}
}