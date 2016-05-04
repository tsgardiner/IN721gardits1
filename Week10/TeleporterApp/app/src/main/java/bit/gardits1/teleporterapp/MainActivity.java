package bit.gardits1.teleporterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements LatLongRandom{

    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public double GenerateLatitude() {
        random = new Random();
        double lat = random.nextDouble();
        return 0;
    }

    @Override
    public double GenerateLongitude() {
        return 0;
    }

    @Override
    public void DisplayLatLong() {

    }
}


