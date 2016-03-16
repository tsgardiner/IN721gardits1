package bit.gardits1.username;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGoToSettings = (Button) findViewById(R.id.btnSettings);
        btnGoToSettings.setOnClickListener(new buttonGoToSettingsHandler());

        Intent launchIntent = getIntent();

        if (findViewById(R.id.tvUsername).toString() == getResources().getString(R.string.username_text))
        {
            Toast.makeText(this,"Please Enter a Username", Toast.LENGTH_SHORT).show();
        }
        else {
            TextView usernameText = (TextView) findViewById(R.id.tvUsername);
            usernameText.setText(launchIntent.getStringExtra("Username"));
        }
    }


    public class buttonGoToSettingsHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(MainActivity.this, SettingsActivity.class);

            startActivity(changeActivityIntent);
        }
    }
}
