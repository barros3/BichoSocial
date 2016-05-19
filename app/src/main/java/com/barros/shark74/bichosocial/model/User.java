package com.barros.shark74.bichosocial.model;

import android.support.annotation.NonNull;

import com.orm.SugarRecord;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by shark74 on 22/04/16.
 */
public class User extends SugarRecord {

    String login;
    String senha;
    String nome;
    String email;
    String telefone;
    String data;

    public User(){

    }

    public User(String login, String senha){
        this.login = login;
        this.senha = senha;
    }

    public User(String login, String senha, String nome, String email, String telefone){
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
