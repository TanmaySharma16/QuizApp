import java.io.*;
import java.util.*;

public class QuizManager {
    private List<Question> questions = new ArrayList<>();

    public void loadQuestions(String topic) {
        questions.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("src/questions/" + topic + ".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String question = line;
                String[] options = br.readLine().split(";");
                int correct = Integer.parseInt(br.readLine());
                questions.add(new Question(question, options, correct));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
