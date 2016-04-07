package bit.gardits1.requestingdata;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
            Intent changeIntent = new Intent(MainActivity.this, ColourChanger.class);

            startActivityForResult(changeIntent, 0); //Should normally make integers xml for result id
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if ((requestCode == 0) && (resultCode == Activity.RESULT_OK)) {
            String colorResult = data.getStringExtra("requestedResult");

            TextView textToChange = (TextView) findViewById(R.id.tvBodyText);
            textToChange.setTextColor(Color.parseColor(colorResult));
        }
    }

}
