package bit.gardits1.doublefetchimagedisplay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String artistImage;
    Bitmap displayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artistImage = "";

        Button btnMakeList = (Button) findViewById(R.id.btnShowList);
        btnMakeList.setOnClickListener(new ShowArtistsList());
    }

    public class ShowArtistsList implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            AsyncApiJson asyncApiJson = new AsyncApiJson();
            asyncApiJson.execute();
        }
    }

    public class AsyncApiJson extends AsyncTask<Void, Void, String>
    {

        @Override
        protected String doInBackground(Void... params) {
            String jsonString = null;

            String url  =  "http://ws.audioscrobbler.com/2.0/" +
                    "?method=" +
                    "chart.getTopArtists" +
                    "&api_key=58384a2141a4b9737eacb9d0989b8a8c" +
                    "&limit=1" +
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonString;
        }


        protected void onPostExecute(String fetchedString)
        {
            GetArtistImage(fetchedString);
            AsyncArtistImage asyncArtistImage = new AsyncArtistImage();
            asyncArtistImage.execute(artistImage);
        }
    }


    public void GetArtistImage(String fetchedString)
    {
        try {
            JSONObject topArtists = new JSONObject(fetchedString);
            JSONObject artists =  topArtists.getJSONObject("artists");
            JSONArray artistArray = artists.getJSONArray("artist");
            JSONObject currentArtist = artistArray.getJSONObject(0); //FirstArtist
            JSONArray imageArray = currentArtist.getJSONArray("image");
            JSONObject image = imageArray.getJSONObject(2); //LargeImage

            artistImage = image.getString("#text");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class AsyncArtistImage extends AsyncTask<String, Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap image = null;

            String url = params[0];

            try {
                URL urlObject = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
                connection.connect();

                int responseCode = connection.getResponseCode();

                if (responseCode == 200) {
                    InputStream is = connection.getInputStream();

                    image = BitmapFactory.decodeStream(is);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return image;
        }


        protected void onPostExecute(Bitmap image)
        {
            displayImage = image;
            ShowImage();
        }
    }

    public void ShowImage() {
        ImageView imageView = (ImageView) findViewById(R.id.ivArtistImage);
        imageView.setImageBitmap(displayImage);
    }


}


