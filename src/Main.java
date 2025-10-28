import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String topic = JOptionPane.showInputDialog("Enter topic (java/dsa/os):");
        new QuizAppGUI(topic).setVisible(true);
    }
}

