/**
 * Lab 4: Generics <br />
 * The {@code GenericHeap} class
 */
public class GenericHeap<K extends Comparable<K>, V> {

    /**
     * Insert an new element to the heap
     * @param key       {@code K} the comparable key of the new element
     * @param value     {@code V} the actual value of the new element
     */
    public void insert(K key, V value) {
        // TODO: Lab 4 Part 2-1 -- GenericHeap, add new element
        
    }

    /**
     * The heap sort procedure
     * @param array     {@code <E extends Comparable<E>>[]} the array to be sorted
     * @return          {@code <E extends Comparable<E>>[]} the sorted array
     */
    public static <E extends Comparable<E>> E[] heapSort(E[] array) {
        // TODO: Lab 4 Part 2-4 -- GenericHeap, return a sorted array
        
        return null;
    }

    /**
     * Main entry: test the HeapSort
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        // Sort an array of integers
        Integer[] numbers = new Integer[10];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = (int) (Math.random() * 200);
        heapSort(numbers);
        for (int n: numbers)
            System.out.print(n + " ");
        System.out.println();

        // Sort an array of strings
        String[] strs = new String[10];
        for (int i = 0; i < strs.length; i++)
            strs[i] = String.format("%c", (int) (Math.random() * 26 + 65));
        heapSort(strs);
        for (String s: strs)
            System.out.print(s + " ");
        System.out.println();
    }

}
