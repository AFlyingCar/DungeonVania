/*
 * Tyler Robbins
 * Player
 * 5/8/15
 */

import java.util.ArrayList;

public class Player{
	private ArrayList<Item> inventory;
	private final String name;
	private int money;
	private int health;

	public Player(String newName){
		name = newName;
		health = 100;
	}

	public String getName(){
		return name;
	}

	public ArrayList<Item> getInventory(){
		return inventory;
	}

	public void setInventoryItem(int index, Item element){
		inventory.set(index,element);
	}

	public int getMoney(){
		return money;
	}

	public int setMoney(int emoe){
		money = emoe;
	}

	public void addMoney(int emone){
		setMoney(emone + getMoney());
	}


	public int getHealth(){
		return health;
	}

	public void setHealth(int newHealth){
		health = newHealth;
	}

	public void addHealth(int newHealth){
		health += newHealth;
	}

}