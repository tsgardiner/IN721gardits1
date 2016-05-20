package bit.gardits1.googlemapsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    GoogleMap map;
    LatLng DunedinLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DunedinLatLng = new LatLng(-45.866811, 170.517186);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        mapFragment.getMapAsync(new MapCallBackClass());
    }


    public class MapCallBackClass implements OnMapReadyCallback{

        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;

            map.addMarker(new MarkerOptions().position(DunedinLatLng).title("Dunedin"));

            map.moveCamera(CameraUpdateFactory.newLatLng(DunedinLatLng));

        }
    }
}
