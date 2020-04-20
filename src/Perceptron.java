import java.util.List;

public class Perceptron {
    private double[] weights;
    private double learningRate = 0.1;
    private String language;

    public Perceptron(int weightsLength, String language) {
        weights = new double[weightsLength];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = Math.random() * 2 - 1;
        }
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public int guess(double[] inputs) {
        double sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += inputs[i] * weights[i];
        }
        return sum >= 0 ? 1 : 0;
    }

    public double getSum(double[] inputs) {
        double sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += inputs[i] * weights[i];
        }
        return sum;
    }

    public void train(List<double[]> vectors, String trainingLanguage) {
        for (double[] vector : vectors) {
            int target;
            if (language.equals(trainingLanguage)) target = 1;
            else target = 0;
            int error = target - guess(vector);
            if (error != 0) {
                for (int i = 0; i < weights.length; i++) {
                    weights[i] += error * vector[i] * learningRate;
                }
            }
        }
    }
}
