package com.luizhendasilva.gestaoorcamento.model;

import jakarta.persistence.*;

import java.sql.Date;

@MappedSuperclass
public abstract class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double valor;
    private String tipo;
    private Date data;
    private int idBanco;

    public Movimentacao(String nome, double valor, String tipo, Date data) {
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }

    public Movimentacao() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
