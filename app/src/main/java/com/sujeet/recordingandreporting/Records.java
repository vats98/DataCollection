package com.sujeet.recordingandreporting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Records extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> rows;
    private TextView tvLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        tvLoad = (TextView) findViewById(R.id.tvLoad);
        listView = (ListView) findViewById(R.id.lvRecords);
        DBHandler dbHandler = new DBHandler(this, null, null, 0);
        rows = dbHandler.showTable(getIntent().getStringExtra("table"));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, rows);
        listView.setAdapter(arrayAdapter);
        tvLoad.setText("Done Loading");
        Toast.makeText(this, ""+rows.size(), Toast.LENGTH_SHORT).show();
    }
}
