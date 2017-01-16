package com.oscarito.godinez;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.oscarito.godinez.Fragments.FragmentConfig;
import com.oscarito.godinez.Fragments.FragmentInicio;
import com.oscarito.godinez.Fragments.FragmentLugares;
import com.oscarito.godinez.Helpers.Permisos;
import com.oscarito.godinez.Views.Filtro;

public class Navegacion extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //Navegación primer item  seleccionado
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        //Verifica si el gps está abierto
        LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){

            ((FragmentInicio)fragment).progressDialog.dismiss();

            Permisos.abrirGpsConfiguracion(this);
        }
        this.navegarFragmentos(R.id.nav_inicio);

    }
    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Permisos.CODIGO_LOCATION: {
                if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        if(Permisos.checkIfPermiso(this)) {
                            ((FragmentInicio)fragment).obtenerEstablecimientos();
                        }
                    }

                }
            }

        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navegacion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_buscar:
                    Intent i = new Intent(Navegacion.this, Filtro.class);
                    startActivity(i);
                break;
        }




        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        this.navegarFragmentos(id);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void navegarFragmentos(int id){
        switch (id){
            case R.id.nav_inicio:
                fragment=new FragmentInicio();
                break;
            case R.id.nav_lugares:
                fragment=new FragmentLugares();
                break;
            case R.id.nav_favorito:
                fragment=new FragmentInicio();
                break;
            case R.id.nav_config:
                fragment=new FragmentConfig();
                break;

        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

    }
}
