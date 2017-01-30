package com.oscarito.godinez.IO.Model.ViewModel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.oscarito.godinez.IO.Model.AroundResponse;
import com.oscarito.godinez.IO.Model.CategoryResponse;
import com.oscarito.godinez.IO.Model.Constants;
import com.oscarito.godinez.IO.Model.DetailsResponse;
import com.oscarito.godinez.IO.Model.GodinezService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by oemy9 on 15/01/2017.
 */

public class Methods {
    private  Context ctx;
    private  GodinezService service;
    public  Methods(Context ctx){
        this.ctx=ctx;
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Constants.URL_BASE).build();
        this.service = retrofit.create(GodinezService.class);
    }
    public Call<ArrayList<AroundResponse>> getAround(double latitude, double longitude, long range)
    {
        Call<ArrayList<AroundResponse>>responseCall=this.service.getAroundResponse(latitude,longitude,range);
        return responseCall;
    }

    public  Call<ArrayList<CategoryResponse>>getCategories(){
        Call<ArrayList<CategoryResponse>> responseCall=this.service.getCategoryResponse();
        return  responseCall;
    }
    public  Call<DetailsResponse>getDetails(int fondaId){
        Call<DetailsResponse>responseDetails=this.service.getDetailsResponse(fondaId);
        return responseDetails;
    }

}
