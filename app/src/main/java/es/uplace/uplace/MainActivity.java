package es.uplace.uplace;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerFilters;
    EditText inputTxtMain;
    LinearLayout btnSearch, btnFilters;
    Spinner spinDwelling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setClickListeners();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.dwelling_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDwelling.setAdapter(adapter);
    }

    private void findViews() {
        drawerFilters = findViewById(R.id.drawer_layout);
        inputTxtMain = findViewById(R.id.inputTxtMain);
        btnFilters = findViewById(R.id.btnFilters);
        btnSearch = findViewById(R.id.btnSearch);
        spinDwelling = findViewById(R.id.spinDwelling);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFilters:
                drawerFilters.openDrawer(Gravity.START);
//                Toast.makeText(this, "Filters", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setClickListeners() {
        btnFilters.setOnClickListener(this);
    }
}
