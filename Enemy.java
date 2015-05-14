
/*Jacob Meserve
*Enemy
*5/14/2015
*Controls the Enemies in the dungeons
*/

public class Enemy{
	protected static int count = 0;
	public static int enemyInRoom;
	private int health;
	private int money;
	protected String name;
	protected int damage;
	protected int defense;
	public Enemy(){
		count ++;
		generateDefense();
		generateHealth();
		generateMoney();
		generateDamage();
	}

	private void generateHealth(){
		health = (int)(Math.random() * (10 * count * (enemyInRoom/10.0)))
	}

	private void generateMoney(){
		money = (int)(Math.random() * (2 * count * (enemyInRoom/10.0)))
	}

	private void generateDamage(){
		damage = (int)(Math.random() * (count * (enemiesInRoom/10.0)))
	}

	private void generateDefense(){
		defense = (int)(Math.random() * (2 * count * (enemiesInRoom/10.0)))
	}

	public void addHealth(int newHealth){
		health += newHealth;
	}

	public void setHealth(int newHealth){
		health = newHealth;
	}

	public int getHealth(){
		return health;
	}

	public String getName(){
		return name;
	}

	public int getDefense(){
		return defense; 
	}

	public int getMoney(){
		return money;
	}

	public int damagePlayer(Player player){
		int damageAmount = -(damage - player.getArmourAbsorption());
		player.addHealth(damageAmount)
		return -damageAmount;
	}

}