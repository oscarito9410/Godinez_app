package com.oscarito.godinez.Helpers;

import android.app.Activity;

import com.oscarito.godinez.IO.Model.DetailsResponse;
import com.oscarito.godinez.IO.Model.MenuResponse;
import com.oscarito.godinez.MultiDexApp;

import java.util.ArrayList;

/**
 * Created by oemy9 on 28/01/2017.
 */

public class GlobalDataHelper {

        private static GlobalDataHelper  mInstance= null;
        public  ArrayList<MenuResponse>responseMenu;
        protected GlobalDataHelper(){
            responseMenu=new ArrayList<MenuResponse>();
        }
        public static synchronized GlobalDataHelper getInstance(){
            if(null == mInstance){
                mInstance = new GlobalDataHelper();
            }
            return mInstance;
        }
    }


