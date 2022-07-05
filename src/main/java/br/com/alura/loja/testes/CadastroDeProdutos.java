package br.com.alura.loja.testes;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;

import java.math.BigDecimal;

public class CadastroDeProdutos {

    public static void main(String[] args) {
        Produto celular = new Produto("Iphone 13","Iphone 13 Pro Max",
                new BigDecimal("8000.00"), Categoria.CELULARES);


        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDao dao = new ProdutoDao(em);

        em.getTransaction().begin();
        dao.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }
}
