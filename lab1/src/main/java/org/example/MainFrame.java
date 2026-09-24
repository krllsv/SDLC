package lab1;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainFrame extends JFrame implements PropertyChangeListener {
    private final lab1.ProcrastinationModel model;
    private final JLabel lblResult = new JLabel("Результат: пока ничего не введено");
    private String lastEnteredValue = ""; // Память для восстановления последних данных

    public MainFrame(lab1.ProcrastinationModel model) {
        this.model = model;
        this.model.addPropertyChangeListener(this);

        setTitle("Утилита «Сколько раз ты сказал “потом”»");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnOpenInput = new JButton("Ввести данные");
        btnOpenInput.addActionListener(e -> openInputDialog());

        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        add(lblResult);
        add(btnOpenInput);
    }

    private void openInputDialog() {
        // Создаем диалоговое окно ввода, восстанавливая последнее значение[cite: 1]
        String input = (String) JOptionPane.showInputDialog(
                this,
                "Введите ваш возраст:",
                "Ввод данных",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                lastEnteredValue
        );

        if (input != null) {
            try {
                int parsedAge = Integer.parseInt(input.trim());
                if (parsedAge < 0 || parsedAge > 120) {
                    throw new NumberFormatException();
                }
                lastEnteredValue = input; // Запоминаем успешный ввод
                model.setAge(parsedAge);  // Передаем данные в модель
            } catch (NumberFormatException ex) {
                // Вывод сообщения об ошибке при некорректных данных[cite: 1]
                JOptionPane.showMessageDialog(
                        this,
                        "Ошибка! Введите корректное целое число (возраст от 0 до 120).",
                        "Некорректные данные",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Обновление интерфейса при изменении активной модели
        if ("laterCount".equals(evt.getPropertyName())) {
            lblResult.setText(String.format("Возраст: %d | Сказано «потом»: ~%d раз",
                    model.getAge(), model.getLaterCount()));
        }
    }
}