package com.oscarito.godinez.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oscarito.godinez.IO.Model.MenuResponse;
import com.oscarito.godinez.R;

import java.util.ArrayList;

/**
 * Created by oemy9 on 28/01/2017.
 */

public class ComentarioAdapter extends BaseAdapter
{

    private LayoutInflater layoutInflater;
    private ArrayList<MenuResponse>listComentarios;

    public static  class ViewHolder{
        TextView tvComentario;
    }

    public  ComentarioAdapter(Activity act, ArrayList<MenuResponse>listComentarios)
    {
        layoutInflater=LayoutInflater.from(act);
        this.listComentarios=listComentarios;
    }


    @Override
    public int getCount() {
        return listComentarios.size();
    }

    @Override
    public Object getItem(int i) {
        return listComentarios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
      final ViewHolder viewHolder;
       if(view==null){
           viewHolder=new ViewHolder();
           view=layoutInflater.inflate(R.layout.item_comment,viewGroup,false);
           viewHolder.tvComentario=(TextView)view.findViewById(R.id.tvComentario);
           view.setTag(viewHolder);
       }
        else{
          viewHolder=(ViewHolder) view.getTag();
       }
        viewHolder.tvComentario=(TextView)view.findViewById(R.id.tvComentario);
        viewHolder.tvComentario.setText("Sabor y calidad de doña pelos!!");
        return  view;


    }
}
