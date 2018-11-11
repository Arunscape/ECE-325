/**
 * Lab 2: Debugging with Eclipse and Red Black Tree) <br />
 * The {@code RedBlackTree} class of integers only <br />
 * Reference: <a href="https://en.wikipedia.org/wiki/Red%E2%80%93black_tree">
 * https://en.wikipedia.org/wiki/Red%E2%80%93black_tree </a>
 */

public class RedBlackTree {
	public static final boolean BLACK = true;
	public static final boolean RED = false;

	/**
	 * Root node of the red black tree
	 */
	private Node root = null;

	/**
	 * Size of the tree
	 */
	private int size = 0;

	/**
	 * Search the tree to find if the value is contained
	 * 
	 * @param value {@code int} the value to be checked
	 * @return {@code boolean} If contains, return {@code true}, otherwise return
	 *         {@code false}
	 */
	public boolean contains(int value) {
		// TODO: Lab 2 Part 2-1 -- find an integer from the tree

		return false;
	}

	/**
	 * Insert an integer to the tree
	 * 
	 * @param data {@code int} New element to be inserted
	 */
	public void insert(int value) {
		// RED BLACK INSERT
		this.size++;
		// BST Insert

		Node x = new Node(value);
		this.colourRed(x);

		bstInsert(x);

		fixRedBlackProperties(x);
	}

	public void bstInsert(Node n) {
		this.root = recursiveInsert(this.root, n);
	}

	public Node recursiveInsert(Node root, Node n) {

		if (root == null || root.value == null) {
			return n;
		} else if (n.value < root.value) {
			root.left = recursiveInsert(root.left, n);
			root.left.parent = root;
		} else if (n.value > root.value) {
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

	public void colourBlack(Node... nodes) {
		for (Node n : nodes)
			if (n != null && !n.isNil()) // nils are already black
				n.colour = BLACK;
	}

	public void colourRed(Node... nodes) {
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
	 * Get the size of the tree
	 * 
	 * @return {@code int} size of the tree
	 */
	public int size() {
		return size;
	}

	public String inOrder(Node root) {
		return (root.left == null ? "" : inOrder(root.left)) + root + " "
				+ (root.right == null ? "" : inOrder(root.right));
	}

	/**
	 * Cast the tree into a string
	 * 
	 * @return {@code String} Printed format of the tree
	 */
	@Override
	public String toString() {
		// TODO: Lab 2 Part 2-3 -- print the tree, where each node contains both value
		// and color
		// You can print it by in-order traversal

		return this.inOrder(this.root);
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
		for (int i = 1; i <= 5; i++) {
			this.printGivenLevel(this.root, i);
			System.out.println();
		}
	}

	/**
	 * Main entry
	 * 
	 * @param args {@code String[]} Command line arguments
	 */
	public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        for (int i = 0; i < 10; i++)
            rbt.insert((int) (Math.random() * 200));

        assert rbt.root.colour == RedBlackTree.Node.BLACK;
        System.out.println(rbt.root);           // This helps to figure out the tree structure
        System.out.println(rbt);

//		RedBlackTree rbt = new RedBlackTree();
//		rbt.insert(7);
//		rbt.printBreadthFirstSearch();
//		rbt.insert(3);
//		rbt.printBreadthFirstSearch();
//		rbt.insert(18);
//		rbt.printBreadthFirstSearch();
//		rbt.insert(10);
//		rbt.printBreadthFirstSearch();
//		rbt.insert(22);
//		rbt.printBreadthFirstSearch();
//		rbt.insert(8);
//		rbt.printBreadthFirstSearch();
//		rbt.insert(11);
//		rbt.printBreadthFirstSearch();
//		rbt.insert(26);
//		rbt.printBreadthFirstSearch();
//		rbt.insert(15);
//		rbt.printBreadthFirstSearch();

//		rbt.rotateRight(rbt.root.right);
////        rbt.rotateRight(rbt.root.right);
//
//		rbt.printBreadthFirstSearch();
	}

	/**
	 * The {@code Node} class for {@code RedBlackTree}
	 */
	private class Node {
		public static final boolean BLACK = true;
		public static final boolean RED = false;

		public Integer value;
		public boolean colour = BLACK;
		public Node parent = null, left = null, right = null;

		public Node(Integer value) { // By default, a new node is black with two NIL children
			this.value = value;
			if (value != null) {
				left = new Node(null); // And the NIL children are both black
				left.parent = this;
				right = new Node(null);
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
			return this.value == null;
		}

		public Node uncle() {
			return this.parent.parent.left == this.parent 
					? this.parent.parent.right
							: this.parent.parent.left;
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
			return (colour == RED) ? "<" + value + ">" : "[" + value + "]";
		}
	}

}
