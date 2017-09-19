package model;

public class Pessoa {
    private int idPessoa;
    private String nome;
    private String contato;
    
    public Pessoa(){}
    
    public Pessoa(String nome, String contato){
        this.nome = nome;
        this.contato = contato;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
    
    @Override
    public String toString(){
        return "Nome: "+this.nome+"\nContato: "+this.contato;
    }
}
