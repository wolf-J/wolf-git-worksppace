/**
 * 
 */
package wolf_j.com.github.tdd.algorithm;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * @author wolf-J
 *
 */
class TestBackpackProblem {

	@Test
	void calculateTypesWhenCanLoadAllThreeGoods() {
		
		int capacities = 10;
		List<Integer> goodsVolumeList = new ArrayList<>(Arrays.asList(1, 2, 4));
		
		int exceptTotalTypes = (int) Math.pow(2.0, 3.0);
		assertEquals(exceptTotalTypes, BackpackProblem.calculateTypes(capacities, goodsVolumeList));
	}
	
	
	@Test
	void calculateTypesWhenCanLoadAllFourGoods() {
		
		int capacities = 15;
		List<Integer> goodsVolumeList = new ArrayList<>(Arrays.asList(1, 2, 4, 5));
		
		int exceptTotalTypes = (int) Math.pow(2.0, 4.0);
		assertEquals(exceptTotalTypes, BackpackProblem.calculateTypes(capacities, goodsVolumeList));
	}
	
	@Test
	void calculateTypesWhenCannotLoadAllThreeGoods() {
		
		int capacities = 5;
		List<Integer> goodsVolumeList = new ArrayList<>(Arrays.asList(1, 2, 4));
		
		int exceptTotalTypes = 6;
		assertEquals(exceptTotalTypes, BackpackProblem.calculateTypes(capacities, goodsVolumeList));
	}
	
	@Test
	void calculateTypesWhenCannotLoadAllFourGoods() {
		
		int capacities = 11;
		List<Integer> goodsVolumeList = new ArrayList<>(Arrays.asList(1, 2, 4, 5));
		
		int exceptTotalTypes = 15;
		assertEquals(exceptTotalTypes, BackpackProblem.calculateTypes(capacities, goodsVolumeList));
	}
	
	@Test
	void calculateTypesWhenCannotLoadAllFiveGoods() {
		
		int capacities = 11;
		List<Integer> goodsVolumeList = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 6));
		
		int exceptTotalTypes = 22;
		assertEquals(exceptTotalTypes, BackpackProblem.calculateTypes(capacities, goodsVolumeList));
	}

}
