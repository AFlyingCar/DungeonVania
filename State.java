/*
 * Tyler Robbins
 * State
 * 5/11/15
 * Used for transferring control over save data from a file to the actual program.
 */

public class State{
	private Player player = null;

	public State(Player play){
		player = play;
	}

	public Player getPlayer(){
		return player;
	}
}
