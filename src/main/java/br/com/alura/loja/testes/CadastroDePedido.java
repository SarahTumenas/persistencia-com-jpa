package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {
    public static void main(String[] args) {
        popularBancoDeDados();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto produto = produtoDao.buscaPorId(1L);
        Produto produto2 = produtoDao.buscaPorId(2L);
        Produto produto3 = produtoDao.buscaPorId(3L);

        ClienteDao clienteDao = new ClienteDao(em);
        Cliente cliente = clienteDao.buscaPorId(1L);

        em.getTransaction().begin();


        Pedido pedido = new Pedido(cliente);
        pedido.adicionaItem(new ItemPedido(10,pedido,produto));
        pedido.adicionaItem(new ItemPedido(15,pedido,produto2));

        Pedido pedido2 = new Pedido(cliente);
        pedido2.adicionaItem(new ItemPedido(5,pedido,produto3));

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);


        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("Total vendido: " + totalVendido);

        List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
        relatorio.forEach(System.out::println);


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

        Cliente cliente = new Cliente ("João", "12345678");

        EntityManager em = JPAUtil.getEntityManager();

        CategoriaDao categoriaDao = new CategoriaDao(em);

        ProdutoDao produtoDao = new ProdutoDao(em);

        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macbook);
        clienteDao.cadastrar(cliente);
        em.getTransaction().commit();
        em.close();
    }
}
