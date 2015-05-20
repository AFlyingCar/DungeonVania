
/*Jacob Meserve
*Main
*5/11/15
*The Main file
*/

import java.util.Scanner;
import java.util.ArrayList;


public class DungeonVania{
	public static final long MAGIC = 0x57ddec8c; // The program will explode if this line is removed!

	private static Player player;
	private static Dungeon dungeon;
	private static Shop shop;
	private static Scanner input;
	private static Logger logan = Logger.getInstance();
	public static void main(String[] args){
		try{
			input = new Scanner(System.in);
			if(SaveGame.doesSaveFileExist()){
				System.out.println("A save file exists, would you like to load it? (yes/no)");
				if(yesNo())
					player = SaveGame.load();
				else{
					System.out.println("Name: ");
					String name = input.nextLine();
					player = new Player(name);				
				}
			}
			shop = new Shop(player);
			
			System.out.println(player.getName() + ": starts in the town");
			System.out.println(player.getName() + ": checks their pockets and finds " + player.getMoney() + " gold");
			System.out.println("0. Go to Bed");
			System.out.println("1. Go to the nearby dungeon");
			System.out.println("2. Go to the store");
			System.out.println("3. Check Inventory");
			System.out.println("4. Save Game");
			System.out.print("Choice: ");
			int intPut = input.nextInt();
			getMenu(intPut);
			while(intPut != 0){
				getMenuText();
				intPut = input.nextInt();
				getMenu(intPut);
			}
		}catch(Exception e){
			System.out.println("A catastrophic error has ocurred, and the program must quit.");
			logan.log_error("A catastrophic error has ocurred, and the program must quit.");
			// logan.log_error(e.getCause());
			// logan.log_error(e.getLocalizedMessage());
			// logan.log_error(e.getMessage());
			throw e;
		}
	}

	/*
	Prompts the user for a yes/no answer. Returns true if yes, else returns false
	*/
	public static boolean yesNo(){
		String yn = "";
		while(true){
			yn = input.nextLine();
			switch(yn){
				case "yes":
				 return true;
				case "no":
				 return false;
				default:
				 System.out.println("Yes or no please!");
			}
		}
	}

	/*
	Prints a menu of options.
	*/
	public static void getMenuText(){
		System.out.println("0. Go to Bed");
		System.out.println("1. Go to the nearby dungeon");
		System.out.println("2. Go to the store");
		System.out.println("3. Check Inventory");
		System.out.println("4. Save Game");
		System.out.print("Choice: ");
	}

	public static void getMenu(int choice){
		if(choice == 1){
			goToDungeon();
		}else if(choice == 2){
			System.out.println(player.getName() + ": walks to the nearby store.");
			goToStore();
		}else if(choice == 3){
			// System.out.println("Health: " + player.getHealth()
			// ArrayList<Item> playerInv = player.getInventory();
			// for(int i = 0; i < playerInv.size(); i++)
			// 	System.out.println((i + 1) + ": " + playerInv.get(i).toString());
			System.out.println(player.toString());
		}else if(choice == 4){
			if(SaveGame.doesSaveFileExist()){
				System.out.print("A save file already exists, are you sure you would like to overwrite it? (yes/no) ");
				if(!yesNo())
					return;
			}
			Logger.getInstance().log_std("Saving game...");
			System.out.println("Saving game...");
			SaveGame.save(player);
		}else if(choice == 0)
			endGame();
	}

	public static void endGame(){
		System.exit(0);
	}

	public static void goToDungeon(){
		System.out.println(player.getName() + ": walks to the nearby dungeon.");
		dungeon = new Dungeon();
		ArrayList<Enemy> enemies;
		int choice = -1;
		while(choice != 0 || dungeon.canMoveToNextRoom()){
			enemies = dungeon.getCurrentRoom().getEnemies();
			if(enemies.size()>0)
				System.out.println(player.getName() + ": encounters " + enemies.size() + " enemies");
	
			for(int i = 0; i < enemies.size(); i++){
				System.out.print(enemies.get(i).getName() + " " + (i + 1) + "\'s Health: " + enemies.get(i).getHealth() + "\t");
				System.out.print("Defense: " + enemies.get(i).getDefense() + "\t");
				System.out.println("Damage: " + enemies.get(i).getDamage());
			}

			if(dungeon.canMoveToNextRoom()){
				System.out.println("1. Search room");
				System.out.println("2. Check Inventory");
				System.out.println("3. Use Items");
				System.out.println("4. Move to next room");
				System.out.println("0. Escape");
			}else{
				System.out.println("1. Attack");
				System.out.println("2. Check Inventory");
				System.out.println("3. Use Items");
				System.out.println("4. Move to next room");
				System.out.println("0. Escape");
			}

			System.out.print("Choice: ");
			int intPut = input.nextInt();
			if(intPut == 1){
				if(dungeon.canMoveToNextRoom()){
					player.searchRoom(dungeon.getCurrentRoom());
				}else{
					if(enemies.size() > 1){
						System.out.println("Which enemy would you like to attack");
						for(int i = 0; i < enemies.size(); i++){
							System.out.print("Enemy " + (i+1) + "\t");
						}
						System.out.print("\nChoice: ");
						intPut = input.nextInt()-1;
						int damageTaken = player.damageEnemy(enemies.get(intPut));
						System.out.println(enemies.get(intPut).getName() + " " + (intPut + 1) +  ": took " + damageTaken + " damage");
						if(enemies.get(intPut).isDead())
							System.out.println(enemies.get(intPut).getName() + " " + (intPut + 1) + " died!");
					}else{
						int damageTaken = player.damageEnemy(enemies.get(0));
						System.out.println(enemies.get(0).getName() + " " + 1 + ": took " + damageTaken + " damage");
					}
				}
			}else if(intPut == 2){
				System.out.println(player.toString());
				continue;
			}else if(intPut == 3){
				System.out.println(player.toString());
				System.out.print("How many potions would you like to use: ");
				intPut = input.nextInt();
				for(int i = 0; i < intPut; i++)
					player.usePotion();
			}else if(intPut == 4){
				if(dungeon.moveToNextRoom())
					System.out.println(player.getName() + " moves on from room " + (dungeon.getCRoom()-1) + ", and ventures further into the dungeon.");
				else
					System.out.println("Cannot move to the next room, because there are enemies in the way!");
				continue;
			}else if(intPut == 0){
				int damageAmount = (int)(Math.random()*(enemies.size()))+1;
				System.out.println(player.getName() + " tried to escape, and took " + damageAmount + " damage in the process.");
				player.addHealth(-damageAmount);
				return;
			}else{
				System.out.println("Invalid choice!");
				continue;
			}
			dungeon.Execute(player);

			if(player.isDead()){
				deathMessage(dungeon);
				return;
			}
		}
	}

	public static void goToStore(){
		System.out.println(player.getName() + ": enters the store");
		System.out.println(shop.menu());
	}

	/*
	Prints the death message of the player.
	*/
	public static void deathMessage(Dungeon d){
		System.out.println("After many battles, " + player.getName() + " was finally defeated in room #" + d.getCRoom() + " of the Dugeon " + d.getName() + ".");
		System.out.println(player.getName() + " died while carrying " + player.getMoney() + " gold and " + player.getInventory().get(0).getItemAttribute("AMOUNT") + " potions.");
		System.out.print("Would you like to load a previous save, or quit? (yes/no) ");
		if(yesNo())
			player = SaveGame.load();
		else
			endGame();
	}
}
