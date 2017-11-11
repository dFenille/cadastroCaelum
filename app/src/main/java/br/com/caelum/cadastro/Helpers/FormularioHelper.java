package br.com.caelum.cadastro.Helpers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.caelum.cadastro.Models.Aluno;
import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.R;

/**
 * Created by android7087 on 28/10/17.
 */

public class FormularioHelper {
    public Aluno aluno;
    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText site;
    private RatingBar formulario_nota;
    private ImageView foto;
    private Button fotoButton;

    public Activity activity;


    // CONSTRUTOR
    public FormularioHelper(FormularioActivity activity){
        this.activity = activity;
        nome = (EditText) activity.findViewById(R.id.nome);
        telefone = (EditText) activity.findViewById(R.id.telefone);
        endereco = (EditText) activity.findViewById(R.id.endereco);
        site = (EditText) activity.findViewById(R.id.site);
        formulario_nota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        foto = (ImageView) activity.findViewById(R.id.formulario_foto);
        fotoButton = (Button) activity.findViewById(R.id.formulario_foto_button);
        this.aluno = new Aluno();
    }

    public Aluno pegaAlunoDoFormulario(){

        aluno.setNome((!nome.getText().toString().isEmpty())?nome.getText().toString():"");
        aluno.setTelefone((!telefone.getText().toString().isEmpty())?telefone.getText().toString():"");
        aluno.setEndereco((!endereco.getText().toString().isEmpty())?endereco.getText().toString():"");
        aluno.setSite((!site.getText().toString().isEmpty())?site.getText().toString():"");
        aluno.setNota(Double.valueOf(formulario_nota.getRating()));
        aluno.setCaminhoFoto((String) foto.getTag());

        return aluno;
    }

    public void preencheFormulario(Aluno aluno){
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());
        formulario_nota.setRating(aluno.getNota().floatValue());
        Bitmap bm;
        if(aluno.getCaminhoFoto() != null)
            bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        else
            bm = BitmapFactory.decodeResource(this.activity.getResources(),R.drawable.ic_no_image);
        bm.createScaledBitmap(bm,100,100,true);
        foto.setImageBitmap(bm);

        this.aluno = aluno;
    }

    public Button getButton(){
        return fotoButton;
    }

    public void carregaImagem(String localArquivoFoto){
        Bitmap imageFoto = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imageFotoReduzida = Bitmap.createScaledBitmap(imageFoto, imageFoto.getWidth(), 300,true);
        foto.setImageBitmap(imageFotoReduzida);
        foto.setTag(localArquivoFoto);
        foto.setScaleType(ImageView.ScaleType.FIT_XY);
        Log.e("teste",localArquivoFoto.toString());

    }
}
