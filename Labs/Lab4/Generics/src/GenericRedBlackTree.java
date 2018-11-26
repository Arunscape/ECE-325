import RedBlackTree.Node;

/**
 * Lab 4: Generics <br />
 * The {@code GenericRedBlackTree} class <br />
 * Reference: <a href="https://en.wikipedia.org/wiki/Red%E2%80%93black_tree">
 * https://en.wikipedia.org/wiki/Red%E2%80%93black_tree </a>
 */
public class GenericRedBlackTree<K extends Comparable<K>, V> {
	public static final boolean BLACK = true;
	public static final boolean RED = false;

	private Node root = null;
	private int size = 0;

	public V find(K key) {
		// TODO: Lab 4 Part 3-1 -- find an element from the tree

		Node n = this.root;
		while (n != null && !n.isNil()) {
			if (key.compareTo(n.key) < -0) {
				n = n.left;
			} else if (key.compareTo(n.key) > 0) {
				n = n.right;
			} else if (n.key.equals(key)) {
				return n.value;
			} else {
				System.out.println("ERRROR");
			}
		}
		return null;
	}

	public Node findNode(K key) {

		Node n = this.root;
		while (n!= null && !n.isNil()) {
			if (key.compareTo(n.key) < -0) {
				n = n.left;
			} else if (key.compareTo(n.key) > 0) {
				n = n.right;
			} else if (n.key.equals(key)) {
				return n;
			}
			else {
				System.out.println("REEEEEE");
			}
		}
		return null;
	}

	public void insert(K key, V value) {
		// TODO: Lab 4 Part 3-2 -- insert an element into the tree
		
		if (this.find(key) != null)
			return;
					
		this.size++;
		// BST Insert

		Node x = new Node(key, value);
		this.colourRed(x);

		bstInsert(x);

		fixRedBlackProperties(x);
	}

	public void bstInsert(Node n) {
		this.root = recursiveInsert(this.root, n);
	}

	public Node recursiveInsert(Node root, Node n) {

		if (root == null || root.isNil()) {
			return n;
		} else if (n.key.compareTo(root.key) < 0) {
			root.left = recursiveInsert(root.left, n);
			root.left.parent = root;
		} else if (n.key.compareTo(root.key) > 0) {
			root.right = recursiveInsert(root.right, n);
			root.right.parent = root;
		}
		return root;
	}

	public void fixRedBlackProperties(Node n) {

		if (n.parent == null) {
			insertCase1(n);
		} else if (n.parent.isBLACK()) {
			insertCase2(n);
		} else if (n.parent.isRED() && n.uncle().isRED()) {
			insertCase3(n);
		} else if (n.parent.isRED() && n.uncle().isBLACK()) {
			insertCase4(n);
		}

	}

	public void insertCase1(Node n) {

		if (n == this.root)
			this.colourBlack(n);
	}

	public void insertCase2(Node n) {
		// noop
	}

	public void insertCase3(Node n) {

		this.colourBlack(n.parent, n.uncle());
		this.colourRed(n.parent.parent);
		this.fixRedBlackProperties(n.parent.parent);
	}

	public void insertCase4(Node n) {

		if (n == n.parent.parent.left.right) {
			this.rotateLeft(n.parent);
			n = n.left;
		} else if (n == n.parent.parent.right.left) {
			this.rotateRight(n.parent);
			n = n.right;
		}

		insertCase4Step2(n);
	}

	public void insertCase4Step2(Node n) {

		this.colourBlack(n.parent);
		this.colourRed(n.parent.parent);

		if (n == n.parent.left) {
			this.rotateRight(n.parent.parent);
		} else if (n == n.parent.right) {
			this.rotateLeft(n.parent.parent);
		}
	}

	@SafeVarargs
	public final void colourBlack(Node... nodes) {
		for (Node n : nodes)
			if (n != null && !n.isNil()) // nils are already black
				n.colour = BLACK;
	}

	@SafeVarargs
	public final void colourRed(Node... nodes) {
		for (Node n : nodes)
			if (n != null && !n.isNil())
				n.colour = RED;
	}

	public void rotateLeft(Node n) {

		if (n.right.isNil()) {
			return;
		}

		Node oldRight = n.right;

		n.right = oldRight.left;

		if (!oldRight.left.isNil()) {
			oldRight.left.parent = n;
		}
		oldRight.parent = n.parent;
		if (n == this.root) {
			this.root = oldRight;
		} else if (n == n.parent.left) {
			n.parent.left = oldRight;
		} else {
			n.parent.right = oldRight;
		}
		oldRight.left = n;
		n.parent = oldRight;
	}

	public void rotateRight(Node n) {

		if (n.left.isNil()) {
			return;
		}

		Node oldLeft = n.left;

		n.left = oldLeft.right;

		if (!oldLeft.right.isNil()) {
			oldLeft.right.parent = n;
		}
		oldLeft.parent = n.parent;
		if (n == this.root) {
			this.root = oldLeft;
		} else if (n == n.parent.right) {
			n.parent.right = oldLeft;
		} else {
			n.parent.left = oldLeft;
		}
		oldLeft.right = n;
		n.parent = oldLeft;
	}

	/**
	 * Remove an element from the tree
	 * 
	 * @param key {@code K} the key of the element
	 * @return {@code V} the value of the removed element
	 */
//	public V remove(K key) {
//		// TODO: Lab 4 Part 3-3 -- remove an element from the tree
//
//		Node n = this.findNode(key);
//		if (n == null) {
//			return null;
//		}
//		
//		if (n.left != null && n.right != null && !n.left.isNil() && !n.right.isNil()) {
//			Node nextLarger = this.nextLarger(n);
//			
//			this.swapNodes(n, nextLarger);
//			n = nextLarger;
//		}
//
//		size--;
//		
//		Node child;
//		
//		if ( n.left != null && !n.left.isNil()) {
//			child = n.left;
//		}
//		else {
//			child = n.right;
//		}
//		
//		if (n != this.root) {
//			child.parent = n.parent;
//			
//			if ( n == n.parent.left) {
//				n.parent.left = child;
//			}
//			else {
//				n.parent.right = child;
//			}
//		}
//		else if ( child == null || child.isNil()) {
//			root = null;
//		}
//		else {
//			root = child;
//			child.parent = null;
//		}
//		
//		if (n.isBLACK()) {
//			if ( child.isRED()) {
//				this.colourBlack(child);
//			}
//			else {
//				fixDelColour(n);
//			}
//		}
//
//		return n.value;
//	}
	
	public V remove(K key) {
		// TODO: Lab 4 Part 3-3 -- remove an element from the tree

		Node n = this.findNode(key);
		if (n == null) {
			return null;
		}
		V v = n.value;
		size--;
		
		if (n.left == null  && n.right == null) {
			n.key = null;
			n.value = null;
			this.colourBlack(n);
			return null;
		}
		
		if (isNil(n)) {
			
		}

		deleteAsBST(key);

		return v;
	}

	public void deleteAsBST(K key) {

		this.root = recursiveDelete(this.root, key);

	}
	
	public boolean isNil(Node n) {
		return n == null || n.isNil();
	}

	public Node recursiveDelete(Node root, K key) {
		if (root == null || root.isNil()) {
			return null;
		}

		if (key.compareTo(root.key) < 0) {
			root.left = recursiveDelete(root.left, key);
		} else if (key.compareTo(root.key) > 0) {
			root.right = recursiveDelete(root.right, key);
		}

		else {
			if ((root.left == null || root.left.isNil())&&(root.right == null || root.right.isNil())) {
				root.key = null;
				root.value = null;
				this.colourBlack(root);
			}
			else if (root.left == null || root.left.isNil()) {
				this.swapNodes(root, root.right);
			}
			else if (root.right == null || root.right.isNil()) {
				this.swapNodes(root,  root.left);
			}
			else {
				Node nextLarger = this.nextLarger(root);
				root = this.recursiveDelete(root, nextLarger.key);
			}
		}
		
		
		return root;
}
	
	public void fixDelColour(Node n) {
		;
	}


	public Node nextLarger(Node n) {
		// next larger node is the leftmost node in right child's subtree
		Node nextLarger = n.right;

		if (n.right == null || n.right.isNil()) {
			return n;
		}
		while (!nextLarger.isNil()) {
			nextLarger = nextLarger.left;
		}
		return nextLarger.parent;

	}

	public void swapNodes(Node x, Node y) {
		K k = x.key;
		V v = x.value;

		x.key = y.key;
		x.value = y.value;

		y.key = k;
		y.value = v;
	}

	public int size() {
		return size;
	}

	void printGivenLevel(Node root, int level) {
		if (root == null)
			return;
		if (level == 1)
			System.out.print(root);
		else if (level > 1) {
			printGivenLevel(root.left, level - 1);
			printGivenLevel(root.right, level - 1);
		}
	}

	void printBreadthFirstSearch() {
		System.out.println("Breadth first search");
		for (int i = 1; i <= 10; i++) {
			this.printGivenLevel(this.root, i);
			System.out.println();
		}
	}

	public String inOrder(Node root) {
		return 
			root == null? "":(root.left == null ? "" : inOrder(root.left)) + root + " "
				+ (root.right == null ? "" : inOrder(root.right));
	}

	/**
	 * Cast the tree into a string
	 * 
	 * @return {@code String} Printed format of the tree
	 */
	@Override
	public String toString() {
		// TODO: Lab 4 Part 3-4 -- print the tree, where each node contains both value
		// and color
		// You can print it by in-order traversal

		return this.inOrder(this.root);
	}

	/**
	 * Main entry
	 * 
	 * @param args {@code String[]} Command line arguments
	 */
	public static void main(String[] args) {
		GenericRedBlackTree<Integer, String> rbt = new GenericRedBlackTree<Integer, String>();
		int[] keys = new int[10];
		for (int i = 0; i < 10; i++) {
			keys[i] = (int) (Math.random() * 200);
			System.out.println(String.format("%2d Insert: %-3d ", i + 1, keys[i]));
			rbt.insert(keys[i], "\"" + keys[i] + "\"");
		} // for (int i = 0; i < 10; i++)

		assert rbt.root.colour == GenericRedBlackTree.Node.BLACK;
		System.out.println(rbt.root); // This helps to figure out the tree structure
		System.out.println(rbt);

		System.out.println();
		System.out.println();
		rbt.printBreadthFirstSearch();
		System.out.println();
		System.out.println();

		for (int i = 0; i < 10; i++) {
			System.out.println(String.format("%2d Delete: %3d(%s)", i + 1, keys[i], rbt.remove(keys[i])));
			rbt.printBreadthFirstSearch();
			if ((i + 1) % 5 == 0) {
				System.out.println(rbt);
			} // if ((i + 1) % 5 == 0)
		} // for (int i = 0; i < 10; i++)

	}

	/**
	 * The {@code Node} class for {@code GenericRedBlackTree}
	 */
	private class Node {
		public static final boolean BLACK = true;
		public static final boolean RED = false;

		public K key;
		public V value;
		public boolean colour = BLACK;
		public Node parent = null, left = null, right = null;

		public Node(K key, V value) { // By default, a new node is black with two NIL children
			this.key = key;
			this.value = value;
			if (value != null) {
				left = new Node(null, null); // And the NIL children are both black
				left.parent = this;
				right = new Node(null, null);
				right.parent = this;
			}
		}

		public Boolean isRED() {
			return !this.colour;
		}

		public Boolean isBLACK() {
			return this.colour;
		}

		public Boolean isNil() {
			return this.value == null || this.key == null;
		}

		public Node uncle() {
			return this.parent.parent.left == this.parent ? this.parent.parent.right : this.parent.parent.left;
		}

		public Node sibling() {
			return this.parent.left == this ? this.parent.right : this.parent.left;
		}

		/**
		 * Print the tree node: red node wrapped by "<>"; black node by "[]"
		 * 
		 * @return {@code String} The printed string of the tree node
		 */
		@Override
		public String toString() {
			if (value == null)
				return "";
			return (colour == RED) ? "<" + value + "(" + key + ")>" : "[" + value + "(" + key + ")]";
		}
	}

}
