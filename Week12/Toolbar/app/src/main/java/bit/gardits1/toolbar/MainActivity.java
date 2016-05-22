package bit.gardits1.toolbar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Bitmap happy, sad, dead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        happy = BitmapFactory.decodeResource(this.getResources(), R.drawable.happy_img);
        sad = BitmapFactory.decodeResource(this.getResources(), R.drawable.sad_img);
        dead = BitmapFactory.decodeResource(this.getResources(), R.drawable.dead_img);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.face_menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String title = (String) item.getTitle();
        ImageView image = (ImageView) findViewById(R.id.ivDisplayImage);
        image.setImageBitmap(null);

        switch (title){
            case "Happy":
                image.setImageBitmap(happy);
                break;
            case "Sad":
                image.setImageBitmap(sad);
                break;
            case "Dead":
                image.setImageBitmap(dead);
                break;
        }

        return true;
    }
}
