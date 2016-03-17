package bit.gardits1.username;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnReturnResult = (Button) findViewById(R.id.btnBackToMain);
        btnReturnResult.setOnClickListener(new ReturnToMainHandler());
    }

    public class ReturnToMainHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent returnIntent = new Intent(SettingsActivity.this , MainActivity.class);

            EditText usernameText = (EditText) findViewById(R.id.etEnterUsername);

            String username = usernameText.getText().toString();

            if (username.length() < 5) {
                Toast.makeText(SettingsActivity.this, "Username must be at least 5 characters long", Toast.LENGTH_LONG).show();
            }
            else{
                returnIntent.putExtra("Username", username);
                startActivity(returnIntent);
            }
        }
    }
}
