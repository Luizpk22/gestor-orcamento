package com.luizhendasilva.gestaoorcamento.controller;


import com.luizhendasilva.gestaoorcamento.model.Rendas;
import com.luizhendasilva.gestaoorcamento.service.RendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/rendas")
public class RendasController {
    @Autowired
    private RendasService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody Rendas renda) {
        service.salvarRenda(renda);
    }

    @PostMapping("/lista")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvarLista(@RequestBody List<Rendas> rendas) {
        service.salvarRendas(rendas);
    }

    @GetMapping
    public List<Rendas> buscarTodos() {
        return service.buscarTodos();
    }

    @GetMapping("{id}")
    public Rendas buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/data")
    public List<Rendas> buscarPorData(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data) {
        return service.buscarPorData(data);
    }

    @GetMapping("/mes")
    public List<Rendas> buscarPorMes(@RequestParam Integer mes) {
        return service.buscarPorMes(mes);
    }

    @PutMapping("/attValor")
    public void atualizarValorRenda(@RequestParam Integer id, @RequestParam Double valor) {
        service.atualizarValorRenda(id, valor);
    }

    @PutMapping("/attNome")
    public void atualizarNomeRenda(@RequestParam Integer id, @RequestParam String nome) {
        service.atualizarNomeRenda(id, nome);
    }

    @PutMapping("/attTipo")
    public void atualizarTipoRenda(@RequestParam Integer id, @RequestParam String tipo) {
        service.atualizarTipoRenda(id, tipo);
    }

    @PutMapping("/attData")
    public void atualizarDataRenda(@RequestParam Integer id, @RequestParam Date data) {
        service.atualizarDataRenda(id, data);
    }

    @DeleteMapping("{id}")
    public void excluirRenda(@PathVariable Integer id) {
        service.excluirRenda(id);
    }

    @DeleteMapping("/lista")
    public void excluirListaRendas(@RequestParam List<Integer> ids) {
        service.excluirListaRendas(ids);
    }
}
