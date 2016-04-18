package com.example.suryansh.contactsmap;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String json_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getJSON(View view){
        new BackgroundTask().execute();

    }

    class BackgroundTask extends AsyncTask<Void,Void,String>{

        String json_url;



        @Override
        protected void onPreExecute() {
            json_url ="http://private-b08d8d-nikitest.apiary-mock.com/contacts";

        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder =new StringBuilder();
                while ((json_string=bufferedReader.readLine())!=null){
                    stringBuilder.append(json_string+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textview = (TextView)findViewById(R.id.textview);
            textview.setText(result);
            json_string = result;
        }
    }

    public void parseJSON(View view){
        if(json_string == null){
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context,"Data not fetched properly",Toast.LENGTH_LONG);
            toast.show();
        }else{
            Intent intent = new Intent(this, Display_List.class);
            Bundle b = new Bundle();
            b.putString("json_data",json_string);
            intent.putExtras(b);
            startActivity(intent);
        }


    }

}
