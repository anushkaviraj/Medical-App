package com.industrialmaster.jewakamedicare;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.industrialmaster.jewakamedicare.actions.DoctorListTaskForAc;
import com.industrialmaster.jewakamedicare.actions.HospitalListTask;
import com.industrialmaster.jewakamedicare.actions.HospitalListTaskForAc;
import com.industrialmaster.jewakamedicare.actions.SpecialListTaskForAc;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AutoCompleteTextView actSpeciality, actDoctor, actHospital;

    int did = 0;
    int hid = 0;
    int sid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        actSpeciality = findViewById(R.id.actSpeciality);
        actDoctor = findViewById(R.id.actDoctor);
        actHospital = findViewById(R.id.actHospital);

        final DoctorListTaskForAc dlt = new DoctorListTaskForAc();
        dlt.execute(actDoctor);

        final HospitalListTaskForAc hlt = new HospitalListTaskForAc();
        hlt.execute(actHospital);

        final SpecialListTaskForAc slt = new SpecialListTaskForAc();
        slt.execute(actSpeciality);


        actDoctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int index, long l) {
                String text = actDoctor.getText().toString();
                did = dlt.doctors.get(text);

                Toast.makeText(MainActivity.this, "Doctor", Toast.LENGTH_SHORT).show();

            }
        });

        actHospital.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = actHospital.getText().toString();
                //   hid = hlt.doctors.get(text);
                hid = hlt.hospital.get(text);

                Toast.makeText(MainActivity.this, "Hospital", Toast.LENGTH_SHORT).show();
            }
        });

        actSpeciality.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = actSpeciality.getText().toString();
                // hid = dlt.doctors.get(text);
                sid = slt.special.get(text);

                Toast.makeText(MainActivity.this, "Speciality", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_doctor_list) {
            Intent intent = new Intent(this, DoctorList.class);
            startActivity(intent);

        } else if (id == R.id.nav_hospital_list) {
            Intent intent = new Intent(this, HospitalList.class);
            startActivity(intent);

        } else if (id == R.id.nav_appointment) {

        } else if (id == R.id.nav_exit) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void search(View v) {
        Intent intent = new Intent(this, DoctorSessionActivity.class);

        intent.putExtra("sid", sid);
        intent.putExtra("did", did);
        intent.putExtra("hid", hid);

        startActivity(intent);
    }

    public void clear(View view) {
        actSpeciality.setText(null);
        sid = 0;

        actHospital.setText(null);
        hid = 0;

        actDoctor.setText(null);
        did = 0;

    }


}

