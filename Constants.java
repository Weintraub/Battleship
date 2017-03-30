package weintraub.daniel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constants {
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

}
