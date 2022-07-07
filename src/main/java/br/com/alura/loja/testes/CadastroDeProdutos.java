package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProdutos {

    public static void main(String[] args) {


        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto prod = produtoDao.buscaPorId(1L);
        System.out.println(prod.getPreco());

        List<Produto> todos = produtoDao.buscarTodos();
        todos.forEach(p-> System.out.println(p.getNome()));

        List<Produto> produtosPorNome = produtoDao.buscarPorNome("Iphone13");
        produtosPorNome.forEach(p1-> System.out.println(p1.getNome()));

        List<Produto> produtosPorNomeDaCategoria = produtoDao.buscarPorNomeDaCategoria("CELULARES");
        produtosPorNomeDaCategoria.forEach(p2-> System.out.println(p2.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscaPrecoDoProdutoComNome("Iphone13");
        System.out.println( "Preço do produto: " + precoDoProduto);


    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");

        Produto celular = new Produto("Iphone 13","Iphone 13 Pro Max",
                new BigDecimal("8000.00"), celulares);


        EntityManager em = JPAUtil.getEntityManager();

        CategoriaDao categoriaDao = new CategoriaDao(em);

        ProdutoDao produtoDao = new ProdutoDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }
}
