package bit.gardits1.eventhandlers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button clickTestingButton = (Button) findViewById(R.id.EventTestingButton);
        EditText editText = (EditText) findViewById(R.id.editText);


        clickTestingButton.setOnClickListener(new ButtonClickHandler());
        clickTestingButton.setOnLongClickListener(new LongClickHandler());
        editText.setOnKeyListener(new editTextKeyHandler());
    }

    public class LongClickHandler implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(MainActivity.this, "Button click held", Toast.LENGTH_SHORT).show();
            return true;
        }
    }//End of LongClick Class

    public class ButtonClickHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "You Clicked the Button", Toast.LENGTH_SHORT).show();
        }
    }//End of ButtonClick Class

    public class editTextKeyHandler implements View.OnKeyListener {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            EditText textInput = (EditText) findViewById(v.getId());
            String currentText = textInput.getText().toString();

            if (keyCode == KeyEvent.KEYCODE_AT) {
                Toast.makeText(MainActivity.this, "Don't type @", Toast.LENGTH_SHORT).show();
            }
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                if (currentText.length() < 8) {
                    Toast.makeText(MainActivity.this, "Username must be 8 characters, " + currentText, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Thank you, " + currentText, Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        }
    }//End of editTextKeyHandler Class
}



