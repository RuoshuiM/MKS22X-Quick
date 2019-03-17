package quick;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class QuickTest {

    @Test(expected = AssertionError.class)
    public void testAssertionEnabled() {
        assert false;
    }

    @Test
    public void defaultTestCase() {
        int[] ary = { 2, 10, 15, 23, 0, 5 };
        int[] ans = { 0, 2, 5, 10, 15, 23 };
        for (int i = 0; i < 6; i++) {
            assertEquals(ans[i], Quick.quickselect(ary.clone(), i));
        }
    }

    @Test
    public void testStaticArray() {
        int[] arr = { 2, 10, 15, 23, 0, 5 };
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        Quick.quicksort(arr);
        
        TestTools.testIntArraysEquals(sorted, arr);
    }
    
    @Test
    public void testAlreadySorted() {
        int[] arr = { 0, 2, 5, 10, 15, 23 };
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        Quick.quicksort(arr);
        
        TestTools.testIntArraysEquals(sorted, arr);
    }
    
    @Test
    public void testQuicksortLimitedVals() {
        final int size = 10;
        int low = 0;
        int high = 5;
        for (int i = 0; i < 5; i++) {

            int[] arr = TestTools.generateIntArray(size, low, high);
            int[] sorted = arr.clone();
            Arrays.sort(sorted);
            Quick.quicksort(arr);
            
            TestTools.testIntArraysEquals(sorted, arr);
        }
    }
    
    @Test
    public void testQuicksortRandVals() {
        final int size = 100;
        for (int i = 0; i < 5; i++) {

            int[] arr = TestTools.generateIntArray(size, 0);
            int[] sorted = arr.clone();
            Arrays.sort(sorted);
            Quick.quicksort(arr);

            TestTools.testIntArraysEquals(sorted, arr);
        }
    }
    
    @Test
    public void testDifferentSizes() {
        final int[] sizes = {1, 2, 2 * 2, 2 * 2 * 2, 2 * 2 * 2 * 2 * 2 * 2,
                2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2, 3 * 3 * 3 };
        
        for (int size : sizes) {
            int[] arr = TestTools.generateIntArray(size, 0);
            int[] sorted = arr.clone();
            Arrays.sort(sorted);
            Quick.quicksort(arr);

            TestTools.testIntArraysEquals(sorted, arr);
        }
    }
}
