package bit.gardits1.listviewjson;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    ArrayList<String> eventTitles;
    String JSONInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventTitles = new ArrayList<>();

        LoadDunedinEventsData();
        CreateEventTitleList();

        Button btnDisplay = (Button) findViewById(R.id.btnFillList);
        listDisplay = (ListView) findViewById(R.id.lvDisplay);


        listDisplay.setOnItemClickListener(new DisplayDescription());
        btnDisplay.setOnClickListener(new DisplayHandler());
    }

    public class DisplayHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            int id = android.R.layout.simple_list_item_1;
            ArrayAdapter<String> titlesAdapter = new ArrayAdapter<>(MainActivity.this, id, eventTitles);
            listDisplay.setAdapter(titlesAdapter);
            }
    }


    public class DisplayDescription implements AdapterView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int itemClicked = position;

            String description = GetEventDescription(itemClicked);

            if (description != null)
                Toast.makeText(MainActivity.this, description, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "No description", Toast.LENGTH_SHORT).show();
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

    public void ToastAllJsonData()
    {
        try {
            JSONObject eventsData = new JSONObject(JSONInput);
            JSONObject events =  eventsData.getJSONObject("events");
            JSONArray eventArray = events.getJSONArray("event");

            Toast.makeText(MainActivity.this, eventArray.toString(), Toast.LENGTH_LONG).show();

            } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void CreateEventTitleList()
    {
        try {
            JSONObject eventsData = new JSONObject(JSONInput);
            JSONObject events =  eventsData.getJSONObject("events");
            JSONArray eventArray = events.getJSONArray("event");

            for (int i = 0; i < eventArray.length(); i++) {
                JSONObject event = eventArray.getJSONObject(i);
                String eventTitle = event.getString("title");
                eventTitles.add(eventTitle);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String GetEventDescription(int listItem)
    {
        String eventDescription = null;

        try {
            JSONObject eventsData = new JSONObject(JSONInput);
            JSONObject events =  eventsData.getJSONObject("events");
            JSONArray eventArray = events.getJSONArray("event");
            JSONObject event = eventArray.getJSONObject(listItem);
            eventDescription = event.getString("description");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return eventDescription;
    }
}
