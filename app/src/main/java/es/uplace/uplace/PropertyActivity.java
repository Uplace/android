package es.uplace.uplace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import es.uplace.uplace.adapters.PropertyCharAdapter;
import es.uplace.uplace.adapters.PropertyExtraAdapter;

public class PropertyActivity extends AppCompatActivity {

    RecyclerView recyclerChar;
    RecyclerView recyclerExtras;
    PropertyCharAdapter propertyCharAdapter = new PropertyCharAdapter(this);
    PropertyExtraAdapter propertyExtraAdapter = new PropertyExtraAdapter(this);

    TextView txtName, txtLocation, txtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        findViews();

        txtName.setText("Test Nombre Largo, 1, 2, 3");
        txtLocation.setText("Test Location: c/Lorem ipsum 123, Prpl. 4 08001, Barcelona, Spain");
        txtPrice.setText("Venta: 999999.99€");
        recyclerChar.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerExtras.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerChar.setAdapter(propertyCharAdapter);
        recyclerExtras.setAdapter(propertyExtraAdapter);
    }

    private void findViews() {
        txtName = findViewById(R.id.txtName);
        txtLocation = findViewById(R.id.txtLocation);
        txtPrice = findViewById(R.id.txtPrice);
        recyclerChar = findViewById(R.id.recyclerChars);
        recyclerExtras = findViewById(R.id.recyclerExtras);
    }
}
