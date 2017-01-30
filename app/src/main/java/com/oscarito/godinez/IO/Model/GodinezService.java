package com.oscarito.godinez.IO.Model;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by oemy9 on 15/01/2017.
 */

public interface GodinezService {

        @GET(Constants.AROUND)
        retrofit2.Call<ArrayList<AroundResponse>>getAroundResponse(@Query("latitude") double latitude, @Query("longitude") double longitude, @Query("range") long range);

        @GET(Constants.CATEGORIES)
        retrofit2.Call<ArrayList<CategoryResponse>>getCategoryResponse();

        @GET(Constants.DETAILS)
        retrofit2.Call<DetailsResponse>getDetailsResponse(@Query("fondaId") int fondaId);

}
