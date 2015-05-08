import java.util.Scanner;
class DungeonVania{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Name: ");
		String name = input.nextLine();
		Player player = new Player(name);
		System.out.println(player.getName() + ": starts in the town");
		System.out.println(player.getName() + ": checks their pockets and finds " + player.getMoney());
		System.out.println("0. Go to Bed");
		System.out.println("1. Go to the nearby dungeon");
		System.out.println("2. Go to the store");
		System.out.println("3. Check Inventory");


		
	}

	public static void getMenu(){


	}
}