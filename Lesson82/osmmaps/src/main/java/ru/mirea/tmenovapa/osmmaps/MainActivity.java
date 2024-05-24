package ru.mirea.tmenovapa.osmmaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import ru.mirea.tmenovapa.osmmaps.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    static final private int REQUEST_CODE_PERMISSION = 200;
    private boolean is_permissions_granted = false;
    private MyLocationNewOverlay locationNewOverlay = null;
    private ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.mapView.setZoomRounding(true);
        binding.mapView.setMultiTouchControls(true);
        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        locationNewOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getApplicationContext()), binding.mapView);
        locationNewOverlay.enableMyLocation();
        binding.mapView.getOverlays().add(locationNewOverlay);

        CompassOverlay compassOverlay = new CompassOverlay(getApplicationContext(), new
                InternalCompassOrientationProvider(getApplicationContext()), binding.mapView);
        compassOverlay.enableCompass();
        binding.mapView.getOverlays().add(compassOverlay);

        final Context context = this.getApplicationContext();
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(binding.mapView);
        scaleBarOverlay.setCentred(true);
        scaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        binding.mapView.getOverlays().add(scaleBarOverlay);


        Marker marker = new Marker(binding.mapView);
        marker.setPosition(new GeoPoint(55.794229, 37.700772));
        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(getApplicationContext(),"MIREA", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        binding.mapView.getOverlays().add(marker);
        marker.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));
        marker.setTitle("Title");



        IMapController mapController = binding.mapView.getController();
        mapController.setZoom(15.0);
        GeoPoint startPoint = new GeoPoint(55.794229, 37.700772);
        mapController.setCenter(startPoint);

        Marker marker1 = new Marker(binding.mapView);
        marker1.setPosition(new GeoPoint(55.753544,  37.621211));
        marker1.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(getApplicationContext(),"Red Square", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        binding.mapView.getOverlays().add(marker1);
        marker1.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));


        Marker marker2 = new Marker(binding.mapView);
        marker2.setPosition(new GeoPoint(55.725909, 37.599085));
        marker2.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(getApplicationContext(),"Gorky Park", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        binding.mapView.getOverlays().add(marker2);
        marker2.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));

        Marker marker3 = new Marker(binding.mapView);
        marker3.setPosition(new GeoPoint(55.748972, 37.5385));
        marker3.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(getApplicationContext(),"Moscow City", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        binding.mapView.getOverlays().add(marker3);
        marker3.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));

    }
    @Override
    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        binding.mapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        Configuration.getInstance().save(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        binding.mapView.onPause();
    }









    private void MakePermissionsRequest() {
        is_permissions_granted =
                PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET) &&
                        PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_NETWORK_STATE) &&
                        PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) &&
                        PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) &&
                        PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION) &&
                        PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(!is_permissions_granted) {
            ActivityCompat.requestPermissions(this,
                    new	String[] { android.Manifest.permission.INTERNET,
                            android.Manifest.permission.ACCESS_NETWORK_STATE,
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },	REQUEST_CODE_PERMISSION);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i("", "onRequestPermissionsResult: " + String.valueOf(requestCode));
        if(requestCode == REQUEST_CODE_PERMISSION) {
            is_permissions_granted = (grantResults[0] == PackageManager.PERMISSION_GRANTED);
        } else {
            finish();
        }
    }

}