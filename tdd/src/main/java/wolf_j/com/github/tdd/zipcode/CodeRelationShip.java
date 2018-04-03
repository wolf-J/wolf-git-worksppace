/**
 * 
 */
package wolf_j.com.github.tdd.zipcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author wolf-J
 *
 */
public class CodeRelationShip {

	static final HashMap<String, String> CODE_MAP = new HashMap<>();

	static final ArrayList<Integer> ZIPCODE_NUMBER_RANGE = new ArrayList<>();

	static {
		CODE_MAP.put("0", "||:::");
		CODE_MAP.put("1", ":::||");
		CODE_MAP.put("2", "::|:|");
		CODE_MAP.put("3", "::||:");
		CODE_MAP.put("4", ":|::|");
		CODE_MAP.put("5", ":|:|:");
		CODE_MAP.put("6", ":||::");
		CODE_MAP.put("7", "|:::|");
		CODE_MAP.put("8", "|::|:");
		CODE_MAP.put("9", "|:|::");

		ZIPCODE_NUMBER_RANGE.add(5);
		ZIPCODE_NUMBER_RANGE.add(9);
	}

	static String getKeyfromCodeMap(String value) {
		Iterator<Entry<String, String>> iterator = CODE_MAP.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			if (entry.getValue().equals(value))
				return entry.getKey();
		}
		return null;
	}

	static String getValuefromCodeMap(String key) {
		return CODE_MAP.get(key);
	}

}
