package br.com.caelum.cadastro.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.cadastro.Models.Prova;
import br.com.caelum.cadastro.R;

/**
 * Created by android7087 on 18/11/17.
 */

public class ListaProvasFragment extends Fragment {
    private ListView listViewProvas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutProvas = inflater.inflate(R.layout.fragment_lista_provas,container,false);

        Prova prova1 = new Prova("20/06/2015","Matematica");
        prova1.setTopicos(Arrays.asList("Algebra Linear","Calculo","Estatistica"));

        Prova prova2 = new Prova("20/07/2015","Portugues");
        prova1.setTopicos(Arrays.asList("Completo nomimal","Oracoes subordinadas","Analise Sintatica"));

        List<Prova> provas = Arrays.asList(prova1,prova2);
        this.listViewProvas.setAdapter(new ArrayAdapter<Prova>(getActivity(),android.R.layout.simple_list_item_1,provas));


        this.listViewProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Prova selecionada = (Prova) adapterView.getItemAtPosition(i);
                Toast.makeText(getActivity(),"Prova Selecionada:"+selecionada, Toast.LENGTH_SHORT).show();
            }
        });
        return layoutProvas;
    }
}
