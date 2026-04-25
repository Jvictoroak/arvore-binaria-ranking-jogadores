void main() {
    Player player1 = new Player("nickname01", 100);
    Player player2 = new Player("nickname02", 99);
    Player player3 = new Player("nickname03", 98);

    BinarySearchTree binarySearchTree = new BinarySearchTree();

    binarySearchTree.insert(player1);
    binarySearchTree.insert(player2);
    binarySearchTree.insert(player3);
}
