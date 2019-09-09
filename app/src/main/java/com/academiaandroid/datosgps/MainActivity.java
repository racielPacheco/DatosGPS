package com.academiaandroid.datosgps;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

public class MainActivity extends Activity extends AppCompat
{
    private TextView tvLatitud, tvLongitud, tvAltura, tvPrecision;

    /*Se declara una variable de tipo LocationManager encargada de proporcionar acceso al servicio de localización del sistema.*/
    private LocationManager locManager;
    /*Se declara una variable de tipo Location que accederá a la última posición conocida proporcionada por el proveedor.*/
    private Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLatitud = (TextView)findViewById(R.id.tvLatitud);
        tvLongitud = (TextView)findViewById(R.id.tvLongitud);
        tvAltura = (TextView)findViewById(R.id.tvAltura);
        tvPrecision = (TextView)findViewById(R.id.tvPrecision);

        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            tvLatitud.setText("No se han definido los permisos necesarios.");
            tvLongitud.setText("");
            tvAltura.setText("");
            tvPrecision.setText("");

            return;
        }else
        {
            /*Se asigna a la clase LocationManager el servicio a nivel de sistema a partir del nombre.*/
            locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            tvLatitud.setText(String.valueOf(loc.getLatitude()));
            tvLongitud.setText(String.valueOf(loc.getLongitude()));
            tvAltura.setText(String.valueOf(loc.getAltitude()));
            tvPrecision.setText(String.valueOf(loc.getAccuracy()));
        }
    }
}
