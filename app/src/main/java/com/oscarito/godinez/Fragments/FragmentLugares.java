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
import com.oscarito.godinez.IO.Model.CategoryResponse;
import com.oscarito.godinez.IO.Model.AroundResponse;
import com.oscarito.godinez.IO.Model.ViewModel.Methods;
import com.oscarito.godinez.R;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Oscar Perez Martinez on 03/01/2017.
 */
public class FragmentLugares extends Fragment {
   private  RecyclerView recyclerView;
    private CircularProgressView progressView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootView=inflater.inflate(R.layout.fragment_lugares, container, false);
        progressView =(CircularProgressView)rootView.findViewById(R.id.progressLugar);
        recyclerView =(RecyclerView)rootView.findViewById(R.id.recyclerLugares);
        this.getItems();
        return rootView;
    }
    public void getItems(){
        Methods methods=new Methods(getContext());
        methods.getAround(10,10,10).enqueue(new Callback<ArrayList<AroundResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<AroundResponse>> call, Response<ArrayList<AroundResponse>> response) {
                if(response.isSuccessful()) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    LugaresAdapter adapter = new LugaresAdapter(getContext(), response.body());
                    recyclerView.setAdapter(adapter);
                    progressView.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<AroundResponse>> call, Throwable t) {
            }
        });
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}