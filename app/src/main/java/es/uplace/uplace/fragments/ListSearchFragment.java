package es.uplace.uplace.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import es.uplace.uplace.R;

public class ListSearchFragment extends Fragment {

    //    Test List
    ArrayList<String> strings = new ArrayList<>();

    ListView listProperty;
    ArrayAdapter<String> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        strings.add("Casa");
        strings.add("Piso");
        strings.add("Oficina");
        strings.add("Terreno");

        listProperty = getView().findViewById(R.id.listProperty);
        adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_list_item_1, strings);
        listProperty.setAdapter(adapter);
    }
}
