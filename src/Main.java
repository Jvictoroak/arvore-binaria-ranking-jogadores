public class Main {
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        CSVReader.loadPlayersFromCSV("src/data/players.csv", binarySearchTree);
    }
}