package model;

public class Objeto {
    private int idObjeto;
    private String titulo;
    private String tipo;
    private boolean estado;
    
    public Objeto (){}
    
    public Objeto(String titulo, String tipo){
        this.titulo = titulo;
        this.tipo = tipo;
    }
    
    public Objeto(int idObjeto, String titulo, String tipo, boolean estado){
        this.idObjeto = idObjeto;
        this.titulo = titulo;
        this.tipo = tipo;
        this.estado = estado;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        return "Titulo: "+this.titulo+"\nTipo: "+this.tipo;
    }
}
