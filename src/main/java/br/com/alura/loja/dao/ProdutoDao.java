package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;

public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        em.persist(produto);
    }

    public void atualizar(Produto produto) {
        em.merge(produto);
    }

    public void remover(Produto produto) {
        em.remove(produto);
    }


}
