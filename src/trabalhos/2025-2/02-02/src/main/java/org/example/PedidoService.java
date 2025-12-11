package org.example;

import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PedidoService {

    public void salvar(Pedido pedido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(pedido);

        tx.commit();
        session.close();
    }

    public List<Pedido> listarTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        //fizemos com pq o codigo quebrou e nao sabiamos resolver
        List<Pedido> pedidos = session.createQuery(
                "SELECT DISTINCT p FROM Pedido p " +
                        "LEFT JOIN FETCH p.cliente " +
                        "LEFT JOIN FETCH p.itens", Pedido.class
        ).list();

        session.close();
        return pedidos;
    }

    public Pedido buscarPorId(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Pedido pedido = session.get(Pedido.class, id);

        session.close();
        return pedido;
    }

    public void atualizar(Pedido pedido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.merge(pedido);

        tx.commit();
        session.close();
    }

    public void remover(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Pedido pedido = session.get(Pedido.class, id);
        if (pedido != null) {
            session.remove(pedido);
        }

        tx.commit();
        session.close();
    }
}