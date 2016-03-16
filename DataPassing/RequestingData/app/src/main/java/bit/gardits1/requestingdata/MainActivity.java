package bit.gardits1.requestingdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnChangeColour = (Button) findViewById(R.id.btnChangeColour);
        btnChangeColour.setOnClickListener(new ButtonChangeColourHandler());

    }


    public class ButtonChangeColourHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

        }
    }
}
