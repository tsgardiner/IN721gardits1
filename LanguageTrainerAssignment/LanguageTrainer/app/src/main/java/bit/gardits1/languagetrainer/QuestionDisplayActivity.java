package bit.gardits1.languagetrainer;

import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class QuestionDisplayActivity extends AppCompatActivity implements IConfirmResullt{

//    TODO All Questions will be displayed using this layout and functions
//    Will basically just change the Image based on the question loaded by the QuestionManager

    private Spinner answerSpinner;
    private ConfirmAnswer confirmAnswer;
    public Question currentQuestion = null;
    private int CURRENT_QUESTION_INDEX = 0; // Set to first question in questions list.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);

        currentQuestion = QuestionManager.questionsList.get(CURRENT_QUESTION_INDEX);

        //Image setup
        String img = currentQuestion.imageString;
        int id = getResources().getIdentifier(img, "drawable", getPackageName());
        ImageView questionImage = (ImageView) findViewById(R.id.imageView);
        questionImage.setImageResource(id);

        Button answerQuestion = (Button) findViewById(R.id.btnConfrimAns);

        //Spinner setup
        answerSpinner = (Spinner) findViewById(R.id.ansSpinner);
        String[] choices = getResources().getStringArray(R.array.spinner_answers);
        int layoutId = android.R.layout.simple_spinner_dropdown_item;
        ArrayAdapter<String> choicesAdapter = new ArrayAdapter<>(this, layoutId, choices);
        answerSpinner.setAdapter(choicesAdapter);

        assert answerQuestion != null; //AS was complaining so let it put this in.
        answerQuestion.setOnClickListener(new OpenConfirmationHandler());

    }

    public class OpenConfirmationHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            if(answerSpinner.getSelectedItem().equals("Select an answer"))
                Toast.makeText(QuestionDisplayActivity.this, "Please select an answer.", Toast.LENGTH_LONG).show();
            else{
                confirmAnswer = new ConfirmAnswer();
                FragmentManager fm = getFragmentManager();
                confirmAnswer.show(fm, "Answer");
            }
        }
    }

    @Override
    public void confirmationResult(Boolean confirmation) {
        confirmAnswer.dismiss();

        String qArt = currentQuestion.article;
        String spinner = answerSpinner.getSelectedItem().toString();

        if (currentQuestion.article.equals(answerSpinner.getSelectedItem().toString())) {
            Toast.makeText(QuestionDisplayActivity.this, "Yay things work...correct", Toast.LENGTH_LONG).show();
            nextQuestion();
        }
        else
            Toast.makeText(QuestionDisplayActivity.this, "Incorrect", Toast.LENGTH_LONG).show();

    }

    public void nextQuestion()
    {
        CURRENT_QUESTION_INDEX++;
        currentQuestion = QuestionManager.questionsList.get(CURRENT_QUESTION_INDEX);

        //Image setup
        String img = currentQuestion.imageString;
        int id = getResources().getIdentifier(img, "drawable", getPackageName());
        ImageView questionImage = (ImageView) findViewById(R.id.imageView);
        questionImage.setImageResource(id);
    }
}
