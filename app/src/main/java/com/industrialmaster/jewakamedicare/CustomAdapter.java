package com.industrialmaster.jewakamedicare;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.industrialmaster.jewakamedicare.models.Session;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Session> implements View.OnClickListener {

    Context context;
    ArrayList<Session> data;

    public CustomAdapter(Context c, ArrayList<Session> data) {
        super(c, R.layout.session_row, data);
        context = c;
        this.data = data;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Session session = (Session) getItem(position);
        switch (v.getId()) {
            case R.id.btnBookNow:
                //  Toast.makeText(context, "Selected " + session.getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra("id", session.getId());
                context.startActivity(intent);
                break;
        }
    }

    public static class ViewHolder {
        TextView txtDoctor;
        TextView txtSpeciality;
        TextView txtHospital;
        TextView txtDate;
        Button btnBookNow;
    }

    @NonNull
    @Override
    //getView funtion
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Initialize
        Session session = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.session_row, parent, false);

            viewHolder.txtDoctor = (TextView) convertView.findViewById(R.id.tvDoctor);
            viewHolder.txtSpeciality = (TextView) convertView.findViewById(R.id.tvSpecial);
            viewHolder.txtHospital = (TextView) convertView.findViewById(R.id.tvHospital);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.tvDate);
            viewHolder.btnBookNow = (Button) convertView.findViewById(R.id.btnBookNow);

            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.txtDoctor.setText(session.getDoctorName());
        viewHolder.txtSpeciality.setText(session.getSpecialityName());
        viewHolder.txtHospital.setText(session.getHospitalName());
        viewHolder.txtDate.setText(session.getDate());
        viewHolder.btnBookNow.setOnClickListener(this);
        viewHolder.btnBookNow.setTag(position);

        return convertView;

    }
}
