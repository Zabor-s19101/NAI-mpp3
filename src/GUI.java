import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUI {
    public GUI(NeuralNetwork neuralNetwork) {
        JFrame frame = new JFrame("NAI-mpp3");

        JTextArea textArea = new JTextArea();
        JScrollPane textAreaScrollPane = new JScrollPane(textArea);
        textAreaScrollPane.setPreferredSize(new Dimension(600, 800));
        frame.getContentPane().add(textAreaScrollPane, BorderLayout.CENTER);

        JPanel outputs = new JPanel();
        outputs.setPreferredSize(new Dimension(200, 800));
        outputs.setLayout(new BoxLayout(outputs, BoxLayout.PAGE_AXIS));
        outputs.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane outputTextAreaScrollPane = new JScrollPane(outputTextArea);
        outputs.add(outputTextAreaScrollPane);

        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextArea.setText(neuralNetwork.guess(textArea.getText()));
            }
        });
        guessButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        outputs.add(Box.createVerticalStrut(5));
        outputs.add(guessButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                outputTextArea.setText("");
            }
        });
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        outputs.add(Box.createVerticalStrut(5));
        outputs.add(clearButton);

        frame.getContentPane().add(outputs, BorderLayout.EAST);

        frame.pack();
        frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frame.getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - frame.getHeight()) / 2);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
