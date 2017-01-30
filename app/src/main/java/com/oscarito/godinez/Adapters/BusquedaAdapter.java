package com.oscarito.godinez.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.oscarito.godinez.IO.Model.CategoryResponse;
import com.oscarito.godinez.Model.ItemContacto;
import com.oscarito.godinez.R;

import java.util.ArrayList;

/**
 * Created by oemy9 on 21/01/2017.
 */

public class BusquedaAdapter extends BaseAdapter {
    private static class ViewHolder {
        TextView tvBusquedaCat;
        ImageView imageBusquedaCat;
    }


    private LayoutInflater inflater;
    private  ArrayList<CategoryResponse> listCategorias;
    public BusquedaAdapter(Activity act, ArrayList<CategoryResponse> listCategorias) {
        this.inflater = LayoutInflater.from(act);
        this.listCategorias=listCategorias;
    }

    @Override
    public int getCount() {
        return  this.listCategorias.size();
    }

    @Override
    public Object getItem(int i) {
        return  this.listCategorias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final  ViewHolder viewHolder;
        if(view==null){
            viewHolder = new ViewHolder();
            view=inflater.inflate(R.layout.item_busqueda,viewGroup,false);
            viewHolder.tvBusquedaCat=(TextView)view.findViewById(R.id.tvBusquedaCat);
            viewHolder.imageBusquedaCat=(ImageView)view.findViewById(R.id.imgBusquedaCat);
            view.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.tvBusquedaCat.setText(listCategorias.get(i).getName().toString());
        return  view;
    }
}
