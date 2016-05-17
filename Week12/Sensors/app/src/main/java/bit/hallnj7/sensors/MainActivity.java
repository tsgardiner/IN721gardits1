package bit.hallnj7.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    SensorManager sensorManager;
    TextView textView;
    TextView textView3;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         textView = (TextView)findViewById(R.id.textView);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);

        //Displays all sensors on the phone.
        //sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        //List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
       // textView.setText(sensors.toString());


        //light sensor
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
//        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
//        sensorManager.registerListener(new lightStuff(), lightSensor, sensorManager.SENSOR_DELAY_NORMAL);


        //accelerometer sensor
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(new movementSensor(), accelerometerSensor, sensorManager.SENSOR_DELAY_NORMAL);
    }



    public class movementSensor implements SensorEventListener
    {
        @Override
        public void onSensorChanged(SensorEvent event)
        {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            textView.setText(String.valueOf("X axis: " + x));
            textView3.setText(String.valueOf("Y axis: " + y));
            textView4.setText(String.valueOf("Z axis: " + z));

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {

        }
    }




//    public class lightStuff implements SensorEventListener
//    {
//        @Override
//        public void onSensorChanged(SensorEvent event)
//        {
//            float illumination = event.values[0];
//            textView.setText(String.valueOf(illumination));
//        }
//
//        @Override
//        public void onAccuracyChanged(Sensor sensor, int accuracy)
//        {
//
//        }
//    }


    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }
}
