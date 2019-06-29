import java.util.Random;

/**
 *
 * @author nokdoot
 */
public class Treap<T extends Comparable<T>> {

    private static final Random rand = new Random();
    public Node<T> root;

    public Node<T> findNth(int n) {
        return findNth(this.root, n);
    }

    private Node<T> findNth(Node<T> node, int n) {
        if ( n == 1 && node.left == null ) {
            return node;
        }
        
        int sizeOfLeft = 0;
        if ( node.left != null ) {
            sizeOfLeft += node.left.size;
        }
        int sizeOfLeftAndMid = sizeOfLeft + 1;
        
        if ( n == sizeOfLeftAndMid ) {
            return node;
        }
        if ( n < sizeOfLeftAndMid ) {
            return findNth(node.left, n);
        } else {
            return findNth(node.right, n - sizeOfLeftAndMid);
        }
    }

    public void insert(T element) {
        Node<T> node = new Node<>(element);
        insertNode(node);
    }

    private void insertNode(Node<T> newNode) {
        if ( this.isEmpty() ) {
            this.root = newNode;
            return;
        }
        this.root = insertNode(newNode, this.root);
    }

    private Node<T> insertNode(Node<T> newNode, Node<T> node) {
        if ( node == null ) {
            return newNode;
        }
        if ( node.priority < newNode.priority ) {
            if ( node.element.compareTo(newNode.element) < 0 ) {
                newNode.left = node;
            } else {
                newNode.right = node;
            }
            newNode.size = node.size + newNode.size;
            return newNode;
        } else {
            if ( node.element.compareTo(newNode.element) < 0 ) {
                node.right = insertNode(newNode, node.right);
            } else {
                node.left = insertNode(newNode, node.left);
            }
            node.size++;
            return node;
        }
    }

    public boolean isEmpty() {
        if ( root == null ) {
            return true;
        } else {
            return false;
        }
    }

    class Node<T extends Comparable<T>> {

        public T element;

        private final int priority = rand.nextInt(100);
        public int size = 1;

        public Node<T> left;
        public Node<T> right;

        public Node(T element) {
            this.element = element;
        }
    }
}
