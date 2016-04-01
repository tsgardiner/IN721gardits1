package bit.gardits1.languagetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

        RelativeLayout clickScreen = (RelativeLayout) findViewById(R.id.rlClickableScreen);
        clickScreen.setOnTouchListener(new startHandler());
    }

    public class startHandler implements View.OnTouchListener
    {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Intent openQuestionGame = new Intent(StartScreenActivity.this, QuestionDisplayActivity.class);
            startActivity(openQuestionGame);
            return true;
        }
    }




    public void setQuestionManager()
    {
        nouns = getResources().getStringArray(R.array.nouns);
        englishWords = getResources().getStringArray(R.array.englishwords);
        questionManager = new QuestionManager(nouns, englishWords);
    }
}
