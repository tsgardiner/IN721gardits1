package bit.gardits1.musicschoolapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup instrumentGroup = (RadioGroup) findViewById(R.id.RadioGroup_Instruments);
        instrumentGroup.setOnClickListener(new RadioGroupListener());

    }

    public class RadioGroupListener implements RadioGroup
}
