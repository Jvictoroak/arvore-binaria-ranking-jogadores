void main() {
    BinarySearchTree binarySearchTree = new BinarySearchTree();

    // Carrega os jogadores do CSV
    CSVReader.loadPlayersFromCSV("src/data/players.csv", binarySearchTree);
}
