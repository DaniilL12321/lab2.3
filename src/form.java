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

    public form() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows = Integer.parseInt(numOfRows.getText());
                int columns = Integer.parseInt(numOfColumns.getText());

                panel123.removeAll();
                panel123.setLayout(new GridLayout(rows, columns));

                random(rows, columns);

                panel123.updateUI();
            }
        });

        createNull.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows = Integer.parseInt(numOfRows.getText());
                int columns = Integer.parseInt(numOfColumns.getText());

                panel123.removeAll();
                panel123.setLayout(new GridLayout(rows, columns));
                panel123.setLocation(0,0);
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        panel123.add(new TextField());
                    }
                }
                panel123.updateUI();
            }
        });
        посчитатьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static int countColsWithZeros(int[][] matrix) { // метод для поиска стобцов с нулями и вывода их количества
        // инициализация переменных
        int rows = matrix.length;
        int cols = matrix[0].length;
        int count = 0; // количество столбцов в которых есть нули

        for (int j = 0; j < cols; j++) { // перебор столбцов пока не закончатся строки
            boolean hasZero = false;
            for (int i = 0; i < rows; i++) {
                if (matrix[i][j] == 0) { // проверка каждого значения на 0
                    hasZero = true;
                    break;
                }
            }
            if (hasZero) { // прибавление 1, если найден 0
                count++;
            }
        }
        return count; // возвращаем количество строк с нулями
    }

    private void random(int rows, int columns) {
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                TextField textField = new TextField();
                textField.setText(String.valueOf(random.nextInt(100)));
                panel123.add(textField);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Form");
        frame.setContentPane(new form().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 400));
        frame.pack();
        frame.setVisible(true);
    }
}
