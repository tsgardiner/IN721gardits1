package bit.gardits1.teleporterapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements LatLongRandom {

    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button display = (Button) findViewById(R.id.button);
        display.setOnClickListener(new DisplayStuff());


    }


    @Override
    public double GenerateRandomDouble(double min, double max) {
        double number = min + Math.random() * (max - min);
        return number;
    }

    @Override
    public void GenerateLatitude() {
        latitude = GenerateRandomDouble(-90, 90);
    }

    @Override
    public void GenerateLongitude() {
        longitude = GenerateRandomDouble(-180, 180);
    }

    @Override
    public void DisplayLatLong() {
        TextView lat = (TextView) findViewById(R.id.tvLatitudeDisplay);
        TextView lon = (TextView) findViewById(R.id.tvLongitudeDisplay);
        lat.setText(String.valueOf(latitude));
        lon.setText(String.valueOf(longitude));
    }

    public class DisplayStuff implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            GenerateLatitude();
            GenerateLongitude();
            DisplayLatLong();

            AsyncApiJson asyncApiJson = new AsyncApiJson();
            asyncApiJson.execute(String.valueOf(latitude), String.valueOf(longitude));
        }
    }



    public class AsyncApiJson extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params) {
            String jsonString = null;
            String lat = params[0];
            String lon = params[1];

            String url  =   "http://www.geoplugin.net/extras/location.gp?" +
                            "lat=" +
                            lat +
                            "&long=" +
                            lon +
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


        protected void onPostExecute(String fetchedString) {
            try {
                TextView cityText = (TextView) findViewById(R.id.tvCityName);
                JSONObject place = new JSONObject(fetchedString);
                String cityName = place.optString("geoplugin_place");
                String country = place.optString("geoplugin_countryCode");

                if (cityName != null && country != null){
                    cityText.setText(cityName + " : " + country);
                } else  {
                    cityText.setText("Not a location!");
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Stupid exception thing for '[[]]'", Toast.LENGTH_LONG).show();
            }
        }
    }

}




