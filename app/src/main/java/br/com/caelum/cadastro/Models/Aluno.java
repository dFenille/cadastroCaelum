package br.com.caelum.cadastro.Models;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by android7087 on 28/10/17.
 */

public class Aluno implements Serializable{


    public Long Id;
    public String Nome;
    public String Telefone;
    public String Endereco;
    public String Site;
    public Double Nota;
    private String caminhoFoto;

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public String getSite() {
        return Site;
    }

    public void setSite(String site) {
        Site = site;
    }

    public Double getNota() {
        return Nota;
    }

    public void setNota(Double nota) {
        Nota = nota;
    }

    public String toString() {
        return this.getId() + " - " + this.getNome();
    }
}
