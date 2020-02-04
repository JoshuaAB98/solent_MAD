package com.example.mapstool;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

public class ChangePositionActivity extends AppCompatActivity implements View.OnClickListener {


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pos);

        Button reset = (Button)findViewById(R.id.resetBtn);
        reset.setOnClickListener(this);
        Button submit = (Button)findViewById(R.id.submitBtn);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        Double defLatitude = getIntent().getDoubleExtra("defaultLatitude", 0.0);
        Double defLongitude = getIntent().getDoubleExtra("defaultLongitude", 0.0);

        Double Longitude;
        Double Latitude;

        EditText lonEditText = (EditText) findViewById(R.id.longitude);
        EditText latEditText = (EditText) findViewById(R.id.latitude);

        if (v.getId()==R.id.submitBtn)
        {
            Longitude = parseLong(lonEditText);
            Latitude = parseLat(latEditText);

            bundle.putDouble("Longitude", Longitude);
            bundle.putDouble("Latitude", Latitude);
            intent.putExtras(bundle);
        }
        else if(v.getId()==R.id.resetBtn)
        {
            bundle.putDouble("Longitude", defLongitude);
            bundle.putDouble("Latitude", defLatitude);
            intent.putExtras(bundle);
        }

        setResult(RESULT_OK,intent);
        finish();
    }

    private Double parseLat(EditText geoEditText) {
        String input = geoEditText.getText().toString();
        try {
            Double latitude = Double.parseDouble(input);
            if (latitude > 90 || latitude < -90) {
                geoEditText.setText("");
                geoEditText.setHint("invalid latitude: " + input);
                String message = "invalid latitude";
                popupMessage(message);
                return null;
            }
            return latitude;
        } catch (Exception e) {
            geoEditText.setText("");
            geoEditText.setHint("invalid latitude: " + input);
            String message = "invalid latitude: " + input;
            popupMessage(message);
            return null;
        }
    }

    private Double parseLong(EditText geoEditText) {
        String input = geoEditText.getText().toString();
        try {
            Double longitude = Double.parseDouble(input);
            if (longitude > 180 || longitude < -180) {
                geoEditText.setText("");
                geoEditText.setHint("invalid longitude: " + input);
                String message = "invalid logitude: " + input;
                popupMessage(message);
                return null;
            }
            return longitude;
        } catch (Exception e) {
            geoEditText.setText("");
            geoEditText.setHint("invalid longitude: " + input);
            String message = "invalid longitude: " + input;
            popupMessage(message);
            return null;
        }
    }

    private void popupMessage(String message) {
        new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage(message).show();

    }

}
