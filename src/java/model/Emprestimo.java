package model;

import java.sql.Date;

public class Emprestimo {
    private int idEmprestimo;
    private int idPessoa;
    private int idObjeto;
    private String data;
    
    public Emprestimo(){}
    
    public Emprestimo(int idPessoa, int idObjeto, String data){
        this.idPessoa = idPessoa;
        this.idObjeto = idObjeto;
        this.data = data;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
}
