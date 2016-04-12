package bit.gardits1.listviewjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDisplay = (Button) findViewById(R.id.btnFillList);
        listDisplay = (ListView) findViewById(R.id.lvDisplay);

        btnDisplay.setOnClickListener(new DisplayHandler());
    }

    public class DisplayHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

        }
    }




}
