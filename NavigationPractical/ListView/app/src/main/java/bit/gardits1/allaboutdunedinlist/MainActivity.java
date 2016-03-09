package bit.gardits1.allaboutdunedinlist;

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
    }

    public void setUpDunedinList()
    {
        String[] groups = getResources().getStringArray(R.array.navigationList);
        ArrayAdapter<String> groupAdapter = new ArrayAdapter<> (this, android.R.layout.simple_list_item_2, groups);
        ListView groupListView = (ListView) findViewById(R.id.lvDunedinMainNav);
        groupListView.setAdapter(groupAdapter);
    }


    public class DunendinListClickHandler implements AdapterView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
}
