package br.com.caelum.cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.caelum.cadastro.DAO.AlunoDao;
import br.com.caelum.cadastro.Helpers.FormularioHelper;
import br.com.caelum.cadastro.Models.Aluno;

public class FormularioActivity extends AppCompatActivity {
    public FormularioHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        // INSTACIA O HELPER DE FORMULARIO
        this.helper = new FormularioHelper(this);
        if(aluno != null){
            this.helper.preencheFormulario(aluno);
        }

    }

    // MENU FORMULARIO
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // ACOES DO MENU DO FORMULARIO
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                // CAPTURA O ALUNO DO FORM
                Aluno aluno = this.helper.pegaAlunoDoFormulario();
                // INSERT OU ATUALIZA O ALUNO
                AlunoDao alunoDao = new AlunoDao(this);
                if(aluno.getId() != null){
                    alunoDao.atualiza(aluno);
                }else{
                    alunoDao.insere(aluno);
                }
                alunoDao.close();
                Toast.makeText(this, "Gravado com sucesso", Toast.LENGTH_LONG).show();
                finish();
                return false;
            default: return super.onOptionsItemSelected(item);
        }

    }


}

