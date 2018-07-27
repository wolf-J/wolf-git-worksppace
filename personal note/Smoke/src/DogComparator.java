import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;


public class DogComparator implements Comparator<Dog> {

	@Override
	public int compare(Dog o1, Dog o2) {
		// TODO Auto-generated method stub
		
		return o1.size-o2.size;
		
	}
	

	
}
