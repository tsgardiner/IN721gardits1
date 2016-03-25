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

    ArrayList<Question> questionsList;
    String[] nouns;
    String[] englishWords;
    String[] genders;

    public QuestionManager(String[] nouns, String[] englishWords, String[] genders){

        this.nouns = nouns;
        this.englishWords = englishWords;
        this.genders = genders;

        questionsList = new ArrayList<>();
    }

    public void buildQuestions()
    {


    }

}
