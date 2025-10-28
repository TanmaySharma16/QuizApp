import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class QuizAppGUI extends JFrame {
    private QuizManager manager = new QuizManager();
    private int current = 0, score = 0;
    private List<Question> questions;
    private JLabel questionLabel;
    private JRadioButton[] options = new JRadioButton[4];
    private ButtonGroup group = new ButtonGroup();
    private JButton nextButton;

    public QuizAppGUI(String topic) {
        setTitle("Quiz App - " + topic);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        manager.loadQuestions(topic);
        questions = manager.getQuestions();

        questionLabel = new JLabel();
        add(questionLabel);

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            add(options[i]);
        }

        nextButton = new JButton("Next");
        add(nextButton);

        loadQuestion();

        nextButton.addActionListener(e -> {
            int selected = -1;
            for (int i = 0; i < 4; i++) {
                if (options[i].isSelected()) selected = i;
            }
            if (selected == questions.get(current).getCorrectIndex()) score++;
            current++;
            if (current < questions.size()) {
                loadQuestion();
            } else {
                JOptionPane.showMessageDialog(this, "Quiz Over! Score: " + score + "/" + questions.size());
                System.exit(0);
            }
        });
    }

    private void loadQuestion() {
        Question q = questions.get(current);
        questionLabel.setText(q.getQuestionText());
        String[] opts = q.getOptions();
        for (int i = 0; i < 4; i++) {
            options[i].setText(opts[i]);
            options[i].setSelected(false);
        }
    }
}
