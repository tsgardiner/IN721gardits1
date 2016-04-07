package bit.gardits1.musicschoolapp;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IGiveData {

    private Spinner monthSpinner;
    ConfirmDialog confirmDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button confirmEnrolment = (Button) findViewById(R.id.confirmButton);


        monthSpinner = (Spinner) findViewById(R.id.spinnerMonths);
        String[] month = getResources().getStringArray(R.array.month);
        int layoutId = android.R.layout.simple_spinner_dropdown_item;

        ArrayAdapter<String> monthsAdapter = new ArrayAdapter<>(this, layoutId, month);
        monthSpinner.setAdapter(monthsAdapter);

        confirmEnrolment.setOnClickListener(new CreateFragmentButtonHandler());

    }

    @Override
    public void giveMeMyData(Boolean confirmation) {

        confirmDialog.dismiss();

        RadioGroup instrumentGroup = (RadioGroup) findViewById(R.id.RadioGroup_Instruments);
        int selectedId = instrumentGroup.getCheckedRadioButtonId();
        RadioButton selectedInstrument = (RadioButton) findViewById(selectedId);

        if (confirmation)
            Toast.makeText(MainActivity.this, "You have enrolled for " + selectedInstrument.getText() + " lessons on " + monthSpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "Enrollment Canceled", Toast.LENGTH_LONG).show();
    }


    public class CreateFragmentButtonHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            RadioGroup instrumentGroup = (RadioGroup) findViewById(R.id.RadioGroup_Instruments);
            int selectedId = instrumentGroup.getCheckedRadioButtonId();

            if (selectedId == -1)
                Toast.makeText(MainActivity.this, "No instrument selected.", Toast.LENGTH_SHORT).show();
            else if (monthSpinner.getSelectedItem().equals("Select Month"))
                Toast.makeText(MainActivity.this, "Please choose a month.", Toast.LENGTH_SHORT).show();
            else {
                confirmDialog = new ConfirmDialog();
                FragmentManager fm = getFragmentManager();
                confirmDialog.show(fm, "confirmDialog");
            }
        }
    }

}
