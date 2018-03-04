package es.uplace.uplace;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import es.uplace.uplace.fragments.ListSearchFragment;
import es.uplace.uplace.fragments.MapSearchFragment;

public class SearchActivity extends AppCompatActivity {

    BottomNavigationView navigation;
    FragmentManager fragmentManager;
    Fragment listFragment, mapFragment;

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        navigation = findViewById(R.id.navigation);
        fragmentManager = getSupportFragmentManager();

        // define your fragments here
        listFragment = new ListSearchFragment();
        mapFragment = new MapSearchFragment();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.searchFrame, listFragment).commit();

        // handle navigation selection
        navigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        fragmentTransaction = fragmentManager.beginTransaction();
                        switch (item.getItemId()) {
                            case R.id.nav_list:
                                fragmentTransaction.replace(R.id.searchFrame, listFragment).commit();
                                return true;
                            case R.id.nav_map:
                                fragmentTransaction.replace(R.id.searchFrame, mapFragment).commit();
                                return true;
                        }
                        return true;
                    }
                }
        );
    }
}
