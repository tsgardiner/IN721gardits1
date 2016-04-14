package bit.gardits1.topartists;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> artistsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artistsList = new ArrayList<>();

        Button btnMakeList = (Button) findViewById(R.id.btnShowList);
        btnMakeList.setOnClickListener(new ShowArtistsList());

        listView = (ListView) findViewById(R.id.lvArtists);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, artistsList);
        listView.setAdapter(arrayAdapter);



    }

    public class ShowArtistsList implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            AsyncApiJson asyncApiJson = new AsyncApiJson();
            asyncApiJson.execute();
        }
    }

    public void CreateArtistListenerList(String fetchedString)
    {
        try {
            JSONObject topArtists = new JSONObject(fetchedString);
            JSONObject artists =  topArtists.getJSONObject("artists");
            JSONArray artistArray = artists.getJSONArray("artist");

            for (int i = 0; i < artistArray.length(); i++) {
                JSONObject currentArtist = artistArray.getJSONObject(i);
                String name = currentArtist.getString("name");
                String playcount = currentArtist.getString("playcount");

                artistsList.add(name + " " + playcount);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void CreateListViewOfArtists() {
        listView = (ListView) findViewById(R.id.lvArtists);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, artistsList);
        listView.setAdapter(arrayAdapter);
    }

    public class AsyncApiJson extends AsyncTask<Void, Void, String>
    {

        @Override
        protected String doInBackground(Void... params) {
            String jsonString = null;

            String url  =   "http://ws.audioscrobbler.com/2.0/" +
                            "?method=" +
                            "chart.getTopArtists" +
                            "&api_key=58384a2141a4b9737eacb9d0989b8a8c" +
                            "&limit=10" +
                            "&format=json";

            try {
                URL urlObject = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
                connection.connect();

                int responseCode = connection.getResponseCode();

                if (responseCode == 200) {
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader bufferedReader = new BufferedReader(isr);

                    String responseString;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((responseString = bufferedReader.readLine()) != null) {
                        stringBuilder = stringBuilder.append(responseString);
                    }
                    jsonString = stringBuilder.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonString;
        }


        protected void onPostExecute(String fetchedString)
        {
            CreateArtistListenerList(fetchedString);
            CreateListViewOfArtists();
            
            //TextView textView = (TextView) findViewById(R.id.tvTesting);
            //textView.setText(fetchedString);
        }
    }




}


