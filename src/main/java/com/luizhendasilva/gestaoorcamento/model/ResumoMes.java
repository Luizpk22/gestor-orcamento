package com.luizhendasilva.gestaoorcamento.model;

public class ResumoMes {
    private int mes;
    private double totalDespesas;
    private double totalRendas;
    private double saldo;

    public ResumoMes() {
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public double getTotalDespesas() {
        return totalDespesas;
    }

    public void setTotalDespesas(double totalDespesas) {
        this.totalDespesas = totalDespesas;
    }

    public double getTotalRendas() {
        return totalRendas;
    }

    public void setTotalRendas(double totalRendas) {
        this.totalRendas = totalRendas;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
