public class BinarySearchTree {
    Node root;
    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(Player player) {
        root = insert(root, player);
    }

    private Node insert(Node current, Player player) {
        if (current == null) {
            return new Node(player);
        }

        if (player.getRanking() < current.player.getRanking()) {
            current.left = insert(current.left, player);
        } else if (player.getRanking() > current.player.getRanking()) {
            current.right = insert(current.right, player);
        }

        return current;
    }

    private Node remove(Node current, String name){
        return current;
    }
    public Player remove(String name){
        return null;
    }

    public boolean search(String name){
        return true;
    }
    private Node search(Node current, String name){
        return current;
    }
}
