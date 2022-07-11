package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TesteCriteria {

    public static void main(String[] args) {

        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        //produtoDao.buscarPorParametroComCriteria("PS5", null, null);

        produtoDao.buscarPorParametroComCriteria(null, new BigDecimal(4500.00), LocalDate.now());

    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");


        Produto celular = new Produto("Iphone 13","Iphone 13 Pro Max",
                new BigDecimal("8000.00"), celulares);
        Produto videogame = new Produto("PS5","Playstation 5",
                new BigDecimal("4500.00"), videogames);
        Produto macbook = new Produto("Mackbook", "MacBook Pro de 13 pol.",
                new BigDecimal("14499.00"), informatica);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);

        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macbook);

        em.getTransaction().commit();
        em.close();
    }
}
