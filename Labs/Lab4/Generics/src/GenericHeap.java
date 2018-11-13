//NOTE: this is still a work in progress

import java.util.ArrayList;

/**
 * Lab 4: Generics <br />
 * The {@code GenericHeap} class
 */
public class GenericHeap<K extends Comparable<K>, V> {

	public ArrayList<Pair> heap;

	public static class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K, V>> {
		public K key;
		public V val;

		public Pair(K k, V v) {
			this.key = k;
			this.val = v;
		}

		@Override
		public int compareTo(Pair<K, V> p) {
			if (p == null) {
				return 1;
			}
			return this.key.compareTo(p.key);
		}
		
		@Override
		public String toString() {
			return "<"+
		this.key+
		","+
		this.val+
		">";
		}
	}

	GenericHeap() {
		this.heap = new ArrayList<Pair>();
	}

	/**
	 * Insert an new element to the heap
	 * 
	 * @param key   {@code K} the comparable key of the new element
	 * @param value {@code V} the actual value of the new element
	 */
	public void insert(K key, V value) {
		// TODO: Lab 4 Part 2-1 -- GenericHeap, add new element
		this.heap.add(new Pair<K, V>(key, value));	

	}

	public void buildMaxHeap() {
		// creates a max heap, so that we can get the largest element ( in max heaps,
		// the key of a node is >= the keys of its children)
		// elements at indices n/2+1....n are all leaves, so no need to maxHeapify
		// those!
		for (int i = this.heap.size() / 2; i >= 0; i--) {
			this.maxHeapify(i, this.heap.size());
		}
	}

	public void swap(int i, int j) {
		// System.out.println("Swapping "+ arr[i] +" and " +arr[j] );
		Pair<K, V> tmp = this.heap.get(i);
		this.heap.set(i, this.heap.get(j));
		this.heap.set(j, tmp);
	}

	public void maxHeapify(int i, int heapSize) {
		// corrects a single violation of the heap property in a subtree's root
		// https://youtu.be/B7hVxCmfPtM?t=18m47s
		// Assumes that the trees rooted at left(i) and right(i) are max heaps

		// which is largest, the parent, left or right child?
		// if parent isn't the largest, then we need to swap it with the largest one,
		// then check
		// if the old root, now child holds the max heap property, and if not, fix it!
		int leftIdx= this.leftChildIndex(i, heapSize);
		int rightIdx = this.rightChildIndex(i, heapSize);

		Pair<K, V> leftVal = (Pair<K, V>) (leftIdx != -1 ? this.heap.get(leftIdx) : null);
		Pair<K, V> rightVal = (Pair<K, V>) (rightIdx != -1 ? this.heap.get(rightIdx) : null);

		// max val is the parent for now
		int indexOfMaxVal = i;

		if (this.heap.get(indexOfMaxVal).compareTo(leftVal) < 0) {
			indexOfMaxVal = leftIdx;
		}
		if (this.heap.get(indexOfMaxVal).compareTo(rightVal) < 0) {
			indexOfMaxVal = rightIdx;
		}
		if (indexOfMaxVal == i) {
			return; // yay, fixed
		} else {
			swap(i, indexOfMaxVal);
			maxHeapify(indexOfMaxVal, heapSize);
		}
	}

	public int leftChildIndex(int i, int heapSize) {
		final int index = 2 * i + 1;
		return index < heapSize ? index : -1;
	}

// return value -1 indicates a nonexistent left/right child
	public int rightChildIndex(int i, int heapSize) {
		final int index = 2 * i + 2;
		return index < heapSize ? index : -1;
	}

	/**
	 * The heap sort procedure
	 * 
	 * @param array {@code <E extends Comparable<E>>[]} the array to be sorted
	 * @return {@code <E extends Comparable<E>>[]} the sorted array
	 */
	public static <E extends Comparable<E>> E[] heapSort(E[] array) {
		// TODO: Lab 4 Part 2-4 -- GenericHeap, return a sorted array

		GenericHeap<E, E> bleh = new GenericHeap<E, E>();

		for (E e : array)
			bleh.insert(e, e);
		System.out.println(bleh.heap);
		
		// fix the heap
		bleh.buildMaxHeap();
		
		int endHeapIdx = bleh.heap.size() - 1;
		
		while(endHeapIdx>0) {
            bleh.swap(0, endHeapIdx);
            bleh.maxHeapify(0,endHeapIdx--);  // endHeapIdx is actually the heap's size before it is decremented
}

//    	for (Pair p: bleh.heap) {
//    		//can't do this because the method is static 🙄
////    		arr
//    	}
		for (int i = 0; i < bleh.heap.size(); i++) {
			Pair<E, E> p = bleh.heap.remove(0);
			array[i] = p.val;

		}
		System.out.println(bleh.heap);
		return array;
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
