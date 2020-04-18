import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, List<double[]>> trainingMap = new HashMap<>();
        Path path = Paths.get("training");
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            String key = null;

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                key = dir.getFileName().toString();
                if (!key.equals("training")) {
                    trainingMap.put(key, new ArrayList<>());
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                trainingMap.get(key).add(getVectorFromFile(file.toString()));
                return FileVisitResult.CONTINUE;
            }
        });
        NeuralNetwork neuralNetwork = new NeuralNetwork(trainingMap);
        neuralNetwork.train();
        new GUI(neuralNetwork);
    }

    private static double[] getVectorFromFile(String file) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(file));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine().toLowerCase());
        }
        return NeuralNetwork.getVectorFromString(sb.toString());
    }
}
