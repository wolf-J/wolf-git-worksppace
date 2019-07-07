package wolf_j.com.github.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SortsTest {

    @Test
    public void insertionSort() {
        int[] array = {1, 3, 5, 4, 2};
        Sorts.insertionSort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }
}