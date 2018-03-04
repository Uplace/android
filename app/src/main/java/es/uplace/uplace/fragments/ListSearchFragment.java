package es.uplace.uplace.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import es.uplace.uplace.R;
import es.uplace.uplace.adapters.ListSearchAdapter;

public class ListSearchFragment extends Fragment {

    //    Test List
    ArrayList<String> strings = new ArrayList<>();

    RecyclerView recyclerProperty;
    //    ArrayAdapter<String> adapter;
    ListSearchAdapter listSearchAdapter = new ListSearchAdapter();


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

        recyclerProperty = getView().findViewById(R.id.recyclerProperty);
//        adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_list_item_1, strings);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerProperty.setLayoutManager(layoutManager);
        recyclerProperty.setAdapter(listSearchAdapter);
    }
}
