package bit.gardits1.listviewjson;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listDisplay;
    ArrayList<String> jsonData;
    String JSONInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadDunedinEventsData();
        Button btnDisplay = (Button) findViewById(R.id.btnFillList);
        listDisplay = (ListView) findViewById(R.id.lvDisplay);

        btnDisplay.setOnClickListener(new DisplayHandler());


    }

    public class DisplayHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            try {
                JSONObject eventsData = new JSONObject(JSONInput);
                JSONObject events =  eventsData.getJSONObject("events");
                JSONArray eventArray = events.getJSONArray("event");

                Toast.makeText(MainActivity.this, eventArray.toString(), Toast.LENGTH_LONG).show();


            } catch (JSONException e) {
                e.printStackTrace();
            }

            }
    }

    public void LoadDunedinEventsData()
    {
        String assetFileName = "dunedin_events.json";

        try {
            AssetManager am = getAssets();
            InputStream is = am.open(assetFileName);

            int fileSineInBytes = is.available();
            byte[] JSONBuffer = new byte[fileSineInBytes];

            is.read(JSONBuffer);
            is.close();

            JSONInput = new String(JSONBuffer);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
