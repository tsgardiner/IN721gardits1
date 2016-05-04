package bit.gardits1.teleporterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements LatLongRandom {

    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GenerateLatitude();
        GenerateLongitude();

        TextView lat = (TextView) findViewById(R.id.tvLatitudeDisplay);
        TextView lon = (TextView) findViewById(R.id.tvLongitudeDisplay);


        lat.setText(Double.toString(latitude));
        lon.setText(Double.toString(longitude));
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

    }
}


