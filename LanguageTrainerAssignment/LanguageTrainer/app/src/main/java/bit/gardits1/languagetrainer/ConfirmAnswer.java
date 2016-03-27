package bit.gardits1.languagetrainer;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Tim on 24/03/2016.
 */
public class ConfirmAnswer extends DialogFragment {
//    TODO Basic confirmation dialogFragment class using the builder
//    For the user to confirm that their entry is the one they wanted.
//    Will need to display chosen answer.

    public ConfirmAnswer(){} //Empty constructor

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm Answer");
        builder.setPositiveButton("Yes", new YesButtonHandler());
        builder.setNegativeButton("No", new NoButtonHandler());


        Dialog confrimDialog = builder.create();

        return confrimDialog;
    }

    public class YesButtonHandler implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            QuestionDisplayActivity questionDisplayActivity = (QuestionDisplayActivity) getActivity();
            questionDisplayActivity.confirmationResult(true);
        }
    }

    public class NoButtonHandler implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            QuestionDisplayActivity questionDisplayActivity = (QuestionDisplayActivity) getActivity();
            questionDisplayActivity.confirmationResult(false);
        }
    }
}


