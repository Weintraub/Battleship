package weintraub.daniel;

import java.util.Scanner;

public class Player {
	private Board board;
	private Board attackBoard;
	private int health = 17;

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board
	 *            the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * @param board
	 * @param attackBoard2 
	 * @param attackBoard
	 */
	public Player(Board board, Board attackBoard) {
		this.board = board;
		this.attackBoard = attackBoard;
	}

	/**
	 * @return the attackBoard
	 */
	public Board getAttackBoard() {
		return attackBoard;
	}

	/**
	 * @param attackBoard
	 *            the attackBoard to set
	 */
	public void setAttackBoard(Board attackBoard) {
		this.attackBoard = attackBoard;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health
	 *            the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	public void attack(Player playerTwo) {
		System.out.println("What column do you want to shoot?");
		int column = verifyIntAttack();
		System.out.println("What row do you want to shoot?");
		int row = verifyIntAttack();
		while (!attackBoard.getBoardValue(column, row).equals("#")) {
			System.out.println("You have already shot there please pick another spot.");
			System.out.println("What column do you want to shoot?");
			column = verifyIntAttack();
			System.out.println("What row do you want to shoot?");
			row = verifyIntAttack();
		}
		if (playerTwo.getBoard().getBoardValue(column, row).equals("S")) {
			System.out.println("Hit.");
			attackBoard.setBoardValue(column, row, "X");
			playerTwo.setHealth(playerTwo.getHealth()-1);
			if (playerTwo.getHealth() == 0) {
				System.out.println("All ships down. You win!");
				System.exit(0);
			}

		} else {
			System.out.println("Miss.");
			attackBoard.setBoardValue(column, row, "O");
		}
		attackBoard.printBoard();
		System.out.println("");
		board.printBoard();
	}

	private int verifyIntAttack() {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		while (true) {
			if ((num > 0) && (num < 11)) {
				break;
			}
			System.out.println("Invalid input please put a number in range 1 - 10.");
			num = sc.nextInt();
		}
		return num;
	}

	public void attack(AI ai) { // Ai needs board methods
		System.out.println("What column do you want to shoot?");
		int column = verifyIntAttack() -1;
		System.out.println("What row do you want to shoot?");
		int row = verifyIntAttack() -1;
		while (!attackBoard.getBoardValue(column, row).equals("#")) {
			System.out.println("You have already shot there please pick another spot.");
			System.out.println("What column do you want to shoot?");
			column = verifyIntAttack();
			System.out.println("What row do you want to shoot?");
			row = verifyIntAttack();
		}
		if (ai.getBoard().getBoardValue(column, row).equals("S")) {
			System.out.println("Hit.");
			attackBoard.setBoardValue(column, row, "X");
			ai.setHealth(ai.getHealth()-1);
			if (ai.getHealth() == 0) {
				System.out.println("All ships down. You win!");
				System.exit(0);
			}

		} else {
			System.out.println("Miss.");
			attackBoard.setBoardValue(column, row, "O");
		}
		attackBoard.printBoard();
		System.out.println("");
		board.printBoard();
	}

}
