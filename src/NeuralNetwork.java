import java.util.*;

public class NeuralNetwork {
    Set<Map.Entry<String, List<double[]>>> trainingMapEntrySet;
    List<Perceptron> perceptrons = new ArrayList<>();

    public NeuralNetwork(Map<String, List<double[]>> trainingMap) {
        this.trainingMapEntrySet = trainingMap.entrySet();
        for (Map.Entry<String, List<double[]>> entry : trainingMapEntrySet) {
            perceptrons.add(new Perceptron(26, entry.getKey()));
        }
    }

    public void train() {
        for (int i = 0; i < 50; i++) {
            for (Map.Entry<String, List<double[]>> entry : trainingMapEntrySet) {
                for (Perceptron perceptron : perceptrons) {
                    perceptron.train(entry.getValue(), entry.getKey());
                }
            }
        }
    }

    public String guess(String text) {
        StringBuilder sb = new StringBuilder();
        Map<Perceptron, Double> sums = new HashMap<>();
        for (Perceptron perceptron : perceptrons) {
            sums.put(perceptron, perceptron.getSum(getVectorFromString(text)));
        }
        Perceptron winner = Collections.max(sums.entrySet(), Map.Entry.comparingByValue()).getKey();
        for (int i = 0; i < perceptrons.size(); i++) {
            Perceptron perceptron = perceptrons.get(i);
            if (perceptron.getLanguage().equals(winner.getLanguage())) {
                sb.append(i + 1).append(". ").append(perceptron.getLanguage().toUpperCase()).append("\n");
            } else {
                sb.append(i + 1).append(". nie ").append(perceptron.getLanguage()).append("\n");
            }
        }
        return sb.toString();
    }

    public static double[] getVectorFromString(String text) {
        char[] charTab = text.replaceAll("[^\\x00-\\x7F]", "").toCharArray();
        int[] count = new int[26];
        int sum = 0;
        int[] tmpTab = new int[127];
        double[] resultTab = new double[26];
        for (char c : charTab) {
            tmpTab[c]++;
        }
        for (int i = 97; i <= 122; i++) {
            count[i - 97] = tmpTab[i];
            sum += tmpTab[i];
        }
        for (int i = 0; i < count.length; i++) {
            resultTab[i] = (double)count[i] / sum;
        }
        return resultTab;
    }
}
