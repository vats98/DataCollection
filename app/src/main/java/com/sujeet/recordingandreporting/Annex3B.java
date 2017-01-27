package com.sujeet.recordingandreporting;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Annex3B extends AppCompatActivity implements View.OnClickListener {
    private EditText etNARHF;
    private EditText etNHFWPER;
    private EditText etDate;
    private EditText etNameofPatient;
    private EditText etAge;
    private RadioButton rbM;
    private RadioButton rbF;
    private EditText etAddress;
    private RadioButton rbPulmonary;
    private RadioButton rbEPulmonary;
    private RadioButton rbSite;
    private RadioButton rbCategory;
    private RadioButton rbCategory2;
    private RadioButton rbCategory3;
    private RadioButton rbnew;
    private RadioButton rbRelapse;
    private RadioButton rbFailure;
    private RadioButton rbTreatmentafterDefault;
    private RadioButton rbCategory1;
    private RadioButton rbOthers;
    private EditText etSignature;
    private EditText etDateReferred;
    private EditText etDesignation;
    private Button btnsubmit;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annex3_b);
        etNARHF= (EditText) findViewById(R.id.etNameRHF1);
        etNHFWPER= (EditText) findViewById(R.id.etNamewpr);
        etDate= (EditText) findViewById(R.id.etDate1);
        etNameofPatient= (EditText) findViewById(R.id.etNamePatient1);
        etAge= (EditText) findViewById(R.id.etAge1);
        rbM= (RadioButton) findViewById(R.id.rbM1);
        rbF= (RadioButton) findViewById(R.id.rbF1);
        etAddress= (EditText) findViewById(R.id.complete_address1);
        rbPulmonary= (RadioButton) findViewById(R.id.Pul1);
        rbEPulmonary= (RadioButton) findViewById(R.id.Extra_pul1);
        rbSite= (RadioButton) findViewById(R.id.rbSite);
        rbCategory= (RadioButton) findViewById(R.id.cat1);
        rbCategory2= (RadioButton) findViewById(R.id.cat2);
        rbCategory3= (RadioButton) findViewById(R.id.cat3);
        rbnew= (RadioButton) findViewById(R.id.New);
        rbRelapse= (RadioButton) findViewById(R.id.Rel);
        rbFailure= (RadioButton) findViewById(R.id.Fail);
        rbTreatmentafterDefault= (RadioButton) findViewById(R.id.TaD);
        rbOthers= (RadioButton) findViewById(R.id.other);
        etSignature= (EditText) findViewById(R.id.sign);
        etDateReferred= (EditText) findViewById(R.id.date_referred);
        etDesignation= (EditText) findViewById(R.id.Designation);
        btnsubmit= (Button) findViewById(R.id.btnSubmit2);
        btnsubmit.setOnClickListener(this);
        dbHandler = new DBHandler(this, null, null, 0);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmit2:
                submitData2();
                break;
        }
    }
    private void submitData2()
    {
        try {
            ContentValues cv1=new ContentValues();
            cv1.put(DBHandler.COLSB[1], etNARHF.getText().toString());
            cv1.put(DBHandler.COLSB[2], etNHFWPER.getText().toString());
            cv1.put(DBHandler.COLSB[3], etDate.getText().toString());
            cv1.put(DBHandler.COLSB[4], etNameofPatient.getText().toString());
            cv1.put(DBHandler.COLSB[5], etAge.getText().toString());
            String sex;
            if (rbM.isSelected())
                sex = "M";
            else if (rbF.isSelected())
                sex = "F";
            else sex = "";
            cv1.put(DBHandler.COLSB[6], sex);
            cv1.put(DBHandler.COLSB[7], etAddress.getText().toString());
            String disease_classification;
            if(rbPulmonary.isSelected())
                disease_classification="Pulmonary";
            else if(rbEPulmonary.isSelected())
                disease_classification="Extra-Pulmonary";
            else if(rbSite.isSelected())
                disease_classification="Site";
            else
                disease_classification="";
            cv1.put(DBHandler.COLSB[8],disease_classification);
            String category;
            if(rbCategory.isSelected())
                category="category1";
            else if(rbCategory2.isSelected())
                category="category2";
            else if(rbCategory3.isSelected())
                category="category3";
            else
                category="";
            cv1.put(DBHandler.COLSB[9],category);
            String type_of_patient;
            if(rbnew.isSelected())
                type_of_patient="New";
            else if(rbRelapse.isSelected())
                type_of_patient="Relapse";
            else if(rbFailure.isSelected())
                type_of_patient="Failure";
            else if(rbTreatmentafterDefault.isSelected())
                type_of_patient="Treatment after default";
            else if(rbOthers.isSelected())
                type_of_patient="Others";
            else
                type_of_patient="";
            cv1.put(DBHandler.COLSB[10],type_of_patient);
            cv1.put(DBHandler.COLSB[11],etSignature.getText().toString());
            cv1.put(DBHandler.COLSB[12],etDateReferred.getText().toString());
            cv1.put(DBHandler.COLSB[13],etDesignation.getText().toString());
            if (dbHandler.addRowB(cv1))
                Toast.makeText(this, "Record submitted successfully", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Error inserting row", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
