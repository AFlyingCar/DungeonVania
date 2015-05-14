
/*Jacob Meserve
*Enemy
*5/14/2015
*Controls the Enemies in the dungeons
*/

public class Enemy{
	private static int count = 0;
	public static int enemyInRoom;
	private int health;
	private int money;
	private String name;
	public Enemy(){
		count ++;
		generateHealth();
		generateMoney;
	}

	public void generateHealth(){
		health = (int)(Math.random() * (10 * count * (enemyInRoom/10)))
	}

	public void generateMoney(){
		money = (int)(Math.random() * (2 * count * (enemyInRoom/10)))
	}

}