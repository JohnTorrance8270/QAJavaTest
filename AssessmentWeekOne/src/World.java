
public class World 
{
	private enum worldObjects {Nothing, Enemy, Chest, Choice, Boss, StartPoint, Exit, Invalid};
	private worldObjects worldObjects(int objectNumber) 
	{
		switch(objectNumber)
		{
			case 0:
			return worldObjects.Nothing;
			case 1:
			return worldObjects.Enemy;
			case 2:
			return worldObjects.Chest;
			case 3:
			return worldObjects.Choice;
			case 4:
			return worldObjects.Boss;
			case 5:
			return worldObjects.StartPoint;
			case 6:
			return worldObjects.Exit;
		}
		return worldObjects.Invalid;
	}
	private String enemyType; 
	private int worldLength = 6;
	
	private int[][] worldInfo = 
	{{0, 1, 0, 1, 0, 1}, 
	{2, 0, 3, 0, 1, 1},
	{1, 4, 0, 0, 1, 2},
	{3, 1, 5, 3, 1, 0},
	{0, 0, 2, 1, 0, 3},
	{6, 0, 1, 1, 4, 1}};
	
	private int[] currentLocation = 
	{3, 2};
	
	public int[] getCurrentLocation()
	{
		return currentLocation;
	}
	
	public int getWorldLength()
	{
		return worldLength;
	}
	
	public String newLocationResponse(int row, int Column)
	{
		currentLocation[0] += row;
		currentLocation[1] += Column;
		
		return (worldObjectsResponse(worldObjects(worldInfo[currentLocation[0]][currentLocation[1]])));
	}

	private String worldObjectsResponse(worldObjects worldPosition)
	{
		String playerState = "movement";
		
		switch(worldPosition)
		{
			case Nothing:
			OutputController.nothingResponse();
			break;
	
			case Enemy:
			OutputController.enemyResponse();			
			if(InputController.shouldPlayerFight())
			{
				enemyType = "Enemy";
				playerState = "fighting";
			}
			break;
			
			case Chest:
			OutputController.chestResponse();
			
			if(InputController.shouldPlayerTry())
			{
				OutputController.chestOpenResponse();		
			}
			
			break;
			
			case Choice:
			OutputController.choiceResponse();
			
			if(InputController.isLeftChoice())
			{
				OutputController.pathTraveledResponse();		
			}
			else
			{
				OutputController.pathTraveledResponse();	
			}
			
			break;
			
			case Boss:
			OutputController.bossResponse();
			if(InputController.shouldPlayerFight())
			{
				enemyType = "Boss";
				playerState = "fighting";
			}
			break;
			
			case StartPoint:
			OutputController.revisitStartResponse();
			break;
			
			case Exit:
			playerState = "exit";
			break;
			
			default:
			OutputController.errorResponse("World Objects Response");
			break;
		}
		
		return playerState;
	}
	
	public String getEnemyType()
	{
		return enemyType;
	}
}
