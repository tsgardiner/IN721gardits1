package bit.gardits1.fileio;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> cityNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityNameList = new ArrayList<>();
        LoadCityNames();

        ListView cities = (ListView) findViewById(R.id.lvCitites);
        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityNameList);
        cities.setAdapter(citiesAdapter);
    }


    public void LoadCityNames()
    {
        String assetFileName = "city_names";

        try {
            AssetManager am = getAssets();
            InputStream is = am.open(assetFileName);
            InputStreamReader sr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(sr);

            String newCity;
            while ((newCity = br.readLine()) != null){
                cityNameList.add(newCity);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
