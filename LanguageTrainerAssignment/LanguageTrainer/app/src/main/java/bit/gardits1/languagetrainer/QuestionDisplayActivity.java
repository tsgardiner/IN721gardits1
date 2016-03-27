package bit.gardits1.languagetrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class QuestionDisplayActivity extends AppCompatActivity implements IConfirmResullt{

//    TODO All Questions will be displayed using this layout and functions
//    Will basically just change the Image based on the question loaded by the QuestionManager

    private Spinner answerSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);

        Button answerButton = (Button) findViewById(R.id.btnConfrimAns);

        //Spinner setup
        answerSpinner = (Spinner) findViewById(R.id.ansSpinner);
        String[] choices = getResources().getStringArray(R.array.spinner_answers);
        int layoutId = android.R.layout.simple_spinner_dropdown_item;
        ArrayAdapter<String> choicesAdapter = new ArrayAdapter<>(this, layoutId, choices);
        answerSpinner.setAdapter(choicesAdapter);



    }

    @Override
    public void confirmationResult(Boolean confirmation) {

    }
}
