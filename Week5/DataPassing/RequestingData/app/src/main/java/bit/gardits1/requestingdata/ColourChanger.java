package bit.gardits1.requestingdata;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class ColourChanger extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_changer);

        Intent returnIntent = new Intent();

        //getColor has been depreciated
        //There was a way with something called a context that I couldn't understand

        //Not sure if this is the right way to get a color as a string but it works.
        //Had errors until debug showed it wasn't getting the # with the resource.
        //Perhaps using this colour in the strings xml instead of as a color type would have been better.
        String colourString = "#" + Integer.toHexString(getResources().getColor(R.color.colorRedText, null));

        returnIntent.putExtra("requestedResult", colourString);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}



