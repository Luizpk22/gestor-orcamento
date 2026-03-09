package com.luizhendasilva.gestaoorcamento.controller;

import com.luizhendasilva.gestaoorcamento.model.Despesas;
import com.luizhendasilva.gestaoorcamento.service.DespesasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/despesas")
public class DespesasController {
    @Autowired
    private DespesasService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody Despesas despesa) {
        service.salvarDespesa(despesa);
    }

    @PostMapping("/lista")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvarLista(@RequestBody List<Despesas> despesas) {
        service.salvarDespesas(despesas);
    }

    @GetMapping
    public List<Despesas> buscarTodos() {
        return service.buscarTodos();
    }

    @GetMapping("{id}")
    public Despesas buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/data")
    public List<Despesas> buscarPorData(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data) {
        return service.buscarPorData(data);
    }

    @GetMapping("/mes")
    public List<Despesas> buscarPorMes(@RequestParam Integer mes) {
        return service.buscarPorMes(mes);
    }

    @PutMapping("/attValor")
    public void atualizarValorDespesa(@RequestParam Integer id, @RequestParam Double valor) {
        service.atualizarValorDespesa(id, valor);
    }

    @PutMapping("/attNome")
    public void atualizarNomeDespesa(@RequestParam Integer id, @RequestParam String nome) {
        service.atualizarNomeDespesa(id, nome);
    }

    @PutMapping("/attTipo")
    public void atualizarTipoDespesa(@RequestParam Integer id, @RequestParam String tipo) {
        service.atualizarTipoDespesa(id, tipo);
    }

    @PutMapping("/attData")
    public void atualizarDataDespesa(@RequestParam Integer id, @RequestParam Date data) {
        service.atualizarDataDespesa(id, data);
    }

    @DeleteMapping
    public void excluirDespesa(@RequestParam Integer id) {
        service.excluirDespesa(id);
    }

    @DeleteMapping("/lista")
    public void excluirListaDespesas(@RequestParam List<Integer> ids) {
        service.excluirListaDespesas(ids);
    }
}
