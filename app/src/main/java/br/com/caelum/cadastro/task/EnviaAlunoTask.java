package br.com.caelum.cadastro.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONStringer;

import java.util.List;

import br.com.caelum.cadastro.DAO.AlunoDao;
import br.com.caelum.cadastro.Models.Aluno;
import br.com.caelum.cadastro.Support.WebClient;
import br.com.caelum.cadastro.converter.AlunoConverter;

/**
 * Created by android7087 on 18/11/17.
 */

public class EnviaAlunoTask extends AsyncTask<Object,Object,String> {

    public Activity activity;

    public String resposta;

    public ProgressDialog progressDialog;

    public EnviaAlunoTask(Activity activity){
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(this.activity,"Aguarde...","Envio de dados para a web");

        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Object... objects) {

        AlunoDao alunoDao = new AlunoDao(this.activity);
        List<Aluno> listaAluno = alunoDao.lista();
        alunoDao.close();
        String json = new AlunoConverter().toString(listaAluno);
        WebClient client = new WebClient();
        resposta = client.post(json);

        return resposta;
    }

    @Override
    protected void onPostExecute(String result) {
        if(progressDialog != null){
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }

         Toast.makeText(this.activity, result, Toast.LENGTH_LONG).show();

    }
}
