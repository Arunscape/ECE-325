import java.util.ArrayList;
import java.util.Random;

/**
 * Lab 6: Java Collection Framework, Skip List and Apache ANT <br />
 * The {@code SkipList} class
 * 
 * @param <K> {@code K} key of each skip list node
 * @param <V> {@code V} value of each skip list node
 */
public class SkipList<K extends Comparable<K>, V> {

	/**
	 * The {@code Node} class for {@code SkipList}
	 */
	private class Node {
		public K key;
		public V value;
		public ArrayList<Node> forwards = new ArrayList<Node>();

		public Node(K key, V value, int level) {
			this.key = key;
			this.value = value;
			for (int i = 0; i < level; i++)
				forwards.add(null);
		}

		public String toString() {
			return String.format("%s(%s,%d)", value, key, forwards.size());
		}

		public Node right(int level) {
			try {
				return this.forwards.get(level);
			} catch (java.lang.IndexOutOfBoundsException e) {
				return null;
			}

		}

		public void setRight(Node n, int level) {

			try {
				this.forwards.set(level, n);
			} catch (java.lang.IndexOutOfBoundsException e) {
				this.forwards.add(level, n);
			}
		}

	}

	/**
	 * Level of the skip list. An empty skip list has a level of 0
	 */
	private int level = 0;

	private int size = 0;

	private Node head = new Node(null, null, 0);

	public static int randomLevel() {
		Random random = new Random();
		int n = 0;

		while (random.nextBoolean()) {
			n++;
		}
		return n;
	}

	/**
	 * Insert an new element into the skip list
	 * 
	 * @param key   {@code K} key of the new element
	 * @param value {@code V} value of the new element
	 */
	public void insert(K key, V value) {
		// TODO: Lab 5 Part 1-1 -- skip list insertion
//		this.recursiveInsert(new Node(key, value, level), this.head, randomLevel());

		int randomLevel = randomLevel();

		// make the head have an appropriate number of levels
		while (this.head.forwards.size() < randomLevel)
			this.head.forwards.add(null);

	}

//	public void recursiveInsert(Node n, Node head, int level) {
//		
//		Node headRight = head.right(level);
//		
//		if (headRight == null) {
//			head.setRight(n, level);
//		}
//		
//		else if (headRight.key.compareTo(n.key) < 0) {
//			recursiveInsert(n, headRight, level);
//		}
//		
//		
//	}

	/**
	 * Remove an element by the key
	 * 
	 * @param key {@code K} key of the element
	 * @return {@code V} value of the removed element
	 */
	public V remove(K key) {
		// TODO: Lab 5 Part 1-2 -- skip list deletion

		return null;
	}

	/**
	 * Search for an element by the key
	 * 
	 * @param key {@code K} key of the element
	 * @return {@code V} value of the target element
	 */
	public V search(K key) {
		// TODO: Lab 5 Part 1-3 -- skip list node search

		return searchNode(key).value;
	}

	public Node searchNode(K key) {

		Node n = this.head;

		for (int i = this.level; i >= 0; i--) {
			while (n.forwards.get(i) != null && n.forwards.get(i).key.compareTo(key) <= 0) {
				if (n.key.equals(key)) {
					return n;
				}
				n = n.forwards.get(i);
				if (n.key.equals(key)) {
					return n;
				}
			}
		}
		return null;
	}

	/**
	 * Get the level of the skip list
	 * 
	 * @return {@code int} level of the skip list
	 */
	public int level() {
		return level;
	}

	/**
	 * Get the size of the skip list
	 * 
	 * @return {@code int} size of the skip list
	 */
	public int size() {
		return size;
	}

	/**
	 * Print the skip list
	 * 
	 * @return {@code String} the string format of the skip list
	 */
	public String toString() {
		// TODO: Lab 5 Part 1-4 -- skip list printing

		return null;
	}

	/**
	 * Main entry
	 * 
	 * @param args {@code String[]} Command line arguments
	 */
	public static void main(String[] args) {
		SkipList<Integer, String> list = new SkipList<Integer, String>();
		int[] keys = new int[10];
		for (int i = 0; i < 10; i++) { // Insert elements
			keys[i] = (int) (Math.random() * 200);
			list.insert(keys[i], "\"" + keys[i] + "\"");
		}

		System.out.println(list);

		for (int i = 0; i < 10; i += 3) {
			int key = keys[i];
			// Search elements
			System.out.println(String.format("Find element             %3d: value=%s", key, list.search(key)));
			// Remove some elements
			System.out.println(String.format("Remove element           %3d: value=%s", key, list.remove(key)));
			// Search the removed elements
			System.out.println(String.format("Find the removed element %3d: value=%s", key, list.search(key)));
		}

		System.out.println(list);
	}

}
