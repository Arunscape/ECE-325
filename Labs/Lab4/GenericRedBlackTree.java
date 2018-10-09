/**
 * Lab 4: Generics <br />
 * The {@code GenericRedBlackTree} class <br />
 * Reference: <a href="https://en.wikipedia.org/wiki/Red%E2%80%93black_tree">
 *              https://en.wikipedia.org/wiki/Red%E2%80%93black_tree
 *            </a>
 */
public class GenericRedBlackTree<K extends Comparable<K>, V> {

    /**
     * Root node of the red black tree
     */
    private Node root = null;

    /**
     * Size of the tree
     */
    private int size = 0;

    /**
     * Search for the node by key, and return the corresponding value
     * @param key       {@code K} the key for searching
     * @return          {@code V} the value of the node, or {@code NULL} if not found
     */
    public V find(K key) {
        // TODO: Lab 4 Part 3-1 -- find an element from the tree

        return null;
    }

    /**
     * Insert an element to the tree
     * @param key       {@code K} the key of the new element
     * @param value     {@code V} the value of the new element
     */
    public void insert(K key, V value) {
        // TODO: Lab 4 Part 3-2 -- insert an element into the tree

    }

    /**
     * Remove an element from the tree
     * @param key       {@code K} the key of the element
     * @return          {@code V} the value of the removed element
     */
    public V remove(K key) {
        // TODO: Lab 4 Part 3-3 -- remove an element from the tree
        
        return null;
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
        // TODO: Lab 4 Part 3-4 -- print the tree, where each node contains both value and color
        // You can print it by in-order traversal

        return null;
    }

    /**
     * Main entry
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        GenericRedBlackTree<Integer, String> rbt = new GenericRedBlackTree<Integer, String>();
        int[] keys = new int[10];
        for (int i = 0; i < 10; i++) {
            keys[i] = (int) (Math.random() * 200);
            System.out.println(String.format("%2d Insert: %-3d ", i+1, keys[i]));
            rbt.insert(keys[i], "\"" + keys[i] + "\"");
        } // for (int i = 0; i < 10; i++)

        assert rbt.root.color == GenericRedBlackTree.Node.BLACK;
        System.out.println(rbt.root);                   // This helps to figure out the tree structure
        System.out.println(rbt);

        for (int i = 0; i < 10; i++) {
            System.out.println(String.format("%2d Delete: %3d(%s)", i+1, keys[i], rbt.remove(keys[i])));
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
        public boolean color = BLACK;
        public Node parent = null, lChild = null, rChild = null;

        public Node(K key, V value) {                   // By default, a new node is black with two NIL children
            this.value = value;
            if (value != null) {
                lChild = new Node(null, null);          // And the NIL children are both black
                lChild.parent = this;
                rChild = new Node(null, null);
                rChild.parent = this;
            }
        }

        /**
         * Print the tree node: red node wrapped by "<>"; black node by "[]"
         * @return          {@code String} The printed string of the tree node
         */
        @Override public String toString() {
            if (value == null)
                return "";
            return (color == RED) ? "<" + value + "(" + key + ")>" : "[" + value + "(" + key + "]";
        }
    }

}
