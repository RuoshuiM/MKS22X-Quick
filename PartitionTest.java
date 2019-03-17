package quick;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class PartitionTest {

    @Test(expected = AssertionError.class)
    public void testAssertionEnabled() {
        assert false;
    }

    @Test
    public void testFewNums() {
        final int size = 10;
        int start = 0;
        int end = size - 1;

        for (int i = 0; i < 3; i++) {
            int[] arr = TestTools.generateIntArray(size, 0, 10000);
            int pivotIndex = Quick.partition(arr, start, end);
            testArrayPivoted(arr, pivotIndex, start, end);
        }
    }

    @Test
    public void testLotsOfNums() {
        final int size = 1000;
        int start = 0;
        int end = size - 1;

        for (int i = 0; i < 2; i++) {
            int[] arr = TestTools.generateIntArray(size, 0, 10000);
            int pivotIndex = Quick.partition(arr, start, end);
            testArrayPivoted(arr, pivotIndex, start, end);
        }
    }

    @Test
    public void testSpecificSizes() {
        final int[] sizes = new int[] { 2, 2 * 2, 2 * 2 * 2, 2 * 2 * 2 * 2 * 2 * 2,
                2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2, 3 * 3 * 3 };
        // Test case of size 1 doesn't work

        for (int size : sizes) {
            int start = 0;
            int end = size - 1;

            int[] arr = TestTools.generateIntArray(size, 0, 10000);
            int pivotIndex = Quick.partition(arr, start, end);
            testArrayPivoted(arr, pivotIndex, start, end);
        }
    }

    @Test
    public void testLargeValueSpace() {
        final int size = 50;
        int start = 0;
        int end = size - 1;

        for (int i = 0; i < 2; i++) {
            int[] arr = TestTools.generateIntArray(size, 0);
            int pivotIndex = Quick.partition(arr, start, end);
            testArrayPivoted(arr, pivotIndex, start, end);
        }
    }

    @Test
    public void testSmallValueSpace() {
        final int size = 10;
        int start = 0;
        int end = size - 1;

        for (int i = 0; i < 3; i++) {
            int[] arr = TestTools.generateIntArray(size, 1, 20);
            int pivotIndex = Quick.partition(arr, start, end);
            testArrayPivoted(arr, pivotIndex, start, end);
        }
    }

    @Test
    public void testDutchRepetition() {
        final int size = 1000;
        int start = 0;
        int end = size - 1;

        for (int i = 0; i < 3; i++) {
            int[] arr = TestTools.generateIntArray(size, 1, 3);
            int result[] = Quick.partitionDutch(arr, start, end);
            int low = result[0], high = result[1];
            testArrayPivoted(arr, low, high, start, end);
        }
    }
    
    @Test
    public void testDutchTwoVals() {
        final int size = 2;
        int start = 0;
        int end = size - 1;
        
        for (int i = 0; i < 3; i++) {
            int[] arr = TestTools.generateIntArray(size, 1);
            int result[] = Quick.partitionDutch(arr, start, end);
            int low = result[0], high = result[1];
            testArrayPivoted(arr, low, high, start, end);
        }
        
        for (int i = 0; i < 3; i++) {
            int[] arr = TestTools.generateIntArray(size, -5, 3);
            int result[] = Quick.partitionDutch(arr, start, end);
            int low = result[0], high = result[1];
            testArrayPivoted(arr, low, high, start, end);
        }
    }

    private void testArrayPivoted(int[] arr, int pivotIndex, int start, int end) {
        int pivot = arr[pivotIndex];
        for (int i = start; i < pivotIndex; i++) {
            assertTrue("Vals on the left of pivot should be < pivot", arr[i] <= pivot);
        }

        for (int i = pivotIndex; i <= end; i++) {
            assertTrue("Vals on the right of pivot should be > pivot", arr[i] >= pivot);
        }
//        System.out.println(Arrays.toString(arr));
//        System.out.println("Pivot: " + pivot);
    }

    private void testArrayPivoted(int[] arr, int low, int high, int start, int end) {
        int pivot = arr[low];
        for (int i = start; i < low; i++) {
            assertTrue("Vals on the left of low bound should be < pivot", arr[i] < pivot);
        }

        for (int i = low; i <= high; i++) {
            assertTrue("Vals in between low and high bound should == pivot", arr[i] == pivot);
        }

        for (int i = high + 1; i <= end; i++) {
            assertTrue("Vals on the right of high bound should be > pivot", arr[i] > pivot);
        }
        System.out.println(Arrays.toString(arr));
        System.out.format("Low: %d, high: %d, pivot: %d%n", low, high, pivot);
        System.out.format("Start: %d, end: %d%n", start, end);
    }


}
