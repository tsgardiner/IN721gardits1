package bit.gardits1.languagetrainer;

import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Tim on 24/03/2016.
 */
public class QuestionManager {
//    TODO Create Manager that creates and looks after the list of questions.
//    Will need to be able to call shuffle on list
//    Needs to keep score
//    Needs control what happens between each screen

    public static ArrayList<Question> questionsList;
    String[] nouns;
    String[] englishWords;

    private String DER = "Der";
    private String DAS = "Das";
    private String DIE= "Die";
    private String Masculine = "Masculine";
    private String Neutral = "Neural";
    private String Feminine = "Feminine";


    public QuestionManager(String[] nouns, String[] englishWords){
        this.nouns = nouns;
        this.englishWords = englishWords;
        questionsList = new ArrayList<>();

        buildQuestions();
    }

    private void buildQuestions()
    {
        questionsList.add(new Question(englishWords[0], nouns[0], Masculine, DER));
        questionsList.add(new Question(englishWords[1], nouns[1], Neutral, DAS));
        questionsList.add(new Question(englishWords[2], nouns[2], Masculine , DER));
        questionsList.add(new Question(englishWords[3], nouns[3], Feminine , DIE));
        questionsList.add(new Question(englishWords[4], nouns[4],  Neutral, DAS));
        questionsList.add(new Question(englishWords[5], nouns[5], Feminine , DIE ));
        questionsList.add(new Question(englishWords[6], nouns[6],Feminine , DIE ));
        questionsList.add(new Question(englishWords[7], nouns[7], Feminine , DIE ));
        questionsList.add(new Question(englishWords[8], nouns[8], Neutral , DAS ));
        questionsList.add(new Question(englishWords[9], nouns[9], Feminine , DIE ));
        questionsList.add(new Question(englishWords[10], nouns[10], Masculine , DER));
    }

}
