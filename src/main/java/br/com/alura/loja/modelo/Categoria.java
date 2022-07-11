package br.com.alura.loja.modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {


    @EmbeddedId
    private CategoriaId id;



    public Categoria() {
    }

    public Categoria(String nome) {
        this.id= new CategoriaId(nome, "LIVRO");
    }

    public String getNome() {
        return this.id.getNome();
    }

    public void setNome(String nome) {
        this.id.setNome(nome);
    }
}
