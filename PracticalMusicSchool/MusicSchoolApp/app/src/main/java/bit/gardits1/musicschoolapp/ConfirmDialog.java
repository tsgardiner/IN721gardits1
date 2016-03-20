package bit.gardits1.musicschoolapp;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmDialog extends DialogFragment {


    public ConfirmDialog() {
        // Required empty public constructor
    }
    

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Enrollment");
        builder.setPositiveButton("Yes", new YesButtonHandler());
        builder.setNegativeButton("No", new NoButtonHandler());

        Dialog customDialog = builder.create();

        return customDialog;
    }

    public class YesButtonHandler implements DialogInterface.OnClickListener
    {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            MainActivity mainActivity = (MainActivity) getActivity();

            mainActivity.giveMeMyData(true);
        }
    }

    public class NoButtonHandler implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.giveMeMyData(false);
        }
    }

}
