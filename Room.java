/*
 * Tyler Robbins
 * 5/12/15
 * Room
 * Controls how rooms in a dungeon work.
 */

import java.util.ArrayList;

public class Room{
	private static Logger logan = Logger.getInstance();

	private int hp_amount;
	private int gold_amount;
	private int room_size;
	private int time;
	private ArrayList<Enemy> enemies;
	private boolean bossRoom;

	public Room(boolean bossRoom){
		if(bossRoom){
			enemies = new ArrayList<Enemy>();
			enemies.add(new Boss());
			room_size = 6;
		}
		else{
			generateRandomRoomSize();
			enemies = new ArrayList<Enemy>();
			Enemy.enemyInRoom = room_size*2;
			generateRandomEnemies();
		}
		generateRandomLoot();
		time = 0;
	}

	/*
	Increases the time spent in a room by 1, and spawns enemies while resetting the timer if the time spent is longer than 5
	PreCondition: time has been initialized
	*/
	public int increaseTime(){
		time++;
		if(time > 5){
			time = 0;
			generateRandomEnemies();
		}
		return time;
	}

	public Enemy removeEnemy(int index){
		return enemies.remove(index);
	}

	/*
	Generates a random number of health potions and a random number of gold depending on the room's size
	PreCondition: The type of room has been determined
	PostCondition: 
	*/
	private void generateRandomLoot(){
		if(!isBossRoom()){
			hp_amount = (int)(Math.random()*(room_size*5));
			gold_amount = (int)(Math.random()*(100*room_size));
		}
		else{
			hp_amount = (int)(Math.random()*(room_size*10));
			gold_amount = (int)(Math.random()*(200*room_size));
		}
	}

	private void generateRandomRoomSize(){
		room_size = (int)(Math.random()*5)+1;
	}

	private void generateRandomEnemies(){
		for(int i = 0; i < getRoomSize()*2; i++){
			enemies.add(new Enemy());
			logan.log_test("DEBUG - Added " + enemies.get(i));
		}
	}

	/*
	Returns an int[] of loot, {amount of health potions, amount of gold}
	PreCondition: hp_amount and gold_amount have been initialized
	PostCondition: hp_amount and gold_amount are less than when they started
	*/
	public int[] loot(){
		double hp_looted = Math.random();
		double gold_looted = Math.random();
		int[] r = {(int)(hp_amount*hp_looted),(int)(gold_looted*gold_amount)};
		hp_amount -= r[0];
		gold_amount -= r[1];
		return r;
	}

	public int getRoomSize(){ return room_size; }

	public ArrayList<Enemy> getEnemies(){ return enemies; }

	public Enemy getEnemy(int index){ return enemies.get(index); }

	public int getEnemyAmount(){ return enemies.size(); }

	/*
	Returns true if and only if there are 0 enemies.
	PreCondition: enemies has been initialized
	*/
	// public boolean canMoveToNextRoom(){ return isObjectArrayEmpty(enemies); }
	public boolean canMoveToNextRoom(){ return enemies.isEmpty(); }

	/*
	Returns true if and only if the 1 enemy is a boss.
	PreCondition: enemies has been initialized
	*/
	public boolean isBossRoom(){ return enemies.get(0) instanceof Boss; }

	/*
	Returns true if an array of Object's is empty (all elements are null)
	PreCondition: a is not null
	*/
	private boolean isObjectArrayEmpty(Object[] a){
		for(Object o : a)
			if(o != null)
				return false;
		return true;
	}
}