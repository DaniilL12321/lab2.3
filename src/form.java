import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class form {
    private JPanel panel;
    private JButton button1;
    private JTextField numOfColumns;
    private JTextField numOfRows;
    private JPanel panel123;
    private JButton createNull;
    private JButton посчитатьButton;
    private JLabel label1;
    private JLabel label2;

    private JTextField[][] matrix;

    public form() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows = Integer.parseInt(numOfRows.getText());
                int columns = Integer.parseInt(numOfColumns.getText());

                panel123.removeAll();
                panel123.setLayout(new GridLayout(rows, columns));

                matrix = new JTextField[rows][columns];

                random(rows, columns);

                panel123.updateUI();

                // автовыполнение расчета
                int countNullColumns = countColumnsWithZeros(matrix);
                label1.setText("Количество столбцов с нулевыми элементами: " + countNullColumns);

                double maxElement = Double.MIN_VALUE;
                String maxElementRows = "";

                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        double currentElement = Double.parseDouble(matrix[i][j].getText());
                        if (currentElement > maxElement) {
                            maxElement = currentElement;
                            maxElementRows = String.valueOf(i);
                        } else if (currentElement == maxElement) {
                            maxElementRows += ", " + (i);
                        }
                    }
                }

                if (!maxElementRows.isEmpty()) {
                    label2.setText("Номера строк с максимальным элементом (" + maxElement + "): " + maxElementRows);
                } else {
                    label2.setText("Нет данных");
                }

            }
        });

        createNull.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows = Integer.parseInt(numOfRows.getText());
                int columns = Integer.parseInt(numOfColumns.getText());

                panel123.removeAll();
                panel123.setLayout(new GridLayout(rows, columns));

                matrix = new JTextField[rows][columns];

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        matrix[i][j] = new JTextField();
                        panel123.add(matrix[i][j]);
                    }
                }
                panel123.updateUI();
            }
        });

        посчитатьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int countNullColumns = countColumnsWithZeros(matrix);
                label1.setText("Количество столбцов с нулевыми элементами: " + countNullColumns);

                double maxElement = Double.MIN_VALUE;
                String maxElementRows = "";

                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        double currentElement = Double.parseDouble(matrix[i][j].getText());
                        if (currentElement > maxElement) {
                            maxElement = currentElement;
                            maxElementRows = String.valueOf(i);
                        } else if (currentElement == maxElement) {
                            maxElementRows += ", " + (i);
                        }
                    }
                }

                if (!maxElementRows.isEmpty()) {
                    label2.setText("Номера строк с максимальным элементом (" + maxElement + "): " + maxElementRows);
                } else {
                    label2.setText("Нет данных");
                }
            }
        });
    }

    private void random(int rows, int columns) {
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = new JTextField();
                matrix[i][j].setText(String.valueOf(random.nextInt(100)));
                panel123.add(matrix[i][j]);
            }
        }
    }

    private int countColumnsWithZeros(JTextField[][] matrix) {
        int count = 0;

        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] != null && matrix[i][j].getText().equals("0")) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Двумерные массивы");
        frame.setContentPane(new form().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(748, 500));
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.pack();
        frame.setVisible(true);
    }
}