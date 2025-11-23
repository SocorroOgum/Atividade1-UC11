/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        //conn = new conectaDAO().connectDB();
        
        
    }
    
 public ArrayList<ProdutosDTO> listarProdutos(){
    String sql = "SELECT * FROM produtos";
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    ArrayList<ProdutosDTO> lista = new ArrayList<>();

    try {
        conectaDAO dao = new conectaDAO();
        conn = dao.connectDB();

        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));

            lista.add(produto);
        }
    } catch (Exception e) {
        System.out.println("Erro ao listar: " + e.getMessage());
    }

    return lista;
    }
 
public void venderProduto(int id) {
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

    try {
        conectaDAO dao = new conectaDAO();
        Connection conn = dao.connectDB();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        stmt.executeUpdate();
        stmt.close();
        conn.close();

        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");

    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + erro.getMessage());
        }
    }   

public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
    ArrayList<ProdutosDTO> lista = new ArrayList<>();

    try {
        conectaDAO dao = new conectaDAO();
        Connection conn = dao.connectDB();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            ProdutosDTO p = new ProdutosDTO();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setValor(rs.getInt("valor"));
            p.setStatus(rs.getString("status"));

            lista.add(p);
        }

        rs.close();
        stmt.close();
        conn.close();

    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro ao listar vendidos: " + erro.getMessage());
    }

    return lista;
    }
}

