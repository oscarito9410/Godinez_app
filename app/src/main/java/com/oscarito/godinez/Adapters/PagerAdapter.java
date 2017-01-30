package com.oscarito.godinez.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.oscarito.godinez.Fragments.FragmentComentarios;
import com.oscarito.godinez.Fragments.FragmentDetalle;
import com.oscarito.godinez.Fragments.FragmentMenu;

/**
 * Created by oemy9 on 07/01/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
   private  int tabs;
    public  PagerAdapter(FragmentManager fm,int tabs){
        super(fm);this.tabs=tabs;
    }

    @Override
    public Fragment getItem(int position) {
          Fragment fragment=null;
            switch (position){
                case 0:
                     fragment=new FragmentDetalle();
                break;
                case 1:
                    fragment=new FragmentMenu();
                    break;
                case 2:
                    fragment=new FragmentComentarios();
                    break;

            }
        return  fragment;
    }

    @Override
    public int getCount() {
        return tabs;
    }
}
