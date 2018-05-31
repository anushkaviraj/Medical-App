package com.industrialmaster.jewakamedicare.actions;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class DoctorListTask extends AsyncTask<ListView, Integer, List<String>> {

    ListView lv;

    @Override
    protected List<String> doInBackground(ListView... listViews) {
        lv = listViews[0];
        List<String> list = new ArrayList<>();

        try {
            URL url = new URL("http://idexserver.tk/anushka/channel/doctor/list.php");

            //connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //SENF REQuset
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
                    response += line;
                }
            }

            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = new JSONObject(jsonArray.getString(i));
                list.add(obj.getString("name") + "-" + obj.getString("speciality_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<String> strings) {
        ArrayAdapter adapter = new ArrayAdapter(lv.getContext(), android.R.layout.simple_list_item_1, strings);
        lv.setAdapter(adapter);

    }
}
