package com.oscarito.godinez.Fragments;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.gson.Gson;
import com.oscarito.godinez.Adapters.DetalleInfoAdapter;
import com.oscarito.godinez.DataBase.SQLiteCrudHelper;
import com.oscarito.godinez.Helpers.GlobalDataHelper;
import com.oscarito.godinez.Helpers.SettingsConstans;
import com.oscarito.godinez.IO.Model.AroundResponse;
import com.oscarito.godinez.IO.Model.DetailsResponse;
import com.oscarito.godinez.IO.Model.ViewModel.Methods;
import com.oscarito.godinez.Model.ItemContacto;
import com.oscarito.godinez.R;
import com.oscarito.godinez.Views.Detalle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by oemy9 on 07/01/2017.
 */
public class FragmentDetalle extends Fragment   implements AdapterView.OnItemClickListener  {
    private  DetalleInfoAdapter adapter;
    private  DetailsResponse responseData;
    private  String selectedTelefono;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_detalle, container, false);
        final CircularProgressView progressDetalle=(CircularProgressView)rootView.findViewById(R.id.progressDetalle);
        final ListView listviewContacto = (ListView) rootView.findViewById(R.id.listViewContacto);
        FloatingActionButton fabFavorito=(FloatingActionButton)rootView.findViewById(R.id.fabFavorito);
        fabFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(responseData!=null){
                    SQLiteCrudHelper crudHelper=new SQLiteCrudHelper(getContext());
                    if(crudHelper.insetFavorito(responseData)) {
                        Snackbar.make(view, R.string.agregado_fav, Snackbar.LENGTH_LONG).show();
                    }
                    else {
                        abrirDialogoDeleteFavorito(responseData.getId(),view);
                    }
                }

            }
        });

        if(getActivity().getIntent().hasExtra(SettingsConstans.ID_FONDA)){
            Integer idFonda=getActivity().getIntent().getIntExtra(SettingsConstans.ID_FONDA,1);
            Methods methods=new Methods(getActivity());
            methods.getDetails(idFonda).enqueue(new Callback<DetailsResponse>() {
                @Override
                public void onResponse(Call<DetailsResponse> call, Response<DetailsResponse> response) {

                    if(!response.isSuccessful()){
                        Toast.makeText(getContext(),"ERROR"+ response.errorBody().toString(),Toast.LENGTH_LONG).show();
                        return;
                    }
                  try {
                      responseData=response.body();
                      //AGREGAMOS ITEMS A ARRAYLIST
                      ArrayList<ItemContacto> listEstablecimiento = new ArrayList<ItemContacto>();
                      listEstablecimiento.add(new ItemContacto(getString(R.string.ranting), String.valueOf(responseData.getRanking()), R.mipmap.ic_star_grey600_24dp));
                      listEstablecimiento.add(new ItemContacto(getString(R.string.telefono), responseData.getPhone(), R.mipmap.ic_phone_grey600_24dp));
                      listEstablecimiento.add(new ItemContacto(getString(R.string.horario), String.format("%1$s a %2$s", responseData.getService().getOpen(), responseData.getService().getClose()), R.mipmap.ic_timetable_grey600_24dp));
                      listEstablecimiento.add(new ItemContacto(getString(R.string.direccion), responseData.getAddress(), R.mipmap.ic_home_modern_grey600_24dp));
                      //ADAPTADOR DE LISTVIEW
                      adapter = new DetalleInfoAdapter(getActivity(), listEstablecimiento);
                      //ESTABLECEMOS ADAPTADOR A LISTVIEW
                      listviewContacto.setAdapter(adapter);
                      listviewContacto.setOnItemClickListener(FragmentDetalle.this);
                      selectedTelefono=responseData.getPhone();
                      getActivity().setTitle(responseData.getName());
                  }
                  catch(NullPointerException ex){
                      Toast.makeText(getContext(),"EXCEPTION: "+ex.getMessage(),Toast.LENGTH_LONG).show();
                  }
                  finally {
                      progressDetalle.setVisibility(View.GONE);
                      listviewContacto.setVisibility(View.VISIBLE);
                  }
                }
                @Override
                public void onFailure(Call<DetailsResponse> call, Throwable t) {
                }
            });

        }
        return  rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public  void abrirDialogoDeleteFavorito(final int id,final  View view){

        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(R.string.eliminar_fav);
        dialogBuilder.setMessage(R.string.eliminar_caption);
        dialogBuilder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SQLiteCrudHelper crudHelper=new SQLiteCrudHelper(getContext());
                if(crudHelper.delete(id)){
                    Snackbar.make(view, R.string.eliminar_ok, Snackbar.LENGTH_LONG).show();
                }
            }
        });
        dialogBuilder.setNegativeButton(R.string.cancelar,null);
        AlertDialog dialogo=dialogBuilder.create();
        dialogo.show();

    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ItemContacto contacto=(ItemContacto)adapter.getItem(i);
        if(contacto.getDescripcion().equals(getString(R.string.telefono))){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", selectedTelefono, null));
            startActivity(intent);
        }
    }
}
