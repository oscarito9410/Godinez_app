package com.oscarito.godinez.Adapters;
import com.oscarito.godinez.Model.ItemContacto;
import  com.oscarito.godinez.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;




public class DetalleInfoAdapter extends BaseAdapter {

    private static class ViewHolder {
        TextView tvDescripcion;
        TextView tvInformacion;
        ImageView imageDetalle;
    }

    private ArrayList<ItemContacto> listEstablecimiento;
    private LayoutInflater inflater;

    public DetalleInfoAdapter(Activity act, ArrayList<ItemContacto> listEstablecimiento) {
        this.inflater = LayoutInflater.from(act);
        this.listEstablecimiento = listEstablecimiento;
    }

    @Override
    public int getCount() {
        return this.listEstablecimiento.size();
    }

    @Override
    public Object getItem(int i) {
        return listEstablecimiento.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_detalle_contacto, viewGroup, false);
            viewHolder.tvDescripcion = (TextView) convertView.findViewById(R.id.tvDescripcion);
            viewHolder.tvInformacion = (TextView) convertView.findViewById(R.id.tvInfo);
            viewHolder.imageDetalle = (ImageView) convertView.findViewById(R.id.imageDetalle);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageDetalle.setImageResource(this.listEstablecimiento.get(i).getImageFile());
        viewHolder.tvDescripcion.setText(this.listEstablecimiento.get(i).getDescripcion());
        viewHolder.tvInformacion.setText(this.listEstablecimiento.get(i).getInformacion());
        return convertView;
    }
}


