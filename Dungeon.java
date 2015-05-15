/*
 * Tyler Robbins
 * Dungeon
 * 5/13/15
 * Class for handling how dungeons work
 */

import java.util.ArrayList;

public class Dungeon{
	private static ArrayList<String> chosen_names;
	private ArrayList<Room> rooms;
	private String NAME; // Supposed to be final, but can't get it to work :(
	private int c_room;

	private final int MAX_AMOUNT_OF_ROOMS = 100;

	public Dungeon(){
		c_room = 0;
		rooms = new ArrayList<Room>();
		generateRandomName();
		generateRooms();
	}

	public Dungeon(String newName){
		c_room = 0;
		rooms = new ArrayList<Room>();
		generateRooms();
		NAME = newName;
	}

	/*
	Generates a random number of rooms in the dungeon, from 1 to the maximum
	PreCondition: rooms has been initialized
	PostCondition: rooms is filled with a random number of rooms, and has a boss room at the end
	*/
	private void generateRooms(){
		int dungeonSize = (int)(Math.random()*MAX_AMOUNT_OF_ROOMS) + 1;

		for(int i = 0; i < dungeonSize; i++){
			Room r = new Room(false);
			rooms.add(r);
		}
		rooms.add(new Room(true));
	}

	/*
	Generates a random unique name for the dungeon.
	PostCondition: NAME = a random name that has not been used before.
	*/
	private void generateRandomName(){
		// TODO: Add names to be chosen at random for the dungeon
		NAME = "Dungeon";
	}

	/*
	Increments the room counter by one and returns true if and only if there is another room to go to. False otherwise.
	PreCondition: rooms has been initialized and filled
	PostCondition: c_rooom = c_room + 1
	*/
	public boolean moveToNextRoom(){
		if(doesHaveNextRoom() && canMoveToNextRoom()){
			c_room++;
			Enemy.enemyInRoom = 0;
			return true;
		}else
			return false;
	}

	/*
	Returns true if and only if there is another room to go to.
	PreCondition: rooms has been initialized and filled
	*/
	public boolean doesHaveNextRoom(){
		return !getCurrentRoom().isBossRoom();
	}

	/*
	Returns true if and only if 
	*/
	public boolean canMoveToNextRoom(){
		return getCurrentRoom().canMoveToNextRoom();
	}

	/*
	Returns the current room that is pointed to by c_room
	PreCondition: rooms has been initialized and filled
	*/
	public Room getCurrentRoom(){
		return rooms.get(c_room);
	}

	public String getName(){
		return NAME;
	}
}