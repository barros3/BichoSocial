package com.barros.shark74.bichosocial.model;

import com.orm.SugarRecord;

/**
 * Created by shark74 on 22/04/16.
 */
public class User2 extends SugarRecord {

    String login;
    String senha;
    String nome;
    String email;
    String telefone;
    String data;

    public User2(){

    }

    public User2(String login, String senha){
        this.login = login;
        this.senha = senha;
    }

    public User2(String login, String senha, String nome, String email, String telefone){
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
