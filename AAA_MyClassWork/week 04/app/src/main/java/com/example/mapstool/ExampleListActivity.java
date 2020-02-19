package com.example.mapstool;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ListView;
import android.content.Context;
import android.content.Intent;

public class ExampleListActivity extends ListActivity
{
    String[] names, details;
    int[] latitudes, longitudes, zooms;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        names = new String[] { "The Crown", "The Cowherds", "The Two Brothers", "Piccolo Mondo" };
        details = new String[] { "pub, 2.5 miles north", "pub, 1.5 miles north",
                "pub, 3.5 miles northeast" , "Italian restaurant, 0.5 miles west" };
        latitudes = new int[]{54,56,58,60};
        longitudes = new int []{-2, -4, -6, -8};
        zooms = new int[]{5,5,5,5};


        MyAdapter adapter = new MyAdapter();
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView lv, View view, int index, long id)
    {
        // handle list item selection
        Intent intent = new Intent();
        Bundle bundle = new Bundle();

    }


    public class MyAdapter extends ArrayAdapter<String>
    {
        public MyAdapter()
        {
            // We have to use ExampleListActivity.this to refer to the outer class (the activity)
            super(ExampleListActivity.this, android.R.layout.simple_list_item_1, names);
        }

        public View getView(int index, View convertView, ViewGroup parent)
        {
            View view = convertView;
            if(view==null)
            {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.poientry, parent, false);
            }
            TextView title = (TextView)view.findViewById(R.id.poi_name), detail = (TextView)view.findViewById(R.id.poi_desc);
            title.setText(names[index]);
            detail.setText(details[index]);
            return view;
        }
    }
}

