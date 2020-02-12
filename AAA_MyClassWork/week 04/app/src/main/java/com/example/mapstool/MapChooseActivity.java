package com.example.mapstool;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

public class MapChooseActivity extends AppCompatActivity implements View.OnClickListener {

    String[] strings, details;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_choose);

        details = new String[] { "This is the normal map view", "This is the bike view map" };
        strings = new String[]{};


        Button regular = (Button)findViewById(R.id.btnRegular);
        regular.setOnClickListener(this);
        Button hikebikemap = (Button)findViewById(R.id.btnHikeBikeMap);
        hikebikemap.setOnClickListener(this);

        MyAdapter adapter = new MyAdapter();
        setListAdapter(adapter);
    }

    private void setListAdapter(MyAdapter adapter) {
    }

    public void onClick(View v)
    {
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        boolean hikebikemap=false;
        if (v.getId()==R.id.btnHikeBikeMap)
        {
            hikebikemap=true;
        }
        bundle.putBoolean("com.example.hikebikemap",hikebikemap);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }

    public class MyAdapter extends ArrayAdapter<String>
    {
        public MyAdapter()
        {
            // We have to use ExampleListActivity.this to refer to the outer class (the activity)
            super(MapChooseActivity.this, android.R.layout.simple_list_item_1, details);
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
            title.setText(strings[index]);
            detail.setText(details[index]);
            return view;
        }
    }
}