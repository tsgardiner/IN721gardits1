package bit.gardits1.languagetrainer;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionDisplayActivity extends AppCompatActivity implements IConfirmResullt{

//    TODO All Questions will be displayed using this layout and functions
//    Will basically just change the Image based on the question loaded by the QuestionManager

    private Spinner answerSpinner;
    private TextView answerResult, tvDisplayCurrent;
    private ConfirmAnswer confirmAnswer;
    public Question currentQuestion = null;
    private int CURRENT_QUESTION_INDEX = 0; // Set to first question in questions list.
    private int CURRENT_SCORE = 0;
    private int NUMBER_OF_QUESTIONS = 11;
    Button btnAnswerQuestion;
    Button btnNextQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);

        currentQuestion = QuestionManager.questionsList.get(CURRENT_QUESTION_INDEX);

        //Image setup
        String img = currentQuestion.imageString;
        int id = getResources().getIdentifier(img, "drawable", getPackageName());
        ImageView questionImage = (ImageView) findViewById(R.id.imageView);
        if (questionImage != null) {
            questionImage.setImageResource(id);
        }

        //Enter button
        btnAnswerQuestion = (Button) findViewById(R.id.btnConfrimAns);
        //Next Question button
        btnNextQuestion = (Button) findViewById(R.id.btnNextQuestion);

        //Display Correct / Incorrect Text
        answerResult = (TextView) findViewById(R.id.tvCorrectIncorrect);
        //Display current question number
        updateQuestionDisplay();

        //Spinner setup
        answerSpinner = (Spinner) findViewById(R.id.ansSpinner);
        String[] choices = getResources().getStringArray(R.array.spinner_answers);
        int layoutId = android.R.layout.simple_spinner_dropdown_item;
        ArrayAdapter<String> choicesAdapter = new ArrayAdapter<>(this, layoutId, choices);
        answerSpinner.setAdapter(choicesAdapter);

        if (btnAnswerQuestion != null) {   //AS was complaining so let it put this in.
            btnAnswerQuestion.setOnClickListener(new OpenConfirmationHandler());
        }
        if (btnNextQuestion != null) {
            btnNextQuestion.setOnClickListener(new NextQuestionHandler());
        }

    }

    public class OpenConfirmationHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            if(answerSpinner.getSelectedItem().equals("Select an answer"))
                Toast.makeText(QuestionDisplayActivity.this, "Please select an answer.", Toast.LENGTH_SHORT).show();
            else{
                confirmAnswer = new ConfirmAnswer();
                FragmentManager fm = getFragmentManager();
                confirmAnswer.show(fm, "Answer");
            }
        }
    }

    public class NextQuestionHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            nextQuestion();
            
            if ((CURRENT_QUESTION_INDEX +1) == NUMBER_OF_QUESTIONS)
                btnNextQuestion.setText(R.string.seeresultsbutton); //Change text of next question button

            btnAnswerQuestion.setVisibility(View.VISIBLE); //Switch buttons over
            btnNextQuestion.setVisibility(View.INVISIBLE);
            answerSpinner.setSelection(0); //Reset Spinner
            answerResult.setText(""); //Clear result text
            answerSpinner.setEnabled(true); //Turn spinner back on
            updateQuestionDisplay();
        }
    }


    public void confirmationResult(Boolean confirmation) {
        confirmAnswer.dismiss();

        if (confirmation) {
            answerSpinner.setEnabled(false);
            if (currentQuestion.article.equals(answerSpinner.getSelectedItem().toString())) {
                answerResult.setText(R.string.correctText);

                int colourCorrect = ContextCompat.getColor(this, R.color.colorCorrect);
                answerResult.setTextColor(colourCorrect);

                CURRENT_SCORE++;
                QuestionManager.setScore(CURRENT_SCORE);
                btnAnswerQuestion.setVisibility(View.INVISIBLE);
                btnNextQuestion.setVisibility(View.VISIBLE);
            } else {
                answerResult.setText(R.string.incorrectText);
                answerResult.setTextColor(ContextCompat.getColor(this, R.color.colorIncorrect));
                btnAnswerQuestion.setVisibility(View.INVISIBLE);
                btnNextQuestion.setVisibility(View.VISIBLE);
            }
        }
    }

    public void updateQuestionDisplay()
    {
        tvDisplayCurrent = (TextView) findViewById(R.id.tvCurrentQuestion);
        tvDisplayCurrent.setText("Question:   " + (CURRENT_QUESTION_INDEX +1)  + " / " + NUMBER_OF_QUESTIONS);
    }

    public void nextQuestion()
    {
        int numQuestions = QuestionManager.questionsList.size();

        if ((CURRENT_QUESTION_INDEX +1) < numQuestions) {
            CURRENT_QUESTION_INDEX++;
            currentQuestion = QuestionManager.questionsList.get(CURRENT_QUESTION_INDEX);

            //Image setup
            String img = currentQuestion.imageString;
            int id = getResources().getIdentifier(img, "drawable", getPackageName());
            ImageView questionImage = (ImageView) findViewById(R.id.imageView);
            if (questionImage != null) {
                questionImage.setImageResource(id);
            }
        }
        else {
            Intent openFinishedScreen = new Intent(QuestionDisplayActivity.this, FinishedActivity.class);
            startActivity(openFinishedScreen);
        }
    }
}
