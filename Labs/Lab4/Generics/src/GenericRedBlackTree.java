/**
 * Lab 4: Generics <br />
 * The {@code GenericRedBlackTree} class <br />
 * Reference: <a href="https://en.wikipedia.org/wiki/Red%E2%80%93black_tree">
 * https://en.wikipedia.org/wiki/Red%E2%80%93black_tree </a>
 * https://mcdtu.files.wordpress.com/2017/03/introduction-to-algorithms-3rd-edition-sep-2010.pdf
 * page 316
 * https://github.com/Arsenalist/Red-Black-Tree-Java-Implementation/blob/master/src/RedBlackTree.java
 */
public class GenericRedBlackTree<K extends Comparable<K>, V> {

	private int size = 0;

	private Node NIL = new Node(null, null);
	private Node root = NIL;

	GenericRedBlackTree() {
		this.root.left = NIL;
		this.root.right = NIL;
		this.root.parent = NIL;
	}

	public V find(K key) {
		return treeSearch(key).value;
	}

	public Node treeSearch(K key) {
		Node n = this.root;
		while (!isNil(n)) {
			if (key.compareTo(n.key) < 0) {
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

	public Node treeMin(Node x) {
		while (x.left != NIL)
			x = x.left;
		return x;
	}

	public Node treeMax(Node x) {
		while (x.right != NIL)
			x = x.right;
		return x;
	}

	public Node treeSuccessor(Node x) {
		if (x.right != NIL)
			return treeMin(x.right);
		Node y = x.parent;
		while (y != NIL && x == y.right) {
			x = y;
			y = y.parent;
		}
		return y;
	}

	public Node treePredecessor(Node x) {
		if (x.left != NIL)
			return treeMax(x.left);
		Node y = x.parent;
		while (y != NIL && x == y.left) {
			x = y;
			y = y.parent;
		}
		return y;
	}

	private boolean isNil(Node node) {
		return node == NIL;
	}

	public void transplant(Node u, Node v) {
		if (u.parent == NIL)
			this.root = v;
		else if (u.isLeftChild())
			u.parent.left = v;
		else
			u.parent.right = v;
		if (v != NIL)
			v.parent = u.parent;
	}

	private void leftRotate(Node x) {

		Node y;
		y = x.right;
		x.right = y.left;

		if (!isNil(y.left))
			y.left.parent = x;
		y.parent = x.parent;

		if (isNil(x.parent))
			root = y;

		else if (x.parent.left == x)
			x.parent.left = y;

		else
			x.parent.right = y;

		y.left = x;
		x.parent = y;
	}

	private void rightRotate(Node y) {

		Node x = y.left;
		y.left = x.right;

		if (!isNil(x.right))
			x.right.parent = y;
		x.parent = y.parent;

		if (isNil(y.parent))
			root = x;

		else if (y.parent.right == y)
			y.parent.right = x;

		else
			y.parent.left = x;
		x.right = y;

		y.parent = x;

	}

	@SafeVarargs
	public final void colourBlack(Node... nodes) {
		for (Node n : nodes)
			if (n != null) // nils are already black
				n.colour = Node.BLACK;
	}

	@SafeVarargs
	public final void colourRed(Node... nodes) {
		for (Node n : nodes)
			if (n != null)
				n.colour = Node.RED;
	}

	public void insert(K key, V value) {

		insert(new Node(key, value));
	}

	public void insert(Node z) {
		Node y = NIL;
		Node x = root;

		while (!isNil(x)) {
			y = x;

			if (z.key.compareTo(x.key) < 0)
				x = x.left;
			else
				x = x.right;
		}

		z.parent = y;

		if (isNil(y))
			root = z;
		else if (z.key.compareTo(y.key) < 0)
			y.left = z;
		else
			y.right = z;

		z.left = NIL;
		z.right = NIL;
		z.colour = Node.RED;

		insertFixup(z);
	}

	private void insertFixup(Node z) {

		Node y = NIL;

		while (z.parent.colour == Node.RED) {

			if (z.parent == z.parent.parent.left) {

				y = z.parent.parent.right;

				// Case 1: if y is red...recolour
				if (y.colour == Node.RED) {
					z.parent.colour = Node.BLACK;
					y.colour = Node.BLACK;
					z.parent.parent.colour = Node.RED;
					z = z.parent.parent;
				}
				// Case 2: if y is black & z is a right child
				else if (z == z.parent.right) {

					// leftRotaet around z's parent
					z = z.parent;
					leftRotate(z);
				}

				// Case 3: else y is black & z is a left child
				else {
					// recolour and rotate round z's grandpa
					z.parent.colour = Node.BLACK;
					z.parent.parent.colour = Node.RED;
					rightRotate(z.parent.parent);
				}
			}

			// If z's parent is the right child of it's parent.
			else {

				// Initialize y to z's cousin
				y = z.parent.parent.left;

				// Case 1: if y is red...recolour
				if (y.colour == Node.RED) {
					z.parent.colour = Node.BLACK;
					y.colour = Node.BLACK;
					z.parent.parent.colour = Node.RED;
					z = z.parent.parent;
				}

				// Case 2: if y is black and z is a left child
				else if (z == z.parent.left) {
					// rightRotate around z's parent
					z = z.parent;
					rightRotate(z);
				}
				// Case 3: if y is black and z is a right child
				else {
					z.parent.colour = Node.BLACK;
					z.parent.parent.colour = Node.RED;
					leftRotate(z.parent.parent);
				}
			}
		}
		// Color root black at all times
		root.colour = Node.BLACK;

	}

	public V remove(K key) {
		return remove(this.treeSearch(key));
	}

	public V remove(Node v) {

		Node z = this.treeSearch(v.key);
		if (z == null)
			return null;
		V val = z.value;

		Node x = NIL;
		Node y = NIL;

		if (isNil(z.left) || isNil(z.right))
			y = z;

		else
			y = treeSuccessor(z);

		if (!isNil(y.left))
			x = y.left;
		else
			x = y.right;

		x.parent = y.parent;

		if (isNil(y.parent))
			root = x;

		else if (!isNil(y.parent.left) && y.parent.left == y)
			y.parent.left = x;

		else if (!isNil(y.parent.right) && y.parent.right == y)
			y.parent.right = x;

		if (y != z) {
			z.key = y.key;
			z.value = y.value;
		}

		if (y.colour == Node.BLACK)
			removeFixup(x);
		size--;
		return val;
	}

	private void removeFixup(Node x) {

		Node w;

		while (x != root && x.colour == Node.BLACK) {

			// if x is it's parent's left child
			if (x == x.parent.left) {

				// set w = x's sibling
				w = x.parent.right;

				// Case 1, w's color is red.
				if (w.colour == Node.RED) {
					w.colour = Node.BLACK;
					x.parent.colour = Node.RED;
					leftRotate(x.parent);
					w = x.parent.right;
				}

				// Case 2, both of w's children are black
				if (w.left.colour == Node.BLACK && w.right.colour == Node.BLACK) {
					w.colour = Node.RED;
					x = x.parent;
				}
				// Case 3 / Case 4
				else {
					// Case 3, w's right child is black
					if (w.right.colour == Node.BLACK) {
						w.left.colour = Node.BLACK;
						w.colour = Node.RED;
						rightRotate(w);
						w = x.parent.right;
					}
					// Case 4, w = black, w.right = red
					w.colour = x.parent.colour;
					x.parent.colour = Node.BLACK;
					w.right.colour = Node.BLACK;
					leftRotate(x.parent);
					x = root;
				}
			}
			// if x is it's parent's right child
			else {

				// set w to x's sibling
				w = x.parent.left;

				// Case 1, w's color is red
				if (w.colour == Node.RED) {
					w.colour = Node.BLACK;
					x.parent.colour = Node.RED;
					rightRotate(x.parent);
					w = x.parent.left;
				}

				// Case 2, both of w's children are black
				if (w.right.colour == Node.BLACK && w.left.colour == Node.BLACK) {
					w.colour = Node.RED;
					x = x.parent;
				}

				// Case 3 / Case 4
				else {
					// Case 3, w's left child is black
					if (w.left.colour == Node.BLACK) {
						w.right.colour = Node.BLACK;
						w.colour = Node.RED;
						leftRotate(w);
						w = x.parent.left;
					}

					// Case 4, w = black, and w.left = red
					w.colour = x.parent.colour;
					x.parent.colour = Node.BLACK;
					w.left.colour = Node.BLACK;
					rightRotate(x.parent);
					x = root;
				}
			}
		}

		x.colour = Node.BLACK;
	}

	public int size() {
		return size;
	}

	public void printGivenLevel(Node root, int level) {
		if (root == null)
			return;
		if (level == 1)
			System.out.print(root);
		else if (level > 1) {
			printGivenLevel(root.left, level - 1);
			printGivenLevel(root.right, level - 1);
		}
	}

	public void printBreadthFirstSearch() {
		System.out.println("Breadth first search");
		for (int i = 1; i <= 10; i++) {
			this.printGivenLevel(this.root, i);
			System.out.println();
		}
	}

	public String inOrder(Node root) {
		return root == NIL ? ""
				: (root.left == NIL ? "" : inOrder(root.left)) + root + " "
						+ (root.right == NIL ? "" : inOrder(root.right));
	}

	@Override
	public String toString() {
		return this.inOrder(root);
	}

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
		System.out.println();

		for (int i = 0; i < 10; i++) {
			System.out.println(String.format("%2d Delete: %3d(%s)", i + 1, keys[i], rbt.remove(keys[i])));
			if ((i + 1) % 5 == 0) {
				System.out.println(rbt.root);
				System.out.println(rbt);
			} // if ((i + 1) % 5 == 0)
		} // for (int i = 0; i < 10; i++)
		
//		for (int i = 0; i < 10; i++) {
//			System.out.println(String.format("%2d Delete: %3d(%s)", i + 1, keys[i], rbt.remove(keys[i])));
//			;
//			rbt.printBreadthFirstSearch();
////			System.out.println(rbt);
//		}

	}

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

		public boolean isLeftChild() {
			return this == this.parent.left;
		}

		public boolean isRightChild() {
			return this == this.parent.right;
		}

		public void colourRed() {
			this.colour = RED;
		}

		public void colourBlack() {
			this.colour = BLACK;
		}

		public boolean isRED() {
			return this.colour == RED;
		}

		public boolean isBLACK() {
			return this.colour == BLACK;
		}

		@Override
		public String toString() {
			if (value == null)
				return "";
			return (colour == RED) ? "<" + value + "(" + key + ")>" : "[" + value + "(" + key + ")]";
		}
	}

}
