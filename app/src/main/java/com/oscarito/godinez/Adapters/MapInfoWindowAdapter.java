package com.oscarito.godinez.Adapters;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;
import com.oscarito.godinez.IO.Model.NearResponse;
import com.oscarito.godinez.R;

/**
 * Created by oemy9 on 05/01/2017.
 */

public class MapInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final  View  contentView;

    public  MapInfoWindowAdapter(View contentView){
        this.contentView=contentView;
    }

    @Override
    public View getInfoWindow(Marker marker)
    {

        return null;

    }

    @Override
    public View getInfoContents(Marker marker){
        NearResponse response=new Gson().fromJson(marker.getSnippet(),NearResponse.class);
        TextView tvTituloInfo=(TextView)contentView.findViewById(R.id.tvTituloInfo);
        TextView tvSubInfo=(TextView)contentView.findViewById(R.id.tvSubInfo);
        RatingBar ratingInfo=(RatingBar)contentView.findViewById(R.id.ratingInfo);
        ratingInfo.setRating((float)response.getRating());
        tvTituloInfo.setText(response.getName());
        tvSubInfo.setText(response.getAddress());
        return contentView;
    }
}
