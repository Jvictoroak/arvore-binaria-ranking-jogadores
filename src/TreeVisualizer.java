import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TreeVisualizer extends Application {

    private BinarySearchTree bst;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Visualizador de Arvore - Ranking de Jogadores");

        bst = new BinarySearchTree();

        CSVReader.loadPlayersFromCSV("src/data/players.csv", bst);

        int height = getHeight(bst.getRoot());

        int canvasHeight = 100 + height * 60;
        int canvasWidth = 200 + countNodes(bst.getRoot()) * 30;

        if (canvasWidth > 8000) canvasWidth = 8000;
        if (canvasHeight > 8000) canvasHeight = 8000;

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);

        drawTree(canvas);

        ScrollPane scrollPane = new ScrollPane();
        Group group = new Group();
        group.getChildren().add(canvas);
        scrollPane.setContent(group);

        Scene scene = new Scene(scrollPane, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }

    private int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
    }

    public void drawTree(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (bst.getRoot() == null) {
            System.out.println("Raiz da arvore esta NULL! Não ha o que desenhar.");
            return;
        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        drawNode(gc, bst.getRoot(), canvas.getWidth() / 2, 40, canvas.getWidth() / 4, 1);
    }

    private void drawNode(GraphicsContext gc, Node node, double x, double y, double xOffset, int level) {
        if (node == null) {
            return;
        }

        if (node.getLeft() != null) {
            double newX = x - xOffset;
            double newY = y + 60;
            gc.setStroke(Color.GRAY);
            gc.setLineWidth(1);
            gc.strokeLine(x, y + 15, newX, newY - 15);
            drawNode(gc, node.getLeft(), newX, newY, xOffset / 2, level + 1);
        }

        if (node.getRight() != null) {
            double newX = x + xOffset;
            double newY = y + 60;
            gc.setStroke(Color.GRAY);
            gc.setLineWidth(1);
            gc.strokeLine(x, y + 15, newX, newY - 15);
            drawNode(gc, node.getRight(), newX, newY, xOffset / 2, level + 1);
        }

        gc.setFill(Color.LIGHTBLUE);
        gc.fillOval(x - 15, y - 15, 30, 30);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeOval(x - 15, y - 15, 30, 30);

        gc.setFill(Color.BLACK);
        gc.setFont(new javafx.scene.text.Font(8));
        String player = node.getPlayer().getNickname();
        gc.fillText(player, x - 30, y - 20);

        String rank = String.valueOf(node.getPlayer().getRanking());
        gc.setFill(Color.BLACK);
        gc.fillText(rank, x - 5, y + 5);
    }

    public static void main(String[] args) {
        launch(args);
    }
}