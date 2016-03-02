package bit.gardits1.multipleactivities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_c);

        Button btnActivityC = (Button) findViewById(R.id.btnActivityC);
        btnActivityC.setOnClickListener(new btnListenerScreenC());

    }

    public class btnListenerScreenC implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Uri myGitHub = Uri.parse("https://github.com/tsgardiner/IN721gardits1");

            Intent githubIntent = new Intent(Intent.ACTION_VIEW, myGitHub);

            startActivity(githubIntent);
        }
    }

}
