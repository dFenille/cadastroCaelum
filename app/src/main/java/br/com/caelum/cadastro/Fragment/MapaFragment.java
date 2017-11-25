package br.com.caelum.cadastro.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.zip.Inflater;

import br.com.caelum.cadastro.DAO.AlunoDao;
import br.com.caelum.cadastro.Helpers.Localizador;
import br.com.caelum.cadastro.Models.Aluno;
import br.com.caelum.cadastro.R;

/**
 * Created by android7087 on 25/11/17.
 */

public class MapaFragment extends SupportMapFragment {
    public List<Aluno> listaAluno;
    @Override
    public void onResume() {
        super.onResume();
        Localizador localizador = new Localizador(getActivity());

        GoogleMap googleMap = (GoogleMap) getMap();
       centralizar(localizador.getCoordenada("Rua Vergueiro 3185 Vila Mariana"),googleMap);

        AlunoDao alunoDao = new AlunoDao(getActivity());
        listaAluno = alunoDao.lista();
        for(Aluno aluno : listaAluno){
            MarkerOptions mark = new MarkerOptions();
            mark.title(aluno.getNome());
            mark.position(localizador.getCoordenada(aluno.getEndereco()));
            googleMap.addMarker(mark);
        }

    }
    public void centralizar(LatLng coord,GoogleMap map){
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coord,11f));
    }
}
