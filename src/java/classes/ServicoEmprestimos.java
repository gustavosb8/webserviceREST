/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import controller.EmprestimosDAO;
import controller.ObjetoDAO;
import controller.PessoaDAO;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Emprestimo;
import model.Emprestimos;

import model.Objeto;
import model.Pessoa;

/**
 * @author Gustavo Santana Bastos
 */


@Path("serviceemprestimo")
public class ServicoEmprestimos {
    
    //OBJETOS

    @GET
    @Path("objeto/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Objeto retornaObjeto(@PathParam("id") int id){

            @SuppressWarnings("UnusedAssignment")
            Objeto ob = null;

            ObjetoDAO daoOb = new ObjetoDAO();
            ob = daoOb.buscaObjetoPorID(id);

            return ob;	
    }

    @GET
    @Path("listarobjetos")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Objeto> listarObjetos(){
            @SuppressWarnings("UnusedAssignment")
            ArrayList<Objeto> lista = new ArrayList();
            ObjetoDAO daoOb = new ObjetoDAO();
            lista = daoOb.getALLObjetos();
            return lista;
    }

    @GET
    @Path("contarobjetos")
    @Produces(MediaType.APPLICATION_JSON)
    public int contarObjetos(){
            ObjetoDAO daoOb = new ObjetoDAO();
            int cont = daoOb.countObjetos();
            return cont;
    }
    
    @GET
    @Path("validaobjeto/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean ValidaPessoa(@PathParam("id") int id){
            ObjetoDAO daoOb = new ObjetoDAO();
            return daoOb.ValidaObejetoPorId(id);

    }
    
    
    @GET
    @Path("cadastraobjeto/{titulo}/{tipo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Objeto cadastraObjeto(@PathParam("titulo") String titulo, @PathParam("tipo") String tipo) {

        Objeto objeto = new Objeto(titulo, tipo);

        try {
            ObjetoDAO daoOb = new ObjetoDAO();
            daoOb.insereObjeto(objeto.getTitulo(), objeto.getTipo());
            objeto = daoOb.buscaObjeto(titulo);
            return objeto;

        } catch (Exception err) {
            err.printStackTrace();
        }

        return objeto;

    }
    
    @POST
    @Path("/removerobjeto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String excluirObjeto(Objeto objeto) {

        try {
            
            ObjetoDAO daoOb = new ObjetoDAO();
            
            Objeto novo = daoOb.buscaObjetoPorID(objeto.getIdObjeto());
            
            daoOb.excluiObjetoPorID(novo.getIdObjeto());
            
            return novo.getTipo()+": "+novo.getTitulo()+" removido com sucesso";

        } catch (Exception err) {
            System.out.println(err.getMessage());
            return "Exclusão não realizada.\nVerifique se objeto está emprestado ou contate o suporte";
        }
    }
    
    
    //Pessoas

    @GET
    @Path("pessoa/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pessoa retornaPessoa(@PathParam("id") int id){

            @SuppressWarnings("UnusedAssignment")
            Pessoa pessoa = null;

            PessoaDAO daoOb = new PessoaDAO();
            pessoa = daoOb.buscaPessoaPorID(id);

            return pessoa;	
    }

    @GET
    @Path("listarpessoas")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Pessoa> listarPessoa(){
            @SuppressWarnings("UnusedAssignment")
            ArrayList<Pessoa> lista =  new ArrayList();
            PessoaDAO daopessoa = new PessoaDAO();
            lista = daopessoa.getALLPessoas();
            return lista;
    }

    @GET
    @Path("contarpessoas")
    @Produces(MediaType.APPLICATION_JSON)
    public int contarPessoa(){
            PessoaDAO daoOb = new PessoaDAO();
            int cont = daoOb.countPessoa();
            return cont;
    }

    @GET
    @Path("validapessoa/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean ValidaPessoaPorId(@PathParam("id") int id){
            PessoaDAO daoOb = new PessoaDAO();
            return daoOb.ValidaPessoaPorId(id);
    }
    
    @GET
    @Path("cadastrapessoa/{nome}/{contato}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Pessoa cadastraPessoa(@PathParam("nome") String nome, @PathParam("contato") String contato) {

        Pessoa pessoa = new Pessoa(nome, contato);

        try {
            PessoaDAO daoPe = new PessoaDAO();
            daoPe.inserePessoa(pessoa.getNome(), pessoa.getContato());
            pessoa = daoPe.buscaPessoa(nome);
            return pessoa;

        } catch (Exception err) {
            err.printStackTrace();
        }

        return pessoa;

    }
    
    @POST
    @Path("/removerpessoa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String excluirPessoa(Pessoa pessoa) {

        try {
            
            PessoaDAO daoP = new PessoaDAO();
            
            Pessoa novo = daoP.buscaPessoaPorID(pessoa.getIdPessoa());
            
            daoP.excluiPessoaPorID(novo.getIdPessoa());
            
            return novo.getNome()+": "+novo.getContato()+" removido(a) com sucesso";

        } catch (Exception err) {
            System.out.println(err.getMessage());
            return "Exclusão não realizada.\nVerifique se objeto está emprestado ou contate o suporte";
        }
    }
    
    //Emprestimo
    
    @POST
    @Path("/emprestar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String Emprestar(Emprestimo emprestimo) {

        try {
            EmprestimosDAO daoEm = new EmprestimosDAO();
            
            Pessoa pessoa = new PessoaDAO().buscaPessoaPorID(emprestimo.getIdPessoa());
            
            Objeto objeto = new ObjetoDAO().buscaObjetoPorID(emprestimo.getIdObjeto());
            
            daoEm.emprestar(objeto, pessoa); 
            
            new ObjetoDAO().atualizaEstadoEmprestimo(objeto);
            
            return "Emprestimo de "+objeto.getTipo()+": "+objeto.getTitulo()+" para ["+pessoa.getNome()+"] realizado com sucesso";

        } catch (Exception err) {
            System.out.println(emprestimo.toString());
            return "Emprestimo não realizado";
        }
    }
    
    @POST
    @Path("/devolver")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String devolver(Emprestimos emprestimo) {

        try {
            
            EmprestimosDAO daoEm = new EmprestimosDAO();
            
            Pessoa pessoa = new PessoaDAO().buscaPessoa(emprestimo.getNome());
            
            Objeto objeto = new ObjetoDAO().buscaObjeto(emprestimo.getTitulo());
            
            daoEm.devolver(objeto, pessoa);
            
            new ObjetoDAO().atualizaEstadoDevolucao(objeto);
            
            return "Devolução realizada com sucesso";

        } catch (Exception err) {
            System.out.println(err.getMessage());
            return "Devolução não realizada, contate o suporte";
        }
    }
    
    
    @GET
    @Path("listarobjetosdisponiveis")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Objeto> listarObjetosDisponiveis(){
            @SuppressWarnings("UnusedAssignment")
            ArrayList<Objeto> lista = new ArrayList();
            ObjetoDAO daoOb = new ObjetoDAO();
            
            try{
                lista = daoOb.getObjetos();
                return lista;
            }catch(Exception err){
                System.out.println(err);
            }
            
            return lista;
    }

    @GET
    @Path("listaremprestimos")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Emprestimos> listarEmprestimos(){
            @SuppressWarnings("UnusedAssignment")
            ArrayList<Emprestimos> lista =  new ArrayList();
            EmprestimosDAO dao = new EmprestimosDAO();
            lista = dao.getEmprestimos();
            return lista;
    }
    
}
