package bit.gardits1.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    DatabaseBuilder databaseBuilder;
    SQLiteDatabase cityCountyDB;
    ListView lvCities;
    Spinner spCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cityCountyDB = openOrCreateDatabase("cityCountyDB", MODE_PRIVATE, null);
        databaseBuilder = new DatabaseBuilder(cityCountyDB);

        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        lvCities = (ListView) findViewById(R.id.lvCities);
        spCountries = (Spinner) findViewById(R.id.spCountries);


        String selectQuery = "SELECT * FROM tblCity";
        Cursor recordSet = cityCountyDB.rawQuery(selectQuery, null);

        int recordCount = recordSet.getCount();
        String[] displayStringArray = new String[recordCount];

        int cityNameIndex = recordSet.getColumnIndex("cityName");
        int countryNameIndex = recordSet.getColumnIndex("countryName");

        recordSet.moveToFirst();

        for (int r=0; r<recordCount; r++)
        {
            String cityName = recordSet.getString(cityNameIndex);
            String countryName = recordSet.getString(countryNameIndex);
            displayStringArray[r] = cityName + ", " + countryName;

            recordSet.moveToNext();
        }


        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayStringArray);
        lvCities.setAdapter(citiesAdapter);

    }
}
