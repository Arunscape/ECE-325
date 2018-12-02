/**
 * Lab 4: Generics <br />
 * The {@code GenericRedBlackTree} class <br />
 * Reference: <a href="https://en.wikipedia.org/wiki/Red%E2%80%93black_tree">
 * https://en.wikipedia.org/wiki/Red%E2%80%93black_tree </a>
 */
public class GenericRedBlackTree<K extends Comparable<K>, V> {

	private int size = 0;

	private Node root;
	private Node NIL;
	
	GenericRedBlackTree(){
		this.NIL = new Node(null, null);
		this.NIL.left = NIL;
		this.NIL.right = NIL;
		this.root = NIL;
	}

	public V find(K key) {
		return treeSearch(this.root, key).value;
	}

	public Node findNode(K key) {
		return treeSearch(this.root, key);
	}

	public Node treeSearch(Node x, K k) {
		if (x == NIL || k.equals(x.key))
			return x;
		if (k.compareTo(x.key) < 0)
			return treeSearch(x.left, k);
		else
			return treeSearch(x.right, k);
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

	public void leftRotate(Node x) {
		if (x == null || x.right == null || x.right == NIL || x == NIL)
			return;
		Node y = x.right;
		x.right = y.left;
		if (y.left != NIL)
			y.left.parent = x;
		y.parent = x.parent;
		if (x.parent == NIL)
			this.root = y;
		else if (x.isLeftChild())
			x.parent.left = y;
		else
			x.parent.right = y;
		y.left = x;
		x.parent = y;
	}

	public void rightRotate(Node x) {
		if (x == null || x.left == null || x.left == NIL || x == NIL)
			return;
		Node y = x.left;
		x.left = y.right;
		if (y.right != NIL)
			y.right.parent = x;
		y.parent = x.parent;
		if (x.parent == NIL)
			this.root = y;
		else if (x.isRightChild())
			x.parent.right = y;
		else
			x.parent.left = y;
		y.right = x;
		x.parent = y;
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

		Node x = this.root;
		Node y = NIL;
		Node z = new Node(key, value);

		while (x != NIL) {
			y = x;
			if (z.key.compareTo(x.key) < 0)
				x = x.left;
			else
				x = x.right;
		}
		z.parent = y;
		if (y == NIL)
			this.root = z;
		else if (z.key.compareTo(y.key) < 0)
			y.left = z;
		else
			y.right = z;
		z.left = NIL;
		z.right = NIL;
		z.colourRed();
		this.insertFixup(z);
		size++;
	}

	public void insertFixup(Node z) {
		while (z.parent.isRED()) {
			if (z.parent.isLeftChild()) {
				Node y = z.parent.parent.right;
				if (y.isRED()) {
					z.parent.colourBlack();
					y.colourBlack();
					z.parent.parent.colourRed();
					z = z.parent.parent;
				} else if (z.isRightChild()) {
					z = z.parent;
					this.leftRotate(z);
				}
				z.parent.colourBlack();
				colourRed(z.parent.parent);
//				z.parent.parent.colourRed();
				this.rightRotate(z.parent.parent);
			} else {
				Node y = z.parent.parent.left;
				if (y.isRED()) {
					z.parent.colourBlack();
					y.colourBlack();
					z.parent.parent.colourRed();
					z = z.parent.parent;
				} else if (z.parent.isLeftChild()) {
					z = z.parent;
					this.rightRotate(z);
				}
				z.parent.colourBlack();
//					z.parent.parent.colourRed();
				colourRed(z.parent.parent);
				this.leftRotate(z.parent.parent);
			}

		}
		this.root.colourBlack();
	}

	public V remove(K key) {
		Node n = this.findNode(key);
		if (n == null)
			return null;
		V v = n.value;
		this.rbDelete(n);;
		return v;
	}
	
	public void rbDelete(Node z) {
		Node x;
		Node y = z;
		boolean yOriginalColour = y.colour;
		
		if (z.left == NIL) {
			x = z.right;
			this.transplant(z, z.right);
		} else if (z.right == NIL) {
			x = z.left;
			this.transplant(z, z.left);
		} else {
			y = this.treeMin(z.right);
			yOriginalColour = y.colour;
			x = y.right;
			if (y.parent == z)
				x.parent = y;
			else {
				this.transplant(y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}
			this.transplant(z, y);
			y.left = z.left;
			y.left.parent = y;
			y.colour = z.colour;
		}
		if (yOriginalColour == Node.BLACK)
				this.deleteFixup(x);
		size--;
	}
	
	
	public void deleteFixup(Node x) {
		while (x != this.root && x.isBLACK()) {
			if (x.isLeftChild()) {
				Node w = x.parent.right;
				if (w.isRED()) {
					w.colourBlack();
					x.parent.colourRed();
					this.leftRotate(x.parent);
					w = x.parent.right;
				}

				if (w.left.isBLACK() && w.right.isBLACK()) {
					w.colourRed();
					x = x.parent;
				} else if (w.right.isBLACK()){
					w.left.colourBlack();
					w.colourRed();
					this.rightRotate(w);
					w = x.parent.right;
				}
				w.colour = x.parent.colour;
				x.parent.colourBlack();
				w.right.colourBlack();
				this.leftRotate(x.parent);
				x = this.root;
			} else {
				Node w = x.parent.left;
				if (w.isRED()) {
					w.colourBlack();
					x.parent.colourRed();
					this.rightRotate(x.parent);
					w = x.parent.left;
				}
				if (w.right.isBLACK() && w.left.isBLACK()) {
					w.colourRed();
					x = x.parent;
				} else if (w.left.isBLACK()){
					w.right.colourBlack();
					w.colourRed();
					this.rightRotate(w);
					w = x.parent.left;
				}
				w.colour = x.parent.colour;
				x.parent.colourBlack();
				w.left.colourBlack();
				this.leftRotate(x.parent);
				x = this.root;
			}
		}
		x.colourBlack();
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
		return root == null ? ""
				: (root.left == null ? "" : inOrder(root.left)) + root + " "
						+ (root.right == null ? "" : inOrder(root.right));
	}

	@Override
	public String toString() {
		return this.inOrder(root);
	}

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
////		for (int i = 0; i < 10; i++) {
////			System.out.println(String.format("%2d Delete: %3d(%s)", i + 1, keys[i], rbt.remove(keys[i])));
////			if ((i + 1) % 5 == 0) {
////				System.out.println(rbt);
////			} // if ((i + 1) % 5 == 0)
////		} // for (int i = 0; i < 10; i++)
//		
//		for (int i = 0; i < 10; i++) {
//			System.out.println(String.format("%2d Delete: %3d(%s)", i + 1, keys[i], rbt.remove(keys[i])));;
//			rbt.printBreadthFirstSearch();
//		}

		
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
		rbt.remove(37);
		System.out.println("removing 37");
		rbt.printBreadthFirstSearch();
		rbt.remove(77);
		rbt.remove(188);
		System.out.println("removing 188");
		rbt.printBreadthFirstSearch();
		rbt.remove(162);
		System.out.println("removing 162");
		rbt.printBreadthFirstSearch();
		rbt.remove(32);
		System.out.println("removing 32");
		rbt.printBreadthFirstSearch();
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
