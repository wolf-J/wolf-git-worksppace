package wolf_j.com.github.algorithms;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class SortsTest {

    @Test
    public void insertionSort() {
        int[] array = {1, 3, 5, 4, 2};
        Sorts.insertionSort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    public void testArrayListLoop() {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("1", "2", "3", "4", "5"));
        for (String element : list) {
            if ("3".equals(element)) {
                list.remove(0);
                list.remove(1);
            }
        }
        assertEquals(3, list.size());
    }
    @Test
    public void testArrayListForEach() {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("1", "2", "3", "4", "5"));
        list.forEach(element -> {
            if ("3".equals(element)) {
                list.remove(0);
                list.remove(1);
            }
        });
        assertEquals(3, list.size());
    }

    @Test
    public void test1() {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("1", "2", "3", "4", "5"));
        list.parallelStream().forEach(element -> {
            list.size();
        });
        assertEquals(3, list.size());
        new HashMap<>()
    }
}