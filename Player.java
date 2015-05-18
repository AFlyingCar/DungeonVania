/*
 * Tyler Robbins
 * Player
 * 5/8/15
 * Controls how the player works in game.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Player{
	private ArrayList<Item> inventory;
	private final String NAME;
	private int money;
	private int health;
	
	public Player(String newName){
		inventory = new ArrayList<Item>();
		resetInventory();
		NAME = newName;
		health = 100;
	}

	/*
	Resets the player's inventory to defaults.
	PreCondition: inventory has been initialized as an ArrayList
	PostCondition: inventory contains three items with all attributes containing default values
	*/
	private void resetInventory(){
		inventory.clear();
		inventory.add(new Item("Potions"));
		inventory.add(new Item("Armour"));
		inventory.add(new Item("Weapon"));

		Item i1 = inventory.get(0);
		Item i2 = inventory.get(1);
		Item i3 = inventory.get(2);

		i1.addAttribute("AMOUNT",new Integer(1));
		i1.addAttribute("HEALTH_POINTS",new Integer(15));
		i1.addAttribute("UPGRADE_COST",new Integer(5));

		i2.addAttribute("DEFENSE",new Integer(1));
		i2.addAttribute("TIER",new Integer(0));
		i2.addAttribute("MAX_TIER",new Integer(5));
		i2.addAttribute("UPGRADE_COST",new Integer(10));

		i3.addAttribute("MIN_DAMAGE",new Integer(0));
		i3.addAttribute("MAX_DAMAGE",new Integer(2));
		i3.addAttribute("TIER",new Integer(0));
		i3.addAttribute("MAX_TIER",new Integer(5));
		i3.addAttribute("UPGRADE_COST",new Integer(10));
	}

	/*
	Returns the player's name.
	PreCondition: name has been initialized.
	*/
	public String getName(){
		return NAME;
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
	PostCondition: money is equal to emoe
	*/
	public void setMoney(int emoe){
		money = emoe;
	}

	/*
	Adds the value of emone to money.
	PostCondition: money = money + emone
	*/
	public void addMoney(int emone){
		money += emone;
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
	PostCondition: health = newHealth
	*/
	public void setHealth(int newHealth){
		health = newHealth;
	}

	/*
	Adds newHealth to player's health.
	PostCondition: health = health + newHealth
	*/
	public void addHealth(int newHealth){
		health += newHealth;
	}

	public int getArmourAbsorption(){
		return ((Integer)inventory.get(1).getItemAttribute("DEFENSE")).intValue()/2;
	}

	/*
	Causes damage to enemy based on the player's current weapon.
	PreCondition: enemy is not null, inventory.get(0) is not null
	PostCondition: enemy has taken a random amount of damage from the weapon's MIN_DAMAGE to the weapon's MAX_DAMAGE
	*/
	public int damageEnemy(Enemy enemy){
		Item weapon = inventory.get(0);
		int minDamage = ((Integer)weapon.getItemAttribute("MIN_DAMAGE")).intValue();
		int maxDamage = ((Integer)weapon.getItemAttribute("MAX_DAMAGE")).intValue();
		int damageAmount = (int)(Math.random() * (maxDamage - minDamage + 1)) - 1;
		damageAmount -= enemy.getDefense();

		enemy.addHealth(-damageAmount);
		return damageAmount;
	}

	/*
	Uses up a potion from the player's inventory, so long as the amount of potions is > 0
	PreCondition: inventory is not null, inventory.get(2) is not null
	PostCondition: inventory->potion->AMOUNT has 1 less than it did at the start of the method
	*/
	public String usePotion(){
		Item potionPouch = inventory.get(2);
		int potionAmt = ((Integer)potionPouch.getItemAttribute("AMOUNT")).intValue();
		if(potionAmt > 0){
			potionPouch.setItemAttribute("AMOUNT",new Integer(potionAmt-1));
			addHealth(((Integer)potionPouch.getItemAttribute("HEALTH_POINTS")).intValue());
			return "Consumed 1 potion.";
		}
		else
			return "No potions!";
	}

	/*
	Increases the player's potion amount at the cost of some of their money, so long as they have enough.
	PreConditon: inventory is not null, inventory.get(0) is not null, the item has all of the proper attributes
	PostCondition: if all conditions are met, money = money - cost, potion amount is 1 higher than when it started
	*/
	public boolean buyPotion(){
		Item potionPouch = inventory.get(2);
		int potionCost = ((Integer)potionPouch.getItemAttribute("UPGRADE_COST")).intValue();
		int potionAmt = ((Integer)potionPouch.getItemAttribute("AMOUNT")).intValue();
		int maxPotionAmount = ((Integer)potionPouch.getItemAttribute("MAX_AMOUNT")).intValue();

		if(money >= potionCost){
			if(potionAmt < maxPotionAmount){
				potionPouch.setItemAttribute("AMOUNT",new Integer(potionAmt+1));
				money -= potionCost;
				return true;
			}
		}
		return false;
	}

	/*
	Upgrades the player's armour to the next tier if available, subtracts the cost from the player's currency, and increases the upgrade cost.
	Returns true if the upgrade was successful, false otherwise.
	PreCondition: inventory is not null, inventory.get(1) is not null, inventory has all proper attributes
	PostCondition: money = money - upgradeCost, armour tier is 1 higher than it started, 
	*/
	public boolean upgradeArmour(){
		Item armour = inventory.get(1);
		int upgradeCost = ((Integer)armour.getItemAttribute("UPGRADE_COST")).intValue();
		int tier = ((Integer)armour.getItemAttribute("TIER")).intValue();
		int maxTier = ((Integer)armour.getItemAttribute("MAX_TIER")).intValue();
		if(upgradeCost <= money){
			if(tier != maxTier){
				armour.setItemAttribute("TIER",new Integer(tier+1));
				armour.setItemAttribute("UPGRADE_COST",new Integer(upgradeCost*2));
				money -= upgradeCost;
				return true;
			}
		}
		return false;
	}

	/*
	Upgrades the player's weapon to the next tier if available, subtracts the cost from the player's currency, and increases the upgrade cost.
	Returns true if the upgrade was successful, false otherwise.
	PreCondition: inventory is not null, inventory.get(0) is not null, inventory has all proper attributes
	PostCondition: money = money - upgradeCost, weapon tier is 1 higher than it started
	*/
	public boolean upgradeWeapon(){
		Item weapon = inventory.get(0);
		int upgradeCost = ((Integer)weapon.getItemAttribute("UPGRADE_COST")).intValue();
		int tier = ((Integer)weapon.getItemAttribute("TIER")).intValue();
		int maxTier = ((Integer)weapon.getItemAttribute("MAX_TIER")).intValue();
		if(upgradeCost <= money){
			if(tier != maxTier){
				weapon.setItemAttribute("TIER",new Integer(tier+1));
				weapon.setItemAttribute("UPGRADE_COST",new Integer(upgradeCost*2));
				money -= upgradeCost;
				return true;
			}
		}
		return false;
	}

	public void setInventory(ArrayList<Item> inv){
		inventory = inv;
	}

	/*
	Returns the player's name, followed by their money, health, and inventory.
	*/
	public String toString(){
		String repr = NAME + "\n\n";
		repr += "Money: " + money + "\tHealth: " + health;
		repr += "\n";
		for(Item i : inventory)
			repr += i.toString() + "\n";
		return repr;
	}
}