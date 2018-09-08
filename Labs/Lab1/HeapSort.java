/**
   Arun Woosaree
 * Lab 1: Java Basics, Heap Sort and Eclipse <br />
 * The {@code HeapSort} class
 */
public class HeapSort {

/**
 * The heap sort procedure
 * @param numbers   {@code int[]} The integer array to be sorted
 */
public static void sort(int[] numbers) {
        // https://youtu.be/B7hVxCmfPtM?t=48m20s

        buildMaxHeap(numbers);
        // largest value is now at root of the tree

        int endHeapIdx = numbers.length-1;
        while(endHeapIdx>0) {
                swap(numbers, 0, endHeapIdx);
                maxHeapify(numbers,0,endHeapIdx--);  // endHeapIdx is actually the heap's size before it is decremented
        }
}

public static void buildMaxHeap(int[] arr){
        // creates a max heap, so that we can get the largest element ( in max heaps, the key of a node is >= the keys of its children)
        // elements at indices n/2+1....n are all leaves, so no need to maxHeapify those!
        for (int i=arr.length/2; i >= 0; i--) {
                maxHeapify(arr,i, arr.length);
        }
}

public static void swap(int[] arr, int i, int j){
        // System.out.println("Swapping "+ arr[i] +" and " +arr[j] );
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
}

public static void maxHeapify(int[] arr, int i, int heapSize){
        // corrects a single violation of the heap property in a subtree's root
        // https://youtu.be/B7hVxCmfPtM?t=18m47s
        // Assumes that the trees rooted at left(i) and right(i) are max heaps

        // which is largest, the parent, left or right child?
        // if parent isn't the largest, then we need to swap it with the largest one, then check
        // if the old root, now child holds the max heap property, and if not, fix it!
        int leftIdx =leftChildIndex( i, heapSize); int rightIdx = rightChildIndex(i, heapSize);
        int leftVal = leftIdx != -1 ? arr[leftIdx] : -1;
        int rightVal = rightIdx != -1 ? arr[rightIdx] : -1;

        // max val is the parent for now
        int indexOfMaxVal =i;

        if (leftVal >arr[indexOfMaxVal]) {
                indexOfMaxVal=leftIdx;
        }
        if (rightVal > arr[indexOfMaxVal]) {
                indexOfMaxVal=rightIdx;
        }
        if (indexOfMaxVal==i) {
                return; // yay, fixed
        }
        else{
                swap(arr, i, indexOfMaxVal);
                maxHeapify(arr, indexOfMaxVal, heapSize);
        }
}

public static int leftChildIndex(int i, int heapSize){
        final int index = 2*i+1;
        return index < heapSize ? index : -1;
}
// return value -1 indicates a nonexistent left/right child
public static int rightChildIndex(int i, int heapSize){
        final int index = 2*i+2;
        return index < heapSize ? index : -1;
}

/**
 * Main entry: test the HeapSort
 * @param args      {@code String[]} Command line arguments
 */
public static void main(String[] args) {
        int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
                numbers[i] = (int) (Math.random() * 200);
                System.out.print(numbers[i] + " ");
        }
        System.out.println();

        sort(numbers);

        for (int n: numbers)
                System.out.print(n + " ");
        System.out.println();
}

}

// References:
// https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-fall-2011/lecture-videos/lecture-4-heaps-and-heap-sort/
// https://en.wikipedia.org/wiki/XOR_swap_algorithm
