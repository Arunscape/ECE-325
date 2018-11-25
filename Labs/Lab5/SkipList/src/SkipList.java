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

		public Node getRight(int level) {
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

		public int level() {
			return this.forwards.size() - 1;
		}

	}

	/**
	 * Level of the skip list. An empty skip list has a level of 0
	 */
	private int level = 0;

	private int size = 0;

	private Node head = new Node(null, null, 0);

//	public static int randomLevel() {
//		Random random = new Random();
//		int n = 0;
//
//		while (random.nextBoolean()) {
//			n++;
//		}
//		return n;
	public int randomLevel() {
		// TESTING purposes
		return this.size == 0 ? 0
				: this.size == 1 ? 1
						: this.size == 2 ? 0
								: this.size == 3 ? 2
										: this.size == 4 ? 0
												: this.size == 5 ? 1 : this.size == 6 ? 0 : this.size == 7 ? 1 : null;

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

		// first insertion
		if (this.size == 0) {
			this.head.forwards.add(new Node(key, value, 0));
			this.size++;
			return;
		}

		Node sn = this.searchNode(key);
		if (sn != null) {
			// key is already exists, why not update its value?
			sn.value = value;
			return;
		}

		int randomLevel = randomLevel();

		if (this.level < randomLevel) {
			this.level = randomLevel;
		}
		// NOTE might need to do some other fixes to head here

//		// make the head have an appropriate number of levels
		while (this.head.forwards.size() - 1 < randomLevel)
			this.head.forwards.add(null);

		Node n = new Node(key, value, randomLevel);

		updateListPointers(n, randomLevel);

		this.size++;

	}

	public void updateListPointers(Node n, int level) {

//		if (this.searchClosestNode(n.key, 0) == this.head) {
//			for (int i = 0; i <= level; i++) {
//				this.head.forwards.set(i, n);
//			}
//		}

		for (int i = 0; i <= level; i++) {
			Node left = this.searchClosestNode(n.key, i);
//			n.forwards.set(i, left.forwards.get(i));
			n.setRight(left.getRight(i), i);
			left.setRight(n, i);
		}

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
		Node n = searchNode(key);
		return n == null ? null : n.value;
	}

	public Node searchNode(K key) {

//		Node n = this.head.forwards.get(0);
//
//		for (int i = this.level; i >= 0; i--) {
//			if (n.forwards.size() == 0)
//				break;
//			while (n.forwards.get(i) != null && n.forwards.get(i).key.compareTo(key) >= 0) {
//				if (n.key.equals(key))
//					return n;
//				n = n.forwards.get(i);
//				if (n.key.equals(key))
//					return n;
//			}
//		}
//		return null;
		
		Node before = this.searchClosestNode(key, 0);
		Node right = before.getRight(0);
		
		if (right == null)
			return null;
		
		return right.key.equals(key) ? right : null;
	}

	public Node searchClosestNode(K key, int minlevel) {
		Node before = null;

		for (int i = this.level; i >= minlevel; i--) {
			Node n = this.head;
//			if (n.getRight(i) != null) {
//				before = n.forwards.get(i);
//			}
			while (n.getRight(i) != null && key.compareTo(n.getRight(i).key) < 0) {
				before = n;
				n = n.getRight(i);
			}
		}

		if (before == null)
			before = this.head;
		return before;
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
		if (this.size == 0)
			return "[]";

		String s = "[";
		Node n = this.head;

		while (n != null && n.forwards.size() > 0) {
			n = n.forwards.get(0);
			s += n + ", ";
		}

		return s.substring(0, s.length() - 2) + "]";

	}

	/**
	 * Main entry
	 * 
	 * @param args {@code String[]} Command line arguments
	 */
	public static void main(String[] args) {
//		SkipList<Integer, String> list = new SkipList<Integer, String>();
//		int[] keys = new int[10];
//		for (int i = 0; i < 10; i++) { // Insert elements
//			keys[i] = (int) (Math.random() * 200);
//			list.insert(keys[i], "\"" + keys[i] + "\"");
//		}
//
//		System.out.println(list);
//
//		for (int i = 0; i < 10; i += 3) {
//			int key = keys[i];
//			// Search elements
//			System.out.println(String.format("Find element             %3d: value=%s", key, list.search(key)));
//			// Remove some elements
//			System.out.println(String.format("Remove element           %3d: value=%s", key, list.remove(key)));
//			// Search the removed elements
//			System.out.println(String.format("Find the removed element %3d: value=%s", key, list.search(key)));
//		}
//
//		System.out.println(list);

		SkipList<Integer, String> list = new SkipList<Integer, String>();
		list.insert(5, "5");
		list.insert(25, "25");
		list.insert(30, "30");
		list.insert(31, "31");
		list.insert(42, "42");
		list.insert(58, "58");
		list.insert(62, "62");
		list.insert(69, "69");
		System.out.println(list);
	}

}
