import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public static void loadPlayersFromCSV(String filePath, BinarySearchTree tree) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (!line.isEmpty()) {
                    String[] parts = line.split(",");

                    if (parts.length == 2) {
                        String nickname = parts[0].trim();
                        int ranking = Integer.parseInt(parts[1].trim());

                        tree.insert(new Player(nickname, ranking));
                        System.out.println("Inserido: " + nickname + " - " + ranking);
                    }
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
}