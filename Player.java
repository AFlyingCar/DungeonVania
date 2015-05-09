/*
 * Tyler Robbins
 * Player
 * 5/8/15
 * Controls the Player.
 */

import java.util.ArrayList;

public class Player{
	private ArrayList<Item> inventory;
	private final String name;
	private int money;
	private int health;

	public Player(String newName){
		inventory = new ArrayList<Item>();
		name = newName;
		health = 100;
	}

	/*
	Returns the player's name.
	PreCondition: name has been initialized.
	*/
	public String getName(){
		return name;
	}

	/*
	Return entire inventory.
	PreCondition: inventory has been initialized
	*/
	public ArrayList<Item> getInventory(){
		return inventory;
	}

	/*
	Replaces the Item at index with element, and returns the replaced item.
	PreCondition: inventory has been initialized, 0 <= index < inventory.size(), element is not null
	PostCondition: the Item at index has been replaced by element
	*/
	public Item setInventoryItem(int index, Item element){
		return inventory.set(index,element);
	}

	/*
	Returns the amount of money the player has.
	PreCondition: money has been initialized.
	*/
	public int getMoney(){
		return money;
	}

	/*
	Sets the amount of money the player has.
	*/
	public int setMoney(int emoe){
		money = emoe;
	}

	/*
	Adds the value of emone to money.
	*/
	public void addMoney(int emone){
		setMoney(emone + getMoney());
	}

	/*
	Returns the player's current health.
	PreCondition: health has been initialized.
	*/
	public int getHealth(){
		return health;
	}

	/*
	Sets the player's health to newHealth.
	*/
	public void setHealth(int newHealth){
		health = newHealth;
	}

	/*
	Adds newHealth to player's health.
	*/
	public void addHealth(int newHealth){
		health += newHealth;
	}

	/*
	Causes damage to enemy based on the player's current weapon.
	PreCondition: enemy is not null, inventory.get(0) is not null
	PostCondition: enemy has taken a random amount of damage from the weapon's MIN_DAMAGE to the weapon's MAX_DAMAGE
	*/
	public int damageEnemy(Enemy enemy){
		Item weapon = inventory.get(0);
		int minDamage = (Integer)(weapon.getItemAttribute("MIN_DAMAGE")).intValue();
		int maxDamage = (Integer)(weapon.getItemAttribute("MAX_DAMAGE")).intValue();
		int damageAmount = -(int)(Math.random() * (maxDamage - minDamage + 1)) - 1;

		enemy.addHealth(damageAmount);
		return damageAmount;
	}
	/*
	Uses up a potion from the player's inventory, so long as the amount of potions is > 0
	PreCondition: inventory is not null, inventory.get(2) is not null
	PostCondition: inventory->potion->AMOUNT has 1 less than it did at the start of the method
	*/
	public String usePotion(){
		Item potionPouch = inventory.get(2);
		int potionAmt = (Integer)(potionPouch.getItemAttribute("AMOUNT")).intValue();
		if(potionAmt > 0){
			potionPouch.setItemAttribute("AMOUNT",new Integer(potionAmt-1));
			addHealth((Integer)potionPouch.getItemAttribute("HEALTH_POINTS").intValue());
			return "Consumed 1 potion.";
		}
		else
			return "No potions!";
	}

	public String toString(){
		String repr = "";
		for(Item i : inventory)
			repr += i + "\n";
		return repr;
	}
}