package bit.gardits1.multipleactivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnActivityA = (Button) findViewById(R.id.btnActivityA);
        btnActivityA.setOnClickListener(new btnListenerScreenA());

    }

    public class btnListenerScreenA implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
        Intent changeActivityIntent = new Intent(MainActivity.this, ActivityB.class);
            startActivity(changeActivityIntent);
        }
    }
}
