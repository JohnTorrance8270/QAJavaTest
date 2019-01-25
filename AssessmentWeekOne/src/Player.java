
public class Player extends Humanoid
{
	public static enum PlayerState {movement, fighting, running, dead, invalid};
	
	private World world; 
	public static boolean isGameover = false;
	
	Player(int health, int attackLevel, int defenseLevel)
	{
		super(health, attackLevel, defenseLevel);
		
		world = new World();
		updatePlayerState(PlayerState.movement);
	}
	
	private void updatePlayerState(String playerstate)
	{
		changedStateResponse(playerstate);
	}
	
	private void updatePlayerState(PlayerState playerstate)
	{
		changedStateResponse(playerstate.toString());
	}
	
	private void playerCombatTurn()
	{	
		Enemy enemy = spawnEnemy();
		InputController.fightInstructions();
		
		while((enemy.getHealth() > 0) && (this.getHealth() > 0))
		{
			OutputController.outputMessage(getCombatParams(this) + " " + getCombatParams(enemy));		
			this.attackChosen = InputController.playerFight();
			enemy.enemyAttackChoice();
			this.resolveCombatRound(enemy);
		}	
		
		if(this.getHealth() <= 0)
		{
			isGameover = true;
			changedStateResponse(PlayerState.dead);
		}
		else
		{
			OutputController.combatVictory();
			changedStateResponse(PlayerState.movement);
		}
	}
	
	public void cleanup()
	{
		world = null;
		InputController.userInput.close();
	}
	
	private Enemy spawnEnemy()
	{
		int enemyHealth = Utility.returnRandInt(20, 100);
		int enemyAttackLevel = Utility.returnRandInt(2, 10);
		int enemyDefenseLevel = Utility.returnRandInt(2, 10);
		
		return (world.getEnemyType().equals("Enemy") ? (new Enemy(enemyHealth, enemyAttackLevel, enemyDefenseLevel)) : (new Boss(enemyHealth, enemyAttackLevel, enemyDefenseLevel)));
	}
	
	private void playerMoveTurn()
	{
		int[][] playerMovement = InputController.movePlayer();
		int[] currentPos = world.getCurrentLocation();
		
		// limit check, to prevent hitting 2d limit	
		if(currentPos[0] + playerMovement[0][0] >= world.getWorldLength() || currentPos[1] + playerMovement[0][1] >= world.getWorldLength() || (currentPos[0] + playerMovement[0][0] < 0 || currentPos[1] + playerMovement[0][1] < 0))
		{
			System.out.println("You have reached the edge of the world!!!");
			System.out.println("Please try a different movement option");
			playerMoveTurn();
		}
		else
		{
			changedStateResponse(world.newLocationResponse(playerMovement[0][0], playerMovement[0][1]));
		}
	}
	
	private void changedStateResponse(PlayerState playerstate) 
	{
		changedStateResponse(playerstate.toString());
	}
	
	private void changedStateResponse(String playerstate)
	{
		switch(playerstate)
		{
			case "movement":
			playerMoveTurn();
			break;
			case "fighting":
			playerCombatTurn();
			break;
			case "running":
			break;
			case "dead":
			OutputController.defeatedResponse();
			Player.isGameover = true;
			break;
			case "exit":
			OutputController.exitResponse();
			Player.isGameover = true;
			break;
			case "invalid":
			break;
			default:
			break;
		}
	}
}
