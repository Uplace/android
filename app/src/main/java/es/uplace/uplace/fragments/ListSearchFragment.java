//package es.uplace.uplace.fragments;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import java.util.List;
//
//import es.uplace.uplace.PropertyActivity;
//import es.uplace.uplace.R;
//import es.uplace.uplace.adapters.ListSearchAdapter;
//import es.uplace.uplace.domain.Property;
//import es.uplace.uplace.retrofit.PropertyService;
//import es.uplace.uplace.retrofit.Service;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class ListSearchFragment extends Fragment implements ListSearchAdapter.OnItemClickListener {
//
//    Retrofit retrofit;
//
//    RecyclerView recyclerProperty;
//
//    public ListSearchFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_list, container, false);
//
//        recyclerProperty = v.findViewById(R.id.recyclerProperty);
//
//        buildRetrofit();
//
//        PropertyService propertyService = retrofit.create(PropertyService.class);
//        Call<List<Property>> call = propertyService.findAllProperties();
//
//        call.enqueue(new Callback<List<Property>>() {
//            @Override
//            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
//                Log.d("ncs", "Response: " + response.code());
//
//                List<Property> properties = response.body();
//
//                Log.d("ncs", "Properties: " + properties);
//
//                for (Property property : properties)
//                    System.out.println(property);
//
//                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//                recyclerProperty.setLayoutManager(layoutManager);
//                recyclerProperty.setAdapter(new ListSearchAdapter(properties));
//            }
//
//            @Override
//            public void onFailure(Call<List<Property>> call, Throwable t) {
//                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
//                Log.d("ncs", "onFailure: " + t);
//            }
//        });
//
//        return v;
//    }
//
//    private void buildRetrofit() {
//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl(Service.API_URL)
//                .addConverterFactory(GsonConverterFactory.create());
//
//        this.retrofit = builder.build();
//    }
//
//    @Override
//    public void itemClicked(View view) {
//        Intent intent = new Intent(this.getActivity(), PropertyActivity.class);
//        startActivity(intent);
//    }
//}
