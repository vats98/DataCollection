package com.sujeet.recordingandreporting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Admin extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private String tables[] = {"Annex3A","Annex3B"};
    //test comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listView = (ListView) findViewById(R.id.activity_admin);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tables);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String x = tables[i];
        Intent intent = new Intent(this, Records.class);
        intent.putExtra("table", x);
        startActivity(intent);
    }
}
