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
		while (notAnull(n)) {
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
		while (notAnull(n)) {
			if (key.compareTo(n.key) < -0) {
				n = n.left;
			} else if (key.compareTo(n.key) > 0) {
				n = n.right;
			} else if (n.key.equals(key)) {
				return n;
			} else {
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
			if (notAnull(n)) // nils are already black
				n.colour = BLACK;
	}

	@SafeVarargs
	public final void colourRed(Node... nodes) {
		for (Node n : nodes)
			if (notAnull(n))
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

	public V remove(K key) {

		Node n = this.findNode(key);
		if (n == null) {
			return null;
		}
		V v = n.value;
		size--;

		Node y = n;
		boolean yOriginalColour = y.colour;
		Node x;

		if (!notAnull(n.left)) {
			x = n.right;
			this.transplant(n, n.right);

		} else if (!notAnull(n.right)) {
			x = n.left;
			this.transplant(n, n.left);
		} else {
			y = this.nextLarger(n);
			yOriginalColour = y.colour;
			x = y.right;
			if (y.parent == n) {
				x.parent = y;
			} else {
				this.transplant(y, y.right);
				y.right = n.right;
				y.right.parent = y;
			}
			this.transplant(n, y);
			y.left = n.left;
			y.left.parent = y;
			y.colour = n.colour;
		}
		if (yOriginalColour) { // if black
			delete_fixup(x);
		}
		return v;
	}

	public void delete_fixup(Node x) {
		Node w;
		while (x != this.root && x.isBLACK()) {
			if (x.isLeftChild()) {
				w = x.parent.right;
				if (w.isRED()) {
					this.colourBlack(w);
					this.colourRed(x.parent);
					this.rotateLeft(x.parent);
					w = x.parent.right;
				}

				if (w.left == null || w.right == null) {
					;
				} else if (w.left.isBLACK() && w.right.isBLACK()) {
					this.colourRed(w);
					x = x.parent;
				} else if (w.right.isBLACK()) {
					this.colourBlack(w.left);
					this.colourRed(w);
					this.rotateRight(w);
					w = x.parent.right;
				}
				w.colour = x.parent.colour;
				this.colourBlack(x.parent, w.right);
				this.rotateLeft(x.parent);
				x = this.root;
			} else {
				w = x.parent.left;
				if (w.isRED()) {
					this.colourBlack(w);
					this.colourRed(x.parent);
					this.rotateRight(x.parent);
					w = x.parent.left;
				}

				if (w.right == null || w.left == null) {
					;
				} else if (w.right.isBLACK() && w.left.isBLACK()) {
					this.colourRed(w);
					x = x.parent;
				} else if (w.left.isBLACK()) {
					this.colourBlack(w.right);
					this.colourRed(w);
					this.rotateLeft(w);
					w = x.parent.left;
				}
				w.colour = x.parent.colour;
				this.colourBlack(x.parent, w.left);
				this.rotateRight(x.parent);
				x = this.root;
			}
		}
		this.colourBlack(x);
	}

	public void deleteAsBST(Node n) {

//		if (isLeaf(n)) {
//			if (n.isLeftChild()) {
//				n.parent.left = null;
//			} else if (n.isRightChild()) {
//				n.parent.right = null;
//			}
//			n = null;
//			return;
//		}

		if (!notAnull(n.left))
			this.transplant(n, n.right);
		else if (!notAnull(n.right))
			this.transplant(n, n.left);
		else {
			Node y = this.nextLarger(n);
			if (y.parent != n) {
				this.transplant(y, y.right);
				y.right = n.right;
				y.right.parent = y;
			}
			this.transplant(n, y);
			y.left = n.left;
			y.left.parent = y;
		}

	}

	// http://crypto.cs.mcgill.ca/~crepeau/COMP251/03graphsC.pdf
	public void transplant(Node u, Node v) {
		if (u.parent == null) {
			this.root = v;
		} else if (u.isLeftChild()) {
			u.parent.left = v;
		} else {
			u.parent.right = v;
		}
		if (notAnull(v)) {
			v.parent = u.parent;
		}
	}

	public boolean notAnull(Node n) {
		return n != null && !n.isNil();
	}

	public boolean isLeaf(Node n) {
		return !notAnull(n.left) && !notAnull(n.right);
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

	public Node nextSmaller(Node n) {
		Node nextSmaller = n.left;

		if (n.left == null || n.left.isNil()) {
			return n;
		}
		while (!nextSmaller.isNil()) {
			nextSmaller = nextSmaller.right;
		}
		return nextSmaller.parent;
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
		return root == null ? ""
				: (root.left == null ? "" : inOrder(root.left)) + root + " "
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
//		int[] keys = new int[10];
//		for (int i = 0; i < 10; i++) {
//			keys[i] = (int) (Math.random() * 200);
//			System.out.println(String.format("%2d Insert: %-3d ", i + 1, keys[i]));
//			rbt.insert(keys[i], "\"" + keys[i] + "\"");
//		} // for (int i = 0; i < 10; i++)
//
//		assert rbt.root.colour == GenericRedBlackTree.Node.BLACK;
//		System.out.println(rbt.root); // This helps to figure out the tree structure
//		System.out.println(rbt);
//
//		System.out.println();
//		System.out.println();
//		rbt.printBreadthFirstSearch();
//		System.out.println();
//		System.out.println();
//
//		for (int i = 0; i < 10; i++) {
//			System.out.println(String.format("%2d Delete: %3d(%s)", i + 1, keys[i], rbt.remove(keys[i])));
//			rbt.printBreadthFirstSearch();
//			if ((i + 1) % 5 == 0) {
//				System.out.println(rbt);
//			} // if ((i + 1) % 5 == 0)
//		} // for (int i = 0; i < 10; i++)

		// test
		rbt.insert(34, "34");
		rbt.insert(64, "64");
		rbt.insert(129, "129");
		rbt.insert(187, "187");
		rbt.insert(92, "92");
		rbt.insert(32, "32");
		rbt.insert(162, "162");
		rbt.insert(188, "188");
		rbt.insert(77, "77");
		rbt.insert(37, "37");
		rbt.printBreadthFirstSearch();

		rbt.remove(34);
		System.out.println("removing 34");
		rbt.printBreadthFirstSearch();
		rbt.remove(64);
		System.out.println("removing 64");
		rbt.printBreadthFirstSearch();
		rbt.remove(129);
		System.out.println("removing 129");
		rbt.printBreadthFirstSearch();
		rbt.remove(187);
		System.out.println("removing 187");
		rbt.printBreadthFirstSearch();
		rbt.remove(92);
		System.out.println("removing 92");
		rbt.printBreadthFirstSearch();

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

		public boolean isLeftChild() {
			if (this.parent != null)
				return this == this.parent.left;
			else
				return false;
		}

		public boolean isRightChild() {
			if (this.parent != null)
				return this == this.parent.right;
			else
				return false;
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
