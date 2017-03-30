package weintraub.daniel;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author daniel
 *
 */
/**
 * @author daniel
 *
 */
public class AI {
	private int level;
	private Board board;
	private Board attackBoard = new Board();
	private int health = 17;
	private ArrayList<ArrayList<Integer>> queue = new ArrayList<ArrayList<Integer>>();

	/**
	 * @param level
	 * @param board
	 * @param attackBoard
	 * @param health
	 */
	public AI(int level, Board board) {
		this.level = level;
		this.board = board;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

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

	/**
	 * This method simply randomly shoots the players board with no care to what
	 * it hits
	 * 
	 * @param p1
	 */
	public void attackOne(Player p1) {
		Random random = new Random();
		int column = random.nextInt(10) + 1;
		int row = random.nextInt(10) + 1;
		while (!attackBoard.getBoardValue(column, row).equals("#")) {
			column = random.nextInt(10) + 1;
			row = random.nextInt(10) + 1;
		}
		if (p1.getBoard().getBoardValue(column, row).equals("S")) {
			System.out.println("The ai hit.");
			attackBoard.setBoardValue(column, row, "X");
			checkHealth(p1);
		} else {
			System.out.println("The ai missed.");
			attackBoard.setBoardValue(column, row, "O");
		}

	}

	/**
	 * Attacking the player with two main modes Hunt mode and Attack mode Hunt
	 * mode simply shoots randomly on the board till it hits a ship. Once its
	 * hit a ship it will switch into Attack mode Attack mode will add every
	 * spot around the hit into a queue to queue its moves and keep attacking
	 * items in that queue until it is empty Then it will return back to hunt
	 * mode This guarantees that once a ship is found it is killed unlike
	 * AttackOne() which will continue randomly shooting
	 * 
	 * @param p1
	 */
	public void attackTwo(Player p1) {
		Random random = new Random();
		if (queue.size() == 0) { // hunt mode
			int column = random.nextInt(10) + 1;
			int row = random.nextInt(10) + 1;
			while (!attackBoard.getBoardValue(column, row).equals("#")) {
				column = random.nextInt(10) + 1;
				row = random.nextInt(10) + 1;
			}
			if (p1.getBoard().getBoardValue(column, row).equals("S")) {
				System.out.println("The ai hit.");
				attackBoard.setBoardValue(column, row, "X");
				checkHealth(p1);
				queue = update(column, row);
			} else {
				System.out.println("The ai missed.");
				attackBoard.setBoardValue(column, row, "O");
			}
		} else { // attack mode
			int column = queue.get(0).get(0);
			int row = queue.get(0).get(1);
			if (p1.getBoard().getBoardValue(column, row).equals("S")) {
				System.out.println("The ai hit.");
				attackBoard.setBoardValue(column, row, "X");
				checkHealth(p1);
				queue = update(column, row);
			} else {
				System.out.println("The ai missed.");
				attackBoard.setBoardValue(column, row, "O");
			}
		}
	}

	/**
	 * Taking attackTwo() and making hunt mode more efficient by adding parity
	 * Since each battleship is atleast length 2 it would be more strategic to attack only diagnols increasing our odds of finding a ship 
	 * this just starts to reduce the randomness of the ai and make it smarter.
	 * 
	 * in this case we will only be shooting spots with an even value
	 * @param p1
	 */
	public void attackThree(Player p1) {
		Random random = new Random();
		if (queue.size() == 0) { // hunt mode
			int column = random.nextInt(9) + 1;
			int row = random.nextInt(9) + 1;
			while(column*row%2==1){
				column = random.nextInt(9) + 1;
				row = random.nextInt(9) + 1;
			}
			while (!attackBoard.getBoardValue(column, row).equals("#")) {
				column = random.nextInt(9) + 1;
				row = random.nextInt(9) + 1;
			}
			if (p1.getBoard().getBoardValue(column, row).equals("S")) {
				System.out.println("The ai hit.");
				attackBoard.setBoardValue(column, row, "X");
				p1.getBoard().setBoardValue(column, row, "/");
				p1.setHealth(p1.getHealth()-1);
				checkHealth(p1);
				queue = update(column, row);
			} else {
				System.out.println("The ai missed.");
				attackBoard.setBoardValue(column, row, "O");
				p1.getBoard().setBoardValue(column, row, "X");
			}
		} else { // attack mode
			int column = queue.get(0).get(0);
			int row = queue.get(0).get(1);
			if (p1.getBoard().getBoardValue(column, row).equals("S")) {
				System.out.println("The ai hit.");
				attackBoard.setBoardValue(column, row, "X");
				p1.getBoard().setBoardValue(column, row, "/");
				p1.setHealth(p1.getHealth()-1);
				checkHealth(p1);
				queue = update(column, row);
			} else {
				System.out.println("The ai missed.");
				attackBoard.setBoardValue(column, row, "O");
				p1.getBoard().setBoardValue(column, row, "X");
			}
		}
	}

	private void checkHealth(Player p1) {
		if (p1.getHealth() == 0) {
			System.out.println("All ships down. You lost");
			System.exit(0);
		}
	}

	private ArrayList<ArrayList<Integer>> update(int column, int row) {
		if (column + 1 < 11) {
			queue.add(surroundCoordinate(column + 1, row));
		} else if (column - 1 > 0) {
			queue.add(surroundCoordinate(column - 1, row));
		} else if (row + 1 < 11) {
			queue.add(surroundCoordinate(column, row + 1));
		} else if (row - 1 > 0) {
			queue.add(surroundCoordinate(column, row - 1));
		}
		return queue;
	}

	private ArrayList<Integer> surroundCoordinate(int column, int row) {
		ArrayList<Integer> coordinates = new ArrayList<Integer>();
		coordinates.add(column);
		coordinates.add(row);
		return coordinates;
	}

	public void attackThree(AI ai1) {
		Random random = new Random();
		if (queue.size() == 0) { // hunt mode
			int column = random.nextInt(9) + 1;
			int row = random.nextInt(9) + 1;
			while(column*row%2==1){
				column = random.nextInt(9) + 1;
				row = random.nextInt(9) + 1;
			}
			while (!attackBoard.getBoardValue(column, row).equals("#")) {
				column = random.nextInt(9) + 1;
				row = random.nextInt(9) + 1;
			}
			if (ai1.getBoard().getBoardValue(column, row).equals("S")) {
				System.out.println("The ai hit.");
				attackBoard.setBoardValue(column, row, "X");
				ai1.getBoard().setBoardValue(column, row, "/");
				ai1.setHealth(ai1.getHealth()-1);
				checkHealth(ai1);
				queue = update(column, row);
			} else {
				System.out.println("The ai missed.");
				attackBoard.setBoardValue(column, row, "O");
				ai1.getBoard().setBoardValue(column, row, "X");
			}
		} else { // attack mode
			int column = queue.get(0).get(0);
			int row = queue.get(0).get(1);
			if (ai1.getBoard().getBoardValue(column, row).equals("S")) {
				System.out.println("The ai hit.");
				attackBoard.setBoardValue(column, row, "X");
				ai1.getBoard().setBoardValue(column, row, "/");
				ai1.setHealth(ai1.getHealth()-1);
				checkHealth(ai1);
				queue = update(column, row);
			} else {
				System.out.println("The ai missed.");
				attackBoard.setBoardValue(column, row, "O");
				ai1.getBoard().setBoardValue(column, row, "X");
			}
		}
		
	}

	private void checkHealth(AI ai1) {
		if (ai1.getHealth() == 0) {
			System.out.println("All ships down. You lost");
			System.exit(0);
		}
		
	}

}
