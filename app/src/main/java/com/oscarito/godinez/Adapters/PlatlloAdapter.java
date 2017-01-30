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

public class PlatlloAdapter extends BaseAdapter {
    private static class ViewHolder {
        TextView tvPlatilloNombre,tvPlatilloPrecio;
    }
    private LayoutInflater inflater;
    private ArrayList<MenuResponse> listMenuResponse;
    public PlatlloAdapter(Activity act, ArrayList<MenuResponse> listMenuResponse) {
        this.inflater = LayoutInflater.from(act);
        this.listMenuResponse = listMenuResponse;
    }
    @Override
    public int getCount() {
        return listMenuResponse.size();
    }
    @Override
    public MenuResponse getItem(int i) {
        return listMenuResponse.get(i);
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
            view=inflater.inflate(R.layout.item_platillo,viewGroup,false);
            viewHolder.tvPlatilloNombre=(TextView)view.findViewById(R.id.tvNombrePlatiloItem);
            viewHolder.tvPlatilloPrecio=(TextView)view.findViewById(R.id.tvPrecioPatilloItem);
            view.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.tvPlatilloNombre.setText(this.getItem(i).getDish());
        viewHolder.tvPlatilloPrecio.setText(String.format("$ %1$s",String.valueOf(this.getItem(i).getPrice())));
        return  view;
    }

}
