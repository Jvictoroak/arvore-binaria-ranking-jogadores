import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TreeVisualizer extends Application {

    private BinarySearchTree bst;
    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Visualizador de Árvore - CRUD Manual");

        bst = new BinarySearchTree();
        CSVReader.loadPlayersFromCSV("src/data/players.csv", bst);

        canvas = new Canvas(1200, 700);

        TextField nameField = new TextField();
        nameField.setPromptText("Nickname");

        TextField rankField = new TextField();
        rankField.setPromptText("Ranking");

        Label resultLabel = new Label("");

        Button insertButton = new Button("Inserir");
        Button removeButton = new Button("Remover");
        Button searchButton = new Button("Buscar");

        insertButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                int rank = Integer.parseInt(rankField.getText());

                Player p = new Player(name, rank);
                bst.insert(p);

                resultLabel.setText("Inserido: " + name);
                redraw();

            } catch (Exception ex) {
                resultLabel.setText("Erro ao inserir");
            }
        });

        removeButton.setOnAction(e -> {
            String name = nameField.getText();

            if (name != null && !name.isEmpty()) {
                bst.remove(name);
                resultLabel.setText("Removido: " + name);
                redraw();
            }
        });

        searchButton.setOnAction(e -> {
            String name = nameField.getText();

            if (name != null && !name.isEmpty()) {
                boolean found = bst.search(name);

                if (found) {
                    resultLabel.setText("Encontrado: " + name);
                } else {
                    resultLabel.setText("Não encontrado");
                }
            }
        });

        HBox inputs = new HBox(10);
        inputs.getChildren().addAll(nameField, rankField);

        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(insertButton, removeButton, searchButton);

        VBox root = new VBox(10);
        root.getChildren().addAll(inputs, buttons, resultLabel);

        ScrollPane scrollPane = new ScrollPane(new Group(canvas));

        VBox main = new VBox(10);
        main.getChildren().addAll(root, scrollPane);

        Scene scene = new Scene(main, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

        redraw();
    }

    private void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (bst.getRoot() == null) {
            gc.setFill(Color.RED);
            gc.fillText("Árvore vazia", 50, 50);
            return;
        }

        drawNode(gc, bst.getRoot(),canvas.getWidth() / 2,50,canvas.getWidth() / 4,1);
    }

    private void drawNode(GraphicsContext gc, Node node,double x, double y,double offset, int level) {

        if (node == null) return;

        if (node.getLeft() != null) {
            double nx = x - offset;
            double ny = y + 60;

            gc.setStroke(Color.GRAY);
            gc.strokeLine(x, y + 15, nx, ny - 15);

            drawNode(gc, node.getLeft(), nx, ny, offset / 2, level + 1);
        }

        if (node.getRight() != null) {
            double nx = x + offset;
            double ny = y + 60;

            gc.setStroke(Color.GRAY);
            gc.strokeLine(x, y + 15, nx, ny - 15);

            drawNode(gc, node.getRight(), nx, ny, offset / 2, level + 1);
        }

        gc.setFill(Color.LIGHTBLUE);
        gc.fillOval(x - 15, y - 15, 30, 30);

        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - 15, y - 15, 30, 30);

        gc.setFill(Color.BLACK);
        gc.setFont(new javafx.scene.text.Font(9));

        gc.fillText(node.getPlayer().getNickname(), x - 20, y - 18);
        gc.fillText(String.valueOf(node.getPlayer().getRanking()), x - 5, y + 5);
    }

    public static void main(String[] args) {
        launch(args);
    }
}