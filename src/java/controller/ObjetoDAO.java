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
import model.Objeto;

public class ObjetoDAO {
    Connection con;
    
    public void insereObjeto(String titulo, String tipo){
        
        con =  new ConectionFactory().getConnection();
        String sql = "INSERT INTO objeto (titulo, tipo, estado) VALUES (?, ?, ?)";
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setString(1, titulo);
           ps.setString(2, tipo);
           ps.setInt(3, 0);
           ps.execute();
           ps.close();
        }
        catch(SQLException ex){
            System.out.println("Erro:"+ex.getMessage());
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmprestimosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void alteraObjeto(Objeto objeto, String titulo, String tipo){
        con =  new ConectionFactory().getConnection();
        String sql = "UPDATE objeto set titulo = ?, tipo = ? where titulo = ?";
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setString(1, titulo);
           ps.setString(2, tipo);
           ps.setString(3, objeto.getTitulo());
           ps.execute();
           ps.close();
           JOptionPane.showMessageDialog(null, "Alterado com sucesso");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro:"+ex.getMessage()+"\n");
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmprestimosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void excluiObjeto(String titulo){
         con =  new ConectionFactory().getConnection();
        String sql = "DELETE from objeto where titulo = ?";
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setString(1, titulo);
           ps.execute();
           ps.close();
           System.out.println("Removido com sucesso!");
        }
        catch(SQLException ex){
            System.out.println("Esta objeto está emprestado.\nPor faver, Realizar a devolução antes de remove-lo");
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmprestimosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void excluiObjetoPorID(int id){
         con =  new ConectionFactory().getConnection();
        String sql = "DELETE from objeto where idObjeto = ?";
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setInt(1, id);
           ps.execute();
           ps.close();
           System.out.println("Removido com sucesso!");
        }
        catch(SQLException ex){
            System.out.println("Esta objeto está emprestado.\nPor faver, Realizar a devolução antes de remove-lo");
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmprestimosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ArrayList<Objeto> getObjetos(){
        
        con =  new ConectionFactory().getConnection();
        ArrayList<Objeto> objetos = new ArrayList();
        String sql = "SELECT * from objeto where estado = false";
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               Objeto objeto = new Objeto();
               objeto.setIdObjeto(Integer.parseInt(rs.getString("idObjeto")));
               objeto.setTitulo(rs.getString("titulo"));
               objeto.setTipo(rs.getString("tipo"));
               objeto.setEstado(rs.getBoolean("estado"));
               
               objetos.add(objeto);
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
        return objetos;
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    public ArrayList<Objeto> getALLObjetos(){
        
        con =  new ConectionFactory().getConnection();
        ArrayList<Objeto> objetos = new ArrayList();
        String sql = "SELECT * from objeto";
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               Objeto objeto = new Objeto();
               objeto.setIdObjeto(Integer.parseInt(rs.getString("idObjeto")));
               objeto.setTitulo(rs.getString("titulo"));
               objeto.setTipo(rs.getString("tipo"));
               objeto.setEstado(rs.getBoolean("estado"));
               
               objetos.add(objeto);
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
        return objetos;
    }
    
    public Objeto buscaObjetoPorID(int id){
        con =  new ConectionFactory().getConnection();
        Objeto objeto = new Objeto();
        String sql = "SELECT * from objeto where idObjeto = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            objeto.setIdObjeto(Integer.parseInt(rs.getString("idObjeto")));
            objeto.setTitulo(rs.getString("titulo"));
            objeto.setTipo(rs.getString("tipo"));
            objeto.setEstado(rs.getBoolean("estado"));
            rs.close();
            ps.close();
        }
        catch(SQLException ex){
            System.out.println("Erro:"+ex.getMessage());
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmprestimosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return objeto;
    }
    
    
    
    public Objeto buscaObjeto(String titulo){
        con =  new ConectionFactory().getConnection();
        Objeto objeto = new Objeto();
        String sql = "SELECT * from objeto where titulo = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, titulo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            objeto.setIdObjeto(Integer.parseInt(rs.getString("idObjeto")));
            objeto.setTitulo(rs.getString("titulo"));
            objeto.setTipo(rs.getString("tipo"));
            objeto.setEstado(rs.getBoolean("estado"));
            rs.close();
            ps.close();
        }
        catch(SQLException ex){
            System.out.println("Erro:"+ex.getMessage());
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmprestimosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return objeto;
    }
    
    public void atualizaEstadoEmprestimo(Objeto objeto){
        con =  new ConectionFactory().getConnection();
        String sql = "UPDATE objeto set estado = true WHERE titulo = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, objeto.getTitulo());
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
    
    public void atualizaEstadoDevolucao(Objeto objeto){
        con =  new ConectionFactory().getConnection();
        String sql = "UPDATE objeto set estado = false WHERE titulo = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, objeto.getTitulo());
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
    
    public boolean verificaObjeto(String titulo){
        con =  new ConectionFactory().getConnection();
        Objeto objeto = new Objeto();
        boolean estado = false;
        String sql = "SELECT * from objeto";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                objeto.setIdObjeto(Integer.parseInt(rs.getString("idObjeto")));
                objeto.setTitulo(rs.getString("titulo"));
                objeto.setTipo(rs.getString("tipo"));
                objeto.setEstado(rs.getBoolean("estado"));
                if(objeto.getTitulo().equalsIgnoreCase(titulo)){
                    estado = true;
                }
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
        return estado;
    }
    
    public int countObjetos(){
        con =  new ConectionFactory().getConnection();
        int cont = 0;
        String sql = "select count(*) from objeto";
        
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
    public boolean ValidaObejetoPorId(int id){
        
        con =  new ConectionFactory().getConnection();
        String sql = "SELECT * from objeto where idObjeto = ?";
        
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
    
    
}
