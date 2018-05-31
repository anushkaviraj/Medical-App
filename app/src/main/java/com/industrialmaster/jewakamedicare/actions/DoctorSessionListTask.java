package com.industrialmaster.jewakamedicare.actions;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.industrialmaster.jewakamedicare.CustomAdapter;
import com.industrialmaster.jewakamedicare.models.Session;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DoctorSessionListTask extends AsyncTask<ListView, Integer, ArrayList<Session>> {

    ListView lv;
    String params = "";

    public DoctorSessionListTask(int sid, int hid, int did) {
        params = "?sid=" + sid + "&hid=" + hid + "&did=" + did;
    }

    @Override
    protected ArrayList<Session> doInBackground(ListView... listViews) {
        lv = listViews[0];
        ArrayList<Session> list = new ArrayList<>();

        try {
            URL url = new URL("http://idexserver.tk/anushka/channel/doctor_session/list.php" + params);

            //connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //send Request
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.flush();
            writer.close();
            int responseCode = conn.getResponseCode();

            //Read Responce
            String response = "";
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String line;

                while ((line = br.readLine()) != null) {
                    response +=line;
                }
            }

            //
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = new JSONObject(jsonArray.getString(i));
                // list.add(obj.getString("doctor_name") + "-" + obj.getString("date") +"-" + obj.getString("hospital_name"));
                Session ses = new Session(obj.getInt("id"),
                        obj.getString("doctor_name"),
                        obj.getString("speciality_name"),
                        obj.getString("hospital_name"),
                        obj.getString("date"));
                list.add(ses);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<Session> data) {
        CustomAdapter adapter = new CustomAdapter(lv.getContext(), data);
        lv.setAdapter(adapter);

    }
}
