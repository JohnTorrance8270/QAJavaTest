
public class Enemy extends Humanoid
{
	Enemy(int health, int attackLevel, int defenseLevel)
	{
		super(health, attackLevel, defenseLevel);
	}
	
	public void enemyAttackChoice()
	{
		String randEnemyChoice = null;
		
		switch(Utility.returnRandInt(0, 2))
		{
			case 0:
			attackChosen = "sword";
			break;
			case 1:
			attackChosen = "lance";
			break;
			case 2:
			attackChosen = "shield";
			break;
			default:
			attackChosen = "An Error Occured In The Enemy Attack Choice Method";
			break;
		}
	}
}
