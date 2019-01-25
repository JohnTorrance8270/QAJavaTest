
public abstract class Humanoid 
{
	private int health;
	private int attackLevel;
	private int defenseLevel;
	protected String attackChosen;
	
	Humanoid(int health, int attackLevel, int defenseLevel)
	{
		this.health = health;
		this.attackLevel = attackLevel;
		this.defenseLevel = defenseLevel;
	}
	
	public int getHealth()
	{
		return this.health;
	}
	
	public void resolveCombatRound(Humanoid enemy)
	{	
		if(this.attackChosen.equals(enemy.attackChosen))
		{
			OutputController.attackDrawResponse(this.attackChosen, enemy.attackChosen);
		}
		else if((enemy.attackChosen.equals("sword") && (this.attackChosen.equals("lance"))) 
				&& (enemy.attackChosen.equals("lance") && (this.attackChosen.equals("shield")))
				&& (enemy.attackChosen.equals("shield") && (this.attackChosen.equals("sword"))))
		{
			OutputController.enemyAttackWinResponse(this.attackChosen, enemy.attackChosen);
			this.takeDamage(enemy.getAttackLevel());
		}
		else
		{
			OutputController.playerAttackWinResponse(this.attackChosen, enemy.attackChosen);
			enemy.takeDamage(this.attackLevel);
		}
	}
	
	public int getAttackLevel()
	{
		return this.attackLevel;
	}
	
	private void takeDamage(int attackDamage)
	{
		this.health -= attackDamage;
	}
	
	protected String getCombatParams(Humanoid humanoid)
	{
		String className = humanoid.getClass().toString();
		String[] classVarName = className.split(" ");
		
		return classVarName[1] + ": " + "Health: " + humanoid.health;
	}
}
