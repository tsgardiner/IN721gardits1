package bit.gardits1.languagepreference;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    Editor prefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("prefsDemo", MODE_PRIVATE);
        prefsEditor = preferences.edit();

        Button bntSelect = (Button) findViewById(R.id.btnSetLanguage);
        bntSelect.setOnClickListener(new SetHandler());

        String languagePreference = preferences.getString("language", null);
        if (languagePreference != null) {
            String languageGreeting = getGreeting(languagePreference);
            TextView tvGreeting = (TextView) findViewById(R.id.tvChosenLanguage);
            tvGreeting.setText(languageGreeting);
        }


    }

    public class SetHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
            int checkedId = rg.getCheckedRadioButtonId();
            RadioButton checkedRB = (RadioButton) findViewById(checkedId);
            String selectedLanguage = checkedRB.getText().toString();

            prefsEditor.putString("language", selectedLanguage);
            prefsEditor.commit();

            String greeting = getGreeting(selectedLanguage);

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

}
