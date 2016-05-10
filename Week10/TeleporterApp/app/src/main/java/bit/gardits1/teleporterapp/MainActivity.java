package bit.gardits1.teleporterapp;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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


public class MainActivity extends AppCompatActivity implements LatLongRandom {

    double latitude;
    double longitude;
    int count = 0;
    ProgressDialog progressDialog;
    private Bitmap displayImage;
    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.ivCityImage);

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

    public void GenerateLocation()
    {
        GenerateLatitude();
        GenerateLongitude();
        DisplayLatLong();

        AsyncApiJson asyncApiJson = new AsyncApiJson();
        asyncApiJson.execute(String.valueOf(latitude), String.valueOf(longitude));
    }

    public class DisplayStuff implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            GenerateLocation();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.show();
        }
    }

    public class AsyncApiJson extends AsyncTask<String, Integer, String>
    {

        public AsyncApiJson()
        {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            progressDialog.setProgress(progress[0]);
        }

        @Override
        protected String doInBackground(String... params) {

            publishProgress((int) ((count / (float) 100)));

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
                JSONObject place = new JSONObject(fetchedString); //If fetchedString is [[]] it always throws an exception as it's not json...apparently.
                String cityName = place.optString("geoplugin_place");
                String country = place.optString("geoplugin_countryCode");

                if (cityName != null && country != null){
                    progressDialog.dismiss();
                    cityText.setText(cityName + " : " + country);

                    String cityNoWhiteSpace = cityName.replace(" ", "");
                    CityImageAsyncTask cityImageAsyncTask = new CityImageAsyncTask();
                    cityImageAsyncTask.execute(cityNoWhiteSpace);
                } else  {
                    cityText.setText("Not a location!");
                }
            } catch (JSONException e) {
                e.printStackTrace();
                //Toast.makeText(MainActivity.this, "Stupid exception thing for '[[]]'", Toast.LENGTH_SHORT).show();
                TextView cityText = (TextView) findViewById(R.id.tvCityName);
                cityText.setText("Not a location!");
                //If json exception is caught from [[]]. Generate new random locations and check for location again.
                GenerateLocation();
            }
        }


    }

    public class CityImageAsyncTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params) {
            String jsonString = null;
            String cityName = params[0];

            String url =    "https://api.flickr.com/services/rest/?" +
                    "method=flickr.photos.search&" +
                    "api_key=eda41a123d459be0f85276d37290651e&" +
                    "text=" +
                    cityName +
                    "&format=json&nojsoncallback=1";

            Log.e("Url", url);


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

        @Override
        protected void onPostExecute(String s) {
            String imageURL;
            Log.e("String", s);
            try {
                JSONObject cityImages = new JSONObject(s);
                JSONObject photos = cityImages.getJSONObject("photos");
                JSONArray photo = photos.getJSONArray("photo");
                JSONObject image = photo.getJSONObject(0);
                String farmId = image.getString("farm");
                String serverId = image.getString("server");
                String photoId = image.getString("id");
                String secret = image.getString("secret");
                String size = "n";

                imageURL = "https://farm" +
                        farmId +
                        ".staticflickr.com/" +
                        serverId + "/" +
                        photoId + "_" +
                        secret + "_" +
                        size + ".jpg";

                Log.e("Url", imageURL);

                AsyncCityImage asyncCityImage = new AsyncCityImage();
                asyncCityImage.execute(imageURL);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public class AsyncCityImage extends AsyncTask<String, Void, Bitmap>
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
        ImageView imageView = (ImageView) findViewById(R.id.ivCityImage);
        imageView.setImageBitmap(displayImage);
    }

}




