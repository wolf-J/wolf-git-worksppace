package wolf_j.com.github.algorithms;

public class Sorts {
    private Sorts() {
    }

    public static void insertionSort(int[] array) {
        int little,temp;
        for (int i = 0; i < array.length; i++) {
            little = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[little]) {
                    little = j;
                }
            }
            if(little != i) {
                temp = array[i];
                array[i] = array[little];
                array[little] = temp;
            }
        }
    }
}
