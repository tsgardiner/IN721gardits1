package bit.gardits1.sqlite;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Tim on 08/04/2016.
 */
public class DatabaseBuilder {

    SQLiteDatabase cityCountyDB;

    public DatabaseBuilder(SQLiteDatabase cityCountyDB) {
        this.cityCountyDB = cityCountyDB;

        DropTable(); //Crude way of stopping duplicate data every runtime.
        CreateTable();
        InsertRecords();
    }

    private void DropTable()
    {
        String dropTable = "DROP TABLE tblCity";
        cityCountyDB.execSQL(dropTable);
    }

    private void CreateTable()
    {
        String createQuery =    "CREATE TABLE IF NOT EXISTS tblCity(" +
                                "cityId INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "cityName TEXT NOT NULL," +
                                "countryName TEXT NOT NULL);";
        cityCountyDB.execSQL(createQuery);
    }

    private void InsertRecords()
    {
        cityCountyDB.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Amsterdamn', 'The Netherlands')");
        cityCountyDB.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Berlin', 'Germany')");
        cityCountyDB.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Munich', 'Germany')");
        cityCountyDB.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Paris', 'France')");
        cityCountyDB.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Nice', 'France')");
        cityCountyDB.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Brighton', 'England')");
        cityCountyDB.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('London', 'England')");
    }



}
