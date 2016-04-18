package com.example.suryansh.contactsmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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
        json_string = getIntent().getExtras().getString("json_data");

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("contacts");
            int count = 0;
            String name, email, phone, office_phone;

            while(count<jsonArray.length()){
                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("name");
                email = jo.getString("email");
                phone = jo.getString("phone");
                office_phone = jo.getString("officephone");
                Contacts contacts =new Contacts(name, email,phone,office_phone);
                contactAdapter.add(contacts);
                count++;

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
