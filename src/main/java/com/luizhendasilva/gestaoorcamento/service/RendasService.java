package com.luizhendasilva.gestaoorcamento.service;

import com.luizhendasilva.gestaoorcamento.model.Rendas;
import com.luizhendasilva.gestaoorcamento.repository.RendasDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class RendasService {
    @Autowired
    private RendasDB rendasDB;

    private void validarRenda(Rendas renda) {
        if (renda.getNome().isEmpty()) {
            throw new RuntimeException("Campo 'nome' não pode ficar vazio.");
        }

        if (renda.getValor() <= 0) {
            throw new RuntimeException("Valor inválido.");
        }

        if (renda.getTipo().isEmpty()) {
            throw new RuntimeException("Campo 'tipo' não pode ficar vazio.");
        }

        if (renda.getData().getClass() != Date.class) {
            throw new RuntimeException("Digite uma data válida.");
        }

        boolean existe = rendasDB.existeRenda(renda.getNome(), renda.getTipo(),renda.getData());
        if (existe) {
            throw new RuntimeException("Renda já cadastrada");
        }
    }

    public void salvarRenda(Rendas renda) {
        validarRenda(renda);
        rendasDB.salvarRenda(renda);
    }

    public void salvarRendas(List<Rendas> rendas) {
        List<Rendas> rendasValidadas = new ArrayList<>();
        for (Rendas renda : rendas) {
            validarRenda(renda);
            rendasValidadas.add(renda);
        }
        rendasDB.salvarRendas(rendasValidadas);
    }

    public List<Rendas> buscarTodos() {
        return rendasDB.buscarTodos();
    }

    public Rendas buscarPorId(int id) {
        return rendasDB.buscarPorId(id);
    }

    public List<Rendas> buscarPorData(Date data) {
        return rendasDB.buscarPorData(data);
    }

    public List<Rendas> buscarPorMes(int mes) {
        return rendasDB.buscarPorMes(mes);
    }

    public void atualizarValorRenda(int id, double valor) {
        boolean existe = rendasDB.existeRenda(rendasDB.buscarPorId(id).getNome(),
                rendasDB.buscarPorId(id).getTipo(), rendasDB.buscarPorId(id).getData());
        if (existe) {
            rendasDB.atualizarValorRenda(id, valor);
        } else {
            throw new RuntimeException("Renda não encontrada no banco.");
        }
    }

    public void atualizarNomeRenda(int id, String nome) {
        boolean existe = rendasDB.existeRenda(rendasDB.buscarPorId(id).getNome(),
                rendasDB.buscarPorId(id).getTipo(), rendasDB.buscarPorId(id).getData());
        if (existe) {
            rendasDB.atualizarNomeRenda(id, nome);
        } else {
            throw new RuntimeException("Renda não encontrada no banco.");
        }
    }

    public void atualizarTipoRenda(int id, String tipo) {
        boolean existe = rendasDB.existeRenda(rendasDB.buscarPorId(id).getNome(),
                rendasDB.buscarPorId(id).getTipo(), rendasDB.buscarPorId(id).getData());
        if (existe) {
            rendasDB.atualizarTipoRenda(id, tipo);
        } else {
            throw new RuntimeException("Renda não encontrada no banco.");
        }
    }

    public void atualizarDataRenda(int id, Date data) {
        boolean existe = rendasDB.existeRenda(rendasDB.buscarPorId(id).getNome(),
                rendasDB.buscarPorId(id).getTipo(), rendasDB.buscarPorId(id).getData());
        if (existe) {
            rendasDB.atualizarDataRenda(id, data);
        } else {
            throw new RuntimeException("Renda não encontrada no banco.");
        }
    }

    public void excluirRenda(int id) {
        boolean existe = rendasDB.existeRenda(rendasDB.buscarPorId(id).getNome(),
                rendasDB.buscarPorId(id).getTipo(), rendasDB.buscarPorId(id).getData());

        if (existe) {
            rendasDB.excluirRenda(id);
        } else {
            throw new RuntimeException("Renda não encontrada no banco.");
        }
    }

    public void excluirListaRendas(List<Integer> ids) {
        List<Integer> idsValidos = new ArrayList<>();
        for (int id : ids) {
            boolean existe = rendasDB.existeRenda(rendasDB.buscarPorId(id).getNome(),
                    rendasDB.buscarPorId(id).getTipo(), rendasDB.buscarPorId(id).getData());
            if (!existe) {
                System.out.println("ID não encontrado: " + id);
            } else {
                idsValidos.add(id);
            }
        }
        rendasDB.excluirListaRendas(idsValidos);
    }
}
