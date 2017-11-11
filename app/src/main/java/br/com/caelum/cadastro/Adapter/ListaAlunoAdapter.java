package br.com.caelum.cadastro.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.caelum.cadastro.DAO.AlunoDao;
import br.com.caelum.cadastro.Models.Aluno;
import br.com.caelum.cadastro.R;

/**
 * Created by android7087 on 11/11/17.
 */

public class ListaAlunoAdapter extends BaseAdapter{
    private final List<Aluno> alunos;
    private final Activity activity;

    public ListaAlunoAdapter(Activity activity,List<Aluno> alunos){
        this.alunos = alunos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int i) {
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alunos.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        // PERSONALIZA A LISTA
        View view = activity.getLayoutInflater().inflate(R.layout.item,viewGroup,false);

        // SETA CORES DE ACORDO A POSICAO
        if(i % 2 == 0){
            view.setBackgroundColor(activity.getResources().getColor(R.color.linha_par,activity.getTheme()));
        }else{
            view.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar,activity.getTheme()));
        }

        // get aluno PELA POSICAO
        Aluno aluno = alunos.get(i);
        // SETA O NOME
        TextView nome = (TextView) view.findViewById(R.id.item_nome);
        nome.setText(aluno.getNome());

        // SETA A IMAGEM
        Bitmap bm;
        if(aluno.getCaminhoFoto() != null)
            bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        else
            bm = BitmapFactory.decodeResource(activity.getResources(),R.drawable.ic_no_image);
        bm.createScaledBitmap(bm,100,100,true);

        ImageView imgView = (ImageView) view.findViewById(R.id.item_foto);
        imgView.setImageBitmap(bm);

        return view;
    }
}
