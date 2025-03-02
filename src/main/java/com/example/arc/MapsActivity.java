package com.example.arc;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.arc.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private static final int FINE_LOCATION_ACCESS_REQUEST_CODE = 10001;
    private static final float GEOFENCE_RADIUS = 20;  // Set the radius for the geofence (circle)
    private static final LatLng markerLocation = new LatLng(53.54072, -113.49409);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Create the notification channel for geofence alerts
        createNotificationChannel();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Check if location services are enabled
        checkLocationServices();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in a default location and move the camera
        LatLng sydney = new LatLng(53.54072, -113.49409); // Example location (replace with the user's location)
        mMap.addMarker(new MarkerOptions().position(sydney).title("BUS STOP 108"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        // Add a circle around the marker
        addCircle(sydney, GEOFENCE_RADIUS);

        // Try to enable user location
        enableUserLocation();

        // Get the user's last known location
        getLastKnownLocation();
    }

    private void enableUserLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Ask for permission if not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_ACCESS_REQUEST_CODE);
        }
    }

    // Handling the result of the permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == FINE_LOCATION_ACCESS_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, enable location on map
                enableUserLocation();
            } else {
                // Permission denied: Show a message to the user
                Toast.makeText(this, "Location permission is required to show your location on the map.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addCircle(LatLng latLng, float radius) {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.radius(radius);  // Set the radius in meters
        circleOptions.strokeColor(Color.argb(255, 0, 255, 0)); // Green color for the border
        circleOptions.fillColor(Color.argb(50, 0, 255, 0)); // Semi-transparent green fill
        circleOptions.strokeWidth(4); // Set the border width
        mMap.addCircle(circleOptions);  // Add the circle to the map
    }

    // Check if location services are enabled
    private void checkLocationServices() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Prompt user to enable GPS
            Toast.makeText(this, "Please enable GPS", Toast.LENGTH_SHORT).show();
        }
    }

    // Create the notification channel
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Geofence Alerts";
            String description = "Notifications when you are near a geofenced area";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("geofence_channel", name, importance);
            channel.setDescription(description);

            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void sendNotification() {
        // Intent for the Yes button that will open QRCodeActivity
        Intent yesIntent = new Intent(this, QRCodeActivity.class);
        PendingIntent yesPendingIntent = PendingIntent.getActivity(this, 0, yesIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Intent for the No button (No action, just dismiss notification)
        Intent noIntent = new Intent(this, MainActivity.class); // You can use any activity here
        PendingIntent noPendingIntent = PendingIntent.getActivity(this, 0, noIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Create the notification
        Notification notification = new Notification.Builder(this, "geofence_channel")
                .setContentTitle("Arc Mobile")
                .setContentText("Are you boarding the bus?  \uD83D\uDE8C")
                .setSmallIcon(R.drawable.logo)  // Set the custom PNG icon from the drawable folder
                .addAction(0, "✅ Yes", yesPendingIntent)  // Yes button with green tick emoji
                .addAction(0, "❌ No", noPendingIntent)
                .build();

        // Show the notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    // Get the last known location using FusedLocationProvider
    private void getLastKnownLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            // Use the location data (latitude, longitude)
                            LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));

                            // Check if the user's location is within the geofenced area
                            checkIntersection(userLocation);
                        } else {
                            Toast.makeText(MapsActivity.this, "Location not available", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void checkIntersection(LatLng userLocation) {
        // Calculate the distance between the user's location and the center of the circle
        float[] results = new float[1];
        Location.distanceBetween(userLocation.latitude, userLocation.longitude,
                markerLocation.latitude, markerLocation.longitude, results);

        // If the distance is less than or equal to the radius of the circle
        if (results[0] <= GEOFENCE_RADIUS+30) {
            // Trigger a notification
            sendNotification();
        }
    }
}
