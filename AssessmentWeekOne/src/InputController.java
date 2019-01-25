import java.util.Scanner;

public class InputController 
{
	static Scanner userInput = new Scanner(System.in);
	
	private static String takeUserInput()
	{
		if(userInput.hasNext())
		{
			if(userInput.hasNextLine())
			{
				return(userInput.nextLine());
			}
		}
		
		return "InvalidInput";
	}
	
	public static int[][] movePlayer()
	{
		System.out.println("Please choose a direction to go to next");
		System.out.println("The commands are: north, south, east, west");
		
		int[][] direction = new int[1][2];	
		
		switch(takeUserInput())
		{
			case "north":
			direction[0][0] = -1;
			break;
			case "south":
			direction[0][0] = 1;
			break;
			case "east":
			direction[0][1] = 1;
			break;
			case "west":
			direction[0][1] = -1;
			break;
			default:
			System.out.println("Sorry that is not a valid movement option, please try again");
			return movePlayer();
		}
		
		return direction;
	}
	
	public static void fightInstructions()
	{
		System.out.println("Time to fight!");
		System.out.println("The combat works on a rock, paper, scissors format");
		System.out.println("sword beats lance, lance beats shield and shield will beat sword");
		System.out.println("The commands are: sword, lance and shield");
		System.out.println("Good luck!");
	}
	
	public static boolean shouldPlayerFight()
	{
		System.out.println("If you wish to fight enter fight, to run, enter run");
		
		switch(takeUserInput())
		{
			case "fight":
			return true;
			case "run":
			return false;
			default:
			System.out.println("Press enter either fight or run, try again");
			return shouldPlayerFight();
		}
	}
	
	public static String playerFight()
	{
		switch(takeUserInput())
		{
			case "sword":	
			return "sword";
			case "shield":
			return "shield";
			case "lance":
			return "lance";
			default:
			System.out.println("Thats not a valid attack, please try again");
			return playerFight();
		}
	}
	
	public static boolean shouldPlayerTry()
	{
		System.out.println("If you wish to try, enter try, otherwise enter leave");
		
		switch(takeUserInput())
		{
			case "try":
			return true;
			case "leave":
			return false;
			default:
			System.out.println("Press enter either try or leave, try again");
			return shouldPlayerFight();
		}
	}
	
	public static boolean isLeftChoice()
	{
		System.out.println("If you wish to go left, enter left, otherwise enter right");
		
		switch(takeUserInput())
		{
			case "left":
			return true;
			case "right":
			return false;
			default:
			System.out.println("Press enter either left or right, try again");
			return shouldPlayerFight();
		}
	}
}
