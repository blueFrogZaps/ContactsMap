package com.example.suryansh.contactsmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Display_List extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactAdapter contactAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);
        listView = (ListView)findViewById(R.id.listview);
        contactAdapter =new ContactAdapter(this,R.layout.row_layout);
        listView.setAdapter(contactAdapter);
        Bundle b = getIntent().getExtras();
        json_string = b.getString("json_data");

        try {
            jsonArray = new JSONArray(json_string);
            jsonObject = new JSONObject(jsonArray.get(0).toString());
            jsonArray = jsonObject.getJSONArray("contacts");
            int count = 0;
            String name, email, phone, officePhone;

            while(count < jsonArray.length()){
                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("name");
                if(jo.has("email")) {
                    email = jo.getString("email");
                }
                else{
                    email = "";
                }
                if(jo.has("phone")) {
                    phone = jo.getString("phone");
                }
                else{
                    phone = "";
                }
                if(jo.has("officePhone")){
                    officePhone = jo.getString("officePhone");
                }
                else{
                    officePhone = "";
                }
                Contacts contacts =new Contacts(name, email,phone,officePhone);
                contactAdapter.add(contacts);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
