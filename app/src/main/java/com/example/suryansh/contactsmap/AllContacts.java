package com.example.suryansh.contactsmap;


import android.content.ContentResolver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllContacts extends Fragment {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactAdapter contactAdapter;
    ListView listView;
    ContactHelper ch;
    ContentResolver cr;
    HomeFragment hf;

    public AllContacts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_contacts, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        if (!this.json_string.isEmpty()) {
            ch = new ContactHelper();
            hf = new HomeFragment();
            contactAdapter = new ContactAdapter(container.getContext(), R.layout.row_layout);
            listView.setAdapter(contactAdapter);
            try {
                jsonArray = new JSONArray(json_string);
                jsonObject = new JSONObject(jsonArray.get(0).toString());
                jsonArray = jsonObject.getJSONArray("contacts");
                int count = 0;
                String name, email, phone, officePhone, latitude, longitude;

                while (count < jsonArray.length()) {
                    JSONObject jo = jsonArray.getJSONObject(count);
                    name = jo.getString("name");
                    if (jo.has("email")) {
                        email = jo.getString("email");
                    } else {
                        email = "";
                    }
                    if (jo.has("phone")) {
                        phone = jo.getString("phone");
                    } else {
                        phone = "";
                    }
                    if (jo.has("officePhone")) {
                        officePhone = jo.getString("officePhone");
                    } else {
                        officePhone = "";
                    }
                    latitude = jo.getString("latitude");
                    longitude = jo.getString("longitude");

                    String addr = hf.getAddress(Double.parseDouble(latitude), Double.parseDouble(longitude));

                    ch.insertContact(cr, name, phone, email, officePhone, addr);
                    Contacts contacts = new Contacts(name, email, phone, officePhone);
                    contactAdapter.add(contacts);
                    count++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return view;


    }
}
