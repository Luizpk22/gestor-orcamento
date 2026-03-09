package com.luizhendasilva.gestaoorcamento.repository;

import com.luizhendasilva.gestaoorcamento.model.Despesas;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DespesasDB {
    public boolean existeDespesa(String nome, String tipo, Date data) {
        var sql = "SELECT nome, data FROM despesas WHERE nome = ? AND tipo = ? AND data = ?";

        try (var conexao = DB.connect();
            var pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, tipo);
            pstmt.setDate(3, data);
            var rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void salvarDespesa(Despesas despesa) {
        var sql = "INSERT INTO despesas(nome, valor, tipo, data)"
                + "VALUES(?,?,?,?)";

        try (var conexao = DB.connect();
            var pstmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstmt.setString(1, despesa.getNome());
                pstmt.setDouble(2, despesa.getValor());
                pstmt.setString(3, despesa.getTipo());
                pstmt.setDate(4, despesa.getData());

                int insertedRow = pstmt.executeUpdate();
                if (insertedRow > 0) {
                    var rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        System.out.println("Despesa salva no banco com sucesso.");
                    }
                }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void salvarDespesas(List<Despesas> despesas) {
        var sql = "INSERT INTO despesas(nome, valor, tipo, data)"
                + "VALUES(?,?,?,?)";

        try (var conexao = DB.connect();
            var pstmt = conexao.prepareStatement(sql)) {
            for (var despesa : despesas) {
                pstmt.setString(1, despesa.getNome());
                pstmt.setDouble(2, despesa.getValor());
                pstmt.setString(3, despesa.getTipo());
                pstmt.setDate(4, despesa.getData());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Despesas> buscarTodos() {
        var despesas = new ArrayList<Despesas>();
        var sql = "SELECT id, nome, valor, tipo, data FROM despesas ORDER BY nome";

        try (var conexao = DB.connect();
            var stmt = conexao.createStatement()) {

            var rs = stmt.executeQuery(sql);

            while (rs.next()) {
                var despesa = new Despesas(rs.getString("nome"),
                        rs.getDouble("valor"),
                        rs.getString("tipo"),
                        rs.getDate("data"));
                despesa.setIdBanco(rs.getInt("id"));
                despesas.add(despesa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return despesas;
    }

    public Despesas buscarPorId(int id) {
        var sql = "SELECT id, nome, valor, tipo, data FROM despesas WHERE id = ?";

        Despesas despesa = new Despesas();
        try (var conexao = DB.connect();
             var pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            var rs = pstmt.executeQuery();

            if (rs.next()) {
                despesa = new Despesas(rs.getString("nome"),
                        rs.getDouble("valor"),
                        rs.getString("tipo"),
                        rs.getDate("data"));
                return despesa;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Despesas> buscarPorData(Date data) {
        var sql = "SELECT id, nome, valor, tipo, data FROM despesas WHERE data = ?";
        var despesas = new ArrayList<Despesas>();

        try (var conexao = DB.connect();
            var pstmt = conexao.prepareStatement(sql)) {

            pstmt.setDate(1, data);
            var rs = pstmt.executeQuery();

            while (rs.next()) {
                var despesa = new Despesas(rs.getString("nome"),
                        rs.getDouble("valor"),
                        rs.getString("tipo"),
                        rs.getDate("data"));
                despesa.setIdBanco(rs.getInt("id"));
                despesas.add(despesa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return despesas;
    }

    public List<Despesas> buscarPorMes(int mes) {
        var sql = "SELECT nome, valor, tipo, data FROM despesas WHERE EXTRACT(MONTH FROM data) = ?";
        var despesas = new ArrayList<Despesas>();

        try (var conexao = DB.connect();
             var pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, mes);
            var rs = pstmt.executeQuery();

            while (rs.next()) {
                var despesa = new Despesas(rs.getString("nome"),
                        rs.getDouble("valor"),
                        rs.getString("tipo"),
                        rs.getDate("data"));
                despesas.add(despesa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return despesas;
    }

    public void atualizarValorDespesa(int id, double valor) {
        var sql = "UPDATE despesas "
                + "SET valor = ? "
                + "WHERE id = ?";

        try (var conexao = DB.connect();
            var pstmt = conexao.prepareStatement(sql)) {
            pstmt.setDouble(1, valor);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarNomeDespesa(int id, String nome) {
        var sql = "UPDATE despesas "
                + "SET nome = ? "
                + "WHERE id = ?";

        try (var conexao = DB.connect();
             var pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarTipoDespesa(int id, String tipo) {
        var sql = "UPDATE despesas "
                + "SET tipo = ? "
                + "WHERE id = ?";

        try (var conexao = DB.connect();
             var pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, tipo);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarDataDespesa(int id, Date data) {
        var sql = "UPDATE despesas "
                + "SET data = ? "
                + "WHERE id = ?";

        try (var conexao = DB.connect();
             var pstmt = conexao.prepareStatement(sql)) {
            pstmt.setDate(1, data);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirDespesa(int id) {
        var sql = "DELETE FROM despesas WHERE id = ?";

        try (var conexao = DB.connect();
            var pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirListaDespesas(List<Integer> ids) {
        var sql = "DELETE FROM despesas WHERE id = ?";

        try (var conexao = DB.connect();
            var pstmt = conexao.prepareStatement(sql)) {
            for (var id : ids) {
                pstmt.setInt(1, id);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double somarDespesas() {
        var sql = "SELECT SUM(valor) FROM despesas";

        try (var conexao = DB.connect();
            var pstmt = conexao.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double despesaPorMes(int mes) {
        var sql = "SELECT SUM(valor) FROM despesas "
                + "WHERE EXTRACT(MONTH FROM data) = ?";

        try (var conexao = DB.connect();
            var pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, mes);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
