package br.com.caelum.cadastro;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Queue;

import br.com.caelum.cadastro.Adapter.ListaAlunoAdapter;
import br.com.caelum.cadastro.DAO.AlunoDao;
import br.com.caelum.cadastro.Models.Aluno;
import br.com.caelum.cadastro.Permissions.Permissao;
import br.com.caelum.cadastro.Support.WebClient;
import br.com.caelum.cadastro.converter.AlunoConverter;
import br.com.caelum.cadastro.task.EnviaAlunoTask;

public class ListaAlunosActivity extends AppCompatActivity {
    private ListView listaAlunos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Permissao.fazPermissao(this);
        // SETA O LAYOUT
        setContentView(R.layout.activity_lista_alunos);

        // ARRAY DE STRINGS
        // String[] alunos = {"Anderson","Filipe","Guilherme"};
        // CAPTURA A LISTVIEW DO LAYOUT
        this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        // SETA O ARRAY DE STRING DENTRO DE UM ARRAYADAPTER
        // final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, alunos);

        // SETA O ARRAYADAPTER DENTRO NA LISTVIEW
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

        // GET ALUNO
        AlunoDao alunoDao = new AlunoDao(ListaAlunosActivity.this);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);
        // END GET ALUNO

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


        // MAPA
        MenuItem itemMapa = menu.add("Mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?q="+aluno.getEndereco()));
        itemMapa.setIntent(intentMapa);
        // END MAPA

        // SITE
        MenuItem itemSite = menu.add("Site");
        String site = aluno.getSite();
        if(site != null){
            if(!site.startsWith("http://")){
                site = "http://"+aluno.getSite();
            }
        }else{
            site =  "http://teste.com";
        }
        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        intentSite.setData(Uri.parse(site));
        itemSite.setIntent(intentSite);
        // END SITE


        // SMS
        MenuItem itemSMS = menu.add("SMS");
        Intent intentSMS = new Intent(Intent.ACTION_VIEW);
        intentSMS.setData(Uri.parse("sms:"+aluno.getTelefone()));
        itemSMS.setIntent(intentSMS);
        // END SMS


        // LIGAR
        MenuItem ligar = menu.add("Ligar");

        Intent intentLigar = new Intent(Intent.ACTION_DIAL);
        intentLigar.setData(Uri.parse("tel:"+aluno.getTelefone()));
        ligar.setIntent(intentLigar);
        // END LIGAR

        MenuItem itemCamera = menu.add("CAMERA");
        itemCamera.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AlunoDao alunoDao = new AlunoDao(ListaAlunosActivity.this);
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);

                //Intent intentMapa = new Intent();
                // intentMapa.setData(Uri.parse("sms:0,0?q="+aluno.getEndereco()));
                //startActivity(intentSMS);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_alunos,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_enviar_notas:
                EnviaAlunoTask alunoTask = (EnviaAlunoTask) new EnviaAlunoTask(this).execute();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void carregaLista(){
        AlunoDao alunoDao = new AlunoDao(this);
        List<Aluno> alunos = alunoDao.lista();
        ListaAlunoAdapter adapter = new ListaAlunoAdapter(this,alunos);
        listaAlunos.setAdapter(adapter);

    }
}
