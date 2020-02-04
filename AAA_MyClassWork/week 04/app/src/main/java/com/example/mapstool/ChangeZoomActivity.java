package com.example.mapstool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;

public class ChangeZoomActivity extends AppCompatActivity implements View.OnClickListener {

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_zoom);

        Button zoomIn = (Button)findViewById(R.id.btnZoomIn);
        zoomIn.setOnClickListener(this);
        Button zoomOut = (Button)findViewById(R.id.btnZoomOut);
        zoomOut.setOnClickListener(this);
        Button defaultZoom = (Button)findViewById(R.id.btnDefaultZoom);
        defaultZoom.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        int mapZoom = getIntent().getIntExtra("currentZoom", 11);

        if(v.getId() == R.id.btnZoomOut){
            mapZoom -= 1;
        }
        else if(v.getId() == R.id.btnZoomIn){
            mapZoom += 1;
        }
        else if(v.getId() == R.id.btnDefaultZoom){
            mapZoom = 11;
        }
        else{
            mapZoom = 16;
        }

        bundle.putInt("zoom",mapZoom);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
}
