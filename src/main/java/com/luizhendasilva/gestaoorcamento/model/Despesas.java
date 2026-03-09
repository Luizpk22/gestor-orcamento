package com.luizhendasilva.gestaoorcamento.model;

import jakarta.persistence.Entity;

import java.sql.Date;

@Entity
public class Despesas extends Movimentacao {
    private String comentario;

    public Despesas(String nome, double valor, String tipo, Date data) {
        super(nome, valor, tipo, data);
    }

    public Despesas() {
        super();
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return String.format("""
                ID no Banco de dados: %d
                Nome: %s
                Valor: R$ %.2f
                Categoria: %s
                Data: %s
                Comentário: %s""", getIdBanco(), getNome(), getValor(), getTipo(), getData(), getComentario());
    }
}
