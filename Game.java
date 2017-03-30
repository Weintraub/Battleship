package weintraub.daniel;

import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		System.out.println("Would you like to play singleplayer or twoplayer? (s / m)");
		String gameMode = verify("s", "m");
		
		System.out.println("Would you like a random board? (y / n)");
		String randomBoard = verify("y","n");
		boolean randomBoardBool = convertToBool(randomBoard);
		
		Board attackBoard = new Board();
		
		//AI ai1 = new AI(verifyLevel(), Input.getAiBoard());
		//AI ai2 = new AI(verifyLevel(), Input.getAiBoard());
		
		if (gameMode.equals("s")) {
			Player p1;
			if(randomBoardBool){
				p1 = new Player(Input.getAiBoard(), attackBoard);
			} else {
				p1 = new Player(Input.getBoard(), attackBoard);
			}
			levelExplain();
			AI ai = new AI(verifyLevel(), Input.getAiBoard());
			playAi(p1, ai);
		} else if (gameMode.equals("m")) {
			Player p1 = new Player(Input.getBoard(), attackBoard);
			Player p2 = new Player(Input.getBoard(), attackBoard);
			play(p1, p2);
		} 

	}

	private static boolean convertToBool(String randomBoard) {
		boolean answer = false;
		if(randomBoard.equals("y")){
			answer = true;
		}
		return answer;
	}

	private static void levelExplain() {
		System.out.println("1 - amateur");
		System.out.println("2 - easy");
		System.out.println("3 - medium");
		System.out.println("4 - expert");

	}

	private static int verifyLevel() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please put a number between 1 - 4");
		int level = sc.nextInt();
		while (!(level > 0) && !(level < 5)) {
			System.out.println("Invalid input, put a number between 1 - 4");
			level = sc.nextInt();
		}
		return level;
	}

	private static String verify(String ans1, String ans2) {
		Scanner sc = new Scanner(System.in);
		String gameMode = sc.next();
		while (!gameMode.equals(ans1) && !gameMode.equals(ans2)) {
			System.out.println("Invalid input, put "+ans1+" or "+ans2+".");
			gameMode = sc.next();
		}
		return gameMode;
	}

	private static void play(Player p1, Player p2) {
		int turn = 0;
		while (true) {
			turn ++;
			System.out.println("Turn " + turn);
			p1.attack(p2);
			p2.attack(p1);
		}

	}

	private static void playAi(Player p1, AI ai) {
		int turn = 0;
		while (true) {
			turn ++;
			System.out.println("Turn " + turn);
			p1.attack(ai);
			if (ai.getLevel() == 1) {
				ai.attackOne(p1);
			} else if (ai.getLevel() == 2) {
				ai.attackTwo(p1);
			} else if (ai.getLevel() == 3) {
				ai.attackThree(p1);
			}

		}

	}

}
