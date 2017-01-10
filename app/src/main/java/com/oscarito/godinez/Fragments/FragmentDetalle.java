package com.oscarito.godinez.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.oscarito.godinez.Adapters.DetalleInfoAdapter;
import com.oscarito.godinez.IO.Model.NearResponse;
import com.oscarito.godinez.Model.ItemContacto;
import com.oscarito.godinez.R;

import java.util.ArrayList;

/**
 * Created by oemy9 on 07/01/2017.
 */

public class FragmentDetalle extends Fragment   implements AdapterView.OnItemClickListener  {

    private  DetalleInfoAdapter adapter;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detalle, container, false);

        //TIENE INFORMACIÓN ENVIADA DESDE EL MARCADOR DE GOOGLE MAPS?
        if(getActivity().getIntent().hasExtra("json")){
            //OBTENEMOS LA INFROMACIÓN DEL JSON
            String jsonData= getActivity().getIntent().getStringExtra("json");
            NearResponse responseData=new Gson().fromJson(jsonData,NearResponse.class);
            //AGREGAMOS ITEMS A ARRAYLIST
            ArrayList<ItemContacto> listEstablecimiento=new ArrayList<ItemContacto>();
            listEstablecimiento.add(new ItemContacto(getString(R.string.ranting),String.valueOf(responseData.getRating()),R.mipmap.ic_star_grey600_24dp ));
            listEstablecimiento.add(new ItemContacto(getString(R.string.telefono),"56005385",R.mipmap.ic_phone_grey600_24dp));
            listEstablecimiento.add(new ItemContacto(getString(R.string.horario),"8:00 am a 10:00 pm",R.mipmap.ic_timetable_grey600_24dp));
            listEstablecimiento.add(new ItemContacto(getString(R.string.direccion) ,responseData.getAddress(),R.mipmap.ic_home_modern_grey600_24dp));
            //ADAPTADOR DE LISTVIEW
            adapter=new DetalleInfoAdapter(getActivity(),listEstablecimiento);
            //ESTABLECEMOS ADAPTADOR A LISTVIEW
            ListView listviewContacto=(ListView)rootView.findViewById(R.id.listViewContacto);
            listviewContacto.setAdapter(adapter);
            listviewContacto.setOnItemClickListener(this);

            getActivity().setTitle(responseData.getName());

        }

        return  rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ItemContacto contacto=(ItemContacto)adapter.getItem(i);
        if(contacto.getDescripcion().equals(getString(R.string.telefono))){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "56005385", null));
            startActivity(intent);
        }
    }
}
