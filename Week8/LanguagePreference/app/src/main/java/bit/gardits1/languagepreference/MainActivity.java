package bit.gardits1.languagepreference;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    Editor prefsEditor;
    private Spinner languageSpinner;
    private Spinner colourSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("prefsDemo", MODE_PRIVATE);
        prefsEditor = preferences.edit();

        Button bntSelect = (Button) findViewById(R.id.btnSetLanguage);
        bntSelect.setOnClickListener(new SetHandler());

        int layoutId = android.R.layout.simple_spinner_dropdown_item;
        //Language Spinner
        languageSpinner = (Spinner) findViewById(R.id.spinLaugage);
        String[] languages = getResources().getStringArray(R.array.languages);
        ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(this, layoutId, languages);
        languageSpinner.setAdapter(languageAdapter);

        //Colour Spinner
        colourSpinner = (Spinner) findViewById(R.id.spinColour);
        String[] colours = getResources().getStringArray(R.array.colours);
        ArrayAdapter<String> coloursAdapter = new ArrayAdapter<>(this, layoutId, colours);
        colourSpinner.setAdapter(coloursAdapter);

        CheckUpdateSettings();
    }

    public class SetHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String selectedLanguage = languageSpinner.getSelectedItem().toString();
            String selectedColour = colourSpinner.getSelectedItem().toString();

            prefsEditor.putString("textColour", selectedColour);
            prefsEditor.putString("language", selectedLanguage);
            prefsEditor.commit();

            CheckUpdateSettings();
        }
    }

    private String getGreeting(String language) {
        String greeting = "";

        switch (language) {
            case ("French"):
                greeting = "Bonjour Le Monde";
                break;
            case ("German"):
                greeting = "Hallo Welt";
                break;
            case ("Spanish"):
                greeting = "Hola Mundo";
                break;
            default:
                break;
        }
        return greeting;
    }


    private int getTextColour(String textColour) {
        int colourValue = 0;

        switch (textColour) {
            case ("Red"):
                colourValue = ContextCompat.getColor(this, R.color.colorRed);
                break;
            case ("Blue"):
                colourValue = ContextCompat.getColor(this, R.color.colorBlue);
                break;
            case ("Black"):
                colourValue = ContextCompat.getColor(this, R.color.colorBlack);
                break;
            default:
                break;
        }
        return colourValue;
    }

    private void CheckUpdateSettings()
    {
        String languagePreference = preferences.getString("language", null);
        String textColourPreference = preferences.getString("textColour", null);
        if ((languagePreference != null) && (textColourPreference != null)) {
            String languageGreeting = getGreeting(languagePreference);
            int colourValue = getTextColour(textColourPreference);
            TextView tvGreeting = (TextView) findViewById(R.id.tvChosenLanguage);
            tvGreeting.setText(languageGreeting);
            tvGreeting.setTextColor(colourValue);
        }
    }

}
