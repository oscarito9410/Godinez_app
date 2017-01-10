package com.oscarito.godinez.Fragments;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.oscarito.godinez.Adapters.LugaresAdapter;
import com.oscarito.godinez.IO.Model.Category;
import com.oscarito.godinez.IO.Model.NearResponse;
import com.oscarito.godinez.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Oscar Perez Martinez on 03/01/2017.
 */
public class FragmentLugares extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootView=inflater.inflate(R.layout.fragment_lugares, container, false);
        RecyclerView recyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerLugares);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LugaresAdapter adapter=new LugaresAdapter(getContext(),getItemsEjemplo());
        CircularProgressView progressView=(CircularProgressView)rootView.findViewById(R.id.progressLugar);
        recyclerView.setAdapter(adapter);
        progressView.setVisibility(View.GONE);
        return rootView;

    }

    public ArrayList<NearResponse>getItemsEjemplo(){

        String[] nombres=new String[3];
        nombres[0]="TAQUERIA EL COMPA";
        nombres[1]="SUSHI CROSS";
        nombres[2]="FONDITA DOÑA ANAHÍ";
        String[] logos=new String[3];
        logos[0]="http://taqueriaelsabor.net/wp-content/uploads/2013/09/sabor_logo_2011-300x158.png";
        logos[1]="http://www.soultravelmultimedia.com/wp-content/uploads/2011/11/Sushi-Logo-10.png";
        logos[2]="http://www.buscorestaurantes.com.mx/files/logo/Fonda-Mexicana--Polanco-5121.jpg";
        ArrayList itemsEjemplo=new ArrayList();
        for(Integer j=0;j<20;j++) {
            Random random=new Random();
            Random randomLogo=new Random();
            int positionLogo=randomLogo.nextInt(3);
            NearResponse response = new NearResponse();
            Category category = new Category();
            category.setType("Restaurante");
            response.setAddress("EJEMPLO DIRECCION"+ j.toString());
            response.setName(nombres[positionLogo]);
            response.setLogo(logos[positionLogo]);
            response.setRating(random.nextInt(5));
            response.setCategory(category);
            itemsEjemplo.add(response);
        }
        return itemsEjemplo;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}