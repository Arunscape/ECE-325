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
        // TODO: Lab 1 -- write heapsort here


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
