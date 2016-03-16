package bit.gardits1.username;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
            Intent returnIntent = new Intent();


        }
    }
}
