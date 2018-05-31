package com.industrialmaster.jewakamedicare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.industrialmaster.jewakamedicare.actions.DoctorSessionListTask;

public class DoctorSessionActivity extends AppCompatActivity {

    ListView lvSessions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_session);

        lvSessions = findViewById(R.id.lvSession);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        int sid = bundle.getInt("sid");
        int did = bundle.getInt("did");
        int hid = bundle.getInt("hid");

        DoctorSessionListTask dslt = new DoctorSessionListTask(sid,hid,did);
        dslt.execute(lvSessions);
    }
}
