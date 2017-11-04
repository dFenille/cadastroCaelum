package br.com.caelum.cadastro.Helpers;

import android.widget.EditText;
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


    // CONSTRUTOR
    public FormularioHelper(FormularioActivity activity){
        nome = (EditText) activity.findViewById(R.id.nome);
        telefone = (EditText) activity.findViewById(R.id.telefone);
        endereco = (EditText) activity.findViewById(R.id.endereco);
        site = (EditText) activity.findViewById(R.id.site);
        formulario_nota = (RatingBar) activity.findViewById(R.id.formulario_nota);

        this.aluno = new Aluno();
    }

    public Aluno pegaAlunoDoFormulario(){

        aluno.setNome((!nome.getText().toString().isEmpty())?nome.getText().toString():"");
        aluno.setTelefone((!telefone.getText().toString().isEmpty())?telefone.getText().toString():"");
        aluno.setEndereco((!endereco.getText().toString().isEmpty())?endereco.getText().toString():"");
        aluno.setSite((!site.getText().toString().isEmpty())?site.getText().toString():"");
        aluno.setNota(Double.valueOf(formulario_nota.getRating()));
        return aluno;
    }

    public void preencheFormulario(Aluno aluno){
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());
        formulario_nota.setRating(aluno.getNota().floatValue());
        this.aluno = aluno;
    }
}
