package controller;

import Conexão.ConectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Pessoa;

public class PessoaDAO {
    
    Connection con;
    
    public void inserePessoa(String nome, String contato){
        con =  new ConectionFactory().getConnection();
        String sql = "INSERT INTO pessoa (Nome, Contato) VALUES (?, ?)";
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setString(1, nome);
           ps.setString(2, contato);
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
    
    public void alteraPessoa(Pessoa pessoa, String nome, String contato){
        con =  new ConectionFactory().getConnection();
        String sql = "UPDATE pessoa set nome = ?, contato = ? where nome = ?";
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setString(1, nome);
           ps.setString(2, contato);
           ps.setString(3, pessoa.getNome());
           ps.execute();
           ps.close();
           JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
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
    
    public void excluiPessoa(String nome){
        
        con =  new ConectionFactory().getConnection();
        String sql = "DELETE from pessoa where nome = ?";
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setString(1, nome);
           ps.execute();
           ps.close();
           JOptionPane.showMessageDialog(null, "Removido com sucesso!");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Esta pessoa possui um empréstimo pendente.\nPor faver, Realizar a devolução antes de remove-la");
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmprestimosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void excluiPessoaPorID(int idPessoa) {
        con =  new ConectionFactory().getConnection();
        String sql = "DELETE from pessoa where idpessoa = ?";
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setInt(1, idPessoa);
           ps.execute();
           ps.close();
           System.out.println("Removido com sucesso!");
        }
        catch(SQLException ex){
            System.out.println( "Esta pessoa possui um empréstimo pendente.\nPor faver, Realizar a devolução antes de remove-la");
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmprestimosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ArrayList<Pessoa> getPessoas(){
        
        con =  new ConectionFactory().getConnection();
        ArrayList<Pessoa> pessoas = new ArrayList();
        String sql = "SELECT * from pessoa";
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               Pessoa pessoa = new Pessoa();
               pessoa.setIdPessoa(Integer.parseInt(rs.getString("idpessoa")));
               pessoa.setNome(rs.getString("nome"));
               pessoa.setContato(rs.getString("contato"));
               
               pessoas.add(pessoa);
           }
           rs.close();
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
        return pessoas;
    }
    
    public Pessoa buscaPessoa(String nome){
        con =  new ConectionFactory().getConnection();
        Pessoa pessoa = new Pessoa();
        String sql = "SELECT * from pessoa where nome = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            rs.next();
            pessoa.setIdPessoa(Integer.parseInt(rs.getString("idpessoa")));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setContato(rs.getString("contato"));
            rs.close();
            ps.close();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro AQUI:"+ex.getMessage());
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmprestimosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pessoa;
    }
    
    public Pessoa buscaPessoaPorID(int id){
        con =  new ConectionFactory().getConnection();
        Pessoa pessoa = new Pessoa();
        String sql = "SELECT * from pessoa where idpessoa = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            pessoa.setIdPessoa(Integer.parseInt(rs.getString("idpessoa")));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setContato(rs.getString("contato"));
            rs.close();
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
        return pessoa;
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    public boolean ValidaPessoaPorId(int id){
        
        con =  new ConectionFactory().getConnection();
        String sql = "SELECT * from pessoa where idpessoa = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return true;
            }else{
                return false;
            }
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
        return false;
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    public int countPessoa(){
        con =  new ConectionFactory().getConnection();
        int cont = 0;
        String sql = "select count(*) from pessoa";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            
            rs.next();
            
            cont = rs.getInt(1);
        
            rs.close();
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
        return cont;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public ArrayList<Pessoa> getALLPessoas() {
        con =  new ConectionFactory().getConnection();
        ArrayList<Pessoa> pessoas = new ArrayList();
        String sql = "SELECT * from pessoa";
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               Pessoa pessoa = new Pessoa();
               pessoa.setIdPessoa(Integer.parseInt(rs.getString("idpessoa")));
               pessoa.setNome(rs.getString("nome"));
               pessoa.setContato(rs.getString("contato"));
               
               
               pessoas.add(pessoa);
           }
           rs.close();
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
        return pessoas;
    }


}
