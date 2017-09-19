package controller;

import Conexão.ConectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Emprestimos;
import model.Objeto;
import model.Pessoa;

public class EmprestimosDAO {
    
    Connection con;
    
    public ArrayList<Emprestimos> getEmprestimos(){
        
        con =  new ConectionFactory().getConnection();
        ArrayList<Emprestimos> listaEmprestimo = new ArrayList();
        String sql = "select nome, titulo, tipo, Data\n" +
                     "from pessoa\n" +
                     "join (objeto, emprestimo)\n" +
                     "on (pessoa.idpessoa = emprestimo.idpessoa and objeto.idObjeto = emprestimo.idObjeto);";
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               Emprestimos emprestimos = new Emprestimos();
               emprestimos.setNome(rs.getString("nome"));
               emprestimos.setTitulo(rs.getString("titulo"));
               emprestimos.setTipo(rs.getString("tipo"));
               emprestimos.setData(rs.getDate("Data"));
               listaEmprestimo.add(emprestimos);
           }
           rs.close();
           ps.close();
        }
        catch(SQLException ex){
            System.out.println( "Erro:"+ex.getMessage());
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmprestimosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaEmprestimo;
    }
    
    public void emprestar(Objeto objeto, Pessoa pessoa){
        con =  new ConectionFactory().getConnection();
        
        String sql = "INSERT INTO emprestimo (Data, idpessoa, idObjeto) VALUES (?, ?, ?)";
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
           ps.setInt(2, pessoa.getIdPessoa());
           ps.setInt(3, objeto.getIdObjeto());
           ps.execute();
           ps.close();
        }
        catch(SQLException ex){
            System.out.println( "Erro AQUI:"+ex.getMessage());
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmprestimosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void devolver(Objeto objeto, Pessoa pessoa){
        con =  new ConectionFactory().getConnection();
        
        String sql = "DELETE from emprestimo WHERE idObjeto = ?";
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setInt(1, objeto.getIdObjeto());
           ps.execute();
           ps.close();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro:"+ex.getMessage());
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmprestimosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
