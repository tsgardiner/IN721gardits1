package bit.gardits1.moreonresources;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int dates[];
    String febFridayDates = "";
    TextView textString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        

        showDates();


    }

    void showDates(){
        Resources r = getResources();
        dates = r.getIntArray(R.array.FebFridays);
        textString = (TextView) findViewById(R.id.fridayTextBox);

        febFridayDates += "Feburay Fridays are on: ";
        for (int date : dates) {
            febFridayDates += Integer.toString(date);
            febFridayDates += " ";
        }

        textString.setText(febFridayDates);
        
    }
}
