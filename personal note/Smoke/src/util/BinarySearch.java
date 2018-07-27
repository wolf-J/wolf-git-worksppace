package util;

public class BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(1/2);
		int[] src = new int[] {1, 3, 5, 7, 8, 9};
		System.out.println(binarySearch(src, 9));
	}
	

	public static int binarySearch(int[] srcArray, int des)
	{
		int low=0;
		int high=srcArray.length-1;
		while(low<=high)
		{
			int middle=(low+high)/2;
			if(des==srcArray[middle])
			{
				return middle;
			}else if(srcArray[middle]>des)
			{
				high=middle-1;
			}else
			{
				low=middle+1;
			}
		}
		return -1;
	}

}
