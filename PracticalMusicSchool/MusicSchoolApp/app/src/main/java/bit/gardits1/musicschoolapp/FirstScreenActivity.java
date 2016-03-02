package bit.gardits1.musicschoolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        Button btnFirstScreen = (Button) findViewById(R.id.btnFirstScreen);
        btnFirstScreen.setOnClickListener(new btnListenerFirstScreen());

    }

    public class btnListenerFirstScreen implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent openCourseSelection = new Intent(FirstScreenActivity.this, MainActivity.class);
            startActivity(openCourseSelection);
        }
    }
}