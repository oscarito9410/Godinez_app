package com.oscarito.godinez.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.oscarito.godinez.Adapters.PlatlloAdapter;
import com.oscarito.godinez.Helpers.GlobalDataHelper;
import com.oscarito.godinez.Helpers.SettingsConstans;
import com.oscarito.godinez.IO.Model.DetailsResponse;
import com.oscarito.godinez.IO.Model.MenuResponse;
import com.oscarito.godinez.IO.Model.ViewModel.Methods;
import com.oscarito.godinez.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oemy9 on 07/01/2017.
 */

public class FragmentMenu extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);


        final ListView listViewMenu = (ListView) rootView.findViewById(R.id.listViewMenu);

        if (getActivity().getIntent().hasExtra(SettingsConstans.ID_FONDA)) {
            Integer idFonda = getActivity().getIntent().getIntExtra(SettingsConstans.ID_FONDA, 1);
            Methods methods = new Methods(getActivity());
            methods.getDetails(idFonda).enqueue(
                    new Callback<DetailsResponse>() {
                        @Override
                        public void onResponse(Call<DetailsResponse> call, Response<DetailsResponse> response) {
                            DetailsResponse responseData = response.body();
                            PlatlloAdapter adapter = new PlatlloAdapter(getActivity(), responseData.getMenu());
                            listViewMenu.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<DetailsResponse> call, Throwable t) {

                        }
                    }
            );

        }
        return rootView;
    }

}
