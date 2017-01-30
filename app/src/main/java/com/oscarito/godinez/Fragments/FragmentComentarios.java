package com.oscarito.godinez.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;

import com.oscarito.godinez.Adapters.ComentarioAdapter;
import com.oscarito.godinez.IO.Model.MenuResponse;
import com.oscarito.godinez.R;

import java.util.ArrayList;

/**
 * Created by oemy9 on 07/01/2017.
 */

public class FragmentComentarios extends Fragment implements  View.OnClickListener  {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_comentarios, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ImageButton btnEnviar=(ImageButton)rootView.findViewById(R.id.btnEnviar);
        ListView listViewComentarios=(ListView)rootView.findViewById(R.id.listviewComentarios);
        ArrayList<MenuResponse>menuResponses=new ArrayList<MenuResponse>();
        MenuResponse menu=new MenuResponse();
        menu.setDish("ok");
        menuResponses.add(menu);
        ComentarioAdapter adapter=new ComentarioAdapter(getActivity(),menuResponses);
        listViewComentarios.setAdapter(adapter);
        btnEnviar.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public  void abrirDialogo(){
        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        final  View dialogView=inflater.inflate(R.layout.dialog_review,null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Calificar fonda");
        dialogBuilder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogBuilder.setNegativeButton(R.string.cancelar,null);
        AlertDialog dialog=dialogBuilder.create();
        dialog.show();;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEnviar:
                this.abrirDialogo();
                break;
        }
    }
}
