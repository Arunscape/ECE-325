import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Lab 4: Generics <br />
 * The {@code GenericHeap} class
 * 
 * @param <E extends Comparable<E>>
 */
public class GenericHeap<K extends Comparable<K>, V, E> {

	public class Pair<K extends Comparable<K>, V> {
		public K key;
		public V value;

		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public int compareTo(Pair pair) {
			// TODO Auto-generated method stub
			return this.key.compareTo((K) pair.key);
		}
	}

	private ArrayList<Pair> heap = new ArrayList<Pair>();

	/**
	 * Insert an new element to the heap
	 * 
	 * @param key   {@code K} the comparable key of the new element
	 * @param value {@code V} the actual value of the new element
	 */
	public <E extends Comparable<E>> void insert(K key, V value) {
		// TODO: Lab 4 Part 2-1 -- GenericHeap, add new element

		this.heap.add(new Pair(key, value));
		maxHeapify(this.heap, this.heap.size()-1, this.heap.size());

	}

	/**
	 * The heap sort procedure
	 * 
	 * @param array {@code <E extends Comparable<E>>[]} the array to be sorted
	 * @return {@code <E extends Comparable<E>>[]} the sorted array
	 */
	public static <E extends Comparable<E>> E[] heapSort(E[] array) {
		// TODO: Lab 4 Part 2-4 -- GenericHeap, return a sorted array

		buildMaxHeap(array);

		int endHeapIdx = array.length - 1;

		while (endHeapIdx > 0) {
			swap(array, 0, endHeapIdx);
			maxHeapify(array, 0, endHeapIdx--); // endHeapIdx is actually the heap's size before it is decremented
		}

		return array;
	}

	public static <E extends Comparable<E>> void buildMaxHeap(E[] arr) {
		for (int i = arr.length / 2; i >= 0; i--) {
			maxHeapify(arr, i, arr.length);
		}
	}

	public static <E extends Comparable<E>> void swap(E[] array, int i, int j) {
		E tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
		tmp = null;
	}
	
	private void swap(ArrayList<Pair> arr, int i, int j) {
		Collections.swap(arr, i, j);
	}

	public static <E extends Comparable<E>> void maxHeapify(E[] arr, int i, int heapSize) {
		int leftIdx = leftChildIndex(i, heapSize);
		int rightIdx = rightChildIndex(i, heapSize);
		E leftVal;
		E rightVal;

		if (leftIdx != -1) {
			leftVal = arr[leftIdx];
		} else {
			leftVal = null;
		}

		if (rightIdx != -1) {
			rightVal = arr[rightIdx];
		} else {
			rightVal = null;
		}

		int indexOfMaxVal = i;

		if (leftVal == null) {
			;
		} else if (leftVal.compareTo(arr[indexOfMaxVal]) > 0) {
			indexOfMaxVal = leftIdx;
		}
		if (rightVal == null) {
			;
		} else if (rightVal.compareTo(arr[indexOfMaxVal]) > 0) {
			indexOfMaxVal = rightIdx;
		}
		if (indexOfMaxVal == i)
			return;
		else {
			swap(arr, i, indexOfMaxVal);
			maxHeapify(arr, indexOfMaxVal, heapSize);
		}

	}
	
	private void maxHeapify(ArrayList<Pair> arr, int i, int heapSize) {
		int leftIdx = leftChildIndex(i, heapSize);
		int rightIdx = rightChildIndex(i, heapSize);
		Pair leftVal;
		Pair rightVal;

		if (leftIdx != -1) {
			leftVal = arr.get(leftIdx);
		} else {
			leftVal = null;
		}

		if (rightIdx != -1) {
			rightVal = arr.get(rightIdx);
		} else {
			rightVal = null;
		}

		int indexOfMaxVal = i;

		if (leftVal == null) {
			;
		} else if (leftVal.compareTo(arr.get(indexOfMaxVal)) > 0) {
			indexOfMaxVal = leftIdx;
		}
		if (rightVal == null) {
			;
		} else if (rightVal.compareTo(arr.get(indexOfMaxVal)) > 0) {
			indexOfMaxVal = rightIdx;
		}
		if (indexOfMaxVal == i)
			return;
		else {
			swap(arr, i, indexOfMaxVal);
			maxHeapify(arr, indexOfMaxVal, heapSize);
		}
		
	}

	public static int leftChildIndex(int i, int heapSize) {
		final int index = 2 * i + 1;
		return index < heapSize ? index : -1;
	}

// return value -1 indicates a nonexistent left/right child
	public static int rightChildIndex(int i, int heapSize) {
		final int index = 2 * i + 2;
		return index < heapSize ? index : -1;
	}

	/**
	 * Main entry: test the HeapSort
	 * 
	 * @param args {@code String[]} Command line arguments
	 */
	public static void main(String[] args) {
		// Sort an array of integers
		Integer[] numbers = new Integer[10];
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = (int) (Math.random() * 200);
		heapSort(numbers);
		for (int n : numbers)
			System.out.print(n + " ");
		System.out.println();

		// Sort an array of strings
		String[] strs = new String[10];
		for (int i = 0; i < strs.length; i++)
			strs[i] = String.format("%c", (int) (Math.random() * 26 + 65));
		heapSort(strs);
		for (String s : strs)
			System.out.print(s + " ");
		System.out.println();
	}

}
