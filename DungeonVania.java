
/*Jacob Meserve
*Main
*5/11/15
*The Main file
*/

import java.util.Scanner;
import java.util.ArrayList;


public class DungeonVania{
	public static final long MAGIC = 0x57ddec8c;

	private static Player player;
	private static Dungeon dungeon;
	private static Shop shop;
	private static Scanner input;
	public static void main(String[] args){
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
		System.out.println(player.getName() + ": checks their pockets and finds " + player.getMoney());
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
	}

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
			ArrayList<Item> playerInv = player.getInventory();
			for(int i = 0; i < playerInv.size(); i++)
				System.out.println((i + 1) + ": " + playerInv.get(i).toString());
		}else if(choice == 4){
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
			System.out.println(player.getName() + ": encounters " + enemies.size() + " enemie(s)");
			for(int i = 0; i < enemies.size(); i++){
				System.out.print(enemies.get(i).getName() + " " + (i + 1) + "\'s Health: " + enemies.get(i).getHealth() + "\t");
				System.out.print("Defense: " + enemies.get(i).getDefense() + "\t");
				System.out.println("Damage: " + enemies.get(i).getDamage());
			}
			System.out.println("1. Attack");
			System.out.println("2. Check Inventory");
			System.out.println("3. Use Items");
			System.out.print("Choice: ");
			int intPut = input.nextInt();
			if(intPut == 1){
				if(enemies.size() > 1){
					System.out.println("Which enemy would you like to attack");
					for(int i = 0; i < enemies.size(); i++){
						System.out.print("Enemy " + (i+1) + "\t");
					}
					System.out.print("Choice: ");
					intPut = input.nextInt()-1;
					int damageTaken = player.damageEnemy(enemies.get(intPut));
					System.out.println(enemies.get(intPut).getName() + " " + (intPut + 1) +  ": took " + damageTaken + " damage");
					if(enemies.get(intPut).isDead())
						System.out.println(enemies.get(intPut).getName() + " " + (intPut + 1) + " died!");
				}else{
					int damageTaken = player.damageEnemy(enemies.get(0));
					System.out.println(enemies.get(0).getName() + " " + 1 + ": took " + damageTaken + " damage");
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
			}
			dungeon.Execute(player);
		}
	}

	public static void goToStore(){
		System.out.println(player.getName() + ": enters the store");
		System.out.println(shop.menu());
	}
}
