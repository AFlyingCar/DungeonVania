
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
		// generateDefense();
		defense = 0;
		generateHealth();
		generateMoney();
		generateDamage();
		generateName();
	}

	/*
	Generates a random amount of health, depending on how many enemies are in the room.
	PostCondition: health is initialized to some int
	*/
	protected void generateHealth(){
		// health = (int)(Math.random() * (10 * enemyInRoom * (count/10.0)))+1;
		// health = (int)(Math.random() * (10 * ( ( (count/4)+25 ) - (enemyInRoom/2) ) ));
		health = (int)(Math.random()*(10+enemyInRoom))+10;
	}

	/*
	Generates a random amount of money, depending on how many enemies are in the room.
	PostCondition: money is initialized to some int
	*/
	protected void generateMoney(){
		// money = (int)(Math.random() * (2 * count * (enemyInRoom/10.0)));
		health = (int)(Math.random()*(2+enemyInRoom))+2;
	}

	/*
	Generates a random amount of damage, depending on how many enemies are in the room.
	PostCondition: damage is initialized to some int
	*/
	protected void generateDamage(){
		// damage = (int)(Math.random() * (count * (enemyInRoom/10.0)));
		damage = (int)(Math.random()*(10-enemyInRoom))+(enemyInRoom);
	}

	/*
	Generates a random amount of defense, depending on how many enemies are in the room.
	PostCondition: defense is initialized to some int
	*/
	protected void generateDefense(){
		defense = (int)(Math.random() * (2 * count * (enemyInRoom/10.0)));
	}

	/*
	Generates a random name. Cached for efficiency.
	*/
	protected void generateName(){
		name = "Enemy";
	}

	public void addHealth(int newHealth){
		health += newHealth;
	}

	public void setHealth(int newHealth){
		health = newHealth;
	}

	public int getDamage(){
		return damage;
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

	public void addDefense(int d){
		defense += d;
	}

	// Action methods, to be carried out by the enemy during battle

	/*
	Increases enemy defense by 1.
	PostCondition: 0 < defense < 10
	*/
	public void defend(){
		if(defense < 10)
			defense++;
	}

	/*
	Damages the player, and returns the amount of damage dealt.
	*/
	public int damagePlayer(Player player){
		int damageAmount = -(damage - player.getArmourAbsorption());
		player.addHealth(damageAmount);
		return -damageAmount;
	}

	/*
	Returns whether or not the Enemy is dead.
	*/
	public boolean isDead(){
		return(health <= 0);
	}

	/*
	Really efficient way to take the inverse square root of a number (1/sqrt(x))
	Extremely important method. DO NOT REMOVE.
	*/
	public float FastInvSqrt(float x){
		float xhalf = 0.5f * x;
		int i = Float.floatToIntBits(x);
		i = 0x5f3759df - (i >> 1);
		x = Float.intBitsToFloat(i);
		x = x*(1.5f-(xhalf*x*x));
		return x;
	}

}