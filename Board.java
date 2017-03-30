package weintraub.daniel;

public class Board {

	private String[][] board = new String[10][10];

	/**
	 * @param board
	 */
	public Board() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				this.board[i][j] = "#";
			}
		}
	}

	public void printBoard() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(board[i][j] + "  ");
			}
			System.out.println("");
		}
	}

	/**
	 * @return the board
	 */
	public String[][] getBoard() {
		return board;
	}

	public void setBoardValue(int i, int j, String value) {
		this.board[i][j] = value;
	}

	public String getBoardValue(int i, int j) {
		return board[i][j];
	}

	/**
	 * @param board
	 *            the board to set
	 */
	public void setBoard(String[][] board) {
		this.board = board;
	}

}
