package com.barros.shark74.bichosocial.model;

import com.orm.SugarRecord;

/**
 * Created by shark74 on 03/05/16.
 */
public class FeedNoticia extends SugarRecord {

    private String descricao;
    private String imagem;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
