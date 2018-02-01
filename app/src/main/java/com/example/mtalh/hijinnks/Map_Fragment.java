package com.example.mtalh.hijinnks;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@SuppressLint("ValidFragment")
public class Map_Fragment extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMapClickListener,
        LocationListener, DirectionFinderListener {


    public static final int REQUEST_LOCATION_CODE = 99;
    private static final int POPUP_POSITION_REFRESH_INTERVAL = 16;
    static DirectionFinderListener context;
    /* public void onClick(View view) {
         if (view.getId() == R.id.button_search) {
             EditText tf_Location = (EditText) findViewById(R.id.search_edittext);
             String location = tf_Location.getText().toString();
             List<Address> addressList = null;
             MarkerOptions mo = new MarkerOptions();
             if (!location.equals("")) {
                 Geocoder geocoder = new Geocoder(this);
                 try {
                     addressList = geocoder.getFromLocationName(location, 1);
                 } catch (IOException e) {
                     e.printStackTrace();
                     Log.d("MAPERROR", "" + "dddddddddddd");
                     Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
                 }
                 for (int i = 0; i < addressList.size(); i++) {

                     Address myaddress = addressList.get(i);
                     LatLng latlang = new LatLng(myaddress.getLatitude(), myaddress.getLongitude());
                     mo.position(latlang);
                     mo.title(location + "");
                     mo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                     mMap.addMarker(mo);
                     mMap.animateCamera(CameraUpdateFactory.newLatLng(latlang));
                 }
             }
             InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
             imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
         }
     }
 */
    static View vv;
    HomeFragmentInterface homeFragmentInterface;
    MapView mapView;
    double current_latitude, current_longitude;
    TextView start_direction, distance_tv;
    ImageView back_image;
    SupportMapFragment mapFragment;
    View view;
    Location location_new;
    Button button1, button2;
    Location location_pref;
    LatLng myLatlng;
    LatLng current_latLng;
    String address_start;
    String address_start_complete;
    String knownName_start;
    String address_end;
    String address_end_complete;
    String knownName_end;
    String distance;
    String duration;
    double latitude, longitude;
    double end_latitude, end_longitude;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    Dialog dialog1;
    PolylineOptions polylineOptions;
    String dimmy_start_address = "Anarkali, Lahore 54000, Pakistan";
     Geocoder geocoder_start;
    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentLocationMarker;
    private View infoWindowContainer;
    private ViewTreeObserver.OnGlobalLayoutListener infoWindowLayoutListener;
    private int popupXOffset;
    private int popupYOffset;
    private RelativeLayout.LayoutParams overlayLayoutParams;
    private LatLng trackedPosition;
    private Handler handler;
    private int markerHeight;
    private Marker mSelectedMarker = null;
    private Circle accuracyCircle;
    private boolean drawAccuracy = true;
    private int accuracyStrokeColor = Color.argb(255, 230, 182, 218);
    private int accuracyFillColor = Color.argb(100, 230, 182, 24);
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    // private OnFragmentInteractionListener mListener;

   /* public Map_Fragment() {
    }*/

    /*public Map_Fragment(HomeFragmentInterface homeFragmentInterface) {
        this.homeFragmentInterface = homeFragmentInterface;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        vv=view;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map_, container, false);
        back_image = (ImageView) view.findViewById(R.id.back_image);
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
//                homeFragmentInterface.icon_change_method_on_back_click(1);
                /*FragmentTransaction fragmentTransaction;
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_framelayout, new HomeFragment());
                fragmentTransaction.commit();*/
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.parseColor("#41539b"));
        }

        infoWindowContainer = view.findViewById(R.id.linearlayout_marker_show);
        infoWindowLayoutListener = new InfoWindowLayoutListener();
        infoWindowContainer.getViewTreeObserver().addOnGlobalLayoutListener(infoWindowLayoutListener);
        overlayLayoutParams = (RelativeLayout.LayoutParams) infoWindowContainer.getLayoutParams();
        button1 = (Button) infoWindowContainer.findViewById(R.id.button1);
        button2 = (Button) infoWindowContainer.findViewById(R.id.button2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Button is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //LatLng latLng = new LatLng(location_pref.getLatitude(), location_pref.getLongitude());
        /*

        if (Prefs.getString("pref_map", "").equals(latLng)) {
            Toast.makeText(getContext(), "Location Matched", Toast.LENGTH_SHORT).show();
        }*/
        return view;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permission is granted
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (client == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else // permission is denied
                {
                    Toast.makeText(getContext(), "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
//                        .title("Long" + latLng)
                        .snippet("Snippet")
//                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.unselect_marker)));

            }
        });
        LatLng hcmus = new LatLng(31.485017, 74.313162);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 14));

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float dpHeight = outMetrics.heightPixels / outMetrics.density;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;


        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 400, r.getDisplayMetrics());

        Toast.makeText(getContext(), "" + dpHeight + "   " + deviceHeight, Toast.LENGTH_SHORT).show();

//                        dialog1.getWindow().setLayout(10,10);
        int set_mapMarkerPadding = (int) (deviceHeight - 400);

        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (400 * scale + 0.5f);
        Toast.makeText(getContext(), "Scale " + scale + "DP as Pixel " + dpAsPixels, Toast.LENGTH_SHORT).show();
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getContext(), "" + myLatlng, Toast.LENGTH_SHORT).show();
                Log.d("LONGCLICKONCLICK", "" + myLatlng);
                MarkerOptions markerOptions = new MarkerOptions();
                end_latitude = marker.getPosition().latitude;
                end_longitude = marker.getPosition().longitude;
                markerOptions.position(new LatLng(end_latitude, end_longitude));
                markerOptions.title("Destination");
                markerOptions.draggable(true);
                float results[] = new float[10];
                Location.distanceBetween(latitude, longitude, end_latitude, end_longitude, results);
                markerOptions.snippet("Distance =" + results[0]);
//                mMap.addMarker(markerOptions);
                Log.d("TOTALDISTANCE", results[0] + "");


                Geocoder geocoder;
                List<Address> addresses = new ArrayList<>();
                geocoder = new Geocoder(getContext(), Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(end_latitude, end_longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                if (addresses != null) {
                try {
                    address_end = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getSubLocality();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    knownName_end = addresses.get(0).getSubLocality();
                    String subAdminArea = addresses.get(0).getSubAdminArea();
                    String locality = addresses.get(0).getLocality();
                    address_end_complete = knownName_end + " " + address_end;
                    //Toast.makeText(MapsActivity.this, "adrs :- " + address_end + " KKK  :-" + knownName_end, Toast.LENGTH_LONG).show();
                    Toast.makeText(getContext(), "end address " + address_end + " ,," + locality, Toast.LENGTH_SHORT).show();
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
                Log.d("TOTALDISTANCENEW1", "" + distance);
//                }
                try {
//                    if (address_start!=null && address_end!=null) {
                    new DirectionFinder(Map_Fragment.this, address_start_complete, address_end_complete).execute();
//                    sendRequest();
//                    }
                    Log.d("TOTALDISTANCENEW2", "" + distance);


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }



                /*THIS IS PART 2THIS IS PART 2THIS IS PART 2THIS IS PART 2THIS IS PART 2THIS IS PART 2THIS IS PART 2 */

                Toast.makeText(getContext(), "marker is clicked", Toast.LENGTH_SHORT).show();
                dialog1 = new Dialog(getContext(), R.style.TransparentProgressDialog);
                Window window = dialog1.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.BOTTOM;// Here you can set window top or bottom
//                dialog1.setCancelable(true);
//wlp.dimAmount=1f;
//                wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                window.setAttributes(wlp);
                View view1 = getLayoutInflater().inflate(R.layout.listrow_map_infowindow, null);
                //   int height=dialog1.getWindow().getDecorView().getHeight();


//                LinearLayout map_info_window_linearlayout=(LinearLayout)view1.findViewById(R.id.map_info_window_linearlayout);
                start_direction = view1.findViewById(R.id.start_direction);
                distance_tv = view1.findViewById(R.id.distance_tv);

                marker.hideInfoWindow();
                int screenSize = getResources().getConfiguration().screenLayout &
                        Configuration.SCREENLAYOUT_SIZE_MASK;

                String toastMsg;
                switch (screenSize)

                {
                    case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                        toastMsg = "Extra Large screen";
                        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                        break;
                    case Configuration.SCREENLAYOUT_SIZE_LARGE:
                        toastMsg = "Large screen";
                        wlp.y = 140;
//                        view1.getHeight();
                        mMap.setPadding(0, 100, 0, 00);

                        break;
                    case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                        toastMsg = "Normal screen";
                        wlp.y = 140;
//                        view1.getHeight();
                        mMap.setPadding(0, 0, 0, 400);

                        break;
                    case Configuration.SCREENLAYOUT_SIZE_SMALL:
                        toastMsg = "Small screen";
                        wlp.y = 140;
//                        view1.getHeight();
                        mMap.setPadding(0, 0, 0, 400);

                        break;
                    default:
                        toastMsg = "Screen size is neither large, normal or small";
                }
                Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();


                start_direction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "start button is clicked", Toast.LENGTH_SHORT).show();
                        polylinePaths.add(mMap.addPolyline(polylineOptions));
                    }
                });
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                ColorDrawable dialogColor = new ColorDrawable(Color.BLACK);
                dialogColor.setAlpha(150); //(0-255) 0 means fully transparent, and 255 means fully opaque
                dialog1.setContentView(view1);
                dialog1.setCanceledOnTouchOutside(true);


                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.unselect_marker);

                if (mSelectedMarker != null) {
                    mSelectedMarker.setIcon(icon);
                }
                mSelectedMarker = marker;
                mSelectedMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.selected_marker));







               /* if (mSelectedMarker!=null)
                    mSelectedMarker.setIcon(icon);
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.selected_marker));
                mSelectedMarker = marker;*/

               /* Projection projection;
                projection = mMap.getProjection();
                trackedPosition = marker.getPosition();
                Point trackedPoint = projection.toScreenLocation(trackedPosition);
                trackedPoint.y -= popupYOffset / 8;
                LatLng newCameraLocation = projection.fromScreenLocation(trackedPoint);
                mMap.animateCamera(CameraUpdateFactory.newLatLng(newCameraLocation), ANIMATION_DURATION, null);
                infoWindowContainer.setVisibility(VISIBLE);
                marker.showInfoWindow();*/
                return false;
            }
        });

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                Log.d("ddddddddddddddddddddddd", "onCameraMove: ");
                //  infoWindowContainer.setVisibility(View.GONE);
            }
        });
   /*     CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(new LatLng(37.4, -122.1));
        circleOptions.radius(1000); // radius of circle in meters
        circleOptions.strokeColor(Color.GREEN);//apply stroke with blue
        circleOptions.fillColor(Color.RED);// fill circle with red
        mMap.addCircle(circleOptions);

        *//*Circle circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(-33.87365, 151.20689))
                .radius(10000)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));*//*
        final CircleOptions accuracyCircleOptions = new CircleOptions()
                .center(new LatLng(37.4, -122.1))
                .fillColor(Color.GREEN)
                .strokeColor(Color.RED)
                .strokeWidth(2.0f);
        mMap.addCircle(accuracyCircleOptions);
*/
       /* mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                infoWindowContainer.setVisibility(View.GONE);
            }
        });*/

//        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
//            @Override
//            public View getInfoWindow(Marker marker) {
//                return null;
//            }
//
//            @Override
//            public View getInfoContents(Marker marker) {
//                View v = null;
//                try {
//
//                    // Getting view from the layout file info_window_layout
//                   // v = getLayoutInflater().inflate(R.layout.listrow_map_infowindow, null);
//
//                    // Getting reference to the TextView to set latitude
//                    TextView addressTxt = (TextView) v.findViewById(R.id.start_direction);
//                    addressTxt.setText(marker.getTitle());
//
//                } catch (Exception ev) {
//                    System.out.print(ev.getMessage());
//                }
//
//                return v;
//            }
//        });


//        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
//            @Override
//            public View getInfoWindow(Marker marker) {
//
//
//                //   infoWindowContainer.setAlpha(marker.getAlpha());
////                marker.
//                final View v = getLayoutInflater().inflate(R.layout.listrow_map_infowindow, null);
//                TextView start_direction = v.findViewById(R.id.start_direction);
//                start_direction.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//
//                        Toast.makeText(view.getContext(), "Start direction button is clicked ", Toast.LENGTH_SHORT).show();
//                    }
//                });
//           /*     Projection projection = mMap.getProjection();
//                LatLng markerPosition = marker.getPosition();
//                Point markerPoint = projection.toScreenLocation(markerPosition);
//                Point targetPoint = new Point(markerPoint.x, markerPoint.y - 300000000);
//                LatLng targetPosition = projection.fromScreenLocation(targetPoint);
//                mMap.animateCamera(CameraUpdateFactory.newLatLng(targetPosition), 100000000, null);
//
//
//                //LayoutInflater inflater = LayoutInflater.from(getContext());
//                final Dialog dialog1 = new Dialog(getContext());
//                Window window = dialog1.getWindow();
//                WindowManager.LayoutParams wlp = window.getAttributes();
//                wlp.gravity = Gravity.BOTTOM; // Here you can set window top or bottom
//                wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
//                window.setAttributes(wlp);
//              //  View view1 = inflater.inflate(R.layout.listrow_map_infowindow, null);
//                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog1.setContentView(v);
//                dialog1.show()
//                ;
//              /*  Point mappoint = googleMap.getProjection().toScreenLocation(new LatLng(current_latitude - 0.002, current_longitude - 0.0034));
//                mappoint.set(mappoint.x, mappoint.y-30);
//                googleMap.animateCamera(CameraUpdateFactory.newLatLng(googleMap.getProjection().fromScreenLocation(mappoint)));
//*/
//
//
//
//
//             /*   float container_height = getResources().getDimension(R.dimen.DIP_300);
//                Projection projection = mMap.getProjection();
//                Point markerScreenPosition = projection.toScreenLocation(marker.getPosition());
//                Point pointHalfScreenAbove = new Point(markerScreenPosition.x,(int) (markerScreenPosition.y - (container_height / 2)));
//                LatLng aboveMarkerLatLng = projection.fromScreenLocation(pointHalfScreenAbove);
//                marker.showInfoWindow();
//                CameraUpdate center = CameraUpdateFactory.newLatLng(aboveMarkerLatLng);
//                mMap.moveCamera(center);
//                mMap.animateCamera(center);
//               // marker.showInfoWindow();
//
//
//*/
//
//
////                start_direction = (TextView) v.findViewById(R.id.start_direction);
//                return v;
//
//            }
//
//            @Override
//            public View getInfoContents(Marker marker) {
//
//                View view_info = getLayoutInflater().inflate(R.layout.listrow_map_infowindow, null);
//                start_direction = (TextView) view_info.findViewById(R.id.start_direction);
//                start_direction.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), "Start Direction Button is Clicking", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//                    @Override
//                    public void onInfoWindowClick(Marker marker) {
//                        View view_info = getLayoutInflater().inflate(R.layout.listrow_map_infowindow, null);
//
//                        start_direction = (TextView) view_info.findViewById(R.id.start_direction);
//                        start_direction.setText("cccccccc");
//                        start_direction.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Toast.makeText(getActivity(), "Start direction is clikced", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
//                return view_info;
//            }
//        });
  /*      current_latitude = location_new.getLatitude();
        current_longitude = location_new.getLongitude();

        mMap.addMarker(new MarkerOptions().position(new LatLng(current_latitude - 0.002, current_longitude - 0.0034)).title("Marker")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(current_latitude - 0.004, current_longitude - 0.0041)).title("Marker")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(current_latitude + 0.007, current_longitude + 0.0042)).title("Marker")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(current_latitude + 0.0088, current_longitude + 0.0063)).title("Marker")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
*/
    }

    protected synchronized void buildGoogleApiClient() {
        client = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        if (currentLocationMarker != null) {
            currentLocationMarker.remove();
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        latitude = location.getLatitude();
        longitude = location.getLongitude();
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.title("" + latLng);
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.unselect_marker));
//        currentLocationMarker = mMap.addMarker(markerOptions);
        // Prefs.putString("pref_map", String.valueOf(latLng));

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
        mMap.animateCamera(cameraUpdate);


//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));
        if (client != null) {
            if (client.isConnected()) {
                LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
            }
        }


//        Log.d("MAPCHANGES", "FFFFFFFFFFFFFFFFF");

        List<Address> addresses = new ArrayList<>();
//        if (geocoder != null)
//            geocoder = new Geocoder(vv.getContext(), Locale.getDefault());

        try {
//            if (geocoder!=null)
            if (addresses != null) {
                geocoder_start = new Geocoder(getActivity(),Locale.getDefault());
                //fetch data from addresses
                addresses = geocoder_start.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5


            } else {
                Toast.makeText(getContext(), "There is not current location ", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            address_start = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getSubLocality();
            String country = addresses.get(0).getFeatureName();
             String postalCode = addresses.get(0).getSubAdminArea();
            String postalCode2 = addresses.get(0).getPostalCode();
            String postalCode3 = addresses.get(0).getPremises();
            String postalCode4 = addresses.get(0).getSubThoroughfare();
            Locale postalCode5 = addresses.get(0).getLocale();
            knownName_start = addresses.get(0).getFeatureName();
            address_start_complete = state + " " + address_start;
            Toast.makeText(getContext(), "adrs :- " + address_start + " sss " + knownName_start + city + state + country + postalCode+ postalCode2+ postalCode3+ postalCode4+ postalCode5, Toast.LENGTH_LONG).show();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();

        }
        current_latitude = location.getLatitude();
        current_longitude = location.getLongitude();
        final CircleOptions accuracyCircleOptions = new CircleOptions()
                .center(new LatLng(current_latitude, current_longitude))
                .fillColor(Color.GREEN)
                .strokeColor(Color.RED)
                .strokeWidth(2.0f);
        mMap.addCircle(accuracyCircleOptions);
       /* mMap.addMarker(new MarkerOptions().position(new LatLng(current_latitude - 0.002, current_longitude - 0.0034))*//*.title("Marker")*//*
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.unselect_marker)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(current_latitude - 0.004, current_longitude - 0.0041))*//*.title("Marker")*//*
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.unselect_marker)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(current_latitude + 0.007, current_longitude + 0.0042))*//*.title("Marker")*//*
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.unselect_marker)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(current_latitude + 0.0088, current_longitude + 0.0063))*//*.title("Marker")*//*
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.unselect_marker)));*/
    }

    public ViewTreeObserver.OnGlobalLayoutListener getInfoWindowLayoutListener() {
        return infoWindowLayoutListener;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(locationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (getActivity() != null) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (client != null) {
                    if (client.isConnected()) {
                        LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
                    }
                }
            }
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            return false;
        } else
            return true;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (null != mSelectedMarker) {
            mSelectedMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.hijinnks_logo));
        }
        mSelectedMarker = null;
    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(getContext(), "Please wait.",
                "Finding direction..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline : polylinePaths) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                distance_tv.setText(distance);
                dialog1.show();
            }
        }, 500);
        progressDialog.dismiss();

        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {

          /*  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
            ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);*/

            distance = (route.distance.text);
            duration = (route.duration.text);
            String distancenew;
            distance = distance.substring(0, distance.length() - 3);
            Toast.makeText(getContext(), ""+distance, Toast.LENGTH_SHORT).show();
            /*
            String numberOnly= distance.replaceAll("[^0-9]", "");
            Toast.makeText(getContext(), "NUMBER DISTANCE"+numberOnly, Toast.LENGTH_SHORT).show();*/
            Log.d("TOTALDISTANCENEW3", "" + distance);
           /* originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                    .title(route.endAddress)
                    .position(route.endLocation)));*/

            polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.MAGENTA).
                    width(5);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));


        }

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

 /*   @Override
    public void onPause() {

        mapView.onPause();
        super.onPause();

    }

    @Override
    public void onResume() {

        mapView.onResume();
        super.onResume();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }*/

    /*@Override
                public void onAttach(Context context) {
                    super.onAttach(context);
                    if (context instanceof OnFragmentInteractionListener) {
                        mListener = (OnFragmentInteractionListener) context;
                    } else {
                        throw new RuntimeException(context.toString()
                                + " must implement OnFragmentInteractionListener");
                    }
                }

                @Override
                public void onDetach() {
                    super.onDetach();
                    mListener = null;
                }
            */

    private class InfoWindowLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            //размеры окна изменились, обновляем смещения
            popupXOffset = infoWindowContainer.getWidth() / 2;
            popupYOffset = infoWindowContainer.getHeight();
        }
    }
}
