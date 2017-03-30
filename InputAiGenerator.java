package weintraub.daniel;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class InputAiGenerator {
	private static Board checkBoard = new Board();

	public InputAiGenerator() {

	}

	/**
	 * @return the checkBoard
	 */
	public static Board getCheckBoard() {
		return checkBoard;
	}

	/**
	 * @param checkBoard
	 *            the checkBoard to set
	 */
	public static void setCheckBoard(Board checkBoard) {
		InputAiGenerator.checkBoard = checkBoard;
	}

	/**
	 * The method that places a ship on the checkboard to make sure its valid
	 * and returns a made ship
	 * 
	 * @param myShips
	 * @param key
	 * @return a created ship that is placed on a valid spot
	 */
	public static Ship createShip(Map<String, Integer> myShips, String key) {
		Integer value = myShips.get(key);
		// System.out.println("Key = " + key + ", Value = " + value);

		// getting input for placing down ships
		String direction = randomDirection();
		int row = randIntPlace("row", value, key, direction);
		int column = randIntPlace("column", value, key, direction);
		checkBoard = placeShip(checkBoard, row - 1, column - 1, direction, key, value);
		Ship ship = new Ship(direction, row, column, value, key);
		//checkBoard.printBoard();
		return ship;
	}

	private static String randomDirection() {
		Random rand = new Random();
		String answer;
		int num = rand.nextInt(2);
		if (num == 1) {
			answer = "v";
		} else {
			answer = "h";
		}
		return answer;
	}

	private static Board placeShip(Board myBoard, int row, int column, String direction, String ship, Integer length) {
		boolean validPlace = true;
		if (direction.equals("v")) {
			for (int i = row; i < row + length; i++) {
				if (!myBoard.getBoardValue(i, column).equals("#")) {
					validPlace = false;
					break;
				}
			}
			if (!validPlace) {
				int newRow = randIntPlace("row", length, ship, direction);
				int newColumn = randIntPlace("column", length, ship, direction);
				return placeShip(myBoard, newRow - 1, newColumn - 1, direction, ship, length);
			} else {
				for (int i = row; i < row + length; i++) {
					myBoard.setBoardValue(i, column, "S");
				}
			}

		} else {
			for (int i = column; i < column + length; i++) {
				if (!myBoard.getBoardValue(row, i).equals("#")) {
					validPlace = false;
					break;
				}
			}
			if (!validPlace) {
				int newRow = randIntPlace("row", length, ship, direction);
				int newColumn = randIntPlace("column", length, ship, direction);
				return placeShip(myBoard, newRow - 1, newColumn - 1, direction, ship, length);
			} else {
				for (int i = column; i < column + length; i++) {
					myBoard.setBoardValue(row, i, "S");
				}
			}

		}
		return myBoard;
	}

	private static int randIntPlace(String string, Integer length, String ship, String direction) {
		Random random = new Random();
		int num;
		if (string.equals("row")) {
			if (direction.equals("v")) {
				num = random.nextInt(10) + 1;
				while (true) {
					if ((num > 0) && (num < 11) && (num + length < 11)) {
						break;
					}
					num = random.nextInt(10) + 1;
				}
			} else {
				num = random.nextInt(10) + 1;
			}
		} else {
			//System.out.printf("What column would you like to put the %s in?\n", ship);
			if (direction.equals("v")) {
				num = random.nextInt(10) + 1;
			} else {
				num = random.nextInt(10) + 1;
				while (true) {
					if ((num > 0) && (num < 11) && (num + length < 11)) {
						break;
					}
					num = random.nextInt(10) + 1;
				}
			}
		}

		return num;
	}

}
