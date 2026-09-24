package lab1;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            lab1.ProcrastinationModel model = new lab1.ProcrastinationModel();
            lab1.MainFrame frame = new lab1.MainFrame(model);
            frame.setVisible(true);
        });
    }
}