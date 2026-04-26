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

        if (player.getRanking() < current.getPlayer().getRanking()) {
            current.setLeft(insert(current.getLeft(), player));
        } else if (player.getRanking() >= current.getPlayer().getRanking()) {
            current.setRight(insert(current.getRight(), player));
        }

        return current;
    }

    public boolean search(String name) {
        return search(root, name) != null;
    }

    private Node search(Node current, String name) {
        if (current == null) {
            return null;
        }

        if (current.getPlayer().getNickname().equals(name)) {
            return current;
        }

        Node foundInLeft = search(current.getLeft(), name);
        if (foundInLeft != null) {
            return foundInLeft;
        }

        return search(current.getRight(), name);
    }

    public Player remove(String name) {
        Node nodeToRemove = search(root, name);
        if (nodeToRemove == null) {
            return null;
        }
        Player removedPlayer = nodeToRemove.getPlayer();
        root = remove(root, name);
        return removedPlayer;
    }

    private Node remove(Node current, String name) {
        if (current == null) {
            return null;
        }

        if (current.getPlayer().getNickname().equals(name)) {
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }

            if (current.getLeft() == null) {
                return current.getRight();
            }

            if (current.getRight() == null) {
                return current.getLeft();
            }

            Node smallest = current.getRight();
            while (smallest.getLeft() != null) {
                smallest = smallest.getLeft();
            }

            current.setPlayer(smallest.getPlayer());
            current.setRight(remove(current.getRight(), smallest.getPlayer().getNickname()));

            return current;
        }
        current.setLeft(remove(current.getLeft(), name));
        current.setRight(remove(current.getRight(), name));

        return current;
    }

    public Node getRoot() {
        return root;
    }

}
