
/*Jacob Meserve
*Main
*5/11/15
*The Main file
*/
import java.util.Scanner;
import java.util.ArrayList;


class DungeonVania{
	private static Player player;
	private static Dungeon dungeon;
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Name: ");
		String name = input.nextLine();
		player = new Player(name);
		
		System.out.println(player.getName() + ": starts in the town");
		System.out.println(player.getName() + ": checks their pockets and finds " + player.getMoney());
		System.out.println("0. Go to Bed");
		System.out.println("1. Go to the nearby dungeon");
		System.out.println("2. Go to the store");
		System.out.println("3. Check Inventory");
		System.out.println("Choice: ");
		int intPut = input.nextInt();
		getMenu(intPut);
		while(intPut != 0){
			getMenuText();
			intPut = input.nextInt();
			getMenu(intPut);
		}
	}

	public static void getMenuText(){
		System.out.println("0. Go to Bed");
		System.out.println("1. Go to the nearby dungeon");
		System.out.println("2. Go to the store");
		System.out.println("3. Check Inventory");
		System.out.println("Choice: ");
	}
	
	public static void getMenu(int choice){
		if(choice == 1){
			goToDungeon();
		}else if(choice == 2)
			System.out.println(player.getName() + ": walks to the nearby store");
		else if(choice == 3){
			ArrayList<Item> playerInv = player.getInventory();
			for(int i = 0; i < playerInv.size(); i++)
				System.out.println((i + 1) + ": " + playerInv.get(i).toString());
		}else if(choice == 0)
			endGame();
	}

	public static void endGame(){
		System.exit(0);
	}

	public static goToDungeon(){
		dungeon = new Dungeon();
		choice = -1;
		while(choice != 0 || dungeon.getCurrentRoom().getEnemy(0).isDead())
	}
}
