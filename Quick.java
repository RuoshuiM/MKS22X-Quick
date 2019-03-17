import java.util.Random;

import java.util.Arrays;

public class Quick {

    /**
     * @return the value that is the kth smallest value of the array.
     */
    public static int quickselect(int[] data, int k) {
        int start = 0;
        int end = data.length - 1;

        int pivotIndex = -1;
        while (pivotIndex != k) {
            pivotIndex = partition(data, start, end);
            if (pivotIndex > k) {
                end = pivotIndex;
            } else if (pivotIndex < k) {
                start = pivotIndex;
            }
        }

        return data[pivotIndex];
    }

    /**
     * Modify the array such that: <br>
     * 1. Only the indices from start to end inclusive are considered in range <br>
     * 2. A random index from start to end inclusive is chosen, the corresponding
     * element is designated the pivot element. <br>
     * 3. all elements in range that are smaller than the pivot element are placed
     * before the pivot element. <br>
     * 4. all elements in range that are larger than the pivot element are placed
     * after the pivot element.
     *
     * @return the index of the final position of the pivot element.
     */
    static int partition(int[] data, int start, int end) {
        Random rand = new Random();
        int pivotIndex = rand.nextInt(end + 1 - start) + start;
        int pivot = data[pivotIndex];

        // swap pivot with first element
        aswap(data, start, pivotIndex);
        pivotIndex = start;
        start++;

        // now swap elements according to pivot
        while (start < end) {
            if (data[start] < pivot)
                start++;
            else if (data[end] > pivot)
                end--;
            else {
                aswap(data, start, end);
            }
        }

        assert start == end;
        aswap(data, pivotIndex, (data[start] < pivot ? start : start - 1));

        // if pivot is not at index "start", then it is at "start - 1"
        return pivot == data[start] ? start : start - 1;
    }

    private static void aswap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static int[] partitionDutch(int[] data, int start, int end) {
        Random rand = new Random();
        int pivotIndex = rand.nextInt(end + 1 - start) + start;
        int pivot = data[pivotIndex];

        // swap pivot with first element
        aswap(data, start, pivotIndex);
        pivotIndex = start;

        // use i to iterate through array
        // increment i only if cur == pivot or cur < pivot,
        // since the left side is always good; right side might have random vals

//        int i = start + 1;
//        while (i <= end) {
//            int cur = data[i];
//            if (cur > pivot) {
//                aswap(data, i, end);
//                end--;
//            } else if (cur < pivot) {
//                aswap(data, i, start);
//                start++;
//                i++;
//            } else {
//                i++;
//            }
//        }

        // equivalent to the while loop
        for (int i = start + 1; i <= end; i++) {
            int cur = data[i];
            if (cur > pivot) {
                aswap(data, i, end);
                end--;
                i--;
            } else if (cur < pivot) {
                aswap(data, i, start);
                start++;
            }
        }


        return new int[] { start, end };
    }

    /**
     * Modify the array to be in increasing order.
     */
    public static void quicksort(int[] data) {
        quicksort(data, 0, data.length - 1);
    }

    private static void quicksort(int[] data, int start, int end) {
        if (end - start < 1) return;

        int[] re = partitionDutch(data, start, end);
        int lt = re[0];
        int gt = re[1];
        quicksort(data, start, lt - 1);
        quicksort(data, gt + 1, end);
    }

    public static void main(String[] args) {

      System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
int[]MAX_LIST = {1000000000,500,10};
for(int MAX : MAX_LIST){
  for(int size = 31250; size < 2000001; size*=2){
    long qtime=0;
    long btime=0;
    //average of 5 sorts.
    for(int trial = 0 ; trial <=5; trial++){
      int []data1 = new int[size];
      int []data2 = new int[size];
      for(int i = 0; i < data1.length; i++){
        data1[i] = (int)(Math.random()*MAX);
        data2[i] = data1[i];
      }
      long t1,t2;
      t1 = System.currentTimeMillis();
      Quick.quicksort(data2);
      t2 = System.currentTimeMillis();
      qtime += t2 - t1;
      t1 = System.currentTimeMillis();
      Arrays.sort(data1);
      t2 = System.currentTimeMillis();
      btime+= t2 - t1;
      if(!Arrays.equals(data1,data2)){
        System.out.println("FAIL TO SORT!");
        System.exit(0);
      }
    }
    System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
  }
  System.out.println();
}

      // testQuicksortRandVals();
    }
/*
    public static void testQuicksortRandVals() {
        final int size = 1000000;
        for (int i = 0; i < 5; i++) {

            int[] arr = TestTools.generateIntArray(size, 0);
            int[] sorted = arr.clone();

            long sysTime = System.nanoTime();
            Arrays.sort(sorted);
            sysTime = System.nanoTime() - sysTime;

            long myTime = System.nanoTime();
            Quick.quicksort(arr);
            myTime = System.nanoTime() - myTime;

            System.out.format("SysTime: %d, myTime: %d%nFaster: %s%n", sysTime, myTime, (sysTime >= myTime?"mine":"sys"));

            TestTools.testIntArraysEquals(sorted, arr);

	}
    }
    */
}
