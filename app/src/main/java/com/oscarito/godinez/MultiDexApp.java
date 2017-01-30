package com.oscarito.godinez;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.oscarito.godinez.IO.Model.DetailsResponse;

/**
 * Created by oemy9 on 08/01/2017.
 */

public class MultiDexApp extends MultiDexApplication {
    private DetailsResponse detalleRespuesta;
    public  void setDetailResponse(DetailsResponse detalleRespuesta){
        this.detalleRespuesta=detalleRespuesta;
    }
    public DetailsResponse getDetailResponse(){
        return this.detalleRespuesta;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
