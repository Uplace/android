package es.uplace.uplace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import es.uplace.uplace.adapters.PropertyCharAdapter;

public class PropertyActivity extends AppCompatActivity {

    GridView gridChar;
    PropertyCharAdapter propertyCharAdapter = new PropertyCharAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        gridChar = findViewById(R.id.gridChars);
        gridChar.setAdapter(propertyCharAdapter);
    }
}
