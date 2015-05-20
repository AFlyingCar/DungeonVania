/*Jacob Meserve
*Shop.java
*5/12/2015
*Used to buy items for the player
*/

import java.util.Scanner;
class Shop{
	
	private Player player;
	private Scanner input;
	

	public Shop(Player newPlayer){
		player = newPlayer;
		input = new Scanner(System.in);
	}

	public String menu(){
		System.out.println(player.getName() + ": enters the shop.");
		System.out.println("Shopkeeper: What do ya need?");
		System.out.println("0. Exit");
		System.out.println("1. Buy Health Potions");
		System.out.println("2. Upgrade Armor");
		System.out.println("3. Upgrade Weapon");
		System.out.println("Choice: ");
		int choice = input.nextInt();
		if(choice == 0)
			return(player.getName() + ": leaves the store");
		else if(choice == 1)
			return buyHealth();
		else if(choice == 2)
			return buyArmor();
		else if(choice == 3)
			return buyDamage();
		else{
			System.out.println(choice + "is not a choice.");
			return menu();
		}

	}

	private String buyHealth(){
		System.out.println("How many would ya like to bye? ");
		System.out.println("Choice: ");
		int choice = input.nextInt();
		int count = 0;
		if(choice > 0){
			for(int i = 0; i < choice; i++){
				if(player.buyPotion())
					count++;
				else
					break;
			}
			return(player.getName() + ": bought " + count + "potion(s)\n" + player.getName() + "'s gold: " + player.getMoney());
		}else{
			System.out.println("You cant bye " + choice + " potions");
			return buyHealth();
		}
	}

	private String buyArmor(){
		System.out.println("How many would ya like to bye? ");
		System.out.println("Choice: ");
		int choice = input.nextInt();
		int count = 0;
		if(choice > 0){
			for(int i = 0; i < choice; i++){
				if(player.buyPotion())
					count++;
				else
					break;
			}
			return(player.getName() + ": bought " + count + "armor upgrade(s)\n" + player.getName() + "'s gold: " + player.getMoney());
		}else{
			System.out.println("You cant bye " + choice + " armor upgrades");
			return buyArmor();
		}
	}

	private String buyDamage(){
		System.out.println("How many would ya like to bye? ");
		System.out.println("Choice: ");
		int choice = input.nextInt();
		int count = 0;
		if(choice > 0){
			for(int i = 0; i < choice; i++){
				if(player.buyPotion())
					count++;
				else
					break;
			}
			return(player.getName() + ": bought " + count + "weapon upgrades(s)\n" + player.getName() + "'s gold: " + player.getMoney());
		}else{
			System.out.println("You cant bye " + choice + " weapon upgrades");
			return buyDamage();
		}
	}
}