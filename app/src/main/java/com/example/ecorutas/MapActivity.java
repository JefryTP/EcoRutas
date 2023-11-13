package com.example.ecorutas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String placeName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Recibe la información del lugar seleccionado
        placeName = getIntent().getStringExtra("placeName");

        // Muestra el nombre en TextView
        TextView textViewPlaceName = findViewById(R.id.textViewPlaceName);
        textViewPlaceName.setText(placeName);

        // Inicializa el fragmento de mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double placeLatitude = getIntent().getDoubleExtra("placeLatitude", 0.0);
        double placeLongitude = getIntent().getDoubleExtra("placeLongitude", 0.0);
        LatLng placeLocation = new LatLng(placeLatitude, placeLongitude);
        // Agrega un marcador en el lugar seleccionado y mueve la cámara
        mMap.addMarker(new MarkerOptions().position(placeLocation).title("Marker in " + placeName));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(placeLocation, 18));
        BitmapDescriptor greenIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);

        // Coordenadas de los puntos de reciclaje
        LatLng recyclingPoint1 = new LatLng(-11.998628, -77.060787);
        LatLng recyclingPoint2 = new LatLng(-11.998739, -77.061527);
        LatLng recyclingPoint3 = new LatLng(-11.998435, -77.059534);
        LatLng recyclingPoint4 = new LatLng(-11.998120532339042, -77.05866837242618);

        // Agrega marcadores verdes para los puntos de reciclaje
        mMap.addMarker(new MarkerOptions().position(recyclingPoint1).icon(greenIcon).title("Punto de Reciclaje 1"));
        mMap.addMarker(new MarkerOptions().position(recyclingPoint2).icon(greenIcon).title("Punto de Reciclaje 2"));
        mMap.addMarker(new MarkerOptions().position(recyclingPoint3).icon(greenIcon).title("Punto de Reciclaje 3"));
        mMap.addMarker(new MarkerOptions().position(recyclingPoint4).icon(greenIcon).title("Punto de Reciclaje 4"));
    }
    @Override
    public void onBackPressed() {
        // Puedes agregar lógica adicional si es necesario

        // Limpia los datos de MainActivity
        Intent intent = new Intent(MapActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

        // Llama al método onBackPressed de la clase base
        super.onBackPressed();
    }
}