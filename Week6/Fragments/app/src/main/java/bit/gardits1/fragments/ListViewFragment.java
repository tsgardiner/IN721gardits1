package bit.gardits1.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListViewFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView = inflater.inflate(R.layout.fragment_list_view, container, false);

        ListView lvCities = (ListView) fragmentView.findViewById(R.id.lvCities);

        String[] cityNamesArray = getResources().getStringArray(R.array.cities);

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, cityNamesArray);

        lvCities.setAdapter(cityAdapter);

        return fragmentView;
    }


}
