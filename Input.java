package weintraub.daniel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Input {
	public static final Map<String, Integer> myShips;
	static {
		Map<String, Integer> aMap = new HashMap<String, Integer>();
		aMap.put("Carrier", 5);
		aMap.put("Battleship", 4);
		aMap.put("Cruiser", 3);
		aMap.put("Submarine", 3);
		aMap.put("Destroyer", 2);
		myShips = Collections.unmodifiableMap(aMap);
	}

	public static Board getBoard() {
		InputPlayerGenerator data = new InputPlayerGenerator();
		Ship Carrier = data.createShip(myShips, "Carrier");
		Ship BattleShip = data.createShip(myShips, "Battleship");
		Ship Cruiser = data.createShip(myShips, "Cruiser");
		Ship Submarine = data.createShip(myShips, "Submarine");
		Ship Destroyer = data.createShip(myShips, "Destroyer");
		return data.getCheckBoard();
	}
	
	public static Board getAiBoard() {
		InputAiGenerator data = new InputAiGenerator();
		Ship Carrier = data.createShip(myShips, "Carrier");
		Ship BattleShip = data.createShip(myShips, "Battleship");
		Ship Cruiser = data.createShip(myShips, "Cruiser");
		Ship Submarine = data.createShip(myShips, "Submarine");
		Ship Destroyer = data.createShip(myShips, "Destroyer");
		Board board = data.getCheckBoard();
		Board empty = new Board();
		data.setCheckBoard(empty);
		return board;
	}

}
