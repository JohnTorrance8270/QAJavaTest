
public class Boss extends Enemy
{
	Boss(int health, int attackLevel, int defenseLevel) 
	{
		super(health*2, attackLevel, defenseLevel*2);
	}
}
