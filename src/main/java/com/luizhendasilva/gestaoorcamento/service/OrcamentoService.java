package com.luizhendasilva.gestaoorcamento.service;

import com.luizhendasilva.gestaoorcamento.model.ResumoMes;
import com.luizhendasilva.gestaoorcamento.model.SaldoMensal;
import com.luizhendasilva.gestaoorcamento.repository.DespesasDB;
import com.luizhendasilva.gestaoorcamento.repository.RendasDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoService {
    @Autowired
    private DespesasDB despesasDB;
    @Autowired
    private RendasDB rendasDB;

    public double calcularSaldoTotal() {
        double totalDespesas = despesasDB.somarDespesas();
        double totalRendas = rendasDB.somarRendas();

        return totalRendas - totalDespesas;
    }

    public ResumoMes resumoMes(int mes) {
        double despesaPorMes = despesasDB.despesaPorMes(mes);
        double rendaPorMes = rendasDB.rendaPorMes(mes);

        ResumoMes resumoMes = new ResumoMes();
        resumoMes.setMes(mes);
        resumoMes.setTotalDespesas(despesaPorMes);
        resumoMes.setTotalRendas(rendaPorMes);
        resumoMes.setSaldo(rendaPorMes - despesaPorMes);

        return resumoMes;
    }

    public SaldoMensal saldomensal(int mes) {
        double despesaMensal = despesasDB.despesaPorMes(mes);
        double rendaMensal = rendasDB.rendaPorMes(mes);
        SaldoMensal saldoMensal = new SaldoMensal();
        saldoMensal.setMes(mes);
        saldoMensal.setSaldo(rendaMensal - despesaMensal);

        return saldoMensal;
    }
}
