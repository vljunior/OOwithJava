package org.example;

import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;


public class ProdutoService {


    public void salvar(Produto produto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(produto);

        tx.commit();
        session.close();
    }

    public List<Produto> listarTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Produto> produtos = session.createQuery("FROM Produto", Produto.class).list();

        session.close();
        return produtos;
    }


    public Produto buscarPorId(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Produto produto = session.get(Produto.class, id);

        session.close();
        return produto;
    }


    public void atualizar(Produto produto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.merge(produto);

        tx.commit();
        session.close();
    }


    public void remover(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Produto produto = session.get(Produto.class, id);
        if (produto != null) {
            session.remove(produto);
        }

        tx.commit();
        session.close();
    }
}