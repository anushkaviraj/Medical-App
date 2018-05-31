package com.industrialmaster.jewakamedicare.actions;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.industrialmaster.jewakamedicare.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class BookingSaveTask extends AsyncTask<String, Integer, String> {


    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        String sessionsId = strings[0];
        String name = strings[1];
        String email = strings[2];
        String contact = strings[3];

        try {
            URL url = new URL("http://idexserver.tk/anushka/channel/book/add.php");

            //connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //SENF REQuset
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            StringBuilder result = new StringBuilder();
            result.append(URLEncoder.encode("session_id", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(sessionsId, "UTF-8"));
            result.append("&");
            result.append(URLEncoder.encode("name", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(name, "UTF-8"));
            result.append("&");
            result.append(URLEncoder.encode("email", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(email, "UTF-8"));
            result.append("&");
            result.append(URLEncoder.encode("contact", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(contact, "UTF-8"));
            result.append("&");

            writer.write(result.toString());


            writer.flush();
            writer.close();
            int responseCode = conn.getResponseCode();

            //Read Responce

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String line;

                while ((line = br.readLine()) != null) {
                    response += line;
                }
            }

            //   JSONArray jsonArray = new JSONArray(response);
            //    for (int i = 0; i < jsonArray.length(); i++) {
            //       JSONObject obj = new JSONObject(jsonArray.getString(i));
            //       list.add(obj.getString("name") + "-" + obj.getString("speciality_name"));
            //   }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    Context context;

    public BookingSaveTask(Context c) {
        context = c;
    }

    @Override
    protected void onPostExecute(String strings) {
        Toast.makeText(context, strings, Toast.LENGTH_SHORT).show();

    }
}
