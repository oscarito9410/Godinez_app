package com.oscarito.godinez.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.oscarito.godinez.Helpers.SettingsConstans;
import com.oscarito.godinez.IO.Model.AroundResponse;
import com.oscarito.godinez.IO.Model.DetailsResponse;
import com.oscarito.godinez.IO.Model.ViewModel.Methods;
import com.oscarito.godinez.R;
import com.oscarito.godinez.Views.Detalle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oemy9 on 07/01/2017.
 */

public class LugaresAdapter extends RecyclerView.Adapter<LugaresAdapter.ViewHolderLugares> {

    private  ArrayList<AroundResponse>listEstablecimientos;
    private  Context context;
    public  LugaresAdapter(Context context, ArrayList<AroundResponse>listEstablecimientos){
        this.listEstablecimientos=listEstablecimientos;
        this.context=context;
    }

    @Override
    public ViewHolderLugares onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_lugares,null);
        ViewHolderLugares viewHolderLugares=new ViewHolderLugares(view,listEstablecimientos);
        return  viewHolderLugares;
    }

    @Override
    public void onBindViewHolder(ViewHolderLugares holder, int position) {
        AroundResponse item=this.listEstablecimientos.get(position);
        holder.tvTipo.setText(item.getCategory().getType());
        holder.tvNombreLugar.setText(item.getName());
        holder.ratingLugar.setRating((float)item.getRating());
        Picasso.with(this.context).load("http://storedot.online/images/uploads/51970/0-Cotton-parka-with-faux-fur-trimmed-hood-gr-s.jpg").resize(100,100).into(holder.imgLugarItem);
        holder.setContext(this.context);
    }

    @Override
    public int getItemCount() {
        return listEstablecimientos.size();
    }


    public static  class  ViewHolderLugares extends  RecyclerView.ViewHolder{
        TextView tvNombreLugar,tvTipo,tvCercania;
        ImageView imgLugarItem;
        RatingBar ratingLugar;
        Context ctx;

        public  void setContext(Context ctx){
            this.ctx=ctx;
        }
        public  ViewHolderLugares(final View itemView,final ArrayList<AroundResponse>listEstablecimiento){
            super(itemView);
            tvNombreLugar=(TextView)itemView.findViewById(R.id.tveNombreLugar);
            tvTipo=(TextView)itemView.findViewById(R.id.tvTipoLugar);
            imgLugarItem=(ImageView)itemView.findViewById(R.id.imageLugarItem);
            ratingLugar=(RatingBar)itemView.findViewById(R.id.ratingLugarItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(itemView.getContext(),Detalle.class);
                    AroundResponse item=listEstablecimiento.get(getLayoutPosition());
                    //String info= new Gson().toJson(item);
                    i.putExtra(SettingsConstans.ID_FONDA,item.getId());
                    itemView.getContext().startActivity(i);

                }
            });
        }

    }
}
