import java.util.Comparator;


public class DogWeightComparator implements Comparator<Dog>{

	@Override
	public int compare(Dog o1, Dog o2) {
		// TODO Auto-generated method stub
		return o1.weight-o2.weight;
	}

	
}
