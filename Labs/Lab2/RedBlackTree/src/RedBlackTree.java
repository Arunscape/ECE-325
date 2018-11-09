/**
 * Lab 2: Debugging with Eclipse and Red Black Tree) <br />
 * The {@code RedBlackTree} class of integers only <br />
 * Reference: <a href="https://en.wikipedia.org/wiki/Red%E2%80%93black_tree">
 *              https://en.wikipedia.org/wiki/Red%E2%80%93black_tree
 *            </a>
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
     * @param value     {@code int} the value to be checked
     * @return          {@code boolean} If contains, return {@code true}, otherwise return {@code false}
     */
    public boolean contains(int value) {
        // TODO: Lab 2 Part 2-1 -- find an integer from the tree

        return false;
    }

    /**
     * Insert an integer to the tree
     * @param data      {@code int} New element to be inserted
     */
    public void insert(int value) {
        // RED BLACK INSERT
    	
    	this.size++;
    	
    	Node x = recursiveInsert(this.root, value);
    	Node y = x.parent.parent.right;
    	
    	while (x.parent == null && x.isRED()) {
    		if(x.parent == x.parent.parent.left) {
    			if(y.isRED()) {
    				x = this.case1(x,y);
    			}
    			else if (x == x.parent.right) {
    				this.case2(x,y);
    			}
    		}
    	}

    }
    
    private Node case1(Node x, Node y) {
    	System.out.println("Case1");
    	
    	this.colourBlack(x.parent, y);
    	this.colourRed(x.parent.parent);
    	return x.parent.parent;
    }
    
    private Node case2(Node x, Node y) {
    	System.out.println("Case 2");
    	
    	
    	return null;
    }
    
    public void colourBlack(Node ...nodes) {
    	for (Node n: nodes)
    		n.colour = BLACK;
    }
    
    public void colourRed(Node ...nodes) {
    	for (Node n: nodes)
    		n.colour = RED;
    }
    
    public Node recursiveInsert(Node root, int value) {
    	// BINARY SEARCH TREE RECURSIVE INSERT
    	if (root == null) {
    		return new Node(value);
    	}
    	
    	if (value < root.value) {
    		root.left = recursiveInsert(root.left, value);
    	}
    	else if (value > root.value) {
    		root.right = recursiveInsert(root.right, value);
    	}
    	return root;
    }
    
    public void rotateLeft(Node p) {
    	
    	Node n = p.right;
    	
//    	if (p == this.root) {
//    		this.root = n
//    	}
    	
    	p.right = n.left;
    	n.left.parent = p;
    	
    	commonRotateOperations(n,p);
    	n.left = p;
    	p.parent = n;
    	
    }
    
    public void rotateRight(Node p) {
    	
    	Node n = p.left;
    	
//    	if (p == this.root) {
//    		this.root = n;
//    	}
    	
    	p.left = n.right;
    	n.right.parent = p;
    	
    	commonRotateOperations(n, p);
    	n.right = p;
    	p.parent=n;
    	
    }
    n.left = n.left.right;
    public void commonRotateOperations(Node n, Node p) {
    	n.parent = p.parent;
    	p.parent.left = n;
    	
    	if (p == p.parent.left) {
    		p.parent.left=n;
    	}
    	else if (p == p.parent.right) {
    		p.parent.right=n;
    	}
    }

    /**
     * Get the size of the tree
     * @return          {@code int} size of the tree
     */
    public int size() {
        return size;
    }

    /**
     * Cast the tree into a string
     * @return          {@code String} Printed format of the tree
     */
    @Override public String toString() {
        // TODO: Lab 2 Part 2-3 -- print the tree, where each node contains both value and color
        // You can print it by in-order traversal

        return null;
    }

    /**
     * Main entry
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        for (int i = 0; i < 10; i++)
            rbt.insert((int) (Math.random() * 200));

        assert rbt.root.colour == RedBlackTree.Node.BLACK;
        System.out.println(rbt.root);           // This helps to figure out the tree structure
        System.out.println(rbt);
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

        public Node(Integer value) {             // By default, a new node is black with two NIL children
            this.value = value;
            if (value != null) {
                left = new Node(null);         // And the NIL children are both black
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

        /**
         * Print the tree node: red node wrapped by "<>"; black node by "[]"
         * @return          {@code String} The printed string of the tree node
         */
        @Override public String toString() {
            if (value == null)
                return "";
            return (color == RED) ? "<" + value + ">" : "[" + value + "]";
        }
    }

}
