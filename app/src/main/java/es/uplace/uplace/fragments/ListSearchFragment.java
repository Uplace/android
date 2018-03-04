package es.uplace.uplace.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.uplace.uplace.PropertyActivity;
import es.uplace.uplace.R;
import es.uplace.uplace.adapters.ListSearchAdapter;

public class ListSearchFragment extends Fragment implements ListSearchAdapter.OnItemClickListener {

    RecyclerView recyclerProperty;
    ListSearchAdapter listSearchAdapter = new ListSearchAdapter();

    public ListSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerProperty = v.findViewById(R.id.recyclerProperty);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerProperty.setLayoutManager(layoutManager);
        recyclerProperty.setAdapter(listSearchAdapter);
        listSearchAdapter.setOnClickListener(this);

        return v;
    }

    @Override
    public void itemClicked(View view) {
        Intent intent = new Intent(this.getActivity(), PropertyActivity.class);
        startActivity(intent);
    }
}
