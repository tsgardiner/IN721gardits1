package bit.gardits1.allaboutdunedinlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpDunedinList();

        ListView groupListView = (ListView) findViewById(R.id.lvDunedinMainNav);
        groupListView.setOnItemClickListener(new DunendinListClickHandler());
    }

    public void setUpDunedinList()
    {
        String[] groups = getResources().getStringArray(R.array.navigationList);
        ArrayAdapter<String> groupAdapter = new ArrayAdapter<> (this, android.R.layout.simple_list_item_1, groups);
        ListView groupListView = (ListView) findViewById(R.id.lvDunedinMainNav);
        groupListView.setAdapter(groupAdapter);
    }


    public class DunendinListClickHandler implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String itemClicked = (String) parent.getItemAtPosition(position);
            Intent nextIntent;


            switch(itemClicked)
            {
                case "Services":
                    nextIntent = new Intent(MainActivity.this, dunedinServices.class);
                    break;
                case "Fun Things To Do":
                    nextIntent = new Intent(MainActivity.this, dunedinActivities.class);
                    break;
                case "Dining":
                    nextIntent = new Intent(MainActivity.this, dunedinDining.class);
                    break;
                case "Shopping":
                    nextIntent = new Intent(MainActivity.this, dunedinShopping.class);
                    break;
                default:
                    nextIntent = null;
            }

            if (nextIntent != null)
                startActivity(nextIntent);
        }//End of ItemClick
    }
}
