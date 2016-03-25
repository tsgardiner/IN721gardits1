package bit.gardits1.languagetrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;

public class StartScreenActivity extends AppCompatActivity {

//    TODO Basic Start the game screen
//    First user interaction with the app

    QuestionManager questionManager;
    String[] nouns;
    String[] genders;
    String[] englishWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        nouns = getResources().getStringArray(R.array.nouns);
        genders = getResources().getStringArray(R.array.genders);
        englishWords = getResources().getStringArray(R.array.english_words);

        questionManager = new QuestionManager(nouns, englishWords, genders);
    }
}
