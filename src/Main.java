void main() {
    Player player1 = new Player("nickname01", 100);
    Player player2 = new Player("nickname02", 99);
    Player player3 = new Player("nickname03", 98);

    Node root = new Node(player1);
    Node esquerda = new Node(player2);
    Node direita = new Node(player3);
    root.left = esquerda;
    root.right = direita;

    BinarySearchTree binarySearchTree = new BinarySearchTree();

}
