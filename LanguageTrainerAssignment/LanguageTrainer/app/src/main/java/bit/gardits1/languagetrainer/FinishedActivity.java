package bit.gardits1.languagetrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FinishedActivity extends AppCompatActivity {

//    TODO Basic finished screen that displays score and option to play again.
//    Will be called by the QuestionManager class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);
    }
}
