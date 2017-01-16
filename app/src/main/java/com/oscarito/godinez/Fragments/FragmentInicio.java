package com.oscarito.godinez.Fragments;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.oscarito.godinez.Adapters.MapInfoWindowAdapter;
import com.oscarito.godinez.Helpers.Permisos;
import com.oscarito.godinez.IO.Model.NearResponse;
import com.oscarito.godinez.R;
import com.oscarito.godinez.Views.Detalle;

/**
 * Created by oemy9 on 03/01/2017.
 */
public class FragmentInicio extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleMap mMap;
    private FusedLocationProviderApi fusedLocationProviderApi;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private SupportMapFragment mapFragment;
    public  ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inicio, container, false);
        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapFragment);
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        mapFragment.getView().setVisibility(View.GONE);
        mapFragment.getMapAsync(this);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.zoomTo(18));
        this.getLocation();
    }


    private void getLocation() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(60*1000); //Actualización cada minuto
        //locationRequest.setFastestInterval(100);
        fusedLocationProviderApi = LocationServices.FusedLocationApi;
        googleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }


    }
    public   void obtenerEstablecimientos(){
        try {
            if(Permisos.checkIfPermiso(getActivity())){
                mMap.setMyLocationEnabled(true);
                fusedLocationProviderApi.requestLocationUpdates(googleApiClient, locationRequest, new com.google.android.gms.location.LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        if(mapFragment.getView()!=null) {
                            mapFragment.getView().setVisibility(View.VISIBLE);
                        }
                        mMap.clear();
                        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
                        Marker marker=mMap.addMarker(new MarkerOptions().position(position));
                        //Adaptador de info window
                        if(getActivity()!=null) {
                            View customView = getActivity().getLayoutInflater().inflate(R.layout.item_map_window, null);
                            MapInfoWindowAdapter adapter = new MapInfoWindowAdapter(customView);
                            mMap.setInfoWindowAdapter(adapter);
                        }

                        //Enviando información adicional al marcador
                        NearResponse respuesta=new NearResponse();
                        respuesta.setName("FONDA DOÑA CHONITA");
                        respuesta.setAddress("EJEMPLO DE CALLE");
                        respuesta.setRating(4.5);
                        //Clase to json
                        String infoMaker= new Gson().toJson(respuesta);
                        marker.setSnippet(infoMaker);
                        marker.showInfoWindow();
                        mMap.setOnInfoWindowClickListener(new
                                                                  GoogleMap.OnInfoWindowClickListener() {
                                                                      @Override
                                                                      public void onInfoWindowClick(Marker marker) {
                                                                          Intent i = new Intent(getContext(),Detalle.class);
                                                                          i.putExtra("json",marker.getSnippet());
                                                                          getActivity().startActivity(i);

                                                                      }
                                                                  });
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
                        progressDialog.dismiss();
                    }


                });
            }


        }
        catch (SecurityException ex) {
            Toast.makeText(getContext(), "No permiso", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        this.obtenerEstablecimientos();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}
