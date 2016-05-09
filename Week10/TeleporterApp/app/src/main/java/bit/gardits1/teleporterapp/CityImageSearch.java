package bit.gardits1.teleporterapp;

import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by Tim on 09/05/2016.
 */
public class CityImageSearch {

    public CityImageSearch()
    {

    }

    public class CityImageAsyncTask extends AsyncTask<String, Void, String>
    {


        @Override
        protected String doInBackground(String... params) {
            String jsonString = null;
            String cityName = params[0];

            String url =    "https://api.flickr.com/services/rest/?" +
                    "method=flickr.photos.search&" +
                    "api_key=42c4c2df25b50670ab964bef4372f3bd&" +
                    "text=" +
                    cityName +
                    "&format=json&nojsoncallback=1&api_sig=63142c6ae4b3e976834aa68d8d7b6f54";


            return jsonString;
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }


    public class ImageAsyncTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params) {
            String jsonString = null;
            String cityImageURL = params[0];

           
            return jsonString;
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }
}
