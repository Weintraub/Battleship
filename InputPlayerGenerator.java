package weintraub.daniel;

import java.util.Map;
import java.util.Scanner;

public class InputPlayerGenerator {
	private static Board checkBoard = new Board();

	public InputPlayerGenerator() {
	}

	/**
	 * @param checkBoard
	 */
	public InputPlayerGenerator(Board checkBoard) {
		this.checkBoard = checkBoard;
	}

	/**
	 * @return the checkBoard
	 */
	public Board getCheckBoard() {
		return checkBoard;
	}

	/**
	 * @param checkBoard
	 *            the checkBoard to set
	 */
	public void setCheckBoard(Board checkBoard) {
		this.checkBoard = checkBoard;
	}

	public static Ship createShip(Map<String, Integer> myShips, String key) {
		Integer value = myShips.get(key);
		// System.out.println("Key = " + key + ", Value = " + value);

		// getting input for placing down ships
		String direction = verify(key, value);
		int row = verifyIntPlace("row", value, key, direction);
		int column = verifyIntPlace("column", value, key, direction);
		checkBoard = placeShip(checkBoard, row - 1, column - 1, direction, key, value);
		Ship ship = new Ship(direction, row, column, value, key);
		checkBoard.printBoard();
		return ship;
	}

	private static Board placeShip(Board myBoard, int row, int column, String direction, String ship, Integer length) {
		// System.out.printf("placeShip(..., row={%d}, col={%d}, dir={%s},
				// ship={%s}, len={%d}\n", row, column, direction,ship, length);
				boolean validPlace = true;
				if (direction.equals("v")) {
					for (int i = row; i < row + length; i++) {
						if (!myBoard.getBoardValue(i, column).equals("#")) {
							validPlace = false;
							break;
						}
					}
					if (!validPlace) {
						System.out.println("Invalid placement please try again.");
						int newRow = verifyIntPlace("row", length, ship, direction);
						int newColumn = verifyIntPlace("column", length, ship, direction);
						return placeShip(myBoard, newRow - 1, newColumn - 1, direction, ship, length);
					} else {
						for (int i = row; i < row + length; i++) {
							myBoard.setBoardValue(i, column,"S");
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
						System.out.println("Invalid placement please try again.");
						int newRow = verifyIntPlace("row", length, ship, direction);
						int newColumn = verifyIntPlace("column", length, ship, direction);
						return placeShip(myBoard, newRow - 1, newColumn - 1, direction, ship, length);
					} else {
						for (int i = column; i < column + length; i++) {
							myBoard.setBoardValue(row, i, "S");
						}
					}

				}
				return myBoard;
	}

	private static int verifyIntPlace(String string, Integer length, String ship, String direction) {
		Scanner sc = new Scanner(System.in);
		int num;
		if (string.equals("row")) {
			System.out.printf("What row would you like to put the %s in?\n", ship);
			if (direction.equals("v")) {
				num = sc.nextInt();
				while (true) {
					if ((num > 0) && (num < 11) && (num + length < 11)) {
						break;
					}
					System.out.println("Invalid input please put a number in range 1 - 10.");
					num = sc.nextInt();
				}
			} else {
				num = sc.nextInt();
				while (true) {
					if ((num > 0) && (num < 11)) {
						break;
					}
					System.out.println("Invalid input please put a number in range 1 - 10.");
					num = sc.nextInt();
				}
			}
		} else {
			System.out.printf("What column would you like to put the %s in?\n", ship);
			if (direction.equals("v")) {
				num = sc.nextInt();
				while (true) {
					if ((num > 0) && (num < 11)) {
						break;
					}
					System.out.println("Invalid input please put a number in range 1 - 10.");
					num = sc.nextInt();
				}
			} else {
				num = sc.nextInt();
				while (true) {
					if ((num > 0) && (num < 11) && (num + length < 11)) {
						break;
					}
					System.out.println("Invalid input please put a number in range 1 - 10.");
					num = sc.nextInt();
				}
			}
		}

		return num;
	}

	private static String verify(String ship, Integer length) {
		Scanner sc = new Scanner(System.in);
		System.out.printf("Place the %s [Length %d] vertically or horizontally? (v / h).\n", ship, length);
		String direction = sc.next();
		while (!direction.equals("v") && !direction.equals("h")) {
			System.out.println("Invalid input, put 'v' or 'h'.");
			direction = sc.next();
		}
		return direction;
	}

}
