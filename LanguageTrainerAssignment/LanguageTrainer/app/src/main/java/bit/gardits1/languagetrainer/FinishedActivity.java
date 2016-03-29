package bit.gardits1.languagetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class FinishedActivity extends AppCompatActivity {

//    TODO Basic finished screen that displays score and option to play again.
//    Will be called by the QuestionManager class


    double currentScore = QuestionManager.getScore();
    double numQuestions = QuestionManager.questionsList.size();
    double percentScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);

        Button playAgain = (Button) findViewById(R.id.btnPlayAgain);
        TextView score = (TextView) findViewById(R.id.tvScore);

        percentScore = (currentScore / numQuestions) * 100;

        score.setText(String.format("%.2f", percentScore) + "%");

        if (playAgain != null) {
            playAgain.setOnClickListener(new PlayAgainHandler());
        }

    }

    public class PlayAgainHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Intent startNewGame = new Intent(FinishedActivity.this, StartScreenActivity.class);
            startActivity(startNewGame);
        }
    }
}
