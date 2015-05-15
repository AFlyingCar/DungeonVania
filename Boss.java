public class Boss extends Enemy{
	public Boss(){
		super();
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

	public void Execute(){

	}
}
