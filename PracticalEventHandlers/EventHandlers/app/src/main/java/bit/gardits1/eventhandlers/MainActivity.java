package bit.gardits1.eventhandlers;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button clickTestingButton = (Button) findViewById(R.id.EventTestingButton);
        clickTestingButton.setOnClickListener(new ButtonClickHandler());

    }

    public class LongClickHandler implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {

            return true;
        }
    }




    public class ButtonClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "You Clicked the Button", Toast.LENGTH_LONG).show();

        }

    }
}
