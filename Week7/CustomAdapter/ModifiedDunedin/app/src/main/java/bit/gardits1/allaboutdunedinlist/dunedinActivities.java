package bit.gardits1.allaboutdunedinlist;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.List;

public class dunedinActivities extends AppCompatActivity {


    FunThings[] funThingsArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dunedin_activities);

        initDataArray();

        FunThingsArrayAdapter funThingsArrayAdapter = new FunThingsArrayAdapter(this, R.layout.custom_listview_item, funThingsArray);

        ListView lvFunThings = (ListView) findViewById(R.id.lvFunThings);
        lvFunThings.setAdapter(funThingsArrayAdapter);
    }


    public class FunThingsArrayAdapter extends ArrayAdapter<FunThings>
    {

        public FunThingsArrayAdapter(Context context, int resource, FunThings[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container)
        {
            LayoutInflater inflater = LayoutInflater.from(dunedinActivities.this);

            View customView = inflater.inflate(R.layout.custom_listview_item, container, false);

            ImageView itemImageView = (ImageView) customView.findViewById(R.id.ivItemImage);
            TextView itemTextView = (TextView) customView.findViewById(R.id.tvItemText);

            FunThings currentItem = getItem(position);

            itemImageView.setImageDrawable(currentItem.funImage);
            itemTextView.setText(currentItem.toString());

            return customView;
        }
    }


    private void initDataArray()
    {
        Resources gr = getResources();

        Drawable larnachCastle = gr.getDrawable(R.drawable.larnach_castle, null);
        Drawable moanaPool = gr.getDrawable(R.drawable.moana_pool, null);
        Drawable olveston = gr.getDrawable(R.drawable.olveston, null);
        Drawable peninsula = gr.getDrawable(R.drawable.peninsula, null);
        Drawable saltWaterPool = gr.getDrawable(R.drawable.salt_water_pool, null);
        Drawable speightBrewery = gr.getDrawable(R.drawable.speights_brewery, null);
        Drawable stKilda = gr.getDrawable(R.drawable.st_kilda_beach, null);
        Drawable taeriGorge = gr.getDrawable(R.drawable.taeri_gorge_railway, null);

        funThingsArray = new FunThings[8];
        funThingsArray[0] = new FunThings("Larnach Castle", larnachCastle);
        funThingsArray[1] = new FunThings("Moana Pool", moanaPool);
        funThingsArray[2] = new FunThings("Olveston", olveston);
        funThingsArray[3] = new FunThings("Peninsula", peninsula);
        funThingsArray[4] = new FunThings("Salt Water Pool", saltWaterPool);
        funThingsArray[5] = new FunThings("Speights Brewery", speightBrewery);
        funThingsArray[6] = new FunThings("St Kilda Beach", stKilda);
        funThingsArray[7] = new FunThings("Taeri Gorge Railway", taeriGorge);
    }

}
