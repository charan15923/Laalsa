package com.cronlogy.charan.laalsa;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cronlogy.charan.laalsa.Adapters.AddressTagsAdapter;
import com.cronlogy.charan.laalsa.Adapters.SavedAddressAdapter;
import com.cronlogy.charan.laalsa.Maps.CustomMapFragment;
import com.cronlogy.charan.laalsa.Maps.MapWrapperLayout;
import com.cronlogy.charan.laalsa.Models.SavedAddressModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.ui.IconGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChangeAddressActivity extends AppCompatActivity implements MapWrapperLayout.OnDragListener,OnMapReadyCallback {

    private static final String TAG = "MarkLocationActivity";

    // Google Map
    private GoogleMap mMap;
    private CustomMapFragment mCustomMapFragment;

    private View mMarkerParentView;
    private ImageView mMarkerImageView;

    private int imageParentWidth = -1;
    private int imageParentHeight = -1;
    private int imageHeight = -1;
    private int centerX = -1;
    private int centerY = -1;

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private TextView mLocationTextView;
    LinearLayout saveLocation;
    LatLng savedLocation;

    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private Boolean mLocationPermissionsGranted = false;


    private RecyclerView addressTagRcv;
    AddressTagsAdapter addressTagsAdapter;
    ArrayList<String> addressTagsList;

    private RecyclerView savedAddressRcv;
    SavedAddressAdapter savedAddressAdapter;
    ArrayList<SavedAddressModel> savedAddressList;

    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_change_address);
        getLocationPermission();

        backArrow=(ImageView)findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addressTagRcv=(RecyclerView)findViewById(R.id.addressTagsRcv);
        addressTagRcv.setHasFixedSize(true);
        addressTagRcv.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        addressTagRcv.setLayoutManager(layoutManager);


        addressTagsList = new ArrayList<>();
        addressTagsList.add("Home");
        addressTagsList.add("Office");
        addressTagsList.add("Hotel");
        addressTagsList.add("Hostel");
        addressTagsList.add("Other");

        addressTagsAdapter = new AddressTagsAdapter(ChangeAddressActivity.this,addressTagsList);
        addressTagRcv.setAdapter(addressTagsAdapter
        );


        savedAddressRcv=(RecyclerView)findViewById(R.id.savedAddressRcv);
        savedAddressRcv.setHasFixedSize(true);
        savedAddressRcv.setNestedScrollingEnabled(false);
        savedAddressRcv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        savedAddressList=new ArrayList<>();
        for(int i=0;i<5;i++){
            SavedAddressModel savedAddressModel = new SavedAddressModel();
            savedAddressModel.setTitle("Home "+ i+1);
            savedAddressModel.setSelected(false);
            savedAddressModel.setAddress("Address "+ i+1);
            savedAddressList.add(savedAddressModel);
        }

        savedAddressAdapter = new SavedAddressAdapter(this,savedAddressList);
        savedAddressRcv.setAdapter(savedAddressAdapter);

    }


    private void initializeUI() {

        try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }
        mLocationTextView = (TextView) findViewById(R.id.location_text_view);
        mMarkerParentView = (View)findViewById(R.id.marker_view_incl);
        mMarkerImageView = (ImageView) findViewById(R.id.marker_icon_view);
        mMarkerParentView.setVisibility(View.INVISIBLE);

    }

    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {FINE_LOCATION,
                COURSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initializeUI();
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            getLocationPermission();
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initializeUI();
                }
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus){
            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

            if(mMarkerParentView!= null){
                imageParentWidth = mMarkerParentView.getWidth();
                imageParentHeight = mMarkerParentView.getHeight();
                imageHeight = mMarkerImageView.getHeight();

                centerX = imageParentWidth / 2;
                centerY = (imageParentHeight / 2) + (imageHeight / 2);
            }else{
                Toast.makeText(this,"NULL",Toast.LENGTH_LONG).show();
            }

        }

    }

    private void initilizeMap() {
        if (mMap == null) {
            mCustomMapFragment = ((CustomMapFragment) getFragmentManager()
                    .findFragmentById(R.id.map));
            mCustomMapFragment.setOnDragListener(ChangeAddressActivity.this);
            mCustomMapFragment.getMapAsync(ChangeAddressActivity.this);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDrag(MotionEvent motionEvent) {
        mMarkerParentView.setVisibility(View.VISIBLE);
        hideMarker();

        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            Projection projection = (mMap != null && mMap
                    .getProjection() != null) ? mMap.getProjection()
                    : null;
            //
            if (projection != null) {
                LatLng centerLatLng = projection.fromScreenLocation(new Point(
                        centerX, centerY));
                savedLocation= centerLatLng;
                updateLocation(centerLatLng);
            }
        }
    }


    private static final float DEFAULT_ZOOM = 18f;
    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{

            final Task location = mFusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        Log.d(TAG, "onComplete: found location!");
                        Location currentLocation = (Location) task.getResult();
                        savedLocation=new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
                        moveCamera(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),DEFAULT_ZOOM,"Your Location");

                    }else{
                        Log.d(TAG, "onComplete: current location is null");
                        Toast.makeText(ChangeAddressActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

    Marker marker;
    private void moveCamera(LatLng latLng, float zoom,String title){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));



        MarkerOptions options = new MarkerOptions();
        IconGenerator iconFactory = new IconGenerator(ChangeAddressActivity.this);
        iconFactory.setRotation(0);
        iconFactory.setBackground(null);
        options.position(latLng);
        options.title(title);
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker));
        marker= mMap.addMarker(options);
        //marker= mMap.addMarker(createMarker(ChangeAddressActivity.this,latLng,0));



    }


    public static MarkerOptions createMarker(Context context, LatLng point, int bedroomCount) {
        MarkerOptions marker = new MarkerOptions();
        marker.position(point);
        int px = context.getResources().getDimensionPixelSize(R.dimen.marker_dimen);
        View markerView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_view, null);
        markerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        markerView.layout(0, 0, px, px);
        markerView.buildDrawingCache();
        Bitmap mDotMarkerBitmap = Bitmap.createBitmap(px, px, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mDotMarkerBitmap);
        markerView.draw(canvas);
        marker.icon(BitmapDescriptorFactory.fromBitmap(mDotMarkerBitmap));
        return marker;
    }

    private void hideMarker(){
        marker.setVisible(false);
    }

    /*@Override
    public void onDrag(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            Projection projection = (googleMap != null && googleMap
                    .getProjection() != null) ? googleMap.getProjection()
                    : null;
            //
            if (projection != null) {
                LatLng centerLatLng = projection.fromScreenLocation(new Point(
                        centerX, centerY));
                updateLocation(centerLatLng);
            }
        }
    }*/

    private void updateLocation(LatLng centerLatLng) {
        if (centerLatLng != null) {
            Geocoder geocoder = new Geocoder(ChangeAddressActivity.this,
                    Locale.getDefault());

            List<Address> addresses = new ArrayList<Address>();
            try {
                addresses = geocoder.getFromLocation(centerLatLng.latitude,
                        centerLatLng.longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (addresses != null && addresses.size() > 0) {

                String addressIndex0 = (addresses.get(0).getAddressLine(0) != null) ? addresses
                        .get(0).getAddressLine(0) : null;
                String addressIndex1 = (addresses.get(0).getAddressLine(1) != null) ? addresses
                        .get(0).getAddressLine(1) : null;
                String addressIndex2 = (addresses.get(0).getAddressLine(2) != null) ? addresses
                        .get(0).getAddressLine(2) : null;
                String addressIndex3 = (addresses.get(0).getAddressLine(3) != null) ? addresses
                        .get(0).getAddressLine(3) : null;

                String completeAddress = addressIndex0 + "," + addressIndex1;

                if (addressIndex2 != null) {
                    completeAddress += "," + addressIndex2;
                }
                if (addressIndex3 != null) {
                    completeAddress += "," + addressIndex3;
                }
                if (completeAddress != null) {
                    mLocationTextView.setText(completeAddress);
                }
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;

        getDeviceLocation();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

    }

    @Override
    public void onBackPressed() {
        finish();

    }
}
