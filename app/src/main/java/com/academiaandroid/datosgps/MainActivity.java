package com.academiaandroid.datosgps;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity extends AppCompat
{
    LocationManager ubicacion;
    TextView latitud, longitud;
    Button actual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latitud = (TextView) findViewById(R.id.latitud);
        longitud = (TextView) findViewById(R.id.longitud);
        actual=(Button) findViewById(R.id.actualizar);
        actual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObtenerLocalizacion();
            }
        });

    }

    public void ObtenerLocalizacion() {

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            },1000);
        }
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        latitud.setText("Latitud:  \n"+String.valueOf(loc.getLatitude()));
        longitud.setText("Longitud:  \n"+String.valueOf(loc.getLongitude()));
    }
}
