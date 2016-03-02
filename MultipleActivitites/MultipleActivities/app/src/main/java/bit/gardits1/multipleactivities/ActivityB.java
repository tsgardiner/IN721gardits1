package bit.gardits1.multipleactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_b);

        Button btnActivityB = (Button) findViewById(R.id.btnActivityB);
        btnActivityB.setOnClickListener(new btnListenerScreenB());

    }

    public class btnListenerScreenB implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(ActivityB.this, ActivityC.class);
            startActivity(changeActivityIntent);
        }
    }

}
