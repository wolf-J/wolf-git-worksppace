/**
 * 
 */
package wolf_j.com.github.tdd.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wolf-J
 *
 */
public class BackpackProblem {

	public static int calculateTypes(int capacities, List<Integer> goodsVolumeList) {
		
		if (goodsVolumeList == null || goodsVolumeList.isEmpty())
			return 1;
		
		List<Integer> goodsVolumeListClone = new ArrayList<>(goodsVolumeList);
		
		if (getTotalGoodsVolume(goodsVolumeListClone) <= capacities) {
			return (int) Math.pow(2.0, goodsVolumeListClone.size());
		}

		if (goodsVolumeListClone.get(0) > capacities) {
			goodsVolumeListClone.remove(0);
			return calculateTypes(capacities, goodsVolumeListClone);
		} else {
			goodsVolumeListClone.remove(0);
			return calculateTypes(capacities - goodsVolumeList.get(0), goodsVolumeListClone)
					+ calculateTypes(capacities, goodsVolumeListClone);
		}

	}

	private static int getTotalGoodsVolume(List<Integer> goodsVolumeList) {
		int totalGoodsVolume = 0;
		for (Integer goodsVolume : goodsVolumeList) {
			totalGoodsVolume += goodsVolume;
		}
		return totalGoodsVolume;
	}

}
