package bit.gardits1.topartists;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMakeList = (Button) findViewById(R.id.btnShowList);
        btnMakeList.setOnClickListener(new ShowArtistsList());

        listView = (ListView) findViewById(R.id.lvArtists);



    }

    public class ShowArtistsList implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

        }
    }

    public class AsynApiJson extends AsyncTask<Void, Void, String>
    {

        @Override
        protected String doInBackground(Void... params) {
            String jsonString = null;

            String url  =   "http://ws.audioscrobbler.com/2.0/;" +
                    "?method=artist.getTopTracks&artist=;" +
                    "Sia;" +
                    "&api_key=58384a2141a4b9737eacb9d0989b8a8c&limit=10;" +
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

        }
    }


}


