package com.industrialmaster.jewakamedicare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.industrialmaster.jewakamedicare.actions.DoctorListTask;

public class DoctorList extends AppCompatActivity {

    ListView lvDoctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        lvDoctors = findViewById(R.id.lvDoctors);

        DoctorListTask dlt = new DoctorListTask();
        dlt.execute(lvDoctors);

    }
}
