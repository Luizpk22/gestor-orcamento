package com.luizhendasilva.gestaoorcamento.repository;

import com.luizhendasilva.gestaoorcamento.model.Rendas;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RendasDB {
    public boolean existeRenda(String nome, String tipo, Date data) {
        var sql = "SELECT nome, data FROM rendas WHERE nome = ? AND tipo = ? AND data = ?";

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

    public void salvarRenda(Rendas renda) {
        var sql = "INSERT INTO rendas(nome, valor, tipo, data)"
                + "VALUES(?,?,?,?)";

        try (var conexao = DB.connect();
             var pstmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Colocando os valores na query
            pstmt.setString(1, renda.getNome());
            pstmt.setDouble(2, renda.getValor());
            pstmt.setString(3, renda.getTipo());
            pstmt.setDate(4, renda.getData());

            // Executando o INSERT e recebendo o id da execução
            int insertedRow = pstmt.executeUpdate();
            if (insertedRow > 0) {
                var rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Renda salva no banco com sucesso.");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void salvarRendas(List<Rendas> rendas) {
        var sql = "INSERT INTO rendas(nome, valor, tipo, data)"
                + "VALUES(?,?,?,?)";

        try (var conexao = DB.connect();
             var pstmt = conexao.prepareStatement(sql)) {
            for (var renda : rendas) {
                pstmt.setString(1, renda.getNome());
                pstmt.setDouble(2, renda.getValor());
                pstmt.setString(3, renda.getTipo());
                pstmt.setDate(4, renda.getData());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Rendas> buscarTodos() {
        var rendas = new ArrayList<Rendas>();
        var sql = "SELECT id, nome, valor, tipo, data FROM rendas ORDER BY nome";

        try (var conexao = DB.connect();
             var stmt = conexao.createStatement()) {

            var rs = stmt.executeQuery(sql);

            while (rs.next()) {
                var renda = new Rendas(rs.getString("nome"),
                        rs.getDouble("valor"),
                        rs.getString("tipo"),
                        rs.getDate("data"));
                renda.setIdBanco(rs.getInt("id"));
                rendas.add(renda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rendas;
    }

    public Rendas buscarPorId(int id) {
        var sql = "SELECT id, nome, valor, tipo, data FROM rendas WHERE id=?";

        Rendas renda = new Rendas();
        try (var conexao = DB.connect();
             var pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            var rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Rendas(rs.getString("nome"),
                        rs.getDouble("valor"),
                        rs.getString("tipo"),
                        rs.getDate("data"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return renda;
    }

    public List<Rendas> buscarPorData(Date data) {
        var sql = "SELECT id, nome, valor, tipo, data FROM rendas WHERE data=?";
        var rendas = new ArrayList<Rendas>();

        try (var conexao = DB.connect();
             var pstmt = conexao.prepareStatement(sql)) {

            pstmt.setDate(1, data);
            var rs = pstmt.executeQuery();

            while (rs.next()) {
                var renda = new Rendas(rs.getString("nome"),
                        rs.getDouble("valor"),
                        rs.getString("tipo"),
                        rs.getDate("data"));
                rendas.add(renda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rendas;
    }

    public List<Rendas> buscarPorMes(int mes) {
        var sql = "SELECT nome, valor, tipo, data FROM rendas WHERE EXTRACT(MONTH FROM data) = ?";
        var rendas = new ArrayList<Rendas>();

        try (var conexao = DB.connect();
             var pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, mes);
            var rs = pstmt.executeQuery();

            while (rs.next()) {
                var renda = new Rendas(rs.getString("nome"),
                        rs.getDouble("valor"),
                        rs.getString("tipo"),
                        rs.getDate("data"));
                rendas.add(renda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rendas;
    }

    public void atualizarValorRenda(int id, double valor) {
        var sql = "UPDATE rendas "
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

    public void atualizarNomeRenda(int id, String nome) {
        var sql = "UPDATE rendas "
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

    public void atualizarTipoRenda(int id, String tipo) {
        var sql = "UPDATE rendas "
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

    public void atualizarDataRenda(int id, Date data) {
        var sql = "UPDATE rendas "
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

    public void excluirRenda(int id) {
        var sql = "DELETE FROM rendas WHERE id = ?";

        try (var conexao = DB.connect();
             var pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirListaRendas(List<Integer> ids) {
        var sql = "DELETE FROM rendas WHERE id = ?";

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

    public double somarRendas() {
        var sql = "SELECT SUM(valor) FROM rendas";

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

    public double rendaPorMes(int mes) {
        var sql = "SELECT SUM(valor) FROM rendas "
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
