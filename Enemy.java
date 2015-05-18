
/*Jacob Meserve
*Enemy
*5/14/2015
*Controls the Enemies in the dungeons
*/

public class Enemy{
	protected static int count = 0;
	public static int enemyInRoom;
	private int health;
	protected int money;
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

	protected void generateHealth(){
		health = (int)(Math.random() * (10 * count * (enemyInRoom/10.0)));
	}

	protected void generateMoney(){
		money = (int)(Math.random() * (2 * count * (enemyInRoom/10.0)));
	}

	protected void generateDamage(){
		damage = (int)(Math.random() * (count * (enemyInRoom/10.0)));
	}

	protected void generateDefense(){
		defense = (int)(Math.random() * (2 * count * (enemyInRoom/10.0)));
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

	// Action methods, to be carried out by the enemy during battle

	public void defend(){
		if(defense < 10)
			defense++;
	}

	public int damagePlayer(Player player){
		int damageAmount = -(damage - player.getArmourAbsorption());
		player.addHealth(damageAmount);
		return -damageAmount;
	}

<<<<<<< HEAD
	public boolean isDead(){ return false; }
=======
	public boolean isDead(){
		return(health <= 0);
	}
>>>>>>> 3a46fa1d905a66c1ffa47563f670170e02fff578

}