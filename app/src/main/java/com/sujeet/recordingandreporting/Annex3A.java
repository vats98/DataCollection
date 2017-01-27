package com.sujeet.recordingandreporting;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Annex3A extends AppCompatActivity implements View.OnClickListener {

    private EditText etNameRHF;
    private EditText etDate;
    private EditText etNamePatient;
    private EditText etAge;
    private RadioButton rbM;
    private RadioButton rbF;
    private EditText etAddress;
    private CheckBox cbPulmonary;
    private CheckBox cbEPulmonary;
    private EditText etSite;
    private CheckBox cbDiagnosis;
    private CheckBox cbRepeat;
    private CheckBox cbFollowUp;
    private EditText etPTN;
    private EditText etNameOfficial;
    private EditText etSIN;
    private EditText etDateSputum;
    private EditText etNameCollector;
    private Button btnSubmit1;

    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annex3);

        initializeUI();

        dbHandler = new DBHandler(this, null, null, 0);
    }

    private void initializeUI() {

        etNameRHF = (EditText) findViewById(R.id.etNameRHF);
        etDate = (EditText) findViewById(R.id.etDate);
        etNamePatient = (EditText) findViewById(R.id.etNamePatient);
        etAge = (EditText) findViewById(R.id.etAge);
        rbM = (RadioButton) findViewById(R.id.rbM);
        rbF = (RadioButton) findViewById(R.id.rbF);
        etAddress = (EditText) findViewById(R.id.etAddress);
        cbPulmonary = (CheckBox) findViewById(R.id.cbPulmonary);
        cbEPulmonary =(CheckBox) findViewById(R.id.cbEPulmonary);
        etSite = (EditText) findViewById(R.id.etSite);
        cbDiagnosis = (CheckBox) findViewById(R.id.cbDiagnosis);
        cbRepeat = (CheckBox) findViewById(R.id.cbRepeat);
        cbFollowUp = (CheckBox) findViewById(R.id.cbFollowUp);
        etPTN = (EditText) findViewById(R.id.etPTN);
        etNameOfficial = (EditText) findViewById(R.id.etNameOfficial);
        etSIN = (EditText) findViewById(R.id.etSIN);
        etDateSputum = (EditText) findViewById(R.id.etDateSputum);
        etNameCollector = (EditText) findViewById(R.id.etNameCollector);
        btnSubmit1 = (Button) findViewById(R.id.btnSubmit1);

        btnSubmit1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit1:
                submitData();
                break;
        }
    }

    private void submitData() {

        try {
            ContentValues cv = new ContentValues();
            cv.put(DBHandler.COLSA[1], etNameRHF.getText().toString());
            cv.put(DBHandler.COLSA[2], etDate.getText().toString());
            cv.put(DBHandler.COLSA[3], etNamePatient.getText().toString());
            cv.put(DBHandler.COLSA[4], etAge.getText().toString());
            String sex;
            if (rbM.isSelected())
                sex = "M";
            else if (rbF.isSelected())
                sex = "F";
            else sex = "";
            cv.put(DBHandler.COLSA[5], sex);
            cv.put(DBHandler.COLSA[6], etAddress.getText().toString());
            StringBuilder sb = new StringBuilder("");
            if (cbPulmonary.isChecked())
                sb.append(cbEPulmonary.getText());
            sb.append(" ");
            if (cbEPulmonary.isChecked())
                sb.append(cbEPulmonary.getText());
            cv.put(DBHandler.COLSA[7], sb.toString());
            cv.put(DBHandler.COLSA[8], etSite.getText().toString());
            StringBuilder sbb = new StringBuilder("");
            if (cbDiagnosis.isChecked())
                sbb.append(cbDiagnosis.getText());
            sbb.append(" ");
            if (cbRepeat.isChecked())
                sbb.append(cbRepeat.getText());
            sbb.append(" ");
            if (cbFollowUp.isChecked())
                sbb.append(cbFollowUp.getText());
            cv.put(DBHandler.COLSA[9], sbb.toString());
            cv.put(DBHandler.COLSA[10], etPTN.getText().toString());
            cv.put(DBHandler.COLSA[11], etNameOfficial.getText().toString());
            cv.put(DBHandler.COLSA[12], etSIN.getText().toString());
            cv.put(DBHandler.COLSA[13], etDateSputum.getText().toString());
            cv.put(DBHandler.COLSA[14], etNameCollector.getText().toString());

            if (dbHandler.addRowA(cv))
                Toast.makeText(this, "Record submitted successfully", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Error inserting row", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
