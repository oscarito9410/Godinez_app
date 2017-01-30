package com.oscarito.godinez.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.oscarito.godinez.Adapters.LugaresAdapter;
import com.oscarito.godinez.DataBase.SQLiteCrudHelper;
import com.oscarito.godinez.IO.Model.AroundResponse;
import com.oscarito.godinez.R;

import java.util.ArrayList;

/**
 * Created by oemy9 on 29/01/2017.
 */
public class FragmentFavoritos extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favoritos, container, false);
        SQLiteCrudHelper crudHelper=new SQLiteCrudHelper(getContext());
        ArrayList<AroundResponse>favoritos=new ArrayList<>();
        favoritos=crudHelper.getFavoritos();
        TextView tvEmpty=(TextView)rootView.findViewById(R.id.emptyElement);
        RecyclerView  recyclerView=(RecyclerView) rootView.findViewById(R.id.recyclerFavoritos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if(favoritos.size()>0) {
            LugaresAdapter adapter = new LugaresAdapter(getContext(), favoritos);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
        }
        else {
            tvEmpty.setVisibility(View.VISIBLE);
        }
        return  rootView;
    }

}
