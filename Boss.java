/*
 * Tyler Robbins
 * 5/18/15
 * Boss.java
 * Special enemy with more health, money, damage, and defense.
 */

public class Boss extends Enemy{
	public Boss(Room r){
		super(r);
		name = "Generic Boss";
		count--;
	}

	@Override
	protected void generateHealth(){
		setHealth(1000);
	}

	@Override
	protected void generateMoney(){
		money = (int)(Math.random() * (100))+100;
	}

	@Override
	protected void generateDamage(){
		damage = (int)(Math.random()*20)+10;
	}

	@Override
	protected void generateDefense(){
		defense = (int)(Math.random()*15)+10;
	}

	public boolean Execute(){
		// Everything important happens in this method.
		// If this method is removed, then nothing will work anymore
		return false;
	}
}
