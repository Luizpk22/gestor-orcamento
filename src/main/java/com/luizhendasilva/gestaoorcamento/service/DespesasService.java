package com.luizhendasilva.gestaoorcamento.service;

import com.luizhendasilva.gestaoorcamento.model.Despesas;
import com.luizhendasilva.gestaoorcamento.repository.DespesasDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class DespesasService {
    @Autowired
    private DespesasDB despesasDB;

    private void validarDespesa(Despesas despesa) {
        if (despesa.getNome().isEmpty()) {
            throw new RuntimeException("Campo 'nome' não pode ficar vazio.");
        }

        if (despesa.getValor() <= 0) {
            throw new RuntimeException("Valor inválido.");
        }

        if (despesa.getTipo().isEmpty()) {
            throw new RuntimeException("Campo 'tipo' não pode ficar vazio.");
        }

        if (despesa.getData().getClass() != Date.class) {
            throw new RuntimeException("Digite uma data válida.");
        }

        boolean existe = despesasDB.existeDespesa(despesa.getNome(), despesa.getTipo(),despesa.getData());
        if (existe) {
            throw new RuntimeException("Despesa já cadastrada");
        }
    }

    public void salvarDespesa(Despesas despesa) {
        validarDespesa(despesa);
        despesasDB.salvarDespesa(despesa);
    }

    public void salvarDespesas(List<Despesas> despesas) {
        List<Despesas> despesasValidadas = new ArrayList<>();
        for (Despesas despesa : despesas) {
            validarDespesa(despesa);
            despesasValidadas.add(despesa);
        }
        despesasDB.salvarDespesas(despesasValidadas);
    }

    public List<Despesas> buscarTodos() {
        return despesasDB.buscarTodos();
    }

    public Despesas buscarPorId(int id) {
        return despesasDB.buscarPorId(id);
    }

    public List<Despesas> buscarPorData(Date data) {
        return despesasDB.buscarPorData(data);
    }

    public List<Despesas> buscarPorMes(int mes) {
        return despesasDB.buscarPorMes(mes);
    }

    public void atualizarValorDespesa(int id, double valor) {
        boolean existe = despesasDB.existeDespesa(despesasDB.buscarPorId(id).getNome(),
                despesasDB.buscarPorId(id).getTipo(), despesasDB.buscarPorId(id).getData());
        if (existe) {
            despesasDB.atualizarValorDespesa(id, valor);
        } else {
            throw new RuntimeException("Despesa não encontrada no banco.");
        }
    }

    public void atualizarNomeDespesa(int id, String nome) {
        boolean existe = despesasDB.existeDespesa(despesasDB.buscarPorId(id).getNome(),
                despesasDB.buscarPorId(id).getTipo(), despesasDB.buscarPorId(id).getData());
        if (existe) {
            despesasDB.atualizarNomeDespesa(id, nome);
        } else {
            throw new RuntimeException("Despesa não encontrada no banco.");
        }
    }

    public void atualizarTipoDespesa(int id, String tipo) {
        var despesa = despesasDB.buscarPorId(id);

        if (despesa == null) {
            throw new RuntimeException("Despesa não encontrada");
        }

        despesasDB.atualizarTipoDespesa(id, tipo);
    }

    public void atualizarDataDespesa(int id, Date data) {
        boolean existe = despesasDB.existeDespesa(despesasDB.buscarPorId(id).getNome(),
                despesasDB.buscarPorId(id).getTipo(), despesasDB.buscarPorId(id).getData());
        if (existe) {
            despesasDB.atualizarDataDespesa(id, data);
        } else {
            throw new RuntimeException("Despesa não encontrada no banco.");
        }
    }

    public void excluirDespesa(int id) {
        boolean existe = despesasDB.existeDespesa(despesasDB.buscarPorId(id).getNome(),
                despesasDB.buscarPorId(id).getTipo(), despesasDB.buscarPorId(id).getData());

        if (existe) {
            despesasDB.excluirDespesa(id);
        } else {
            throw new RuntimeException("Despesa não encontrada no banco.");
        }
    }

    public void excluirListaDespesas(List<Integer> ids) {
        List<Integer> idsValidos = new ArrayList<>();
        for (int id : ids) {
            boolean existe = despesasDB.existeDespesa(despesasDB.buscarPorId(id).getNome(),
                    despesasDB.buscarPorId(id).getTipo(), despesasDB.buscarPorId(id).getData());
            if (!existe) {
                System.out.println("ID não encontrado: " + id);
//                throw new RuntimeException("ID não encontrado: " + id);
            } else {
                idsValidos.add(id);
            }
        }
        despesasDB.excluirListaDespesas(idsValidos);
    }
}
