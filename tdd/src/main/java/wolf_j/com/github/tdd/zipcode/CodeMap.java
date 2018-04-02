/**
 * 
 */
package wolf_j.com.github.tdd.zipcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author wolf-J
 *
 */
public class CodeMap {

	static final HashMap<String, String> CODE_MAP = new HashMap<>();
	
	static final ArrayList<Integer> ZIPCODE_NUMBER_RANGE = new ArrayList<>();

	static {
		CodeMap.CODE_MAP.put("0", "||:::");
		CodeMap.CODE_MAP.put("1", ":::||");
		CodeMap.CODE_MAP.put("2", "::|:|");
		CodeMap.CODE_MAP.put("3", "::||:");
		CodeMap.CODE_MAP.put("4", ":|::|");
		CodeMap.CODE_MAP.put("5", ":|:|:");
		CodeMap.CODE_MAP.put("6", ":||::");
		CodeMap.CODE_MAP.put("7", "|:::|");
		CodeMap.CODE_MAP.put("8", "|::|:");
		CodeMap.CODE_MAP.put("9", "|:|::");
		
		ZIPCODE_NUMBER_RANGE.add(5);
		ZIPCODE_NUMBER_RANGE.add(9);
	}

}
