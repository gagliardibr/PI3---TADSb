/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3b.agenda1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author bruna.gagliardi
 */
public class Agenda {

    private Connection obterConexão() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/agendadb",
                "root",
                "");
        return conn;
    }

    public List<Pessoa> listar() throws ClassNotFoundException, SQLException {

        List<Pessoa> lista = new ArrayList<Pessoa>();   
         
        try (Connection conn = obterConexão();
                PreparedStatement stmt = conn.prepareStatement("SELECT id, nome, dtnascimento FROM PESSOA");
                ResultSet resultados = stmt.executeQuery();) {
            
            while (resultados.next()) {
                long id = resultados.getLong("id");
                String nome = resultados.getString("nome");
                Date dtnascimento = resultados.getDate("dtnascimento");
                Pessoa p = new Pessoa();
                p.setId(id);
                p.setNome(nome);
                p.setDtNascimento(dtnascimento);
                lista.add(p);
               // System.out.println(id + ", " + nome + ", " + dtnascimento);
            }

        }
        return lista;
    }

    public void incluir() throws ClassNotFoundException, SQLException {

        try (Connection conn = obterConexão();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO PESSOA (nome, dtnascimento) VALUES (?,?)")) {
            stmt.setString(1, "MARIA DE SOUZA");
            GregorianCalendar cal = new GregorianCalendar(1992, 10, 5); //5 de novembro de 1992
            stmt.setDate(2, new java.sql.Date(cal.getTimeInMillis()));

            int status = stmt.executeUpdate();
            System.out.println("Status: " + status);
        }
    }

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        try {
            agenda.incluir();
            List<Pessoa> lista = agenda.listar();
            for (Pessoa p : lista) {
                System.out.println(p.getId() + ", " + p.getNome() + "," + p.getDtNascimento());
            }
        } catch (ClassNotFoundException ex) {
            System.err.print(ex.getMessage());
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
        
        
    }
}
