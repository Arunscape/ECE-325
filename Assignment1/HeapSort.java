/**
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

        for(int i=numbers.length-1; i > 1; i--) {
                swap(numbers, 0, i);    // largest value at the end of array
                maxHeapify(numbers,0); // fix the heap so that it's a maxHeap again
        }
}

// the key of a node is >= the keys of its children
public static void buildMaxHeap(int[] arr){
        // creates a max heap, so that we can get the largest element

        // elements at indices n/2+1....n are all leaves, so no need to maxHeapify those!
        // for (int i=(int)Math.floor(arr.length/2); i > 0; i--) {
        //         maxHeapify(arr,i);
        // }
        for (int i=arr.length/2; i > 0; i--) {
                maxHeapify(arr,i);
        }
}

public static void swap(int[] arr, int i, int j){
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
}

public static void maxHeapify(int[] arr, int i){
        // corrects a single violation of the heap property in a subtree's root
        // https://youtu.be/B7hVxCmfPtM?t=18m47s
        // Assumes that the trees rooted at left(i) and right(i) are max heaps

        // which is largest, the parent, left or right child?
        // if parent isn't the largest, then we need to swap it with the largest one, then check
        // if the old root, now child holds the max heap property, and if not, fix it!
        int parent = arr[i];
        int left = arr[leftChildIndex(arr, i)]; int right = arr[rightChildIndex(arr, i)];

        int largest = Math.max(Math.max(left, right), parent);

        if(i==largest) {;}               // yay!
        else{
                swap(arr, i, largest);
                maxHeapify(arr, largest);
        }

}

public static int leftChildIndex(int[] arr, int i){
        return (2*i < arr.length) ? 2*i : null;
}

public static int rightChildIndex(int[] arr, int i){
        return (2*i +1 < arr.length) ? 2*i+1 : null;
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
        } // for (int i = 0; i < numbers.length; i++)
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
