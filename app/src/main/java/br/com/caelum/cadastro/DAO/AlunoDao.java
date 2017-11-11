package br.com.caelum.cadastro.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastro.Models.Aluno;

/**
 * Created by android7087 on 04/11/17.
 */

public class AlunoDao extends SQLiteOpenHelper {
    public static final int VERSAO = 2;
    public AlunoDao(Context context){
        super(context,"CadastroCaelum", null, VERSAO);

    }

    public void onCreate (SQLiteDatabase db){
        String sql = "CREATE TABLE ALUNOS (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, telefone TEXT, endereco TEXT, site TEXT, nota REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAnterior, int versaoNova) {
        //String sql = "DROP TABLE IF EXISTS ALUNOS";
        String sql = "ALTER TABLE ALUNOS ADD COLUMN caminhoFoto TEXT";
        db.execSQL(sql);
    }

    public void insere(Aluno aluno){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nome",aluno.getNome());
        dados.put("telefone",aluno.getTelefone());
        dados.put("endereco",aluno.getEndereco());
        dados.put("nota",aluno.getNota());
        dados.put("caminhoFoto", aluno.getCaminhoFoto());
        db.insert("alunos",null,dados);
    }

    public List<Aluno> lista(){
            String sql = "SELECT * FROM ALUNOS";
        List<Aluno> alunos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        while (c.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
            aluno.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));
            alunos.add(aluno);
        }
        c.close();
        return alunos;
    }

    public void atualiza(Aluno aluno){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nome",aluno.getNome());
        dados.put("endereco",aluno.getEndereco());
        dados.put("site",aluno.getSite());
        dados.put("telefone",aluno.getTelefone());
        dados.put("nota",aluno.getNota());
        dados.put("caminhoFoto", aluno.getCaminhoFoto());

        db.update("alunos",dados,"id = ?",new String[]{aluno.getId().toString()});
    }

    public void excluir(Aluno aluno){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("alunos","id=?",new String[]{aluno.getId().toString()});
    }

    public boolean isAluno(String telefone){
        String[] parametros = {telefone};
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT telefone FROM ALUNOS WHERE telefone = ?", parametros);
        int total = rawQuery.getCount();
        rawQuery.close();
        return total > 0 ;
    }
}
