package bit.gardits1.sqlite;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Tim on 08/04/2016.
 */
public class DatabaseBuilder {

    SQLiteDatabase cityCountryDB;

    public DatabaseBuilder(SQLiteDatabase cityCountryDB) {
        this.cityCountryDB = cityCountryDB;

        DropTable(); //Crude way of stopping duplicate data every runtime.
        CreateTable();
        InsertRecords();
    }

    private void DropTable()
    {
        String dropTable = "DROP TABLE IF EXISTS tblCity";
        cityCountryDB.execSQL(dropTable);
    }

    private void CreateTable()
    {
        String createQuery =    "CREATE TABLE IF NOT EXISTS tblCity(" +
                                "cityId INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "cityName TEXT NOT NULL," +
                                "countryName TEXT NOT NULL);";
        cityCountryDB.execSQL(createQuery);
    }

    private void InsertRecords()
    {
        cityCountryDB.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Amsterdamn', 'The Netherlands')");
        cityCountryDB.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Berlin', 'Germany')");
        cityCountryDB.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Munich', 'Germany')");
        cityCountryDB.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Paris', 'France')");
        cityCountryDB.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Nice', 'France')");
        cityCountryDB.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Brighton', 'England')");
        cityCountryDB.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('London', 'England')");
    }



}
