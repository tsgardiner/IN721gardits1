package bit.gardits1.photomosaic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.net.URI;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String photoFileName;
    File photoFile;
    Uri photoFileUri;
    ArrayList<ImageView> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViews = new ArrayList<>();

        imageViews.add((ImageView) findViewById(R.id.imageView));
        imageViews.add((ImageView) findViewById(R.id.imageView2));
        imageViews.add((ImageView) findViewById(R.id.imageView3));
        imageViews.add((ImageView) findViewById(R.id.imageView4));

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new TakePhotoButton());
}


    public class TakePhotoButton implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            StartCamera();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {

            if (resultCode == RESULT_OK) {
                String realFilePath = photoFile.getPath();

                Bitmap bitmap = BitmapFactory.decodeFile(realFilePath);

                for ( ImageView i : imageViews ) {
                    i.setImageBitmap(bitmap);
                }

            }
        }
    }


    public void StartCamera()
    {
        photoFile = CreateImageFile();

        photoFileUri = Uri.fromFile(photoFile);

        Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFileUri);

        startActivityForResult(imageIntent, 1);
    }


    public File CreateImageFile() {
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File imageStorageDIR = new File(imageRootPath, "CameraLab");
        if (!imageStorageDIR.exists())
            imageStorageDIR.mkdirs();

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMDD_HHmmss");
        Date currentTime = new Date();
        String timeStamp = timeStampFormat.format(currentTime);

        photoFileName = "IMG_" + timeStamp + ".jpg";

      return new File(imageStorageDIR.getPath() + File.separator + photoFileName);
    }



}
