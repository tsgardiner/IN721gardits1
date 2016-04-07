package bit.gardits1.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    DatabaseBuilder databaseBuilder;
    SQLiteDatabase cityCountryDB;
    ListView lvCities;
    Spinner spCountries;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityCountryDB = openOrCreateDatabase("cityCountryDB", MODE_PRIVATE, null);
        databaseBuilder = new DatabaseBuilder(cityCountryDB);

        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        lvCities = (ListView) findViewById(R.id.lvCities);
        spCountries = (Spinner) findViewById(R.id.spCountries);

        //DatabaseListViewTest();
        SetCountrySpinner();

        assert btnSearch != null;
        btnSearch.setOnClickListener(new SearchSelectedCountry());

    }

    public class SearchSelectedCountry implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            String searchedCountry = spCountries.getSelectedItem().toString();
            DisplayCities(searchedCountry);
        }
    }


    public void DisplayCities(String countrySearched)
    {
        String selectCountry = "SELECT cityName from tblCity WHERE countryName LIKE ";
        selectCountry += "'" + countrySearched + "'";
        Cursor citySet = cityCountryDB.rawQuery(selectCountry, null);

        int cityCount = citySet.getCount();
        String[] displayCityArray = new String[cityCount];

        int cityNameIndex = citySet.getColumnIndex("cityName");

        citySet.moveToFirst();

        for (int i = 0; i < cityCount; i++)
        {
            String cityName = citySet.getString(cityNameIndex);
            displayCityArray[i] = cityName;
            citySet.moveToNext();
        }

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayCityArray);
        lvCities.setAdapter(cityAdapter);
    }


    public void SetCountrySpinner()
    {
        String selectCountry = "SELECT DISTINCT countryName from tblCity";
        Cursor countrySet = cityCountryDB.rawQuery(selectCountry, null);

        int countryCount = countrySet.getCount();
        String[] displayCountryArray = new String[countryCount];

        int countryNameIndex = countrySet.getColumnIndex("countryName");

        countrySet.moveToFirst();

        for (int i = 0; i < countryCount; i++)
        {
            String countryName = countrySet.getString(countryNameIndex);
            displayCountryArray[i] = countryName;
            countrySet.moveToNext();
        }

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, displayCountryArray);
        spCountries.setAdapter(countryAdapter);
    }




    //Testing if the database displays correct data.
    public void DatabaseListViewTest()
    {
        String selectQuery = "SELECT * FROM tblCity";
        Cursor recordSet = cityCountryDB.rawQuery(selectQuery, null);

        int recordCount = recordSet.getCount();
        String[] displayStringArray = new String[recordCount];

        int cityNameIndex = recordSet.getColumnIndex("cityName");
        int countryNameIndex = recordSet.getColumnIndex("countryName");

        recordSet.moveToFirst();

        for (int r = 0; r < recordCount; r++)
        {
            String cityName = recordSet.getString(cityNameIndex);
            String countryName = recordSet.getString(countryNameIndex);
            displayStringArray[r] = cityName + ", " + countryName;

            recordSet.moveToNext();
        }

        lvCities = (ListView) findViewById(R.id.lvCities);
        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayStringArray);
        lvCities.setAdapter(citiesAdapter);
    }
}
