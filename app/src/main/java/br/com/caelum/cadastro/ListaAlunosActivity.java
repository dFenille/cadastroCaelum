package br.com.caelum.cadastro;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.DAO.AlunoDao;
import br.com.caelum.cadastro.Models.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {
    private ListView listaAlunos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // SETA O LAYOUT
        setContentView(R.layout.activity_lista_alunos);

        // ARRAY DE STRINGS
       // String[] alunos = {"Anderson","Filipe","Guilherme"};
        // CAPTURA A LISTVIEW DO LAYOUT
        this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        // SETA O ARRAY DE STRING DENTRO DE UM ARRAYADAPTER
       // final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, alunos);

       // SETA O ARRAYADAPTER DENTRO NA LISTVIEW
        //listaAlunos.setAdapter(adapter);
        carregaLista();

        // CLICK - EDIT USER
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Aluno alunoSelecionado = (Aluno) listaAlunos.getItemAtPosition(i);
                Intent intent = new Intent(ListaAlunosActivity.this,FormularioActivity.class);
                intent.putExtra("aluno", alunoSelecionado);
                startActivity(intent);
            }
        });

        // REGISTRA NO O MENU CONTEXT
        registerForContextMenu(listaAlunos);

        // LONG CLICK
       /* listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListaAlunosActivity.this, "Ola clique longo no item - "+listaAlunos.getItemAtPosition(i), Toast.LENGTH_LONG).show();

                return false;
            }
        });*/


        // REDIRECT PARA A ACTIVY DE CADASTRO
        FloatingActionButton btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
       carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem item = menu.add("Deletar");
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                    AlunoDao alunoDao = new AlunoDao(ListaAlunosActivity.this);
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);
                alunoDao.excluir(aluno);
                carregaLista();

                return false;
            }
        });
    }

    public void carregaLista(){
        AlunoDao alunoDao = new AlunoDao(this);
        List<Aluno> alunos = alunoDao.lista();
        ArrayAdapter<Aluno> adapter  = new ArrayAdapter<Aluno>(this,android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);

    }
}
