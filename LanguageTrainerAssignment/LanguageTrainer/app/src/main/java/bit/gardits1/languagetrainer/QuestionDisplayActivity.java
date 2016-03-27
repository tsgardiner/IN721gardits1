package bit.gardits1.languagetrainer;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class QuestionDisplayActivity extends AppCompatActivity implements IConfirmResullt{

//    TODO All Questions will be displayed using this layout and functions
//    Will basically just change the Image based on the question loaded by the QuestionManager

    private Spinner answerSpinner;
    private ConfirmAnswer confirmAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);

        Button answerQuestion = (Button) findViewById(R.id.btnConfrimAns);

        //Spinner setup
        answerSpinner = (Spinner) findViewById(R.id.ansSpinner);
        String[] choices = getResources().getStringArray(R.array.spinner_answers);
        int layoutId = android.R.layout.simple_spinner_dropdown_item;
        ArrayAdapter<String> choicesAdapter = new ArrayAdapter<>(this, layoutId, choices);
        answerSpinner.setAdapter(choicesAdapter);

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

    }
}
