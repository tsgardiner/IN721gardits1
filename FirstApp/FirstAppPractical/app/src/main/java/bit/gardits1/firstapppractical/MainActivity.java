package bit.gardits1.firstapppractical;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String dogBreed;
    String[] breeds = {"Poodle", "Labrador", "Shar Pei", "Newfoundland"};
    Random rand;
    int randomDog;
    TextView txtRandomString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtRandomString = (TextView)findViewById(R.id.breedTextView);

        rand = new Random();
        randomDog = rand.nextInt(4);
        dogBreed = breeds[randomDog];
        txtRandomString.setText(dogBreed);
    }
}
