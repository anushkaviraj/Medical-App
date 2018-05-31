package com.industrialmaster.jewakamedicare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.industrialmaster.jewakamedicare.actions.HospitalListTask;

public class HospitalList extends AppCompatActivity {

    ListView lvHospital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);

        lvHospital = findViewById(R.id.lvHospital);

        HospitalListTask hlt = new HospitalListTask();
        hlt.execute(lvHospital);

    }
}
