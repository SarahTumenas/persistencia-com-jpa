package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Categoria;


import javax.persistence.EntityManager;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria) {
        em.persist(categoria);
    }

    public void atualizar(Categoria categoria) {
        em.merge(categoria);
    }

    public void remover(Categoria categoria) {
        em.remove(categoria);
    }


}
