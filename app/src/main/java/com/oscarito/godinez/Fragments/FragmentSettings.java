package com.oscarito.godinez.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.oscarito.godinez.Helpers.Settings;
import com.oscarito.godinez.Helpers.SettingsConstans;
import com.oscarito.godinez.IO.Model.CategoryResponse;
import com.oscarito.godinez.IO.Model.ViewModel.Methods;
import com.oscarito.godinez.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oemy9 on 15/01/2017.
 */

public class FragmentSettings extends Fragment {

    private Settings st;
    private EditText txtRadio;
    private RatingBar ratingSettings;
    private Spinner spinnerCategoria;
    private Button btnGuardar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.activity_filtro, container, false);
        this.st=new Settings(getActivity());
        this.spinnerCategoria=(Spinner)rootView.findViewById(R.id.spinnerCategorias);
        this.ratingSettings=(RatingBar)rootView.findViewById(R.id.ratingSettings);
        this.txtRadio=(EditText)rootView.findViewById(R.id.txtRadio);
        this.btnGuardar=(Button)rootView.findViewById(R.id.btnGuardar);
        this.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Guarda configuracion
                st.add(SettingsConstans.STARTS,ratingSettings.getRating());
                st.add(SettingsConstans.RANGE, Integer.parseInt(txtRadio.getText().toString()));
                st.add(SettingsConstans.CATEGORY_ID,1);
                Toast.makeText(getContext(),R.string.configuracion_ok, Toast.LENGTH_LONG).show();
            }
        });
        this.getItems();
        return  rootView;
    }

    private void getItems(){

        /*Configuración por default*/
        this.txtRadio.setText(String.valueOf(this.st.getInt(SettingsConstans.RANGE)));
        this.ratingSettings.setRating(this.st.getFloat(SettingsConstans.STARTS));

        Methods methods=new Methods(getContext());
        methods.getCategories().enqueue(new Callback<ArrayList<CategoryResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoryResponse>> call, Response<ArrayList<CategoryResponse>> response) {
                if(response.isSuccessful()){
                    ArrayList<String>categorias=new ArrayList<String>();
                    for(CategoryResponse categoria: response.body()) {
                        categorias.add(categoria.getName());
                    }
                    ArrayAdapter<String>adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,categorias);
                    spinnerCategoria.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CategoryResponse>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
