package com.example.mapstool;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static Double DEFAULT_LAT = 50.9246;
    public static Double DEFAULT_LON = -1.3719;
    public static Integer DEFAULT_ZOOM = 11;

    MapView mv;


    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.chooseMap)
        {
            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivityForResult(intent,0);
            return true;
        }
        else if(item.getItemId() == R.id.changePos)
        {
            Intent intent = new Intent(this, ChangePositionActivity.class);
            intent.putExtra("defaultLat", DEFAULT_LAT);
            intent.putExtra("defaultLon", DEFAULT_LON);
            startActivityForResult(intent,1);
            return true;
        }
        else if(item.getItemId() == R.id.changeZoom)
        {
            Intent intent = new Intent(this, ChangeZoomActivity.class);
            intent.putExtra("currentZoom", mv.getZoomLevel());
            startActivityForResult(intent, 2);
            return true;
        }
        else if(item.getItemId() == R.id.preferences)
        {
            Intent intent = new Intent(this, MapPreferences.class);
            startActivityForResult(intent, 3);
            return true;
        }
        else if(item.getItemId() == R.id.viewList)
        {
            Intent intent = new Intent(this, ExampleListActivity.class);
            startActivityForResult(intent, 4);
            return true;
        }
        return false;
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {

        if(requestCode==0)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean hikebikemap = extras.getBoolean("com.example.hikebikemap");
                if(hikebikemap==true)
                {
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }
        else if(requestCode==1){
            if(resultCode==RESULT_OK){
                Bundle extras=intent.getExtras();

                Double lon = extras.getDouble("Latitude");
                Double lat = extras.getDouble("Longitude");
                if (lon != null && lat != null) {
                    mv.getController().setCenter(new GeoPoint(lat, lon));
                }
            }
        }
        else if(requestCode==2){
            if(resultCode==RESULT_OK){
                //TODO Add code for changing the map Zoom
                Bundle extras=intent.getExtras();
                int newZoom = extras.getInt("zoom");
                mv.getController().setZoom(newZoom);
            }
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        mv = (MapView) findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(DEFAULT_ZOOM);
        mv.getController().setCenter(new GeoPoint(DEFAULT_LAT, DEFAULT_LON));
    }


    private void popupMessage(String message) {
        new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage(message).show();
    }



    public void onClick(View view) {

    }

    public void onResume()
    {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        double lat = Double.parseDouble ( prefs.getString("lat", "50.9") );
        double lon = Double.parseDouble ( prefs.getString("lon", "-1.4") );
        int zoom =  Integer.parseInt(prefs.getString("zoom", "11"));
        boolean autodownload = prefs.getBoolean("autodownload", true);
        String pizzaCode = prefs.getString("pizza", "NONE");

        // do something with the preference data...
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
