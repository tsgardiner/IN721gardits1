package bit.gardits1.languagetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;

public class StartScreenActivity extends AppCompatActivity {

//    TODO Basic Start the game screen
//    First user interaction with the app

    QuestionManager questionManager;
    String[] nouns;
    String[] englishWords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        setQuestionManager();

        Button startButton = (Button) findViewById(R.id.btnStart);
        startButton.setOnClickListener(new startButtonHandler());
    }

    public class startButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent openQuestionGame = new Intent(StartScreenActivity.this, QuestionDisplayActivity.class);
            startActivity(openQuestionGame);
        }
    }


    public void setQuestionManager()
    {
        nouns = getResources().getStringArray(R.array.nouns);
        englishWords = getResources().getStringArray(R.array.englishwords);

        questionManager = new QuestionManager(nouns, englishWords);
    }
}
