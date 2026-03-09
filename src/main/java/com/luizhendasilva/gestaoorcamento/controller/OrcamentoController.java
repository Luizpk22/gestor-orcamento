package com.luizhendasilva.gestaoorcamento.controller;

import com.luizhendasilva.gestaoorcamento.model.ResumoMes;
import com.luizhendasilva.gestaoorcamento.model.SaldoMensal;
import com.luizhendasilva.gestaoorcamento.service.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orcamento")
public class OrcamentoController {
    @Autowired
    private OrcamentoService orcamentoService;

    @GetMapping("/saldoTotal")
    public double calcularSaldoTotal() {
        return orcamentoService.calcularSaldoTotal();
    }

    @GetMapping("/saldoMes")
    public ResumoMes resumoMes(@RequestParam Integer mes) {
        return orcamentoService.resumoMes(mes);
    }

    @GetMapping("/saldoMensal")
    public SaldoMensal saldoMensal(@RequestParam Integer mes) {
        return orcamentoService.saldomensal(mes);
    }
}
