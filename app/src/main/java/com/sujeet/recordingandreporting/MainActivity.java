package com.sujeet.recordingandreporting;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        AdapterView.OnItemSelectedListener {

    private String listItems[] = {"Admin", "Annex 3A","Annex 3B"};
    private String languages[] = {"English", "Hindi"};
    private int lastLanguage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeListView();

        initializeSpinner();
    }

    public void initializeListView() {
        ListView lvLaunch = (ListView) findViewById(R.id.lvLaunch);
        lvLaunch.setOnItemClickListener(this);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listItems);
        lvLaunch.setAdapter(listAdapter);
    }

    public void initializeSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, languages);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String tempName = listItems[i];
        StringBuilder classname = new StringBuilder("");
        for(int j=0; j<tempName.length(); j++) {
            if((tempName.charAt(j)>='A' && tempName.charAt(j)<='Z') ||
                    (tempName.charAt(j)>='a' && tempName.charAt(j)<='z') ||
                    (tempName.charAt(j)>='0' && tempName.charAt(j)<='9'))
                classname.append(tempName.charAt(j));
        }
        Class mClass = null;
        try {
            mClass = Class.forName("com.sujeet.recordingandreporting."+classname.toString());
            Intent intent = new Intent(MainActivity.this, mClass);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(lastLanguage == i)
            return;
        String language = languages[i];
        lastLanguage = i;
        StringBuilder isoCode = new StringBuilder("");
        isoCode.append(language.charAt(0));
        isoCode.append(language.charAt(1));
        Locale locale=new Locale(isoCode.toString());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getApplicationContext().getResources().updateConfiguration(config, null);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
